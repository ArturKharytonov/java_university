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
                FileHandler.addToHistory("\nДроїд " + this.name + " має нанести " + critDamage + " критичної шкоди дроїду " + enemy.name +
                        "\nДроїд " + enemy.name + " на " + enemy.evasion + "% блокує шкоду" +
                        "\nДроїд " + this.name + " наносить " + actualDamage + " критичної шкоди дроїду " + enemy.name + "\n");
            } else {
                enemy.health -= critDamage;
                FileHandler.addToHistory("\nДроїд " + this.name + " наносить " + critDamage + " критичної шкоди дроїду " + enemy.name + "\n");
            }
            if(enemy.health <= 0) {
                enemies.remove(enemy);
                FileHandler.addToHistory("Дроїд " + enemy.name + " загинув\n");
            }
        } else {
            this.attack(enemies);
        }
    }
}
