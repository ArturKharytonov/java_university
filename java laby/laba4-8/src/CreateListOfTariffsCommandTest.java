import Models.Command.CreateListOfTariffsCommand;
import Models.Company.Company;
import Models.Tariffs.BasicTariff;
import Models.Tariffs.PremiumTariff;
import Models.Tariffs.Tariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateListOfTariffsCommandTest {
    private Company company;
    @BeforeEach
    public void setup() {
        company = new Company();
    }

    @Test
    public void testExecute() {
//        // Create a real Company object
//        Company company = new Company();
//
//        // Create some sample Tariffs for testing
//        List<Tariff> sampleTariffs = new ArrayList<>();
//        company.add(new BasicTariff("Tariff A", 10.0, 100));
//        sampleTariffs.add(new PremiumTariff("Tariff B", 20.0, 200, 120));
//
//        // Set the sample tariffs in the Company (for testing purposes)
//        company.setTariffs(sampleTariffs);
//
//        // Create the command and execute it
//        CreateListOfTariffsCommand command = new CreateListOfTariffsCommand();
//        command.execute(company);
//
//        // You can now assert or perform any necessary validations
//        // based on the expected behavior of createListOfTariffs().
    }
}