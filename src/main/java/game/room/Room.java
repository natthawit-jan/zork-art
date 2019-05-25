package game.room;

import java.util.HashMap;


public class Room {

    private String locationName;

    private HashMap<String, Room> exits;



    /**
     * Create a room location "locationName". Initially, it has
     * no exits. "location name" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param locationName The room's name.
     */

    public Room(String locationName) {
        this.locationName = locationName;
        exits = new HashMap<String, Room>() {{
            put("up", null);
            put("down", null);
            put("left", null);
            put("right", null);
        }};


    }


    public void setExit(String direction, Room neighbour) {
        exits.put(direction, neighbour);

    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }


    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * @return The description of the room.
     */
    public String getLocationName() {
        return locationName;
    }

}
