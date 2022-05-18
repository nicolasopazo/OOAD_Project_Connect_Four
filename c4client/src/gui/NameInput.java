package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class NameInput implements ActionListener {

    private JMenuItem yellow;
    private JMenuItem red;
    private JMenuItem blue;
    private JMenuItem green;
    public String name;
    public Color selectedColor;

    private String optionPane(String message) {
        return JOptionPane.showInputDialog(null,
                createPanel(),
                "ENTER NAME, "+message,
                JOptionPane.PLAIN_MESSAGE);
    }

    private JPanel addMenu() {
        JPanel colorPanel = new JPanel();
        JMenuBar colorsBar = new JMenuBar();
        JMenu colors = new JMenu("CHOOSE COLOR");
        colors.setFont(new Font("Druk Wide", Font.BOLD, 12));
        yellow = new JMenuItem("YELLOW");
        red = new JMenuItem("RED");
        blue = new JMenuItem("BLUE");
        green = new JMenuItem("GREEN");
        for (JMenuItem item : Arrays.asList(yellow,red,blue,green)) {
            item.setFont(new Font("Druk Wide", Font.BOLD, 12));
            item.addActionListener(this);
            colors.add(item);
        }
        colorsBar.add(colors);
        colorPanel.add(colorsBar);
        colorPanel.setSize(new Dimension(10,10));
        return colorPanel;
    }

    private JPanel createPanel() {
        JPanel basePanel = new JPanel();
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        basePanel.setLayout(grid);
        JPanel panel = new JPanel();
        JLabel label = new JLabel(new ImageIcon("resources/login.png"));
        panel.add(label);
        panel.setSize(600, 300);
        panel.setVisible(true);
        gc.gridy = 0;
        basePanel.add(panel,gc);
        gc.gridy = 1;
        basePanel.add(addMenu(),gc);
        return basePanel;
    }

    public String inputName(String message) {
        while (true) {
            name = optionPane(message);
            if (name == null) {
                return null;
            } else if (!name.trim().equals("")) {
                name = name.trim();
                return name;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yellow) {
            selectedColor = GuiColors.PIECE_YELLOW;
        }
        if (e.getSource() == red) {
            selectedColor = GuiColors.PIECE_RED;
        }
        if (e.getSource() == blue) {
            selectedColor = GuiColors.PIECE_BLUE;
        }
        if (e.getSource() == green) {
            selectedColor = GuiColors.PIECE_GREEN;
        }
    }
}
