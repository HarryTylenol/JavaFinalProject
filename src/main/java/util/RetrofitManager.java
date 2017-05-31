package util;

import io.reactivex.Observable;
import model.WeatherInfo;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by baghyeongi on 2017. 5. 23..
 */
public class RetrofitManager {

    String key = "bbe89337eb55b5ee53b69a72b04e9423";
    String url = "http://api.openweathermap.org/data/2.5/";

    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public WeatherInfoInterface getWeatherInfoInterface() {
        return getRetrofit().create(WeatherInfoInterface.class);
    }

    public Observable<WeatherInfo> getWeatherInfoCall(String cityName) {
        return getWeatherInfoInterface().getWeatherInfo(key, cityName);
    }

    interface WeatherInfoInterface {
        // 비동기 방식으로 API Call
        @GET("weather")
        Observable<WeatherInfo> getWeatherInfo(@Query("appId") String appId, @Query("q") String q);

    }

}
