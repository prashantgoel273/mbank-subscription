package in.demo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import in.demo.datastore.SubscriptionDataStrore;
import in.demo.pojo.Subscriptions;

@Service
public class TransactionService {

	Logger logger=LoggerFactory.getLogger(TransactionService.class);
	
	void deductBalance(String userId, double amt){

		double balance=SubscriptionDataStrore.getUserBalances().get(userId);

			if(balance>=amt) {
				SubscriptionDataStrore.getUserBalances().put(userId,balance-amt);
				
				logger.info("Subscription started for "+userId+" of amount"+amt);
				
			}
			else {
				System.out.println();
				logger.info("insuffficient blance for "+userId);
				inactiveSubscription(userId);
			}

	}

	void inactiveSubscription(String userId) {

	Optional<Subscriptions>	optSubscription=Optional.ofNullable(SubscriptionDataStrore.getSubscribers().get(userId));

		if(optSubscription.isPresent()) {
			Subscriptions subscription=optSubscription.get();
			subscription.setActiveSubscription(false);
			SubscriptionDataStrore.getSubscribers().put(userId, subscription);
		}
	}
}

