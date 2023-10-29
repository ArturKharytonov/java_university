import Models.Command.*;
import Models.Company.Company;
import Models.Enums.MenuChoice;

import java.util.Scanner;

import static Models.Enums.MenuChoice.*;

public class Main {
    private static final Scanner _scanner;
    private static final Company _company;
    private static final CommandProcessor<Company> _commandProcessor;
    static {
        _scanner = new Scanner(System.in);
        _company = new Company();
        _commandProcessor = new CommandProcessor<Company>();
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("Menu:\n" +
                    "1 - Create list of tariffs.\n" +
                    "2 - Calculate number of clients.\n" +
                    "3 - Sort tariffs.\n" +
                    "4 - Find tariff.\n");
            System.out.print("Your choice:");
            int choice = _scanner.nextInt();

            switch (values()[choice - 1]) {
                case CREATE_TARIFFS:
                    _commandProcessor.executeCommand(new CreateListOfTariffsCommand(), _company);
                case CALCULATE_CLIENTS:
                    _commandProcessor.executeCommand(new CalculateTotalClientsCommand(), _company);
                    break;
                case SORT_TARIFFS:
                    _commandProcessor.executeCommand(new SortTarrifsCommand(), _company);
                    break;
                case FIND_TARIFF:
                    _commandProcessor.executeCommand(new FindTariffInRangeCommand(), _company);
                    break;
                case EXIT:
                    _scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
