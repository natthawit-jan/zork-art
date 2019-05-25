package game.player;

import game.Inventory;

import java.util.List;

public class Player {

    private String name;
    private int score;
    private int hp;
    private List<Inventory> inventoryList;

    public Player(String name) {
        this.name = name;
    }


}
