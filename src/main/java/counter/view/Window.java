package counter.view;

import counter.exceptions.NegativeNumberException;
import counter.model.PlayersCount;
import counter.model.Player;
import counter.controller.*;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import lombok.NonNull;

public class Window extends JFrame implements View {
    private Field field;
    private final JButton cleanAll = new JButton("Очистить всё");
    private final JButton go = new JButton("Подсчитать!");
    private final JComboBox<PlayersCount> playersCountComboBox = new JComboBox<>(PlayersCount.values());
    
    public Window() {
        field = new Field(PlayersCount.THREE);
        playersCountComboBox.setSelectedItem(PlayersCount.THREE);
        constructPanels();
        setResizable(false);
        setTitle("Преферанс-счётчик. ©А.А Зотин, 2023 — 2024");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(505, 530));
        pack();
    }

    private void constructPanels() {
        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());

        JPanel panelWithComboBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelWithComboBox.add(new JLabel("Количество игроков:"));
        panelWithComboBox.add(playersCountComboBox);
        panelWithComboBox.add(cleanAll);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add("Center", panelWithComboBox);
        southPanel.add("South", go);
        generalPanel.add("South", southPanel);
        generalPanel.add("Center", field);

        setContentPane(generalPanel);
    }

    @Override
    public void subscribeToListener(@NonNull Listener listener) {
        go.addActionListener(listener);
        cleanAll.addActionListener(listener);
        playersCountComboBox.addItemListener(listener);
    }

    @Override
    public void cleanData() {
        if(ViewUtils.showYesNoDialogWithWarning("<html>Вы уверены, что хотите<br>очистить все текстовые поля?</html>")) {
            SwingUtilities.invokeLater(field::cleanTextFields);
        }
    }

    @Override
    public void update(@NonNull PlayersCount count) {
        field.update(count);
        repaint();
    }

    @Override
    public Player[] parseData() throws NumberFormatException, NegativeNumberException {
        Object selected = playersCountComboBox.getSelectedItem();
        assert selected != null;
        PlayersCount playersCount = (PlayersCount) selected;
        Player[] players = new Player[playersCount.getNum()];
        players = Arrays.stream(players).map(_-> new Player()).toArray(Player[]::new);
        parseLooseAndWinPoints(players);
        parseWhists(players);
        return players;
    }

    private void parseLooseAndWinPoints(Player[] players) throws NumberFormatException {
        for(int i = 0; i < field.textFieldsForLoosePoints.length; i++) {
            players[i].setLoosePoints(ViewUtils.parseInt(field.textFieldsForLoosePoints[i]));
            players[i].setWinPoints(ViewUtils.parsePositiveInt(field.textFieldsForWinPoints[i]));
        }
    }

    private void parseWhists(Player[] players) throws NumberFormatException {
        for (int i = 0; i < players.length; i++) {
            Map<Integer, Integer> currentWhists = new HashMap<>();
            for(int j = 0; j < players.length; j++) {
                if(i == j) continue;
                currentWhists.put(j, ViewUtils.parseInt(field.textFieldsForWhists.get(i).get(j)));
            }
            players[i].setWhists(currentWhists);
        }
    }

    @Override
    public void showResult(@NonNull int[] result) {
        ViewUtils.showResultDialog(ViewUtils.toResultList("Игрок ", result));
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(true);
        if (visible) {
            repaint();
            setLocationRelativeTo(null);
        }
    }
}