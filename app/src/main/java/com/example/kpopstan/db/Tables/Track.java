package com.example.kpopstan.db.Tables;

public class Track {
    private int _id;
    private String track_name;
    private String continuance;

    public Track() {
        this._id = 0;
        this.track_name = "None";
        this.continuance = "00:00:00";
    }

    public Track(int _id, String track_name, String continuance) {
        this._id = _id;
        this.track_name = track_name;
        this.continuance = continuance;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTrack_name() {
        return track_name;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public String getContinuance() {
        return continuance;
    }

    public void setContinuance(String continuance) {
        this.continuance = continuance;
    }
}
