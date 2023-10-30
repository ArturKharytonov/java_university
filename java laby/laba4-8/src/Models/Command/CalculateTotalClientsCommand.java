package Models.Command;

import Models.Command.Interfaces.ICommand;
import Models.Company.Company;

public class CalculateTotalClientsCommand implements ICommand<Company> {
    @Override
    public void execute(Company company) {
        int totalClients = company.calculateCountOfClients();
        System.out.println("Total clients: " + totalClients);
    }
}
