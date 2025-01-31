import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class WindowQuestionImage extends JLabel {
    String search_path;

    WindowQuestionImage(String search_path) {
        this.search_path = search_path;

        setPreferredSize(new Dimension(400, 0));
    }

    public void setImage(String path) {
        BufferedImage source;

        try {
            source = ImageIO.read(
                new File(search_path, path)
            );
        } 
        catch (Exception exception) {
            setIcon(null);
            return;
        }

        double width = getWidth(),
            height = getHeight(),
            ratio = width / height,
            image_width = source.getWidth(),
            image_height = source.getHeight(),
            image_ratio = image_width / image_height;

        if (image_ratio > ratio) {
            image_width = width;
            image_height = width / image_ratio;
        } else {
            image_height = height;
            image_width = height * image_ratio;
        }

        BufferedImage buffer = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = buffer.createGraphics();

        graphics.setColor(getBackground());
        graphics.fillRect(0, 0, (int)width, (int)height);

        graphics.drawImage(
            source,
            (int)(width / 2 - image_width / 2),
            (int)(height / 2 - image_height / 2),
            (int)image_width,
            (int)image_height,
            null
        );

        setIcon(new ImageIcon(buffer));
    }
}
