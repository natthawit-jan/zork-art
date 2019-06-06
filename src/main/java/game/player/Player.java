package game.player;

import game.inventory.Diamond;
import game.inventory.Inventory;
import game.logic.Command;
import game.logic.CommandWord;
import game.logic.Parser;
import game.monster.Monster;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Player {
    private static final Random RAN = new Random();

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
    private int runAwayToken;
    private boolean runAwayLastTime;

    public Player(String name) {
        runAwayLastTime = false;
        this.name = name;
        monstersKilled = 0;
        score = 0;
        level = 1;
        runAwayToken = 2;
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
        boolean finished = false;


        printWelcomeToTheFight(monster);
        while (!finished) {
            Command c = parser.getCommand();
            finished = processCommand(c, monster);
        }


    }





    private void printWelcomeToTheFight(Monster monster) {
        System.out.println("You are now in the fight with " + monster.getName() + " ( " + monster.getHp() + " )");
        System.out.println("Your option is to attack or run away (Current Run away token : " + runAwayToken + ")");
        System.out.println("Type (A)ttack or (F)lee ");
        System.out.println();
    }

    private boolean processCommand(Command c, Monster monster) {
        boolean quitTheFight = false;
        CommandWord word = c.getCommandWord();
        if (word == null) return quitTheFight;
        switch (word) {
            case ATTACK:
                quitTheFight = attack(monster);
                break;
            case FLEE:
                quitTheFight = flee();
                break;
        }

        return quitTheFight;


    }

    public boolean isRunAwayLastTime() {
        return runAwayLastTime;
    }

    private boolean flee() {

        if (runAwayToken == 0) {
            System.out.println("You can no longer run away! ");
            return false;
        }
        runAwayToken-- ;
        runAwayLastTime = true;
        System.out.println("You have run away! The remaining token is " + runAwayToken);
        return true;
    }

    private boolean attack(Monster monster) {
        DecimalFormat df2 = new DecimalFormat("#.##");
        double playerToMonsterDam = RAN.nextDouble() * monster.getHp();

        System.out.println("You've attacked with the damage of " + df2.format(playerToMonsterDam));
        monster.getDamagedBy(playerToMonsterDam);
        double monstersFigthBackDam = RAN.nextDouble() * RAN.nextDouble() * RAN.nextDouble() * getHp();
        System.out.println("The monster has attacked back with the damage of " + df2.format(monstersFigthBackDam));
        monster.fight(this, monstersFigthBackDam);
        System.out.println("Your HP now = " + df2.format(getHp()));
        System.out.println("Monster HP now = " + df2.format(monster.getHp()));
        return handleCause(monster);


    }

    private boolean handleCause(Monster monster) {
        long monsterHPRound = Math.round(monster.getHp());
        long roundedPlayerHP = Math.round(getHp());

        if (monsterHPRound <= 0) {
            System.out.println("You have defeated the monster");
            runAwayLastTime = false;
            monstersKilled++;
            return true;

        } else if (roundedPlayerHP <= 0) {
            System.out.println("You have lost! Please, try again");
            System.exit(0);
        }
        return false;
    }


    public boolean take(Inventory inventory) {
        return playerInventory.putIn(inventory);
    }

    public StringBuilder getInventoryString() {
        StringBuilder s = new StringBuilder();
        for (Inventory inventory : getInventoryList()) {
            s.append(inventory.getName()).append("\n");
        }
        return s;
    }

    public List<Inventory> getInventoryList() {
        return playerInventory.getInventoryList();
    }

    public Inventory getInventoryAt(int i) {
        return getInventoryList().get(i);
    }

    public boolean drop(Inventory toDrop) {
        return playerInventory.drop(toDrop);
    }

    public boolean foundDiamond() {
        for (Inventory inventory : getInventoryList()) {
            if (inventory instanceof Diamond) return true;
        }
        return false;

    }
}
