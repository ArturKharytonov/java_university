package droid;

import save.FileHandler;

import java.util.List;

public class Trickster extends Droid{
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
        if (super.random.nextInt(100) <= enemy.evadChance ) {
            double actualDamage = seriesDamage - (seriesDamage * (enemy.evasion / 100));
            enemy.health -= actualDamage;
            FileHandler.addToHistory("\nДроїд " + this.name + " має нанести " + seriesDamage + " шкоди дроїду " + enemy.name + " за серію з " + seriesOfAttack + " атак" +
                    "\nДроїд " + enemy.name + " на " + enemy.evasion + "% блокує шкоду" +
                    "\nДроїд " + this.name + " наносить " + actualDamage + " шкоди дроїду " + enemy.name + "\n");
        } else {
            enemy.health -= seriesDamage;
            FileHandler.addToHistory("\nДроїд " + this.name + " наніс " + seriesDamage + " шкоди дроїду " + enemy.name + " за серію з " + seriesOfAttack + " атак\n");
        }
        if(enemy.health <= 0) {
            enemies.remove(enemy);
            FileHandler.addToHistory("Дроїд " + enemy.name + " загинув\n");
        }
    }
}

