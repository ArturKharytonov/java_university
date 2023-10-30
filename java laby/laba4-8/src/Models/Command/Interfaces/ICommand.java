package Models.Command.Interfaces;

public interface ICommand<T>{
    void execute(T target);
}
