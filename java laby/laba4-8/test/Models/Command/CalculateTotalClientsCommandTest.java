package Models.Command;

import Models.Company.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculateTotalClientsCommandTest {
    private Company company;
    @BeforeEach
    public void setup() {
        company = new Company();
    }

    @Test
    public void testExecute() {
        // Arrange

        CalculateTotalClientsCommand command = new CalculateTotalClientsCommand();

        // Redirect System.out to ByteArrayOutputStream
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Act
        command.execute(company);

        // Assert
        String output = outputStreamCaptor.toString().trim(); // Get the console output
        assertEquals("Total clients: 0", output); // Assuming the initial count is 0
    }
}