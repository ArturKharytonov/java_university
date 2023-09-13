package Models;

import Models.Enums.MenuChoice;
import Models.Interfaces.IDataHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;

public class ReadDataHandler implements IDataHandler<MenuChoice, Student> {
    private Scanner _scanner = new Scanner(System.in);
    private DateTimeFormatter _formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Predicate<Student> GetPredicate(MenuChoice menuChoice){
        _scanner.nextLine();
        switch (menuChoice){
            case Group -> {
                System.out.println("Enter desired group: ");
                String desiredGroup = _scanner.nextLine();
                return student -> student.compareGroups(desiredGroup);
            }
            case Birthday -> {
                System.out.println("Enter birth date(format - yyyy-MM-dd): ");
                String dateString = _scanner.nextLine();
                LocalDate desiredBirthDate = LocalDate.parse(dateString, _formatter);;
                return student -> student.compareDates(desiredBirthDate);
            }
            case Faculty -> {
                System.out.println("Enter desired faculty: ");
                String desiredFaculty = _scanner.nextLine();

                return student -> student.compareFaculties(desiredFaculty);
            }
            case Exit -> {
                return null;
            }
        }
        return null;
    }
    public MenuChoice GetMenuChoice(){
        MenuChoice menuChoice;
        try {
            System.out.print("Enter your choice: ");
            var choice = Integer.parseInt(_scanner.next());
            menuChoice = MenuChoice.values()[choice - 1];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            menuChoice = null;
        }
        return menuChoice;
    }
}
