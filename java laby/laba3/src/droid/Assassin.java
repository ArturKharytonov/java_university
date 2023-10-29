package droid;

import save.FileHandler;

import java.util.List;

public class Assassin extends Droid {
    private final int critChance = 35;
    private final int maxModifier = 4;

    public Assassin(String name) {
        super.name = name;
        super.health = 100;
        super.damage = 15;
        super.evasion = 5;
        super.evadChance = 15;
    }

    @Override
    public void uniqueAbility(List<Droid> teammates, List<Droid> enemies) {
        if (super.random.nextInt(100) <= this.critChance) {
            Droid enemy = enemies.get(super.random.nextInt(enemies.size()));
            double critDamage = this.damage + super.random.nextInt(1, maxModifier) * this.damage;
            if (super.random.nextInt(100) <= enemy.evadChance ) {
                double actualDamage = critDamage - (critDamage * (enemy.evasion / 100));
                enemy.health -= actualDamage;
                System.out.println("\nDroid " + this.name + " is about to deal " + critDamage + " critical damage to droid " + enemy.name +
                        "\nDroid " + enemy.name + " blocks " + enemy.evasion + "% of the damage" +
                        "\nDroid " + this.name + " deals " + actualDamage + " critical damage to droid " + enemy.name + "\n");
                FileHandler.addToHistory("\nDroid " + this.name + " is about to deal " + critDamage + " critical damage to droid " + enemy.name +
                        "\nDroid " + enemy.name + " blocks " + enemy.evasion + "% of the damage" +
                        "\nDroid " + this.name + " deals " + actualDamage + " critical damage to droid " + enemy.name + "\n");
            } else {
                enemy.health -= critDamage;
                System.out.println("\nDroid " + this.name + " deals " + critDamage + " critical damage to droid " + enemy.name + "\n");
                FileHandler.addToHistory("\nDroid " + this.name + " deals " + critDamage + " critical damage to droid " + enemy.name + "\n");
            }
            if(enemy.health <= 0) {
                enemies.remove(enemy);
                System.out.println("Droid " + enemy.name + " has been defeated\n");
                FileHandler.addToHistory("Droid " + enemy.name + " has been defeated\n");
            }
        } else {
            this.attack(enemies);
        }
    }
}
