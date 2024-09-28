package counter;

import counter.controller.ApplicationController;
import counter.controller.Controller;
import counter.view.Listener;
import counter.view.View;
import counter.view.Window;

import javax.swing.UIManager;

public class Main {
    private static Controller controller;

    public static void main (String [] args) {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        View view = new Window();
        controller = new ApplicationController(view);
        view.subscribeToListener(new Listener());
        view.setVisible(true);
    }

    public static Controller getController() {
        return controller;
    }
}