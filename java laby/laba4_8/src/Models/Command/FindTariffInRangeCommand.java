package Models.Command;

import Models.Command.Interfaces.ICommand;
import Models.Company.Company;
import Models.Tariffs.Tariff;

import java.util.List;


public class FindTariffInRangeCommand implements ICommand<Company> {
    @Override
    public void execute(Company company) {
        List<Tariff> tariffsInRange = company.findTariffsInPriceRange(/* min, max */);
        System.out.println("Tariffs in specified price range: " + tariffsInRange);
    }
}
