import javax.swing.*;
import java.awt.*;

public class WindowQuestion extends JPanel {
    WindowQuestion(WindowQuestionText text, WindowQuestionImage image) {
        setLayout(new BorderLayout());

        add(text, BorderLayout.CENTER);
        add(image, BorderLayout.LINE_END);

        setPreferredSize(new Dimension(0, 300));
    }
}
