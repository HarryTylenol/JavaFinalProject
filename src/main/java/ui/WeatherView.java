package ui;

import model.WeatherInfo;
import sun.security.timestamp.TSRequest;
import sun.security.timestamp.TSResponse;
import sun.security.timestamp.Timestamper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class WeatherView extends JFrame {

    private JLabel tempMin = new JLabel();
    private JLabel temp = new JLabel();
    private JLabel tempMax = new JLabel();
    private JLabel windSpeed = new JLabel();
    private JLabel windDeg = new JLabel();
    private JLabel sunrise = new JLabel();
    private JLabel sunset = new JLabel();
    private JLabel icon;
    private ImageIcon imageIcon;

    public WeatherView() {
        JPanel jp = new JPanel();

        String dir = System.getProperty("user.dir");
        System.out.println(dir);

        setSize(210, 150);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jp.add(tempMin);
        jp.add(temp);
        jp.add(tempMax);
        jp.add(windSpeed);
        jp.add(windDeg);
        jp.add(sunrise);
        jp.add(sunset);

        imageIcon = new ImageIcon(System.getProperty("user.dir") + "/blue.png");

        Image img = imageIcon.getImage();
        Image finalImage = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(finalImage);

        icon = new JLabel(imageIcon, JLabel.CENTER);
        icon.setVerticalTextPosition(JLabel.CENTER);

        jp.add(icon);

        jp.setLayout(new FlowLayout());

        add(jp);

    }

    public void setWeatherData(HashMap<String, WeatherInfo> weatherInfoArrayList) {
        WeatherInfo weatherInfo = (WeatherInfo)weatherInfoArrayList.values().toArray()[0];
        tempMin.setText(Double.toString(weatherInfo.getMain().getTemp_min()));
        temp.setText(Double.toString(weatherInfo.getMain().getTemp()));
        tempMax.setText(Double.toString(weatherInfo.getMain().getTemp_max()));
        windSpeed.setText(Double.toString(weatherInfo.getWind().getSpeed()));
        windDeg.setText(Integer.toString(weatherInfo.getWind().getDeg()));

        Date sunriseDate = new Date(weatherInfo.getSys().getSunrise());
        Date sunsetDate = new Date(weatherInfo.getSys().getSunset());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

        sunrise.setText(simpleDateFormat.format(sunriseDate));
        sunset.setText(simpleDateFormat.format(sunsetDate));

    }

    private Image getScaledImage(Image srcImg, int w, int h){

        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2.drawImage(srcImg, 0, 0, w, h, null);

        g2.dispose();

        return resizedImg;

    }

}
