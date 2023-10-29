package droid;

import save.FileHandler;

import java.util.List;

public class Phantom extends Droid {
    private final int normAtkChance = 85;

    public Phantom(String name) {
        super.name = name;
        super.health = 100;
        super.damage = 15;
        super.evasion = 100;
        super.evadChance = 35;
    }

    @Override
    public void uniqueAbility(List<Droid> teammates, List<Droid> enemies) {
        Droid enemy = enemies.get(random.nextInt(enemies.size()));
        if (super.random.nextInt(100) <= this.normAtkChance){
            this.attack(enemies);
        } else {
            double lowDamage = this.damage - super.random.nextInt(1, 5);
            if (super.random.nextInt(100) <= enemy.evadChance) {
                double actualDamage = lowDamage - (lowDamage * (enemy.evasion / 100));
                enemy.health -= actualDamage;
                System.out.println("\nDroid " + this.name + " is about to deal " + lowDamage + " weakened damage to droid " + enemy.name +
                        "\nDroid " + enemy.name + " blocks " + enemy.evasion + "% of the damage" +
                        "\nDroid " + this.name + " deals " + actualDamage + " weakened damage to droid " + enemy.name + "\n");
                FileHandler.addToHistory("\nDroid " + this.name + " is about to deal " + lowDamage + " weakened damage to droid " + enemy.name +
                        "\nDroid " + enemy.name + " blocks " + enemy.evasion + "% of the damage" +
                        "\nDroid " + this.name + " deals " + actualDamage + " weakened damage to droid " + enemy.name + "\n");
            } else {
                enemy.health -= lowDamage;
                System.out.println("\nDroid " + this.name + " deals " + lowDamage + " weakened damage to droid " + enemy.name + "\n");
                FileHandler.addToHistory("\nDroid " + this.name + " deals " + lowDamage + " weakened damage to droid " + enemy.name + "\n");
            }
            if(enemy.health <= 0) {
                enemies.remove(enemy);
                System.out.println("Droid " + enemy.name + " has been defeated\n");
                FileHandler.addToHistory("Droid " + enemy.name + " has been defeated\n");
            }
        }
    }
}
