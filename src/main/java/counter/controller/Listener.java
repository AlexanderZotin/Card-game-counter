package counter.controller;

import counter.model.PlayersCount;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Listener implements ActionListener, ItemListener {
    private final CounterController linkedController;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        var clickedButton = (JButton) e.getSource();
        String text = clickedButton.getText();
        if(text.equals("Подсчитать!")) {
            linkedController.countRequested();
        } else {
            linkedController.dataCleanRequested();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
        Object selected = comboBox.getSelectedItem();
        assert selected != null;
        linkedController.getView().update((PlayersCount) selected);
    }
}
