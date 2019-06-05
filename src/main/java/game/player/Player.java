package game.player;

import game.inventory.Inventory;
import game.logic.Command;
import game.logic.Parser;
import game.monster.Monster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Player {

    private static final Map<Integer, Double> LEVEL_HP_TABLE = new HashMap<Integer, Double>() {{
        put(1, 1000.0);
        put(2, 3000.0);
        put(3, 5000.0);
        put(4, 7000.0);
        put(5, 9000.0);

    }};
    private int monstersKilled;
    private String name;
    private int score;
    private double hp;
    private int level;
    private PlayerInventory playerInventory;

    public Player(String name) {

        this.name = name;
        monstersKilled = 0;
        score = 0;
        level = 1;
        hp = LEVEL_HP_TABLE.get(level);
        playerInventory = new PlayerInventory();
    }

    public static Map<Integer, Double> getLEVEL_HP_TABLE() {
        return LEVEL_HP_TABLE;
    }

    public boolean hasInventoryToFight() {
        return !playerInventory.isEmpty();

    }

    public boolean bagIsFull() {
        return playerInventory.isFull();

    }

    public int getMonstersKilled() {
        return monstersKilled;
    }

    public void levelUp() {
        level++;
        playerInventory.setBagCapacity(playerInventory.getBagCapacity() * 2);
        hp = hp * 2;
        score = score * 2;
    }


    public String getName() {
        return name;
    }

    // Not allow a player to change name after entering a game.
    private void setName(String name) {
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


    public int getLevel() {
        return level;

    }

    public void intoTheFightWith(Monster monster, Parser parser) {
        boolean monsterIsDead = false;
        boolean fled = false;
        boolean playerIsDead = false;

        while (!monsterIsDead && !fled && !playerIsDead) {
            Command c = parser.getCommand();
            System.out.println("Hi");
            System.out.println(c.getCommandWord().toString());
            monsterIsDead = true;



        }

    }

    public boolean take(Inventory inventory) {
        return playerInventory.putIn(inventory);
    }

    public StringBuilder getInventoryString() {
        StringBuilder s = new StringBuilder();
        for (Inventory inventory : playerInventory.getInventoryList()){
            s.append(inventory.getName()).append("\n");
        }
        return s;
    }

    public List<Inventory> getInventotyList() {
        return playerInventory.getInventoryList();
    }

    public Inventory getInventoryAt(int i) {
        return getInventotyList().get(i);
    }

    public boolean drop(Inventory toDrop) {
        return playerInventory.drop(toDrop);
    }
}
