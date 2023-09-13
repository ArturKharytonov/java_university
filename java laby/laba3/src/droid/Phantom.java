package droid;

import save.FileHandler;

import java.util.List;

public class Phantom extends Droid{
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
            double lowDamage = this.damage - super.random.nextInt(1,5);
            if (super.random.nextInt(100) <= enemy.evadChance) {
                double actualDamage = lowDamage - (lowDamage * (enemy.evasion / 100));
                enemy.health -= actualDamage;
                FileHandler.addToHistory("\nДроїд " + this.name + " має нанести " + lowDamage + " послабленої шкоди дроїду " + enemy.name +
                        "\nДроїд " + enemy.name + " на " + enemy.evasion + "% блокує шкоду" +
                        "\nДроїд " + this.name + " наносить " + actualDamage + " послабленої шкоди дроїду " + enemy.name + "\n");
            } else {
                enemy.health -= lowDamage;
                FileHandler.addToHistory("\nДроїд " + this.name + " наносить " + lowDamage + " послабленої шкоди дроїду " + enemy.name + "\n");
            }
            if(enemy.health <= 0) {
                enemies.remove(enemy);
                FileHandler.addToHistory("Дроїд " + enemy.name + " загинув\n");
            }
        }
    }
}
