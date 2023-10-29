import Enums.Team;
import battle.Battle;

import save.FileHandler;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;


public class ThirdTask {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Battle battle = new Battle();

        while (true) {
            System.out.println("""
                ----MAIN MENU----
                1 - Add a droid to Team Shining.
                2 - Add a droid to Team Darkness.
                3 - Start a battle.
                4 - Repeat the last battle.
                5 - Display droids from Team Shining.
                6 - Display droids from Team Darkness.
                7 - Remove a droid from Team Shining.
                8 - Remove a droid from Team Darkness.
                9 - Exit the program.
                """);

            switch (in.nextInt()) {
                case (1) -> battle.addDroidToBattle(Team.Shining);
                case (2) -> battle.addDroidToBattle(Team.Darkness);
                case (3) -> battle.startBattle();
                case (4) -> {
                    CompletableFuture<List<String>> history =  FileHandler.readFromFileAsync();
                    history.thenAccept(story -> {
                        for (String line : story) {
                            System.out.println(line);
                        }
                    });
                }
                case (5) -> battle.printDroidsFromTeam(Team.Shining);
                case (6) -> battle.printDroidsFromTeam(Team.Darkness);
                case (7) -> battle.deleteDroidFromBattle(Team.Shining);
                case (8) -> battle.deleteDroidFromBattle(Team.Darkness);
                case (9) -> {
                    System.out.println("Exiting the program...");
                    return;
                }
                default -> System.out.println("Option does not exist!\n");
            }
        }
    }
}



