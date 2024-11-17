package counter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PlayersCount {
    TWO(2), THREE(3), FOUR(4);

    private @Getter final int num;

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
