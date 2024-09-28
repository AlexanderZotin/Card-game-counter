package counter.model;

public enum PlayersCount {
    TWO(2), THREE(3), FOUR(4);

    private final int num;

    PlayersCount(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
