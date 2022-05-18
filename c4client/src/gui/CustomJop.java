package gui;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.IOException;

public class CustomJop {

    private static Dialog dialog;

    public CustomJop(String message) throws IOException {
        JFrame frame = new JFrame();
        dialog = new JDialog(frame , true);
        dialog.setLayout( new BorderLayout());

        JButton button = new JButton (new ImageIcon("resources/ok_button.png"));
        button.addActionListener (e -> CustomJop.dialog.setVisible(false));
        button.setBounds(520,600,200,80);

        JLabel label = new JLabel(new ImageIcon("resources/scoreboard.png"));
        dialog.add (label,BorderLayout.CENTER);

        JTextPane score = new JTextPane();
        score.setBounds(386,300,450,600);
        score.setOpaque(false);
        score.setText(message);
        score.setForeground(GuiColors.BOARD);
        score.setFont(new Font("Druk Wide",Font.BOLD,30));
        StyledDocument documentStyle = score.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

        label.add(button);
        label.add(score);

        dialog.setSize(1000,800);
        dialog.setVisible(true);

    }
}