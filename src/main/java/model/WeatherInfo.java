package model;

public class WeatherInfo {
    private int dt;
    private WeatherInfoCoord coord;
    private int visibility;
    private WeatherInfoWeather[] weather;
    private String name;
    private int cod;
    private WeatherInfoMain main;
    private WeatherInfoClouds clouds;
    private int id;
    private WeatherInfoSys sys;
    private String base;
    private WeatherInfoWind wind;

    public int getDt() {
        return this.dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public WeatherInfoCoord getCoord() {
        return this.coord;
    }

    public void setCoord(WeatherInfoCoord coord) {
        this.coord = coord;
    }

    public int getVisibility() {
        return this.visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public WeatherInfoWeather[] getWeather() {
        return this.weather;
    }

    public void setWeather(WeatherInfoWeather[] weather) {
        this.weather = weather;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return this.cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public WeatherInfoMain getMain() {
        return this.main;
    }

    public void setMain(WeatherInfoMain main) {
        this.main = main;
    }

    public WeatherInfoClouds getClouds() {
        return this.clouds;
    }

    public void setClouds(WeatherInfoClouds clouds) {
        this.clouds = clouds;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WeatherInfoSys getSys() {
        return this.sys;
    }

    public void setSys(WeatherInfoSys sys) {
        this.sys = sys;
    }

    public String getBase() {
        return this.base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public WeatherInfoWind getWind() {
        return this.wind;
    }

    public void setWind(WeatherInfoWind wind) {
        this.wind = wind;
    }
}
