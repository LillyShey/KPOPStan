package com.example.kpopstan.db.Tables;

import java.util.ArrayList;

public class AlbumTrack {
    private int _id;
    private int album_id;
    private int track_id;

    public AlbumTrack() {
        this._id = 0;
        this.album_id = 0;
        this.track_id = 0;
    }

    public AlbumTrack(int _id, int album_id, int track_id) {
        this._id = _id;
        this.album_id = album_id;
        this.track_id = track_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public int getTrack_id() {
        return track_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public Track getTrack(ArrayList<Track> trackArrayList) {
        Track result = new Track();
        for (Track track : trackArrayList) {
            if (track.get_id() == this.track_id) {
                result = track;
            }
        }
        return result;
    }
}
