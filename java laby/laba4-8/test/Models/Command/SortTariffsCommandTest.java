package Models.Command;

import Models.Command.Interfaces.ICommand;
import Models.Company.Company;
import Models.Tariffs.BasicTariff;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTariffsCommandTest {
    @Test
    public void testSortTariffsBySubscriptionFee() {
        // Create a company
        Company company = new Company();

        company.addTariff(new BasicTariff("Tariff A", 20.0, 100));
        company.addTariff(new BasicTariff("Tariff B", 10.0, 200));
        company.addTariff(new BasicTariff("Tariff C", 15.0, 200));

        // Create the command and execute it
        ICommand<Company> command = new SortTariffsCommand();
        command.execute(company);

        // Check if the tariffs are sorted correctly
        assertEquals("Tariff B", company.getTariffs().get(0).getName());
        assertEquals("Tariff C", company.getTariffs().get(1).getName());
        assertEquals("Tariff A", company.getTariffs().get(2).getName());
    }
}