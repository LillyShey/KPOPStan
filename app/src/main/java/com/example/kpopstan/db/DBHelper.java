package com.example.kpopstan.db;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.kpopstan.db.Tables.Album;
import com.example.kpopstan.db.Tables.AlbumTrack;
import com.example.kpopstan.db.Tables.Award;
import com.example.kpopstan.db.Tables.City;
import com.example.kpopstan.db.Tables.Composition;
import com.example.kpopstan.db.Tables.Group;
import com.example.kpopstan.db.Tables.Label;
import com.example.kpopstan.db.Tables.Member;
import com.example.kpopstan.db.Tables.Region;
import com.example.kpopstan.db.Tables.Track;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DBHelper {
    private final Context context;
    private DatabaseReference mDatabase;
    private final String CITY_KEY = "city";
    private final String REGION_KEY = "region";
    private final String LABEL_KEY = "label";
    private final String GROUP_KEY = "group";
    private final String MEMBER_KEY = "member";
    private final String COMPOSITION_KEY = "composition";
    private final String ALBUM_KEY = "album";
    private final String TRACK_KEY = "track";
    private final String ALBUM_TRACK_KEY = "album_track";
    private final String AWARD_KEY = "daesang";

    public DBHelper(Context context) {
        this.context = context;
    }

    public ArrayList<City> getAllCites() {
        ArrayList<City> cityArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(CITY_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    City city = ds.getValue(City.class);
                    cityArrayList.add(city);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Something went wrong :(\nWe can`t load the cities list", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(context), error.getMessage());
            }
        });
        return cityArrayList;
    }

    public ArrayList<Region> getAllRegions() {
        ArrayList<Region> regionArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(REGION_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Region region = ds.getValue(Region.class);
                    regionArrayList.add(region);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Something went wrong :(\nWe can`t load the regions list", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(context), error.getMessage());
            }
        });
        return regionArrayList;
    }

    public ArrayList<Label> getAllLabels() {
        ArrayList<Label> labelArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(LABEL_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Label label = ds.getValue(Label.class);
                    labelArrayList.add(label);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Something went wrong :(\nWe can`t load the labels list", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(context), error.getMessage());
            }
        });
        return labelArrayList;
    }

    public ArrayList<Group> getAllGroups() {
        ArrayList<Group> groupArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(GROUP_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Group group = ds.getValue(Group.class);
                    groupArrayList.add(group);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Something went wrong :(\nWe can`t load the groups list", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(context), error.getMessage());
            }
        });
        return groupArrayList;
    }

    public ArrayList<Member> getAllMembers() {
        ArrayList<Member> memberArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(MEMBER_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Member member = ds.getValue(Member.class);
                    memberArrayList.add(member);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Something went wrong :(\nWe can`t load the members list", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(context), error.getMessage());
            }
        });
        return memberArrayList;
    }

    public ArrayList<Composition> getAllCompositions() {
        ArrayList<Composition> compositionArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(COMPOSITION_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Composition composition = ds.getValue(Composition.class);
                    compositionArrayList.add(composition);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Something went wrong :(\nWe can`t load the compositions list", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(context), error.getMessage());
            }
        });
        return compositionArrayList;
    }

    public ArrayList<Album> getAllAlbums() {
        ArrayList<Album> albumArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(ALBUM_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Album album = ds.getValue(Album.class);
                    albumArrayList.add(album);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Something went wrong :(\nWe can`t load the albums list", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(context), error.getMessage());
            }
        });
        return albumArrayList;
    }

    public ArrayList<Track> getAllTracks() {
        ArrayList<Track> trackArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(TRACK_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Track track = ds.getValue(Track.class);
                    trackArrayList.add(track);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Something went wrong :(\nWe can`t load the tracks list", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(context), error.getMessage());
            }
        });
        return trackArrayList;
    }

    public ArrayList<AlbumTrack> getAllAlbumTracks() {
        ArrayList<AlbumTrack> albumTrackArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(ALBUM_TRACK_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    AlbumTrack albumTrack = ds.getValue(AlbumTrack.class);
                    albumTrackArrayList.add(albumTrack);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Something went wrong :(\nWe can`t load the tracks list", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(context), error.getMessage());
            }
        });
        return albumTrackArrayList;
    }

    public ArrayList<Award> getAllAward() {
        ArrayList<Award> awardArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference(AWARD_KEY);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Award award = ds.getValue(Award.class);
                    awardArrayList.add(award);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Something went wrong :(\nWe can`t load the awards list", Toast.LENGTH_LONG).show();
                Log.e(String.valueOf(context), error.getMessage());
            }
        });
        return awardArrayList;
    }

    public void updateGroup(Group group) {
        mDatabase = FirebaseDatabase.getInstance().getReference(GROUP_KEY);
        mDatabase.child(String.valueOf(group.get_id() - 1)).setValue(group);
    }

    public void deleteGroup(Group group) {
        mDatabase = FirebaseDatabase.getInstance().getReference(GROUP_KEY);
        mDatabase.child(String.valueOf(group.get_id() - 1)).removeValue();
    }

    public void updateLabel(Label label) {
        mDatabase = FirebaseDatabase.getInstance().getReference(LABEL_KEY);
        mDatabase.child(String.valueOf(label.get_id() - 1)).setValue(label);
    }

    public void deleteLabel(Label label) {
        mDatabase = FirebaseDatabase.getInstance().getReference(LABEL_KEY);
        mDatabase.child(String.valueOf(label.get_id() - 1)).removeValue();
    }
    public void updateMember(Member member) {
            mDatabase = FirebaseDatabase.getInstance().getReference(MEMBER_KEY);
            mDatabase.child(String.valueOf(member.get_id() - 1)).setValue(member);
    }
    public void deleteMember(Member member) {
        mDatabase = FirebaseDatabase.getInstance().getReference(MEMBER_KEY);
        mDatabase.child(String.valueOf(member.get_id() - 1)).removeValue();
    }
    public void updateComposition(Composition composition) {
        mDatabase = FirebaseDatabase.getInstance().getReference(COMPOSITION_KEY);
        mDatabase.child(String.valueOf(composition.get_id() - 1)).setValue(composition);
    }
    public void deleteComposition(Composition composition) {
        mDatabase = FirebaseDatabase.getInstance().getReference(COMPOSITION_KEY);
        mDatabase.child(String.valueOf(composition.get_id() - 1)).removeValue();
    }

    public void updateAlbum(Album album) {
        mDatabase = FirebaseDatabase.getInstance().getReference(ALBUM_KEY);
        mDatabase.child(String.valueOf(album.get_id() - 1)).setValue(album);
    }
    public void deleteAlbum(Album album) {
        mDatabase = FirebaseDatabase.getInstance().getReference(ALBUM_KEY);
        mDatabase.child(String.valueOf(album.get_id() - 1)).removeValue();
    }

    public void updateAward(Award award) {
        mDatabase = FirebaseDatabase.getInstance().getReference(AWARD_KEY);
        mDatabase.child(String.valueOf(award.get_id() - 1)).setValue(award);
    }
    public void deleteAward(Award award) {
        mDatabase = FirebaseDatabase.getInstance().getReference(AWARD_KEY);
        mDatabase.child(String.valueOf(award.get_id() - 1)).removeValue();
    }

    public void updateTrack(Track track) {
        mDatabase = FirebaseDatabase.getInstance().getReference(TRACK_KEY);
        mDatabase.child(String.valueOf(track.get_id() - 1)).setValue(track);
    }
    public void deleteTrack(Track track) {
        mDatabase = FirebaseDatabase.getInstance().getReference(TRACK_KEY);
        mDatabase.child(String.valueOf(track.get_id() - 1)).removeValue();
    }

    public void updateAlbumTrack(AlbumTrack albumTrack) {
        mDatabase = FirebaseDatabase.getInstance().getReference(ALBUM_TRACK_KEY);
        mDatabase.child(String.valueOf(albumTrack.get_id() - 1)).setValue(albumTrack);
    }
    public void deleteAlbumTrack(AlbumTrack albumTrack) {
        mDatabase = FirebaseDatabase.getInstance().getReference(ALBUM_TRACK_KEY);
        mDatabase.child(String.valueOf(albumTrack.get_id() - 1)).removeValue();
    }
}
