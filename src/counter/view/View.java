package counter.view;

import counter.model.PlayersCount;
import counter.model.Player;

public interface View {
    void subscribeToListener(Listener listener);
    void cleanData();
    void setVisible(boolean visible);
    void update(PlayersCount playersCount);
    Player[] parseData();
    void showResult(int[] result);
}
