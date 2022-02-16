package in.demo.pojo;

public class Subscriptions {

	private String userName;
	private double amount;
	private boolean activeSubscription;
	public Subscriptions(String userName, double amount, boolean activeSubscription) {
		super();
		this.userName = userName;
		this.amount = amount;
		this.activeSubscription = activeSubscription;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isActiveSubscription() {
		return activeSubscription;
	}
	public void setActiveSubscription(boolean activeSubscription) {
		this.activeSubscription = activeSubscription;
	}

	@Override
	public String toString() {
		return "Subscriptions [userName=" + userName + ", amount=" + amount + ", activeSubscription="
				+ activeSubscription + "]";
	}
	
	
}
