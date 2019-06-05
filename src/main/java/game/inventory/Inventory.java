package game.inventory;

public class Inventory {
    // inventory will have a name

    private String name;
    public Inventory(String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
