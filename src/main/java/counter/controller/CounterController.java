package counter.controller;

import counter.view.View;

public interface CounterController {
    View getView();
    void dataCleanRequested();
    void countRequested();
}
