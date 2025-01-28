import javax.swing.*;
import java.awt.*;

public class WindowPhase extends JPanel {
    float phase = 0;

    WindowPhase() {
        setPreferredSize(new Dimension(800, 50));
    }

    public void setPhase(int phase) {
        this.phase = phase;
        repaint();
    }

    public void animatePhase(int phase) {
        float old_phase = this.phase;
        float direction = phase - old_phase;

        new Thread(() -> {
            try {
                for (int i = 0; i <= 20; ++i) {
                    this.phase = old_phase + direction * ((float)i / 20);
                    repaint();
                    Thread.sleep(50);
                }
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.fillArc(getWidth() - 40, 10, 30, 30, 0, 360);
        graphics.fillRect(25, 10, getWidth() - 50, 30);

        graphics.setColor(new Color(52, 120, 83));
        graphics.fillArc(10, 10, 30, 30, 0, 360);
            
        int offset = (int)((phase / 5) * (getWidth() - 50));

        graphics.fillRect(25, 10, offset, 30);
        graphics.fillArc(offset, 0, 50, 50, 0, 360);

        graphics.setFont(new Font("Arial", Font.BOLD, 24)); 
        graphics.setColor(Color.white);

        for (int i = 0; i < 6; ++i) {
            graphics.drawString(
                String.valueOf(i + 1),
                18 + (getWidth() - 50) / 5 * i,
                34
            );
        }
    }
}
