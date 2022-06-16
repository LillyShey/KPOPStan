package com.example.kpopstan.db.Tables;

import java.util.ArrayList;

public class Album {
    private int _id;
    private String album_name;
    private String album_photo;
    private int group_id;
    private String mv;
    private String release_date;
    private int sales_number;

    public Album() {
        this._id = 0;
        this.album_name = "None";
        this.album_photo = "None";
        this.group_id = 0;
        this.mv = "None";
        this.release_date = "None";
        this.sales_number = 0;
    }

    public Album(int _id, String album_name, String album_photo, int group_id, String mv, String release_date, int sales_number) {
        this._id = _id;
        this.album_name = album_name;
        this.album_photo = album_photo;
        this.group_id = group_id;
        this.mv = mv;
        this.release_date = release_date;
        this.sales_number = sales_number;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_photo() {
        return album_photo;
    }

    public void setAlbum_photo(String album_photo) {
        this.album_photo = album_photo;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getMv() {
        return mv;
    }

    public void setMv(String mv) {
        this.mv = mv;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getSales_number() {
        return sales_number;
    }

    public void setSales_number(int sales_number) {
        this.sales_number = sales_number;
    }

    public ArrayList<Track> getTracks(ArrayList<AlbumTrack> albumTrackArrayList, ArrayList<Track> trackArrayList) {
        ArrayList<Track> result = new ArrayList<>();
        for (AlbumTrack albumTrack : albumTrackArrayList) {
            if (albumTrack.getAlbum_id() == this._id) {
                result.add(albumTrack.getTrack(trackArrayList));
            }
        }
        return result;
    }

    public String getVideoID() {
        String urlHead = "https://youtu.be/";
        String result = "";
        for (int i = urlHead.length(); i < this.getMv().length(); i++)
            result += this.getMv().charAt(i);
        return result;
    }
}
