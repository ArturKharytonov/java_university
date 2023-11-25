package Models.Command;

import Models.Company.Company;
import Models.Tariffs.Tariff;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateListOfTariffsCommandTest {
    private final Company company = new Company();
    private final List<Tariff> _tariffs = new ArrayList<Tariff>();
    private final InputStream originalSystemIn = System.in;

    @BeforeEach
    void setUp() {
        // Replace the following string with test input for the scanner
        String testInput = "1\nBasicTariff\n100.0\n5\n3\n" +
                "2\nPremiumTariff\n150.0\n8\n10\n3\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    void testCreateListOfTariffs() {
        // Execute the method
        CreateListOfTariffsCommand command = new CreateListOfTariffsCommand();
        command.execute(company);

        assertEquals(2, _tariffs.size());
    }
}