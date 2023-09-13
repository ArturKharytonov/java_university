import Enums.Team;
import battle.Battle;

import save.FileHandler;
import java.util.Scanner;


public class ThirdTask {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Battle battle = new Battle();

        while (true) {
            System.out.println("""
                    ----MAIN MENU----
                    1 - додати дроїда до команди Сяйва.
                    2 - додати дроїда до команди Пітьми.
                    3 - почати бій.
                    4 - повторити останній бій.
                    5 - вивести на екран дроїдів з команди Сяйва.
                    6 - вивести на екран дроїдів з команди Пітьми.
                    7 - вилучити дроїда з команди Сяйва.
                    8 - вилучити дроїда з команди Пітьми.
                    9 - вийти з програми.
                    """);

            switch (in.nextInt()) {
                case (1) -> battle.addDroidToBattle(Team.Shining);
                case (2) -> battle.addDroidToBattle(Team.Darkness);
                case (3) -> battle.startBattle();
                case (4) -> FileHandler.readFromFileAsync();
                case (5) -> battle.printDroidsFromTeam(Team.Shining);
                case (6) -> battle.printDroidsFromTeam(Team.Darkness);
                case (7) -> battle.deleteDroidFromBattle(Team.Shining);
                case (8) -> battle.deleteDroidFromBattle(Team.Darkness);
                case (9) -> {
                    System.out.println("Exiting the program...");
                    return;
                }
                default -> System.out.println("Такої опції не існує!\n");
            }
        }
    }
}



