package counter.controller;

import counter.view.View;

public interface Controller {
    View getView();
    void dataCleanRequested();
    void countRequested();
}
