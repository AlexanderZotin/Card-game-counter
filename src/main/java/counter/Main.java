package counter;

import counter.controller.ControllerImplementation;
import counter.controller.Listener;
import counter.model.Counter;
import counter.view.Window;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main (String [] args) {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        SwingUtilities.invokeLater(() -> {
            var view = new Window();
            var controller = new ControllerImplementation(view, new Counter());
            view.subscribeToListener(new Listener(controller));
            view.setVisible(true);
        });
    }
}