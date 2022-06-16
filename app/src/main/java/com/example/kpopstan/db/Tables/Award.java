package com.example.kpopstan.db.Tables;

public class Award {
    private int _id;
    private String award;
    private String daesang_name;
    private int receipt_year;
    private int group_id;

    public Award() {
        this._id = 0;
        this.award = "None";
        this.daesang_name = "None";
        this.receipt_year = 0;
        this.group_id = 0;
    }

    public Award(int _id, String award, String daesang_name, int receipt_year, int group_id) {
        this._id = _id;
        this.award = award;
        this.daesang_name = daesang_name;
        this.receipt_year = receipt_year;
        this.group_id = group_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getDaesang_name() {
        return daesang_name;
    }

    public void setDaesang_name(String daesang_name) {
        this.daesang_name = daesang_name;
    }

    public int getReceipt_year() {
        return receipt_year;
    }

    public void setReceipt_year(int receipt_year) {
        this.receipt_year = receipt_year;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
