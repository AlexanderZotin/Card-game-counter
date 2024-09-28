package counter.view;

import counter.Main;
import counter.model.PlayersCount;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Listener implements ActionListener, ItemListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String text = clickedButton.getText();
        if(text.equals("Подсчитать!")) {
            Main.getController().countRequested();
        } else {
            Main.getController().dataCleanRequested();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
        Object selected = comboBox.getSelectedItem();
        assert selected != null;
        Main.getController().getView().update((PlayersCount) selected);
    }
}
