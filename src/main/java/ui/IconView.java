package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by baghyeongi on 2017. 6. 2..
 */
public class IconView extends JLabel {
    public IconView(int x, int y, String path) {

        try {
            File file = new File( System.getProperty("user.dir") + "/src/main/resources/" + path);
            Image image = ImageIO.read(file).getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(image));

        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
