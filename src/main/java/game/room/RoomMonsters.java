package game.room;

import game.monster.Dragon;
import game.monster.Godzilla;
import game.monster.Monster;

import java.util.*;

public class RoomMonsters {

    private static final Random RANDOM = new Random();
    private static int monsterCount = 0;
    private static Map<Class, Double> monstersProbability = new HashMap<Class, Double>() {{
        put(Dragon.class, 0.6);
        put(Godzilla.class, 0.4);
    }};

    private List<Monster> monsters;


    public RoomMonsters() {

        monsters = new ArrayList<>();
        randomMonsters();
    }
    public List<Monster> getMonsters() {
        return monsters;
    }

    public int monstersSize() {
        return monsters.size();
    }

    public Monster getMonsterAt(int index){
        return monsters.get(index);
    }

    /**
     * Randomize monsters into the room according to their probabilities.
     */
    private void randomMonsters() {
        double random = RANDOM.nextDouble();

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
                monsterCount++;
                monsters.add(m);

            } else {
                random = random - prob;
            }
        }
    }


    /**
     * @return the integer indicating the total number of monsters in all rooms
     */
    public static int getMonsterCount() {
        return monsterCount;
    }

}
