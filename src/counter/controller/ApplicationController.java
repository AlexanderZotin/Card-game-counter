package counter.controller;

import counter.view.View;
import counter.model.Player;
import counter.model.Counter;

import java.util.Objects;

public class ApplicationController implements Controller {
    private final View view;

    public ApplicationController(View view) {
        this.view = Objects.requireNonNull(view, "Параметр view не должен быть null!");
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void dataCleanRequested() {
        view.cleanData();
    }

    @Override
    public void countRequested() {
        Player[] players = view.parseData();
        if(players.length != 0) {
            int[] result = Counter.count(players);
            view.showResult(result);
        }
    }
}