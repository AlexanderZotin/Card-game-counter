package counter.view;

import counter.model.PlayersCount;
import counter.model.Player;
import counter.controller.Listener;

import lombok.NonNull;

public interface View {
    void subscribeToListener(@NonNull Listener listener);
    void cleanData();
    void setVisible(boolean visible);
    void update(@NonNull PlayersCount playersCount);
    Player[] parseData();
    void showResult(@NonNull int[] result);
}
