package com.example.kpopstan;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kpopstan.db.Adapters.TrackListAdapter;
import com.example.kpopstan.db.Tables.Track;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;

import java.util.ArrayList;

public class TrackListActivity extends AppCompatActivity {
    private RecyclerView trackRecyclerView;
    private ArrayList<Track> trackArrayList;
    private YouTubePlayerSupportFragmentX youTubePlayerFragment;
    private YouTubePlayer youTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);
        getSupportActionBar().hide();
        ((TextView) findViewById(R.id.textTitle)).setText(GroupListActivity.group_name + " - " + GroupInfoActivity.album_name);
        trackRecyclerView = findViewById(R.id.trackListView);
        findViewById(R.id.backToList)
                .setOnClickListener(view -> onBackPressed());
        showTrackList();
        initializeYoutubePlayer();
    }

    private void initializeYoutubePlayer() {
        youTubePlayerFragment = YouTubePlayerSupportFragmentX.newInstance();
        youTubePlayerFragment = (YouTubePlayerSupportFragmentX) getSupportFragmentManager().findFragmentById(R.id.youtube_player_fragment);
        if (youTubePlayerFragment == null)
            return;
        youTubePlayerFragment.initialize(YouTubeConfig.getApiKey(), new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    for (int i = 0; i < GroupListActivity.albumArrayList.size(); i++) {
                        if (GroupListActivity.albumArrayList.get(i).getAlbum_name().equals(GroupInfoActivity.album_name)) {
                            youTubePlayer = player;
                            youTubePlayer.cueVideo(GroupListActivity.albumArrayList.get(i).getVideoID());
                        }
                    }
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                Toast.makeText(TrackListActivity.this, "Something went wrong :(\nWe can`t load the members list", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showTrackList() {
        try {
            trackArrayList = new ArrayList<>();
            for (int i = 0; i < GroupListActivity.albumArrayList.size(); i++) {
                if (GroupListActivity.albumArrayList.get(i).getAlbum_name().equals(GroupInfoActivity.album_name)) {
                    trackArrayList = GroupListActivity.albumArrayList.get(i).getTracks(GroupListActivity.albumTrackArrayList, GroupListActivity.trackArrayList);
                }
            }
            TrackListAdapter trackListAdapter = new TrackListAdapter(this, trackArrayList);
            trackRecyclerView.setAdapter(trackListAdapter);
        } catch (Exception e) {
            Toast.makeText(TrackListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}