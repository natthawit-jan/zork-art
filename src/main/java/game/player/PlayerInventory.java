package game.player;

import game.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventory {
    private List<Inventory> inventoryList;
    private int bagCapacity;

    public void setBagCapacity(int bagCapacity) {
        this.bagCapacity = bagCapacity;
    }

    public PlayerInventory() {
        this.inventoryList = new ArrayList<>();
        this.bagCapacity = 10;
    }

    public boolean isEmpty(){
        return inventoryList.size() == 0;
    }


    public boolean isFull(){
        return bagCapacity == inventoryList.size();
    }



    /**
     * This function is called when a player wants to
     * put an inventory in their bag.
     *
     * @param inventory an inventory they want to put in the bag
     * @return true if putting in the bag is successful, false o/w
     */
    public boolean putIn(Inventory inventory) {
        if (bagCapacity != 0) {
            inventoryList.add(inventory);
            bagCapacity--;
            return true;
        }
        return false;
    }

    /**
     * @return bag capacity
     */
    public int getBagCapacity() {
        return bagCapacity;
    }

    /**
     * This function is called when a player want to drop
     * an inventory from their bag.
     *
     * @param inventory an inventory they want to drop.
     * @return true if an inventory is dropped successfully. False o/w
     */
    public boolean drop(Inventory inventory) {
        if (inventoryList.remove(inventory)) {
            bagCapacity++;
            return true;
        }
        return false;
    }

    /**
     * @return List of inventory
     */

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    /**
     * NOT USED
     *
     * @param inventoryList
     */
    private void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
