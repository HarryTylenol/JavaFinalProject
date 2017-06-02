package ui;

import model.WeatherInfo;
import model.WeatherInfoMain;
import model.WeatherInfoSys;
import model.WeatherInfoWind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.*;

public class WeatherView extends JFrame {

    private JButton selectLocationButton;
    private JButton menu;

    private String[] jLabelProperties = {
            "temp",
            "tempDetail",
            "windDetail",
            "sunrise",
            "sunset"
    };

    private String[] jLabelFontList = {
            RobotoLabel.LIGHT,
            RobotoLabel.LIGHT,
            RobotoLabel.BOLD,
            RobotoLabel.BOLD,
            RobotoLabel.BOLD
    };

    private float[] jLabelSize = {48f, 18f, 12f, 12f, 12f};

    private Color translucent = new Color(0, 0, 0, 0);

    private HashMap<String, RobotoLabel> labelHashMap = new HashMap<>();

    private Box box = Box.createVerticalBox();

    private int index = 0;

    private Color frontColor = Color.WHITE;

    public WeatherView() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(325, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPanelAttribute();

        Arrays.stream(jLabelProperties).forEach(string -> {
            RobotoLabel newLabel = new RobotoLabel(string);
            newLabel.setColor(frontColor);
            newLabel.setFontFromString(jLabelFontList[index], jLabelSize[index]);
            labelHashMap.put(string, newLabel);
            index++;
        });

    }

    WeatherPanel tempDetail = new WeatherPanel(BoxLayout.PAGE_AXIS);
    WeatherPanel windDetail = new WeatherPanel(BoxLayout.PAGE_AXIS);
    WeatherPanel sunDetail = new WeatherPanel(BoxLayout.PAGE_AXIS);

    public void setWeatherData(HashMap<String, WeatherInfo> infoArrayList) throws IOException {
        selectLocationButton = new JButton("White");
        menu = new JButton("Menu");

        WeatherInfo info = (WeatherInfo) infoArrayList.values().toArray()[0];

        WeatherInfoMain infoMain = info.getMain();
        WeatherInfoSys infoSys = info.getSys();
        WeatherInfoWind infoWind = info.getWind();

        Date sunriseDate = new Date(infoSys.getSunrise());
        Calendar sunriseCalendar = Calendar.getInstance();
        sunriseCalendar.setTime(sunriseDate);

        Date sunsetDate = new Date(infoSys.getSunset());
        Calendar sunsetCalendar = Calendar.getInstance();
        sunsetCalendar.setTime(sunsetDate);

        ClassLoader loader = ClassLoader.getSystemClassLoader();
        if (loader != null) {
            // ICON
            String weatherIconCode = info.getWeather()[0].getIcon().replaceAll("(n+|d+)", "");
            System.out.println(weatherIconCode);
            IconView weatherStatus = new IconView(50, 50, weatherIconCode + ".png");
            IconView windIcon = new IconView(30, 30, "ic_wind" + ".png");
            IconView sunIcon = new IconView(30, 30, "ic_wb_sunny" + ".png");
            windIcon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            sunIcon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            menu.addActionListener(new LocationMenu());



            labelHashMap.get("temp").setTextAndAdd(String.format("%1.0f°C", infoMain.getTemp() - 273.15), box);
            labelHashMap.get("tempDetail").setTextAndAdd(String.format("MAX : %1.0f°C / ", infoMain.getTemp_max() - 273.15) + String.format("MIN : %1.0f°C", infoMain.getTemp_min() - 273.15), box);
            tempDetail.add(labelHashMap.get("tempDetail"));

            labelHashMap.get("windDetail").setTextAndAdd(String.format("%1.0fms / ", infoWind.getSpeed()) + String.format("%d°", infoWind.getDeg()), box);
            windDetail.add(labelHashMap.get("windDetail"));

            labelHashMap.get("sunrise").setTextAndAdd("SUN RISE : " + sunriseCalendar.get(Calendar.HOUR_OF_DAY) + ":" + sunriseCalendar.get(Calendar.MINUTE), box);
            labelHashMap.get("sunset").setTextAndAdd("SUN SET : " + sunsetCalendar.get(Calendar.HOUR_OF_DAY) + ":" + sunsetCalendar.get(Calendar.MINUTE), box);
            sunDetail.addAll(labelHashMap.get("sunrise"), labelHashMap.get("sunset"));

            RobotoLabel title1 = new RobotoLabel();
            title1.setFontFromString(RobotoLabel.BOLD, 18f);
            title1.setColor(frontColor);
            RobotoLabel title2 = new RobotoLabel();
            title2.setFontFromString(RobotoLabel.BOLD, 18f);
            title2.setColor(frontColor);
            // Add All
            box.add(weatherStatus);
            box.add(labelHashMap.get("temp"));
            box.add(tempDetail);
            box.add(windIcon);
            box.add(title1.setTextAndReturn("Wind"));
            box.add(windDetail);
            box.add(sunIcon);
            box.add(title2.setTextAndReturn("Sun"));
            box.add(sunDetail);
            box.add(selectLocationButton);
            box.add(menu);
            add(box);

            selectLocationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    if(button.getText().equals("White")) {
                        index = 0;
                        frontColor = Color.black;
                        title1.setColor(frontColor);
                        title2.setColor(frontColor);
                        Arrays.stream(jLabelProperties).forEach(string -> {
                            RobotoLabel label = labelHashMap.get(string);
                            label.setColor(frontColor);
                        });
                        // TODO 아이콘 색상값 바꿔줘야함.
                        button.setText("Black");
                    } else {
                        frontColor = Color.WHITE;
                        title1.setColor(frontColor);
                        title2.setColor(frontColor);
                        Arrays.stream(jLabelProperties).forEach(string -> {
                            RobotoLabel label = labelHashMap.get(string);
                            label.setColor(frontColor);
                        });
                        // TODO 아이콘 색상값 바꿔줘야함.
                        button.setText("White");
                    }
                }
            });
        }


    }

    public void showWindow() {
        getContentPane().setBackground(translucent);
        setBackground(translucent);
        setVisible(true);

    }

    public void setPanelAttribute() {
        JRootPane root = getRootPane();
        root.putClientProperty("Window.shadow", Boolean.FALSE);

        box.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        box.setBackground(translucent);
        tempDetail.setBackground(translucent);
        windDetail.setBackground(translucent);
        sunDetail.setBackground(translucent);
    }

    private static BufferedImage dye(BufferedImage image, Color color)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0,0,w,h);
        g.dispose();
        return dyed;
    }

}
