package Models.Tariffs;

public class PremiumTariff extends Tariff {
    private int minutesOfTvSubscription;

    public PremiumTariff(String name, double subscriptionFee, int includedMinutes, int minutesOfTvSubscription) {
        super(name, subscriptionFee, includedMinutes);
        this.minutesOfTvSubscription = minutesOfTvSubscription;
    }
}
