package game.room;

import game.inventory.Inventory;
import game.monster.Dragon;
import game.monster.Godzilla;
import game.monster.Monster;

import java.util.*;


public class Room {

    private String locationName;

    private HashMap<String, Room> exits;
    private List<Monster> monsters;
    private List<Inventory> inventories;
    private static Map<Class, Double> monstersProbability = new HashMap<Class, Double>() {{
        put(Dragon.class, 0.8);
        put(Godzilla.class, 0.2);
    }};



    /**
     * Create a room location "locationName". Initially, it has
     * no exits. "location name" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param locationName The room's name.
     */

    public Room(String locationName) {
        this.locationName = locationName;
        inventories = new ArrayList<>();
        monsters = new ArrayList<>();
        randomInventories();
        randomMonsters();
        exits = new HashMap<String, Room>() {{
            put("up", null);
            put("down", null);
            put("left", null);
            put("right", null);
        }};


    }

    private void randomInventories() {

    }


    private void randomMonsters() {
        double random = Math.random();
        for (Map.Entry<Class, Double> entry : monstersProbability.entrySet()) {
            Class type = entry.getKey();
            Double prob = entry.getValue();
            if (random < prob) {

                Monster m = null;
                try {
                    m = (Monster) type.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                m.setName(type.getSimpleName());
                monsters.add(m);

            } else {
                random = random - prob;
            }
        }
    }


    public void setExit(String direction, Room neighbour) {
        exits.put(direction, neighbour);

    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }


    public List<Monster> getMonsters() {
        return monsters;
    }

    /**
     * @return The description of the room.
     */
    public String getLocationName() {
        return locationName;
    }

}
