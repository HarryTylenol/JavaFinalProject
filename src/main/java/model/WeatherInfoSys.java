package model;

public class WeatherInfoSys {
    private String country;
    private long sunrise;
    private long sunset;
    private int id;
    private int type;
    private double message;

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return this.sunrise * 1000;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return this.sunset * 1000;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMessage() {
        return this.message;
    }

    public void setMessage(double message) {
        this.message = message;
    }
}
