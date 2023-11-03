package Models.Command;

import Models.Command.Interfaces.ICommand;
import Models.Company.Company;

public class SortTariffsCommand implements ICommand<Company> {
    @Override
    public void execute(Company company) {
        company.sortTariffsBySubscriptionFee();
        System.out.println("Tariffs sorted by subscription fee.");
    }
}
