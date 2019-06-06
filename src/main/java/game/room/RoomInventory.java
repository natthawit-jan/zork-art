package game.room;

import game.inventory.Diamond;
import game.inventory.Inventory;
import game.inventory.Pen;
import game.inventory.Sword;

import java.util.*;

public class RoomInventory {


    private static Map<Class, Double> inventoryProbability = new HashMap<Class, Double>() {{
        put(Pen.class, 0.6);
        put(Sword.class, 0.3);
        put(Diamond.class, 0.2);
    }};
    private static int inventoriesCount = 0;
    private static final Random RANDOM = new Random();
    private List<Inventory> inventories;


    public RoomInventory() {
        inventories = new ArrayList<>();
        randomInventories();
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public int getInventoriesCount() {
        return inventoriesCount;
    }

    private void randomInventories() {
        double random = RANDOM.nextDouble();
        for (Map.Entry<Class, Double> entry : inventoryProbability.entrySet()) {
            Class type = entry.getKey();
            Double prob = entry.getValue();
            if (random < prob) {

                Inventory i = null;
                try {
                    i = (Inventory) type.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                inventoriesCount++;
                inventories.add(i);

            } else {
                random = random - prob;
            }
        }


    }


    public int inventoriesSize() {
        return inventories.size();
    }

    public Inventory getInventoryAt(int i) {
        return inventories.get(i);
    }

    public void remove(Inventory inventory) {
        inventories.remove(inventory);
    }

    public void place(Inventory thisInventory) {
        inventories.add(thisInventory);
    }
}
