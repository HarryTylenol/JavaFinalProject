import model.WeatherInfo;
import presenter.WeatherInfoPresenterImp;

import java.util.HashMap;

/**
 * Created by baghyeongi on 2017. 5. 22..
 */
public class Main implements WeatherInfoPresenterImp.View {

    public static void main(String[] args) {

        new Main().businessLogicMain();

    }

    public void businessLogicMain() {
        WeatherInfoPresenterImp weatherInfoPresenterImp = new WeatherInfoPresenterImp();
        weatherInfoPresenterImp.setWeatherInfoPresenter(this);
        // 비동기로 받아옴
        weatherInfoPresenterImp.getData(new String[]{"Daegu", "Seoul", "Gyeonggi-do", "Busan", "Daejeon"});
    }

    @Override
    public void setView(HashMap<String, WeatherInfo> weatherInfoArrayList) {
        // Test
        weatherInfoArrayList.forEach((city, data) -> {
            System.out.println("=================");
            System.out.println(city + " : " + data.getWeather()[0].getMain());
            System.out.println("=================");
        });
    }
}
