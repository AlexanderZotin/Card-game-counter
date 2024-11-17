package counter.view;

import counter.model.PlayersCount;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import lombok.NonNull;

class Field extends GraphicsPanel {
    private SpringLayout layout;
    private JLabel[] labelsWithPlayersNumber;
    JTextField[] textFieldsForWinPoints;
    JTextField[] textFieldsForLoosePoints;
    final Map<Integer, Map<Integer, JTextField>> textFieldsForWhists = new HashMap<>();

    Field(@NonNull PlayersCount playersCount) {
        super(playersCount);
        update(playersCount);
    }

    void update(@NonNull PlayersCount playersCount) {
        super.setPlayersCount(playersCount);
        removeAll();
        reinitializeComponents(playersCount);
        activateSpringLayout(playersCount);
        addComponents();
        revalidate();
    }

    private void reinitializeComponents(PlayersCount playersCount) {
        int count = playersCount.getNum();
        textFieldsForWinPoints = new JTextField[count];
        textFieldsForLoosePoints = new JTextField[count];
        labelsWithPlayersNumber = new JLabel[count];

        textFieldsForWhists.clear();
        for (int i = 0; i < playersCount.getNum(); i++) {
            textFieldsForWhists.put(i, new HashMap<>());
            textFieldsForWinPoints[i] = new JTextField(5);
            textFieldsForLoosePoints[i] = new JTextField(5);
            labelsWithPlayersNumber[i] = new JLabel("Игрок " + (i + 1));
        }
        for(int i = 0; i < playersCount.getNum(); i++) {
            for(int j = 0; j < playersCount.getNum(); j++) {
                if(i == j) continue;
                textFieldsForWhists.get(i).put(j, new JTextField(5));
            }
        }
    }

    private void activateSpringLayout(PlayersCount playersCount) {
        layout = new SpringLayout();
        setLayout(layout);
        switch(playersCount) {
            case TWO -> forTwoPlayers();
            case THREE -> forThreePlayers();
            case FOUR -> forFourPlayers();
            default -> throw new UnsupportedOperationException("Unknown enum-constant: " + playersCount);
        }
    }

    private void forTwoPlayers() {
        labelsFor2Players();
        whistsFieldsFor2Players();
        looseFieldsFor2Players();
        winFieldsFor2Players();
    }

    private void labelsFor2Players() {
        layout.putConstraint(SpringLayout.NORTH, labelsWithPlayersNumber[0], 170, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, labelsWithPlayersNumber[0], 230, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, labelsWithPlayersNumber[1], 210, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, labelsWithPlayersNumber[1], 230, SpringLayout.WEST, this);
    }

