package counter.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

import lombok.NonNull;

public class Counter implements PreferenceCounter {
    public int[] count(@NonNull Player[] players) {
        Arrays.stream(players).forEach(Objects::requireNonNull);
        Player[] copy = Player.cloneArray(players);
        convertWinPoints(copy);
        return countIt(copy);
    }
  
    private void convertWinPoints(Player[] players) {
        int maximalWinPoints = Arrays.stream(players).mapToInt(Player::getWinPoints)
                .max().getAsInt();
        for (Player player : players) {
            player.setLoosePoints(player.getLoosePoints() + (maximalWinPoints - player.getWinPoints()) * 2);
        }
    }

    private int[] countIt(Player[] players) {
        int[][] allWhists = new int[players.length][players.length];
        for(int i = 0; i < players.length; i++){
            BigDecimal whistsLostByLoosePoints = new BigDecimal(players[i].getLoosePoints() * 10);
            whistsLostByLoosePoints = whistsLostByLoosePoints.divide(BigDecimal.valueOf(players.length), 
                    RoundingMode.HALF_UP);
            for(int j = 0; j < players.length; j++) {
                if(i == j) continue;
                allWhists[i][j] +=
                        players[i].getWhists().get(j) - players[j].getWhists().get(i);
                allWhists[j][i] += whistsLostByLoosePoints.intValue();
                allWhists[i][j] -= whistsLostByLoosePoints.intValue();
            }
        }
        return sumPoints(allWhists);
    }

    private int[] sumPoints(int[][] whists) {
        return Arrays.stream(whists).map(current -> Arrays.stream(current)
                .sum()).mapToInt(Integer::valueOf).toArray();
    }
}