package Models.Tariffs;

public abstract  class Tariff {
    private final String name;
    private final double subscriptionFee;
    private int countOfUsers;

    public Tariff(String name, double subscriptionFee, int countOfUsers) {
        this.name = name;
        this.subscriptionFee = subscriptionFee;
        this.countOfUsers = countOfUsers;
    }

    public String getName() {
        return name;
    }

    public double getSubscriptionFee() {
        return subscriptionFee;
    }

    public int getCountOfUsers() {
        return countOfUsers;
    }
}
