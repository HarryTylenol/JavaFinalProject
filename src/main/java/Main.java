import model.WeatherInfo;
import presenter.WeatherInfoPresenterImp;
import ui.WeatherView;

import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Created by baghyeongi on 2017. 5. 22..
 */
public class Main implements WeatherInfoPresenterImp.View {

    WeatherView weatherView;

    public static void main(String[] args) {

        Main main = new Main();
        main.weatherView = new WeatherView();


        main.businessLogicMain();
    }

    public void businessLogicMain() {
        WeatherInfoPresenterImp weatherInfoPresenterImp = new WeatherInfoPresenterImp();
        weatherInfoPresenterImp.setWeatherInfoPresenter(this);
        // 비동기로 받아옴
        weatherInfoPresenterImp.getData(new String[]{"Seoul"});
    }

    @Override
    public void setView(HashMap<String, WeatherInfo> weatherInfoArrayList) {
        // Test
        weatherView.setWeatherData(weatherInfoArrayList);

        weatherInfoArrayList.forEach((city, data) -> {
            System.out.println("=================");
            System.out.println(city + " : " + data.getWeather()[0].getMain());
            System.out.println("=================");
        });
    }
}
