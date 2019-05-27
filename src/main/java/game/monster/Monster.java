package game.monster;


import game.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Monster {
    // attributes of monster
    private String name;
    private double hp;
    private int level;
    private static Random random = new Random();
    private static final Map<Integer,Double> LEVEL_HP = new HashMap<Integer, Double>() {{

        put(1, 1000.0);
        put(2, 3000.0);
        put(3, 6000.0);


    }};

    public Monster() {
        this("no name");
    }

    public Monster(String name){
        this.name = name;
        this.hp = LEVEL_HP.get(1);
        this.level = 1;


    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Decrease the amount of hp when attackted by a player
     * @param amount
     */
    public void attacked(double amount) {
        this.hp -= amount;

    }


    /**
     * Fight back the player with some amount of hp.
      * @param player
     * @param amount
     */
    public void fight(Player player, double amount) {

        double nextDouble = random.nextDouble();
        player.setHp(player.getHp() - nextDouble * player.getHp());


    }

    private boolean isDead() {
        return this.hp <= 0;
    }
}
