package com.gavinfinancialgroup.googleplacesapi.Next5Days;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zhang on 4/22/2017.
 */

public class Broadcast5 {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<List5> list = null;
    @SerializedName("city")
    @Expose
    private City5 city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List5> getList() {
        return list;
    }

    public void setList(java.util.List<List5> list) {
        this.list = list;
    }

    public City5 getCity() {
        return city;
    }

    public void setCity(City5 city) {
        this.city = city;
    }

}
