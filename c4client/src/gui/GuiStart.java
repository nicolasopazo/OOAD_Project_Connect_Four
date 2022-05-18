package gui;

import java.awt.*;

import static javax.swing.UIManager.put;

public class GuiStart {

    public GuiStart() {
        GuiFrame gui = new GuiFrame();
        guiPut();
        gui.start();
    }

    private void guiPut() {
        put("OptionPane.background", Color.WHITE);
        put("Panel.background", Color.WHITE);
        put("OptionPane.messageFont", new Font("Druk Wide", Font.BOLD, 12));
        put("OptionPane.buttonFont", new Font("Druk Wide", Font.PLAIN, 12));
    }

}
