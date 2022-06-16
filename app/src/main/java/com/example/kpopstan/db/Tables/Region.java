package com.example.kpopstan.db.Tables;

import java.util.ArrayList;

public class Region {
    private int _id;
    private String region_name;
    private int city_id;

    public Region() {
        this._id = 0;
        this.region_name = "None";
        this.city_id = 0;
    }

    public Region(int _id, String region_name, int city_id) {
        this._id = _id;
        this.region_name = region_name;
        this.city_id = city_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public City getCity(ArrayList<City> cityArrayList) {
        City result = new City();
        for (City city : cityArrayList) {
            if (this.city_id == city.get_id()) {
                result = city;
            }
        }
        return result;
    }
}
