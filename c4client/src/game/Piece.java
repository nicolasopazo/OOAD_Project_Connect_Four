package game;

import gui.GuiColors;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Piece extends JLabel {
    private int team;
    private final Player playerOne;
    private final Player playerTwo;
    private ImageIcon teamOneCircle;
    private ImageIcon teamTwoCircle;
    private ImageIcon teamOneWin;
    private ImageIcon teamTwoWin;

    public Piece(Player playerOne, Player playerTwo) {
        this.team = 0;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        ImageIcon emptyCircle = new ImageIcon(createCircle(GuiColors.TEXT));
        this.setIcon(emptyCircle);
        this.setBackground(GuiColors.BOARD);
        this.setHorizontalAlignment(0);
        this.setVisible(true);
        this.setOpaque(true);
        setPieceColors();
    }

    private void setPieceColors() {
        teamOneCircle = new ImageIcon(createCircle(playerOne.getPlayerColor()));
        teamTwoCircle = new ImageIcon(createCircle(playerTwo.getPlayerColor()));
        teamOneWin = new ImageIcon(createCircle(playerOne.getPlayerColor().brighter()));
        teamTwoWin = new ImageIcon(createCircle(playerTwo.getPlayerColor().brighter()));
    }

    public int getTeam() {
        return team;
    }

    public void winningPieces(int team) {
        if (team == playerOne.getTeam()) {
            this.setIcon(teamOneWin);
            repaint();
            revalidate();
        } else if (team == playerTwo.getTeam()) {
            this.setIcon(teamTwoWin);
            repaint();
            revalidate();
        }
    }

    public void changeTeam(int teamTo) {
        if (team == 0) {
            this.team = teamTo;
            if (team == playerOne.getTeam()) {
                this.setIcon(teamOneCircle);
                repaint();
                revalidate();
            }
            if (team == playerTwo.getTeam()) {
                this.setIcon(teamTwoCircle);
                repaint();
                revalidate();
            }
        }
    }

    private BufferedImage createCircle(Color color) {
        BufferedImage bufferedImage = new BufferedImage(110, 110, BufferedImage.TYPE_INT_ARGB);
        Color transparent = new Color(0x00FFFFFF, true);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setColor(transparent);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));
        g.drawOval(5, 5, 100, 100);
        g.setColor(color);
        g.fillOval(5, 5, 100, 100);
        return bufferedImage;
    }
}
