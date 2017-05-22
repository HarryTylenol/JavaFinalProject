package presenter;

import model.WeatherInfo;

import java.util.HashMap;

/**
 * Created by baghyeongi on 2017. 5. 23..
 */
interface WeatherInfoPresenter {


    void setWeatherInfoPresenter(WeatherInfoPresenter.View view);

    void getData(String[] cities);

    interface View {
        void setView(HashMap<String, WeatherInfo> weatherInfoArrayList);
    }

}
