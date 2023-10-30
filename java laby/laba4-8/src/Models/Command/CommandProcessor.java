package Models.Command;

import Models.Command.Interfaces.ICommand;


public class CommandProcessor<T>{
    public void executeCommand(ICommand<T> command, T target) {
        command.execute(target);
    }
}
