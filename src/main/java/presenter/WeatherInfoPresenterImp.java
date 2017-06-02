package presenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import model.WeatherInfo;
import util.RetrofitManager;

import java.util.HashMap;

/**
 * Created by baghyeongi on 2017. 5. 23..
 */
public class WeatherInfoPresenterImp implements WeatherInfoPresenter {

    WeatherInfoPresenter.View view;
    HashMap<String, WeatherInfo> tempWeatherInfoMap;
    RetrofitManager retrofitManager;

    public WeatherInfoPresenterImp() {
        retrofitManager = new RetrofitManager();
    }

    @Override
    public void setWeatherInfoPresenter(WeatherInfoPresenter.View view) {
        this.view = view;
    }

    @Override
    public void getData(String city) {
        tempWeatherInfoMap = new HashMap<>();
        System.out.println(city + " Detected");
        retrofitManager
                .getWeatherInfoCall(city)
                .subscribe(new Observer<WeatherInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(WeatherInfo weatherInfo) {
                        tempWeatherInfoMap.put(weatherInfo.getName(), weatherInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        view.setView(tempWeatherInfoMap);
                    }
                });


    }

}
