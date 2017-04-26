package com.gavinfinancialgroup.googleplacesapi.Next5Days;

/**
 * Created by zhang on 4/22/2017.
 */

import com.gavinfinancialgroup.googleplacesapi.CurrentWeather.Clouds;
import com.gavinfinancialgroup.googleplacesapi.CurrentWeather.Main;
import com.gavinfinancialgroup.googleplacesapi.CurrentWeather.Sys;
import com.gavinfinancialgroup.googleplacesapi.CurrentWeather.Wind;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List5 {

    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather5> weather = null;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("rain")
    @Expose
    private Rain5 rain;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<Weather5> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather5> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain5 getRain() {
        return rain;
    }

    public void setRain(Rain5 rain) {
        this.rain = rain;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

}
