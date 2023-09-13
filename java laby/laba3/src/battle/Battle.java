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
                "Введіть назву дроїда: ");
        String name = in.nextLine();

        System.out.println("""
                ----DROID MENU----
                1 - створити клас асасіна.
                2 - створити клас фантома.
                3 - створити клас хілера.
                4 - створити клас жнеця.
                5 - створити клас трікстера.
                """);

        switch (in.nextInt()) {
            case (1) -> {
                System.out.println("Обраний клас - асасін\n");
                droid = new Assassin(name);
            }
            case (2) -> {
                System.out.println("Обраний клас - фантом\n");
                droid = new Phantom(name);
            }
            case (3) -> {
                System.out.println("Обраний клас - хілер\n");
                droid = new Healer(name);
            }
            case (4) -> {
                System.out.println("Обраний клас - жнець\n");
                droid = new Phantom(name);
            }
            case (5) -> {
                System.out.println("Обраний клас - трікстер\n");
                droid = new Trickster(name);
            }
            default -> System.out.println("Не існує данного класу!\n");
        }
        if(droid == null) return;

        if (team == Team.Shining)
            teammates.add(droid);
        else
            enemies.add(droid);
    }

    public void deleteDroidFromBattle(Team team){
        in.nextLine();
        System.out.println("Введіть назву дроїда: ");
        String name = in.nextLine();
        if (team == Team.Shining)
            teammates.remove(name);
        else
            enemies.remove(name);
        System.out.println("Дроїда " + name + " вилучено\n");
    }

    private void printDroidList(List<Droid> droids) {
        for (Droid droid : droids)
            System.out.print(droid.getName() + " ");
    }

    public void printDroidsFromTeam(Team team){
        System.out.println("Список дроїдів: ");
        if (team == Team.Shining)
            printDroidList(teammates);

        else
            printDroidList(enemies);

        System.out.println();
    }

    public void startBattle() {
        boolean turn = random.nextBoolean();
        while(!teammates.isEmpty() && !enemies.isEmpty()) {
            if(turn) {
                teammates.get(random.nextInt(teammates.size())).uniqueAbility(teammates, enemies);
            } else {
                enemies.get(random.nextInt(enemies.size())).uniqueAbility(enemies, teammates);
            }
            turn = !turn;
            this.showHP();
            FileHandler.addToHistory("\n-------------------------------------------------------------------------------");
        }
        if (teammates.isEmpty()) {
            FileHandler.addToHistory("\nПеремогла команда Пітьми!\n");
        } else {
            FileHandler.addToHistory("\nПеремогла команда Сяйва!\n");
        }

        CompletableFuture<Void> writeToFileFuture = FileHandler.writeToFileAsync();
        writeToFileFuture.join();

        FileHandler.closeFile();
    }

    private void showHP() {
        FileHandler.addToHistory("Команда Сяйва: ");
        for (Droid droid : teammates) {
            String res = String.format("%.6f", droid.getHealth());
            FileHandler.addToHistory(droid.getName() + " - Здоров'я: " + res);
        }
        FileHandler.addToHistory("Команда Пітьми: ");
        for (Droid droid : enemies) {
            String res = String.format("%.6f", droid.getHealth());
            FileHandler.addToHistory(droid.getName() + " - Здоров'я: " + res);
        }
    }
}

