package com.example.kpopstan.db.Tables;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Member {
    private int _id;
    private String birth_date;
    private String member_first_name;
    private String member_last_name;
    private String member_photo;
    private String position;
    private String stage_name;

    public Member() {
        this._id = 0;
        this.birth_date = "0001-01-01";
        this.member_first_name = "None";
        this.member_last_name = "None";
        this.member_photo = "None";
        this.position = "None";
        this.stage_name = "None";
    }

    public Member(int _id, String birth_date, String member_first_name, String member_last_name, String member_photo, String position, String stage_name) {
        this._id = _id;
        this.birth_date = birth_date;
        this.member_first_name = member_first_name;
        this.member_last_name = member_last_name;
        this.member_photo = member_photo;
        this.position = position;
        this.stage_name = stage_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getMember_first_name() {
        return member_first_name;
    }

    public void setMember_first_name(String member_first_name) {
        this.member_first_name = member_first_name;
    }

    public String getMember_last_name() {
        return member_last_name;
    }

    public void setMember_last_name(String member_last_name) {
        this.member_last_name = member_last_name;
    }

    public String getMember_photo() {
        return member_photo;
    }

    public void setMember_photo(String member_photo) {
        this.member_photo = member_photo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStage_name() {
        return stage_name;
    }

    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
    }

    public int getAge() {
        Calendar now = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(Date.valueOf(birth_date));
        if (dob.after(now)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }
        int year1 = now.get(Calendar.YEAR);
        int year2 = dob.get(Calendar.YEAR);
        int age = year1 - year2;
        int month1 = now.get(Calendar.MONTH);
        int month2 = dob.get(Calendar.MONTH);
        if (month2 > month1) {
            age--;
        } else if (month1 == month2) {
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            int day2 = dob.get(Calendar.DAY_OF_MONTH);
            if (day2 > day1) {
                age--;
            }
        }
        return age;
    }

    public String getLeavingDate(ArrayList<Composition> compositionArrayList) {
        String result = "0001-01-01";
        for (Composition composition : compositionArrayList) {
            if (composition.getMember_id() == this.get_id())
                result = composition.getLeaving_date();
        }
        return result;
    }

    public String getAccessingDate(ArrayList<Composition> compositionArrayList) {
        String result = "0001-01-01";
        for (Composition composition : compositionArrayList) {
            if (composition.getMember_id() == this.get_id())
                result = composition.getAccession_date();
        }
        return result;
    }
}