    private void winFieldsFor2Players() {
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWinPoints[0], 70, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWinPoints[0], 222, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWinPoints[1], 310, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWinPoints[1], 222, SpringLayout.WEST, this);
    }

    private void looseFieldsFor2Players() {
        layout.putConstraint(SpringLayout.NORTH, textFieldsForLoosePoints[0], 140, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForLoosePoints[0], 222, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForLoosePoints[1], 235, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForLoosePoints[1], 222, SpringLayout.WEST, this);
    }

    private void whistsFieldsFor2Players() {
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(0).get(1), 15, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(0).get(1), 222, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(1).get(0), 365, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(1).get(0), 222, SpringLayout.WEST, this);
    }

    private void forThreePlayers() {
        labelsFor3Players();
        winFieldsFor3Players();
        looseFieldsFor3Players();
        whistsFieldsFor3Players();
    }

    private void labelsFor3Players() {
        layout.putConstraint(SpringLayout.NORTH, labelsWithPlayersNumber[0], 190, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, labelsWithPlayersNumber[0], 190, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, labelsWithPlayersNumber[1], 220, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, labelsWithPlayersNumber[1], 230, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, labelsWithPlayersNumber[2], 190, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, labelsWithPlayersNumber[2], 270, SpringLayout.WEST, this);
    }

    private void looseFieldsFor3Players() {
        layout.putConstraint(SpringLayout.NORTH, textFieldsForLoosePoints[0], 150, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForLoosePoints[0], 160, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForLoosePoints[1], 250, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForLoosePoints[1], 225, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForLoosePoints[2], 150, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForLoosePoints[2], 280, SpringLayout.WEST, this);
    }

    private void winFieldsFor3Players() {
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWinPoints[0], 150, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWinPoints[0], 80, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWinPoints[1], 310, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWinPoints[1], 225, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWinPoints[2], 150, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWinPoints[2], 365, SpringLayout.WEST, this);
    }

    private void whistsFieldsFor3Players() {
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(0).get(1), 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(0).get(1), 250, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(0).get(2), 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(0).get(2), 125, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(1).get(0), 150, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(1).get(0), 365, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(1).get(2), 300, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(1).get(2), 365, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(2).get(0), 430, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(2).get(0), 125, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(2).get(1), 430, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(2).get(1), 250, SpringLayout.NORTH, this);
    }

    private void forFourPlayers() {
        labelsFor4Players();
        winFieldsFor4Players();
        looseFieldsFor4Players();
        whistsFieldsFor4Players();
    }

    private void labelsFor4Players() {
        layout.putConstraint(SpringLayout.NORTH, labelsWithPlayersNumber[0], 190, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, labelsWithPlayersNumber[0], 190, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, labelsWithPlayersNumber[1], 220, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, labelsWithPlayersNumber[1], 230, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, labelsWithPlayersNumber[2], 190, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, labelsWithPlayersNumber[2], 270, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, labelsWithPlayersNumber[3], 160, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, labelsWithPlayersNumber[3], 230, SpringLayout.WEST, this);
    }

    private void winFieldsFor4Players() {
        layout.putConstraint(SpringLayout.NORTH, textFieldsForLoosePoints[0], 220, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForLoosePoints[0], 130, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForLoosePoints[1], 260, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForLoosePoints[1], 225, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForLoosePoints[2], 220, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForLoosePoints[2], 310, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForLoosePoints[3], 120, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForLoosePoints[3], 225, SpringLayout.WEST, this);
    }

    private void looseFieldsFor4Players() {
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWinPoints[0], 205, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWinPoints[0], 75, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWinPoints[1], 310, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWinPoints[1], 225, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWinPoints[2], 205, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWinPoints[2], 375, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWinPoints[3], 70, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWinPoints[3], 225, SpringLayout.WEST, this);
    }

    private void whistsFieldsFor4Players() {
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(0).get(1), 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(0).get(1), 285, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(0).get(2), 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(0).get(2), 190, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(0).get(3), 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(0).get(3), 95, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(1).get(0), 105, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(1).get(0), 365, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(1).get(2), 340, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(1).get(2), 365, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(1).get(3), 225, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(1).get(3), 365, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(2).get(0), 430, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(2).get(0), 190, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(2).get(1), 430, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(2).get(1), 285, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(2).get(3), 430, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(2).get(3), 95, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(3).get(0), 105, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(3).get(0), 25, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(3).get(1), 225, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(3).get(1), 25, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textFieldsForWhists.get(3).get(2), 340, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, textFieldsForWhists.get(3).get(2), 25, SpringLayout.NORTH, this);
    }

    private void addComponents() {
        textFieldsForWhists.values().stream().flatMap(current -> current.values().stream()).forEach(this::add);
        Arrays.stream(textFieldsForWinPoints).forEach(this::add);
        Arrays.stream(textFieldsForLoosePoints).forEach(this::add);
        Arrays.stream(labelsWithPlayersNumber).forEach(this::add);
    }

    void cleanTextFields() {
        Consumer<JTextField> consumer = current -> current.setText("");
        Arrays.stream(textFieldsForWinPoints).forEach(consumer);
        Arrays.stream(textFieldsForLoosePoints).forEach(consumer);
        textFieldsForWhists.values().stream().flatMap(current -> current.values().stream()).forEach(consumer);
    }
}
