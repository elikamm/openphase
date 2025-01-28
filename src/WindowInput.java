import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowInput extends JTextArea {
    WindowInput(Window window) {
        setPreferredSize(new Dimension(0, 300));

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent event) {}

            @Override
            public void keyReleased(KeyEvent event) {}

            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == 10) {
                    window.checkInput(getText());
                }
            }
        });
    }
}
