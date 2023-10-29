package droid;

import save.FileHandler;

import java.util.List;
import java.util.Random;

public abstract class Droid {
    protected String name;
    protected double health;
    protected double damage;
    protected double evasion;
    protected int evadChance;

    public abstract void uniqueAbility(List<Droid> teammates, List<Droid> enemies);
    protected Random random = new Random();

    public String getName() {
        return name;
    }
    public double getHealth() {
        return health;
    }
    public double getDamage() {
        return damage;
    }
    public double getEvasion() {
        return evasion;
    }

    public void attack(List<Droid> enemies) {
        Droid enemy = enemies.get(random.nextInt(enemies.size()));
        if (random.nextInt(100) <= enemy.evadChance) {
            double actualDamage = this.damage - (this.damage * (enemy.evasion / 100));
            enemy.health -= actualDamage;
            System.out.println("\nDroid " + this.name + " is about to deal " + this.damage + " damage to droid " + enemy.name +
                    "\nDroid " + enemy.name + " blocks " + enemy.evasion + "% of the damage" +
                    "\nDroid " + this.name + " deals " + actualDamage + " damage to droid " + enemy.name + "\n");
            FileHandler.addToHistory("\nDroid " + this.name + " is about to deal " + this.damage + " damage to droid " + enemy.name +
                    "\nDroid " + enemy.name + " blocks " + enemy.evasion + "% of the damage" +
                    "\nDroid " + this.name + " deals " + actualDamage + " damage to droid " + enemy.name + "\n");
        } else {
            enemy.health -= this.damage;
            System.out.println("\nDroid " + this.name + " deals " + this.damage + " damage to droid " + enemy.name + "\n");
            FileHandler.addToHistory("\nDroid " + this.name + " deals " + this.damage + " damage to droid " + enemy.name + "\n");
        }
        if(enemy.health <= 0) {
            enemies.remove(enemy);
            System.out.println("Droid " + enemy.name + " has been defeated\n");
            FileHandler.addToHistory("Droid " + enemy.name + " has been defeated\n");
        }
    }

    @Override
    public String toString() {
        return "Droid{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", random=" + random +
                '}';
    }
}
