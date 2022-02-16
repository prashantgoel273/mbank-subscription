package in.demo.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import in.demo.datastore.SubscriptionDataStrore;
import in.demo.pojo.Subscriptions;



@Service
public class SubscriptionService {

	@Autowired
	TransactionService transServ;
	
    Logger logger=LoggerFactory.getLogger(SubscriptionService.class);
    
	public String checkandActivateSubscription(String user, double amt)
	{
		Optional<Subscriptions> optSubsciption= Optional.ofNullable(getSubscriptionsByUserId(user));
		if(optSubsciption.isPresent())
			return "There is already active Subcription for this user";
		double balance=getUserBalance(user);
		if(balance>amt) {

			Subscriptions objSub= new Subscriptions(user, amt, true);			
			SubscriptionDataStrore.getSubscribers().put(user,objSub);
			return "subscription started for "+user+" with amount "+ objSub.getAmount();
		}else
			return "Insuffcient balance";
	}

	@Scheduled(cron="00 07 1 */1 * *")
	public void startSubscription(){
		
		List<Subscriptions> activeSubscriptionsList=SubscriptionDataStrore.getSubscribers().values().stream()
				.filter(s->s.isActiveSubscription()) .collect(Collectors.toList());

		for(Subscriptions sub:activeSubscriptionsList) {
			logger.info("Starting balance  deducting service for "+sub.getUserName()+" of amount"+sub.getAmount());
			transServ.deductBalance(sub.getUserName(), sub.getAmount());
		}

	}


	Subscriptions getSubscriptionsByUserId(String user){
		Map<String, Subscriptions> subcription=SubscriptionDataStrore.getSubscribers();
		return subcription.get(user);
	}

	double getUserBalance(String userId){

		Map<String, Double> userBalances=SubscriptionDataStrore.getUserBalances();
		Double balance=Optional.ofNullable(userBalances.get(userId)).orElse(0.0);
		return balance;
	}

}
