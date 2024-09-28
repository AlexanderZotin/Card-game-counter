package counter.view;

import counter.model.PlayersCount;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Objects;

class GraphicsPanel extends JPanel {
    private PlayersCount playersCount;

    GraphicsPanel(PlayersCount playersCount) {
        setPlayersCount(playersCount);
    }

    void setPlayersCount(PlayersCount playersCount) {
        this.playersCount = Objects.requireNonNull(playersCount, "Параметр playersCount не должен быть null!");
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawLine(0, 400, 500, 400);
        switch (playersCount) {
            case TWO -> drawFieldForTwoPlayers(g);
            case THREE -> drawFieldForThreePlayers(g);
            case FOUR -> drawFieldForFourPlayers(g);
            default -> throw new UnsupportedOperationException("Неизвестная enum-константа: " + playersCount);
        }
    }

    private void drawFieldForTwoPlayers(Graphics g) {
        g.drawPolygon(new int[]{250, 0, 250, 500}, new int[]{40, 200, 360, 200}, 4);
        g.drawPolygon(new int[]{250, 100, 250, 400}, new int[]{100, 200, 300, 200}, 4);
        g.drawLine(0, 200, 500, 200);
    }

    private void drawFieldForThreePlayers(Graphics g) {
        g.drawLine(250, 0, 250, 200);
        g.drawPolygon(new int[]{0, 250, 500}, new int[]{400,  200, 400}, 3);
        //-1 — это чтобы не было линии у края окна, она не нужна и выглядит некрасиво
        g.drawPolygon(new int[]{75, 75, 425, 425}, new int[]{-1, 340, 340, -1}, 4);
        g.drawPolygon(new int[]{140, 140, 360, 360}, new int[]{-1, 290, 290, -1}, 4);
        g.drawLine(0, 200, 75, 200);
        g.drawLine(425, 200, 500, 200);
        g.drawLine(250, 340, 250, 400);
    }

    private void drawFieldForFourPlayers(Graphics g) {
        g.drawLine(0, 0, 500, 400);
        g.drawLine(500, 0, 0, 400);
        g.drawPolygon(new int[]{75, 75, 425, 425}, new int[]{60, 340, 340, 60}, 4);
        g.drawPolygon(new int[]{125, 125, 375, 375}, new int[]{100, 300, 300, 100}, 4);
        //-1 — это чтобы не было линии у края окна, она не нужна и выглядит некрасиво
        g.drawPolygon(new int[]{-1, 75, 75, -1}, new int[]{133, 133, 266, 266}, 4);
        g.drawPolygon(new int[]{500, 425, 425, 500}, new int[]{133, 133, 266, 266}, 4);
        g.drawPolygon(new int[]{166, 166, 332, 332}, new int[]{-1, 60, 60, -1}, 4);
        g.drawPolygon(new int[]{166, 166, 332, 332}, new int[]{340, 400, 400, 340}, 4);
    }
}
