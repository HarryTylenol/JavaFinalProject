package ui;

import model.WeatherInfo;
import model.WeatherInfoMain;
import model.WeatherInfoSys;
import model.WeatherInfoWind;
import presenter.WeatherInfoPresenterImp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class WeatherView extends JFrame implements WeatherInfoPresenterImp.View {

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

    private int index = 0;

    private Color frontColor = Color.WHITE;

    WeatherInfoPresenterImp weatherInfoPresenterImp = new WeatherInfoPresenterImp();

    private Box box = Box.createVerticalBox();
    private WeatherPanel tempDetail = new WeatherPanel(BoxLayout.PAGE_AXIS);
    private WeatherPanel windDetail = new WeatherPanel(BoxLayout.PAGE_AXIS);
    private WeatherPanel sunDetail = new WeatherPanel(BoxLayout.PAGE_AXIS);

    private RobotoLabel title1 = new RobotoLabel();
    private RobotoLabel title2 = new RobotoLabel();

    IconView weatherStatus;
    IconView windIcon;
    IconView sunIcon;

    public WeatherView() {
        setTitle("Weather");
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

        businessLogicMain("Seoul");
    }

    public void businessLogicMain(String name) {
        weatherInfoPresenterImp.setWeatherInfoPresenter(this);
        weatherInfoPresenterImp.getData(name);
    }

    @Override
    public void setView(HashMap<String, WeatherInfo> weatherInfoArrayList) {
        try {
            setWeatherData(weatherInfoArrayList);
            showWindow();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void setWeatherData(HashMap<String, WeatherInfo> infoArrayList) throws IOException {
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
            weatherStatus = new IconView(50, 50, weatherIconCode + ".png");
            windIcon = new IconView(30, 30, "ic_wind" + ".png");
            sunIcon = new IconView(30, 30, "ic_wb_sunny" + ".png");
            windIcon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            sunIcon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            labelHashMap.get("temp").setText(String.format("%1.0f°C", infoMain.getTemp() - 273.15));
            labelHashMap.get("tempDetail").setText(String.format("MAX : %1.0f°C / ", infoMain.getTemp_max() - 273.15) + String.format("MIN : %1.0f°C", infoMain.getTemp_min() - 273.15));
            labelHashMap.get("windDetail").setText(String.format("%1.0fms / ", infoWind.getSpeed()) + String.format("%d°", infoWind.getDeg()));
            labelHashMap.get("sunrise").setText("SUN RISE : " + sunriseCalendar.get(Calendar.HOUR_OF_DAY) + ":" + sunriseCalendar.get(Calendar.MINUTE));
            labelHashMap.get("sunset").setText("SUN SET : " + sunsetCalendar.get(Calendar.HOUR_OF_DAY) + ":" + sunsetCalendar.get(Calendar.MINUTE));

            title1.setFontFromString(RobotoLabel.BOLD, 18f);
            title1.setColor(frontColor);
            title2.setFontFromString(RobotoLabel.BOLD, 18f);
            title2.setColor(frontColor);
        }
    }

    boolean isFirst = true;

    public void showWindow() {
        getContentPane().setBackground(translucent);
        setBackground(translucent);
        setVisible(true);
        // Add All


        if (isFirst) {
            tempDetail.add(labelHashMap.get("tempDetail"));
            windDetail.add(labelHashMap.get("windDetail"));
            sunDetail.addAll(labelHashMap.get("sunrise"), labelHashMap.get("sunset"));

            box.add(weatherStatus);
            box.add(labelHashMap.get("temp"));
            box.add(tempDetail);
            box.add(windIcon);
            box.add(title1.setTextAndReturn("Wind"));
            box.add(windDetail);
            box.add(sunIcon);
            box.add(title2.setTextAndReturn("Sun"));
            box.add(sunDetail);

            add(box);
            initMenu();
        } else {
            box.remove(0);
            box.add(weatherStatus, 0);
            // 이미지 갱신
        }

        isFirst = false;
    }

    String[] locations = {"Seoul", "Busan", "Daegu", "Gwang-ju", "Incheon", "Daejeon", "Ulsan",
            "Gyeonggi-do"};

    public void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu viewMenu = new JMenu("View");

        JMenuItem darkModeMenu = new JMenuItem("Dark Mode");
        darkModeMenu.addActionListener(e -> {
            frontColor = Color.BLACK;
            title1.setColor(frontColor);
            title2.setColor(frontColor);
            Arrays.stream(jLabelProperties).forEach(string -> {
                RobotoLabel label = labelHashMap.get(string);
                label.setColor(frontColor);
            });
        });

        JMenuItem whiteModeMenu = new JMenuItem("White Mode");
        whiteModeMenu.addActionListener(e -> {
            frontColor = Color.WHITE;
            title1.setColor(frontColor);
            title2.setColor(frontColor);
            Arrays.stream(jLabelProperties).forEach(string -> {
                RobotoLabel label = labelHashMap.get(string);
                label.setColor(frontColor);
            });
        });
        viewMenu.add(darkModeMenu);
        viewMenu.add(whiteModeMenu);

        JMenu locationSelectMenu = new JMenu("Location");
        Arrays.stream(locations).forEach((String location) -> {
            JMenuItem newLocationMenu = new JMenuItem(location);
            newLocationMenu.addActionListener(e -> {
                businessLogicMain(((JMenuItem) e.getSource()).getText());
            });
            locationSelectMenu.add(newLocationMenu);
        });

        menuBar.add(locationSelectMenu);
        menuBar.add(viewMenu);
        setJMenuBar(menuBar);
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

}
