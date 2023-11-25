package Models.Tariffs;

public class PremiumTariff extends Tariff{
    private int minutesOfTvSubscription;

    public PremiumTariff(String name, double subscriptionFee, int countOfUsers, int minutesOfTvSubscription) {
        super(name, subscriptionFee, countOfUsers);
        this.minutesOfTvSubscription = minutesOfTvSubscription;
    }

    @Override
    public String toString(){
        return "Type of tariff: Premium\n" +
                "- name: " + getName() + "\n" +
                "- subscription fee" + getSubscriptionFee() + "\n" +
                "- count of users" + getCountOfUsers() + "\n" +
                "- minutes of TV" + minutesOfTvSubscription + "\n";
    }
}
