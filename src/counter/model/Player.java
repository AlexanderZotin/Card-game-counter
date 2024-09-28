package counter.model;

import counter.exceptions.NegativeNumberException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player implements Cloneable {
    private int winPoints;
    private int loosePoints;
    private Map<Integer, Integer> whists = new HashMap<>();

    public int getWinPoints() {
        return winPoints;
    }
    
    public void setWinPoints(int winPoints) {
        if(winPoints < 0) throw new NegativeNumberException("Пуля не может быть отрицательной!");
        this.winPoints = winPoints;
    }
    
    public int getLoosePoints() {
        return loosePoints;
    }
    
    public void setLoosePoints(int loosePoints) {
        this.loosePoints = loosePoints;
    }
    
    public Map<Integer, Integer> getWhists() {
        return whists;
    }
   
    public void setWhists(Map<Integer, Integer> whists) {
        this.whists = Objects.requireNonNull(whists, "Параметр whists не должен быть null!");
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        Player otherPlayer = (Player) otherObject;
        return winPoints == otherPlayer.winPoints
                && loosePoints == otherPlayer.loosePoints
                && Objects.equals(this.whists, otherPlayer.whists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winPoints, loosePoints, whists);
    }

    @Override
    public String toString() {
        return "Player {" +
                "winPoints = " + winPoints +
                "; loosePoints = " + loosePoints +
                "; whists = " + whists +
                '}';
    }

    @Override
    public Player clone() {
        try {
            return (Player) super.clone();
        } catch (CloneNotSupportedException _) {
            throw new AssertionError("Случилось непредвиденное: по какой-то причине класс Player " +
                    "больше не реализует Cloneable, но всё ещё имеет метод clone()");
        }
    }

    public static Player[] cloneArray(Player[] array) {
        Player[] copy = new Player[array.length];
        for(int i = 0; i < array.length; i++) {
            copy[i] = array[i].clone();
        }
        return copy;
    }
}
