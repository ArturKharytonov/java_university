package droid;

import save.FileHandler;
import java.util.List;

public class Healer extends Droid {
    private final int singleHealChance = 30;

    private int singleHeal = 0;

    private final int globalHealChance = 5;

    private final int globalHeal = 40;

    public Healer(String name) {
        super.name = name;
        super.health = 100;
        super.damage = 10;
        super.evasion = 10;
        super.evadChance = 10;
    }

    @Override
    public void uniqueAbility(List<Droid> teammates, List<Droid> enemies) {
        Droid teammate = teammates.get(super.random.nextInt(teammates.size()));
        this.singleHeal = super.random.nextInt(10, 21);
        if (super.random.nextInt(100) <= this.singleHealChance) {
            teammate.health += this.singleHeal;
            if (teammate.health > 100) {
                teammate.health = 100;
            }
            System.out.println("\nDroid " + this.name + " healed " + teammate.name + " by " + this.singleHeal + " units");
            FileHandler.addToHistory("\nDroid " + this.name + " healed " + teammate.name + " by " + this.singleHeal + " units");
        }
        if (super.random.nextInt(100) <= this.globalHealChance) {
            for (Droid droid:teammates) {
                droid.health += this.globalHeal;
                if (droid.health > 100) {
                    droid.health = 100;
                }
            }
            System.out.println("\nDroid " + this.name + " used global healing for " + this.globalHeal + " units");
            FileHandler.addToHistory("\nDroid " + this.name + " used global healing for " + this.globalHeal + " units");
        }
        this.attack(enemies);
    }
}
