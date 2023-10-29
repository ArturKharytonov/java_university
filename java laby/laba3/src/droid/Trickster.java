package droid;

import save.FileHandler;

import java.util.List;

public class Trickster extends Droid {
    private int seriesOfAttack;

    public Trickster(String name) {
        super.name = name;
        super.health = 100;
        super.damage = 3;
        super.evasion = 10;
        super.evadChance = 10;
    }

    @Override
    public void uniqueAbility(List<Droid> teammates, List<Droid> enemies) {
        Droid enemy = enemies.get(random.nextInt(enemies.size()));
        seriesOfAttack = super.random.nextInt(1, 11);
        double seriesDamage = this.damage * seriesOfAttack;
        if (super.random.nextInt(100) <= enemy.evadChance) {
            double actualDamage = seriesDamage - (seriesDamage * (enemy.evasion / 100));
            enemy.health -= actualDamage;
            System.out.println("\nDroid " + this.name + " is about to deal " + seriesDamage + " damage to droid " + enemy.name + " in a series of " + seriesOfAttack + " attacks" +
                    "\nDroid " + enemy.name + " blocks " + enemy.evasion + "% of the damage" +
                    "\nDroid " + this.name + " deals " + actualDamage + " damage to droid " + enemy.name + "\n");
            FileHandler.addToHistory("\nDroid " + this.name + " is about to deal " + seriesDamage + " damage to droid " + enemy.name + " in a series of " + seriesOfAttack + " attacks" +
                    "\nDroid " + enemy.name + " blocks " + enemy.evasion + "% of the damage" +
                    "\nDroid " + this.name + " deals " + actualDamage + " damage to droid " + enemy.name + "\n");
        } else {
            enemy.health -= seriesDamage;
            System.out.println("\nDroid " + this.name + " dealt " + seriesDamage + " damage to droid " + enemy.name + " in a series of " + seriesOfAttack + " attacks\n");
            FileHandler.addToHistory("\nDroid " + this.name + " dealt " + seriesDamage + " damage to droid " + enemy.name + " in a series of " + seriesOfAttack + " attacks\n");
        }
        if (enemy.health <= 0) {
            enemies.remove(enemy);
            System.out.println("Droid " + enemy.name + " has been defeated\n");
            FileHandler.addToHistory("Droid " + enemy.name + " has been defeated\n");
        }
    }
}
