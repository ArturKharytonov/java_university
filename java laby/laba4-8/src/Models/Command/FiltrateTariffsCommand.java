package Models.Command;

import Models.Command.Interfaces.ICommand;
import Models.Company.Company;
import Models.Tariffs.Tariff;

import java.util.List;


public class FiltrateTariffsCommand implements ICommand<Company> {
    @Override
    public void execute(Company company) {
        List<Tariff> tariffsInRange = company.filterTariffs();
        System.out.println("Filtered Tariffs:");
        for (Tariff tariff : tariffsInRange) {
            System.out.println(tariff.toString());
        }

    }
}
