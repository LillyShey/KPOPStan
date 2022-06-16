package com.example.kpopstan.db.Tables;

import java.util.ArrayList;

public class Label {
    private int _id;
    private String label_name;
    private double average_annual_income;
    private int city_id;
    private int region_id;
    private String avenu;
    private String building;
    private String director_last_name;
    private String director_first_name;
    private String director_photo;


    public Label() {
        this._id = 0;
        this.label_name = "None";
        this.average_annual_income = 0;
        this.city_id = 0;
        this.region_id = 0;
        this.avenu = "None";
        this.building = "None";
        this.director_last_name = "None";
        this.director_first_name = "None";
        this.director_photo = "None";
    }

    public Label(int _id, String label_name, double average_annual_income, int city_id, int region_id, String avenu, String building, String director_last_name, String director_first_name, String director_photo) {
        this._id = _id;
        this.label_name = label_name;
        this.average_annual_income = average_annual_income;
        this.city_id = city_id;
        this.region_id = region_id;
        this.avenu = avenu;
        this.building = building;
        this.director_last_name = director_last_name;
        this.director_first_name = director_first_name;
        this.director_photo = director_photo;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public double getAverage_annual_income() {
        return average_annual_income;
    }

    public void setAverage_annual_income(double average_annual_income) {
        this.average_annual_income = average_annual_income;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getAvenu() {
        return avenu;
    }

    public void setAvenu(String avenue) {
        this.avenu = avenue;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getDirector_last_name() {
        return director_last_name;
    }

    public void setDirector_last_name(String director_last_name) {
        this.director_last_name = director_last_name;
    }

    public String getDirector_first_name() {
        return director_first_name;
    }

    public void setDirector_first_name(String director_first_name) {
        this.director_first_name = director_first_name;
    }

    public String getDirector_photo() {
        return director_photo;
    }

    public void setDirector_photo(String director_photo) {
        this.director_photo = director_photo;
    }

    public Region getRegion(ArrayList<Region> regionArrayList) {
        Region result = new Region();
        for (Region region : regionArrayList) {
            if (this.region_id == region.get_id()) {
                result = region;
            }
        }
        return result;
    }
}
