package Models.Command;

import Models.Command.Interfaces.ICommand;
import Models.Company.Company;
import Models.Tariffs.Tariff;

import java.util.List;
import java.util.function.Predicate;


public class FiltrateTariffsCommand implements ICommand<Company> {
    @Override
    public void execute(Company company) {
        Predicate<Tariff> predicate = company.getFiltrationPredicate();

        List<Tariff> tariffsInRange = company.filterTariffs(predicate);
        System.out.println("Filtered Tariffs:");
        for (Tariff tariff : tariffsInRange) {
            System.out.println(tariff.toString());
        }
    }
}
