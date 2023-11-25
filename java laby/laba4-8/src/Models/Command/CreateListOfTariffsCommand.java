package Models.Command;

import Models.Command.Interfaces.ICommand;
import Models.Company.Company;

public class CreateListOfTariffsCommand implements ICommand<Company> {
    @Override
    public void execute(Company company) { company.createListOfTariffs(); }
}