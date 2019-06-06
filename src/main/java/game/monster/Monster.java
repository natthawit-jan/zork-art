package game.monster;

import game.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Monster {
    private static final Map<Integer, Double> LEVEL_HP = new HashMap<Integer, Double>() {{

        put(0, 1000.0);
        put(1, 3000.0);
        put(2, 6000.0);


    }};
    private static Random random = new Random();
    // attributes of monster
    private String name;
    private double hp;
    private int level;

    public Monster() {
        this("no name");
    }

    public Monster(String name) {
        this.name = name;
        //Random level
        int rdLevel = random.nextInt(3);
        this.hp = LEVEL_HP.get(rdLevel);
        this.level = rdLevel + 1;


    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void getDamagedBy(double amount) {
        setHp(getHp() - amount);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Decrease the amount of hp when attackted by a player
     *
     * @param amount
     */
    public void attacked(double amount) {
        this.hp -= amount;

    }


    /**
     * Fight back the player with some amount of hp.
     *
     * @param player
     * @param amount
     */
    public void fight(Player player, double amount) {
        player.setHp(player.getHp() - amount);


    }

    private boolean isDead() {
        return this.hp <= 0;
    }

    public int getLevel() {
        return level;
    }
}
