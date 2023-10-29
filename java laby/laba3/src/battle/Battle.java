package battle;

import Enums.Team;
import droid.*;
import save.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Battle {
    private final List<Droid> teammates = new ArrayList<>();;
    private final List<Droid> enemies = new ArrayList<>();;
    private final Random random = new Random();
    private final Scanner in = new Scanner(System.in);

    public void addDroidToBattle(Team team) {
        in.nextLine();
        Droid droid = null;

        System.out.println(
                "Enter the droid's name: ");
        String name = in.nextLine();

        System.out.println("""
            ----DROID MENU----
            1 - create an assassin class.
            2 - create a phantom class.
            3 - create a healer class.
            4 - create a reaper class.
            5 - create a trickster class.
            """);

        switch (in.nextInt()) {
            case (1) -> {
                System.out.println("Selected class - assassin\n");
                droid = new Assassin(name);
            }
            case (2) -> {
                System.out.println("Selected class - phantom\n");
                droid = new Phantom(name);
            }
            case (3) -> {
                System.out.println("Selected class - healer\n");
                droid = new Healer(name);
            }
            case (4) -> {
                System.out.println("Selected class - reaper\n");
                droid = new Phantom(name);
            }
            case (5) -> {
                System.out.println("Selected class - trickster\n");
                droid = new Trickster(name);
            }
            default -> System.out.println("This class does not exist!\n");
        }
        if(droid == null) return;

        if (team == Team.Shining)
            teammates.add(droid);
        else
            enemies.add(droid);
    }

    public void deleteDroidFromBattle(Team team) {
        in.nextLine();
        System.out.println("Enter the droid's name: ");
        String name = in.nextLine();
        if (team == Team.Shining)
            teammates.remove(name);
        else
            enemies.remove(name);
        System.out.println("Droid " + name + " removed\n");
    }

    private void printDroidList(List<Droid> droids) {
        for (Droid droid : droids)
            System.out.print(droid.getName() + " ");
    }

    public void printDroidsFromTeam(Team team){
        System.out.println("List of droids: ");
        if (team == Team.Shining)
            printDroidList(teammates);

        else
            printDroidList(enemies);

        System.out.println();
    }

    public void startBattle() {
        boolean turn = random.nextBoolean();
        while (!teammates.isEmpty() && !enemies.isEmpty()) {
            if (turn) {
                teammates.get(random.nextInt(teammates.size())).uniqueAbility(teammates, enemies);
            } else {
                enemies.get(random.nextInt(enemies.size())).uniqueAbility(enemies, teammates);
            }
            turn = !turn;
            this.showHP();
            System.out.println("\n-------------------------------------------------------------------------------");
            FileHandler.addToHistory("\n-------------------------------------------------------------------------------");
        }
        if (teammates.isEmpty()) {
            System.out.println("\nTeam Pitmy Wins!\n");
            FileHandler.addToHistory("\nTeam Pitmy Wins!\n");
        } else {
            System.out.println("\nTeam Skyva Wins!\n");
            FileHandler.addToHistory("\nTeam Skyva Wins!\n");
        }

        CompletableFuture<Void> writeToFileFuture = FileHandler.writeToFileAsync();
        writeToFileFuture.join();

        FileHandler.closeFile();
    }

    private void showHP() {
        FileHandler.addToHistory("Team Skyva: ");
        for (Droid droid : teammates) {
            String res = String.format("%.6f", droid.getHealth());
            FileHandler.addToHistory(droid.getName() + " - Health: " + res);
        }
        FileHandler.addToHistory("Team Pitmy: ");
        for (Droid droid : enemies) {
            String res = String.format("%.6f", droid.getHealth());
            FileHandler.addToHistory(droid.getName() + " - Health: " + res);
        }
    }

}

