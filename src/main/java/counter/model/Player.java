package counter.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class Player implements Cloneable {
    private int winPoints;
    private int loosePoints;
    private Map<Integer, Integer> whists = new HashMap<>();

    @Override
    public Player clone() {
        try {
            return (Player) super.clone();
        } catch (CloneNotSupportedException _) {
            throw new AssertionError("This shoudn't happen, because Player is cloneable!");
        }
    }

    public static Player[] cloneArray(@NonNull Player[] array) {
        Player[] copy = new Player[array.length];
        for(int i = 0; i < array.length; i++) {
            copy[i] = array[i].clone();
        }
        return copy;
    }
}
