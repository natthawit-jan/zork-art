package game.player;

import game.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Player {

    private String name;
    private int score;
    private double hp;
    private int level;
    private List<Inventory> inventoryList;
    private int bagCapacity;
    private static final Map<Integer, Double> LEVEL_HP_TABLE = new HashMap<Integer, Double>() {{
        put(1, 1000.0);
        put(2, 3000.0);
        put(3, 5000.0);
        put(4, 7000.0);
        put(5, 9000.0);

    }} ;

    public static Map<Integer, Double> getLEVEL_HP_TABLE() {
        return LEVEL_HP_TABLE;
    }

    public Player(String name) {

        this.name = name;
        score = 0;
        level = 1;
        hp =
        bagCapacity = 10;
        inventoryList = new ArrayList<>();
    }

    public void levelUp(){
        level++;
        bagCapacity *= 2;
        hp = hp * 2;
        score = score * 2;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public int getLevel() {
        return level;

    }
}
