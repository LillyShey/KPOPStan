package com.example.kpopstan;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kpopstan.db.Adapters.AlbumListAdapter;
import com.example.kpopstan.db.Adapters.AwardListAdapter;
import com.example.kpopstan.db.Adapters.MemberListAdapter;
import com.example.kpopstan.db.Tables.Album;
import com.example.kpopstan.db.Tables.Award;
import com.example.kpopstan.db.Tables.Label;
import com.example.kpopstan.db.Tables.Member;

import java.io.InputStream;
import java.util.ArrayList;

public class GroupInfoActivity extends AppCompatActivity {
    public static String album_name;
    private ArrayList<Album> albumArrayList;
    private ArrayList<Member> memberArrayList;
    private ArrayList<Member> exMemberArrayList;
    private ArrayList<Award> awardArrayList;
    private RecyclerView memberRecyclerView;
    private RecyclerView exMemberRecyclerView;
    private RecyclerView albumRecyclerView;
    private RecyclerView awardRecyclerView;
    private MemberListAdapter memberListAdapter;
    private MemberListAdapter exMemberListAdapter;
    private AlbumListAdapter albumListAdapter;
    private AwardListAdapter awardListAdapter;
    private MemberListAdapter.OnStateClickListener memberClickListener;
    private AlbumListAdapter.OnStateClickListener albumClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        getSupportActionBar().hide();
        try {
            ((TextView) findViewById(R.id.textTitle)).setText(GroupListActivity.group_name);
            findViewById(R.id.backToList)
                    .setOnClickListener(view -> onBackPressed());
            memberRecyclerView = findViewById(R.id.memberRecyclerView);
            exMemberRecyclerView = findViewById(R.id.exMemberRecyclerView);
            showMembersList();
            if (exMemberArrayList.size() == 0) {
                findViewById(R.id.textViewTitleFormerMember).setVisibility(View.GONE);
            }
            showLabelInfo();
            albumRecyclerView = findViewById(R.id.albumListView);
            showAlbumsList();
            awardRecyclerView = findViewById(R.id.awardListView);
            showAwardsList();
            if (albumArrayList.size() == 0) {
                findViewById(R.id.albumsTitle).setVisibility(View.GONE);
            }
            if (awardArrayList.size() == 0) {
                findViewById(R.id.awardsTitle).setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Toast.makeText(GroupInfoActivity.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
            Log.e(String.valueOf(GroupInfoActivity.this), e.getMessage());
        }
    }

    private void addMembers() {
        try {
            memberListAdapter = new MemberListAdapter(GroupInfoActivity.this, memberArrayList, memberClickListener);
            memberRecyclerView.setAdapter(memberListAdapter);
            exMemberListAdapter = new MemberListAdapter(GroupInfoActivity.this, exMemberArrayList, memberClickListener);
            exMemberRecyclerView.setAdapter(exMemberListAdapter);
        } catch (Exception e) {
            Toast.makeText(GroupInfoActivity.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
            Log.e(String.valueOf(GroupInfoActivity.this), e.getMessage());
        }
    }

    public void showMembersList() {
        try {
            memberArrayList = new ArrayList<>();
            exMemberArrayList = new ArrayList<>();
            for (int i = 0; i < GroupListActivity.groupArrayList.size(); i++) {
                if (GroupListActivity.groupArrayList.get(i).getGroup_name().equals(GroupListActivity.group_name)) {
                    memberArrayList = GroupListActivity.groupArrayList.get(i).getMembers(GroupListActivity.compositionArrayList, GroupListActivity.memberArrayList);
                }
            }
            for (int i = 0; i < GroupListActivity.groupArrayList.size(); i++) {
                if (GroupListActivity.groupArrayList.get(i).getGroup_name().equals(GroupListActivity.group_name)) {
                    exMemberArrayList = GroupListActivity.groupArrayList.get(i).getExMembers(GroupListActivity.compositionArrayList, GroupListActivity.memberArrayList);
                }
            }
            memberClickListener = new MemberListAdapter.OnStateClickListener() {
                @Override
                public void onStateClick(Member member, int position) {
                    Dialog dialog = new Dialog(GroupInfoActivity.this);
                    dialog.setTitle("Detail member info");
                    dialog.setContentView(R.layout.member_info);
                    (dialog.findViewById(R.id.textView5)).setVisibility(View.GONE);
                    (dialog.findViewById(R.id.textViewLeavingDate)).setVisibility(View.GONE);
                    for (int i = 0; i < exMemberArrayList.size(); i++) {
                        if (member.get_id() == exMemberArrayList.get(i).get_id()) {
                            ((TextView) dialog.findViewById(R.id.textView5)).setVisibility(View.VISIBLE);
                            ((TextView) dialog.findViewById(R.id.textViewLeavingDate)).setVisibility(View.VISIBLE);
                            ((TextView) dialog.findViewById(R.id.textViewLeavingDate))
                                    .setText(member.getLeavingDate(GroupListActivity.compositionArrayList));
                        }
                    }
                    ((ImageView) dialog.findViewById(R.id.imageViewInfoMember_photo))
                            .setImageBitmap(setImage(member.getMember_photo()));
                    ((TextView) dialog.findViewById(R.id.textViewInfoStage_name))
                            .setText(member.getStage_name());
                    ((TextView) dialog.findViewById(R.id.textViewRealName))
                            .setText(member.getMember_last_name());
                    ((TextView) dialog.findViewById(R.id.textViewRealName)).append(" " + member.getMember_first_name());
                    ((TextView) dialog.findViewById(R.id.textViewBirthday))
                            .setText(member.getBirth_date());
                    ((TextView) dialog.findViewById(R.id.textViewBirthday)).append("(" + String.valueOf(member.getAge()) + ")");
                    ((TextView) dialog.findViewById(R.id.textViewPosition))
                            .setText(member.getPosition());
                    dialog.show();
                }
            };
            addMembers();
        } catch (Exception e) {
            Toast.makeText(GroupInfoActivity.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
            Log.e(String.valueOf(GroupInfoActivity.this), e.getMessage());
        }
    }

    public void showLabelInfo() {
        Label label = new Label();
        for (int i = 0; i < GroupListActivity.groupArrayList.size(); i++) {
            if (GroupListActivity.groupArrayList.get(i).getGroup_name().equals(GroupListActivity.group_name)) {
                label = GroupListActivity.groupArrayList.get(i).getLabel(GroupListActivity.labelArrayList);
            }
        }
        ((TextView) findViewById(R.id.textViewInfoLabel_name)).setText(label.getLabel_name());
        ((TextView) findViewById(R.id.textViewInfoDirector_name)).setText(label.getDirector_last_name() + " " + label.getDirector_first_name());
        ((TextView) findViewById(R.id.textViewIncome)).setText(Double.toString(label.getAverage_annual_income()) + " mln $/year");
        ((TextView) findViewById(R.id.textViewAddress)).setText(label.getRegion(GroupListActivity.regionArrayList).getCity(GroupListActivity.cityArrayList).getCity_name() + ", " + label.getRegion(GroupListActivity.regionArrayList).getRegion_name() + ", " + label.getAvenu() + ", " + label.getBuilding());
        ((ImageView) findViewById(R.id.imageViewInfoDirector_photo)).setImageBitmap(setImage(label.getDirector_photo()));
    }

    public void showAlbumsList() {
        try {
            albumArrayList = new ArrayList<>();
            for (int i = 0; i < GroupListActivity.groupArrayList.size(); i++) {
                if (GroupListActivity.groupArrayList.get(i).getGroup_name().equals(GroupListActivity.group_name)) {
                    albumArrayList = GroupListActivity.groupArrayList.get(i).getAlbums(GroupListActivity.albumArrayList);
                }
            }
            albumClickListener = new AlbumListAdapter.OnStateClickListener() {
                @Override
                public void onStateClick(Album album, int position) {
                    GroupInfoActivity.album_name = album.getAlbum_name();
                    Intent intent = new Intent(GroupInfoActivity.this, TrackListActivity.class);
                    startActivity(intent);
                }

                @Override
                public boolean onStateLongClick(Album album, int position, View view) {
                    return false;
                }
            };
            albumListAdapter = new AlbumListAdapter(GroupInfoActivity.this, albumArrayList, albumClickListener);
            albumRecyclerView.setAdapter(albumListAdapter);
        } catch (Exception e) {
            Toast.makeText(GroupInfoActivity.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
            Log.e(String.valueOf(GroupInfoActivity.this), e.getMessage());
        }
    }

    public void showAwardsList() {
        try {
            awardArrayList = new ArrayList<>();
            for (int i = 0; i < GroupListActivity.groupArrayList.size(); i++) {
                if (GroupListActivity.groupArrayList.get(i).getGroup_name().equals(GroupListActivity.group_name)) {
                    awardArrayList = GroupListActivity.groupArrayList.get(i).getAwards(GroupListActivity.awardArrayList);
                }
            }
            awardListAdapter = new AwardListAdapter(GroupInfoActivity.this, awardArrayList);
            awardRecyclerView.setAdapter(awardListAdapter);
        } catch (Exception e) {
            Toast.makeText(GroupInfoActivity.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
            Log.e(String.valueOf(GroupInfoActivity.this), e.getMessage());
        }
    }

    public static Bitmap setImage(String path) {
        InputStream is = GroupInfoActivity.class.getResourceAsStream("/assets/images" + path);
        Bitmap btm = BitmapFactory.decodeStream(is);
        return btm;
    }
}