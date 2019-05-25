package game.room;

public class Outside extends Room {


    public Outside() {
        super("outside the entrance");
    }
    // override the room description
    public Outside(String locationName) {
        super(locationName);
    }
}
