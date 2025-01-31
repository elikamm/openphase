import javax.swing.*;
import javax.swing.border.*;

public class WindowQuestionText extends JTextArea {
    WindowQuestionText() {
        setEditable(false);
        setOpaque(false);

        setLineWrap(true);
        setWrapStyleWord(true);

        setBorder(new EmptyBorder(40, 20, 40, 0));
    }
}
