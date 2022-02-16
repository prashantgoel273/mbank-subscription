package in.demo.datastore;

import java.util.HashMap;
import java.util.Map;

import in.demo.pojo.Subscriptions;

public class SubscriptionDataStrore {
	static Map<String,Subscriptions> subscribers;
	static Map<String, Double> userBalances;

	static {
		subscribers=new HashMap<>();
		subscribers.put("John Doe", new Subscriptions("John Doe", 100, true));
		subscribers.put("Alice", new Subscriptions("Alice", 200, true));
		subscribers.put("James",new Subscriptions("James", 150, false) );
       
		userBalances=new HashMap<>();
		userBalances.put("John Doe",1000.0);
		userBalances.put("Alice", 2000.0);
		userBalances.put("James", 1500.0);
	}

	public static Map<String, Subscriptions> getSubscribers() {
		return subscribers;
	}

	public static Map<String, Double> getUserBalances() {
		return userBalances;
	}

}
