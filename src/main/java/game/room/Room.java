package game.room;

import game.inventory.Inventory;
import game.monster.Monster;

import java.util.HashMap;

public class Room {


    private String locationName;
    private HashMap<String, Room> exits;
    private RoomMonsters roomMonsters;
    private RoomInventory roomInventory;


    /**
     * Create a room location "locationName". Initially, it has
     * no exits. "location name" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param locationName The room's name.
     */

    public Room(String locationName) {
        this.locationName = locationName;
        // Create monsters
        roomMonsters = new RoomMonsters();
        roomInventory = new RoomInventory();


        exits = new HashMap<String, Room>() {{
            put("up", null);
            put("down", null);
            put("left", null);
            put("right", null);
        }};


    }

    public Monster getMonsterAt(int index){
        return roomMonsters.getMonsterAt(index);
    }


    public int monstersSize(){
        return roomMonsters.monstersSize();
    }

    public int inventoriesSize(){
        return roomInventory.inventoriesSize();
    }


    /**
     * print to the user monster in the list
     */
    public StringBuilder prettyPrintMonstersAndInventories() {
        int index = 1;
        StringBuilder s = new StringBuilder();
        s.append("_______________________________________________\n" +
                "             Monsters in This Room             \n");

        for (Monster m : roomMonsters.getMonsters())
            s.append(index++ + ". " + m.getName() + " at level " + m.getLevel() + "\n");

        s.append("_______________________________________________\n" +
                "            Inventories in This Room            \n");
        index = 1;
        for (Inventory i : roomInventory.getInventories())
            s.append(index++ + ". " + i.getName() + "\n");


        s.append("_______________________________________________");
        return s;
    }


    public void setExit(String direction, Room neighbour) {
        exits.put(direction, neighbour);

    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }


    /**
     * @return The description of the room.
     */
    public String getLocationName() {
        return locationName;
    }

}
