package Models.Command;

import Models.Company.Company;
import Models.Tariffs.BasicTariff;
import Models.Tariffs.Tariff;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class FiltrateTariffsCommandTest {
    @Test
    public void testExecute() {
        // Set up a Company with some sample Tariffs
        Company company = new Company();

        company.addTariff(new BasicTariff("Tariff A", 10.0, 100));
        company.addTariff(new BasicTariff("Tariff B", 20.0, 200));

        // Set up a Predicate that filters tariffs with subscription fee >= 15.0
        Predicate<Tariff> predicate = tariff -> tariff.getSubscriptionFee() >= 15.0;

        // Redirect System.out to capture output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create the command and execute it
        FiltrateTariffsCommand command = new FiltrateTariffsCommand();
        command.execute(company);

//        assertTrue(output.contains("Filtered Tariffs:"));
//        assertTrue(output.contains("Tariff B"));
//        assertFalse(output.contains("Tariff A"));
    }
}