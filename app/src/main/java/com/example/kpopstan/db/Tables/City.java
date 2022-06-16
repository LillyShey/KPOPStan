package com.example.kpopstan.db.Tables;

public class City {
    private int _id;
    private String city_name;

    public City() {
        this._id = 0;
        this.city_name = "None";
    }

    public City(int _id, String city_name) {
        this._id = _id;
        this.city_name = city_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
