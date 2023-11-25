package Models.Tariffs;

public class BasicTariff extends Tariff {
    public BasicTariff(String name, double subscriptionFee, int countOfUsers) {
        super(name, subscriptionFee, countOfUsers);
    }

    @Override
    public String toString(){
        return "Type of tariff: Basic\n" +
                "- name: " + getName() + "\n" +
                "- subscription fee" + getSubscriptionFee() + "\n" +
                "- count of users" + getCountOfUsers() + "\n";
    }
}