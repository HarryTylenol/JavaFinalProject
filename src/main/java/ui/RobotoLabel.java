package ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by baghyeongi on 2017. 6. 2..
 */
public class RobotoLabel extends JLabel {

   final static String BOLD = "BOLD";
    final  static String LIGHT = "LIGHT";
    final  static String REGULAR = "REGULAR";

    public RobotoLabel() {
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public RobotoLabel(String text) {
        super(text);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }

    public void setTextAndAdd(String string, Box box) {
        setText(string);
        box.add(this);
    }


    public RobotoLabel setTextAndReturn(String string) {
        setText(string);
        return this;
    }

    public void setFontFromString(String fontName, float size) {

        try {
            Font robotoLight = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir") + "/src/main/resources/" + "roboto/Roboto-Light.ttf"));
            Font rotbotoRegular = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir") + "/src/main/resources/" + "roboto/Roboto-Regular.ttf"));
            Font robotoBold = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir") + "/src/main/resources/" + "roboto/Roboto-Bold.ttf"));

            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(robotoLight);
            genv.registerFont(rotbotoRegular);
            genv.registerFont(robotoBold);

            switch (fontName) {
                case LIGHT: {
                    setFont(robotoLight.deriveFont(size));
                    break;
                }
                case BOLD: {
                    setFont(robotoBold.deriveFont(size));
                    break;
                }
                case REGULAR: {
                    setFont(rotbotoRegular.deriveFont(size));
                    break;
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        } catch (FontFormatException font) {
            font.printStackTrace();
        }
    }
}
