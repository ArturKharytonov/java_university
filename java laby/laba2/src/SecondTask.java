import Models.Enums.MenuChoice;
import Models.ReadDataHandler;
import Models.Student;

import java.util.ArrayList;
import java.util.function.Predicate;

public class SecondTask {
    private static ReadDataHandler _readDataHandler = new ReadDataHandler();
    public static void main(String[] args){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1, "Kharytonov", "Artur", "Oleksandrovych", "2005-02-19",
                "Lisova street", "1234567890", "Faculty 1", 2, "Group A"));

        var isEnd = false;

        while (!isEnd){
            System.out.println("----MENU----\n" +
                    "1 - Compare by group.\n" +
                    "2 - Compare by birth day.\n" +
                    "3 - Compare by faculty.\n" +
                    "4 - EXIT");

            MenuChoice menuChoice = _readDataHandler.GetMenuChoice();
            if(menuChoice != null){
                Predicate<Student> predicate = _readDataHandler.GetPredicate(menuChoice);

                if(predicate == null)
                    isEnd = true;
                else
                    students.stream().filter(predicate).forEach(System.out::println);
            }
        }
    }
}
