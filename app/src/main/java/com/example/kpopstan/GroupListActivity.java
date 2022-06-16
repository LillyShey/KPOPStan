package com.example.kpopstan;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kpopstan.db.Adapters.GroupListAdapter;
import com.example.kpopstan.db.DBHelper;
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
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class GroupListActivity extends AppCompatActivity {
    public static String group_name;
    public static ArrayList<City> cityArrayList;
    public static ArrayList<Region> regionArrayList;
    public static ArrayList<Group> groupArrayList;
    public static ArrayList<Label> labelArrayList;
    public static ArrayList<Member> memberArrayList;
    public static ArrayList<Composition> compositionArrayList;
    public static ArrayList<Album> albumArrayList;
    public static ArrayList<Track> trackArrayList;
    public static ArrayList<AlbumTrack> albumTrackArrayList;
    public static ArrayList<Award> awardArrayList;
    private GroupListAdapter groupListAdapter;
    private ImageButton searchButton;
    private GroupListAdapter.OnStateClickListener groupClickListener;
    private RecyclerView groupListView;
    public static DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        groupListView = findViewById(R.id.groupListView);
        dbHelper = new DBHelper(this);
        labelArrayList = dbHelper.getAllLabels();
        groupArrayList = dbHelper.getAllGroups();
        compositionArrayList = dbHelper.getAllCompositions();
        memberArrayList = dbHelper.getAllMembers();
        showGroupList();
        cityArrayList = dbHelper.getAllCites();
        regionArrayList = dbHelper.getAllRegions();
        albumArrayList = dbHelper.getAllAlbums();
        trackArrayList = dbHelper.getAllTracks();
        albumTrackArrayList = dbHelper.getAllAlbumTracks();
        awardArrayList = dbHelper.getAllAward();
        searchButton = findViewById(R.id.imageButtonSearch);
        searchButton.setOnClickListener(view -> {
            ArrayList<Group> searchGroupList = new ArrayList<>();
            EditText editTextSearch = findViewById(R.id.editTextSearch);
            String search = editTextSearch.getText().toString();
            for (int i = 0; i < groupArrayList.size(); i++) {
                if (groupArrayList.get(i).startsOn(search))
                    searchGroupList.add(groupArrayList.get(i));
            }
            if (searchGroupList.size() == 0)
                Toast.makeText(GroupListActivity.this, "No matches found", Toast.LENGTH_LONG).show();
            setGroupListAdapter(searchGroupList);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setGroupListAdapter(ArrayList<Group> list) {
        try {
            groupListAdapter = new GroupListAdapter(GroupListActivity.this, list, groupClickListener);
            groupListView.setAdapter(groupListAdapter);
        } catch (Exception e) {
            Toast.makeText(GroupListActivity.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
            Log.e(String.valueOf(GroupListActivity.this), e.getMessage());
        }
    }

    private void showGroupList() {
        groupClickListener = new GroupListAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Group group, int position) {
                group_name = group.getGroup_name();
                Intent intent = new Intent(GroupListActivity.this, GroupInfoActivity.class);
                startActivity(intent);
            }
        };
        setGroupListAdapter(groupArrayList);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        try {
            int id = item.getItemId();
            ArrayList<Group> sorted_list = groupArrayList;
            Sorting sorting = new Sorting(sorted_list);
            Filter filter = new Filter(groupArrayList);
            switch (id) {
                case R.id.menu_sortingByName: {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.sort_by_string);
                    (dialog.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    (dialog.findViewById(R.id.ok_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (((RadioButton) dialog.findViewById(R.id.NameAZ)).isChecked()) {
                                sorting.NameSort(0, sorted_list.size() - 1, 1);
                            } else if (((RadioButton) dialog.findViewById(R.id.NameZA)).isChecked()) {
                                sorting.NameSort(0, sorted_list.size() - 1, -1);
                            }
                            dialog.cancel();
                            setGroupListAdapter(sorted_list);
                        }
                    });
                    dialog.show();
                    break;
                }
                case R.id.menu_sortingByYear: {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.sort_by_digital);
                    (dialog.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    (dialog.findViewById(R.id.ok_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (((RadioButton) dialog.findViewById(R.id.YearMaxMin)).isChecked()) {
                                sorting.DebutYearSort(0, sorted_list.size() - 1, 1);
                            } else if (((RadioButton) dialog.findViewById(R.id.YearMinMax)).isChecked()) {
                                sorting.DebutYearSort(0, sorted_list.size() - 1, -1);
                            }
                            dialog.cancel();
                            setGroupListAdapter(sorted_list);
                        }
                    });
                    dialog.show();
                    break;
                }
                case R.id.menu_sortingByLabel: {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.sort_by_string);
                    ((Button) dialog.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    ((Button) dialog.findViewById(R.id.ok_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (((RadioButton) dialog.findViewById(R.id.NameAZ)).isChecked()) {
                                sorting.LabelSort(0, sorted_list.size() - 1, 1);
                            } else if (((RadioButton) dialog.findViewById(R.id.NameZA)).isChecked()) {
                                sorting.LabelSort(0, sorted_list.size() - 1, -1);
                            }
                            dialog.cancel();
                            setGroupListAdapter(sorted_list);
                        }
                    });
                    dialog.show();
                    break;
                }
                case R.id.menu_sortingByMembersCount: {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.sort_by_digital);
                    ((Button) dialog.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    ((Button) dialog.findViewById(R.id.ok_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (((RadioButton) dialog.findViewById(R.id.YearMaxMin)).isChecked()) {
                                sorting.MembersCountSort(0, sorted_list.size() - 1, 1);
                            } else if (((RadioButton) dialog.findViewById(R.id.YearMinMax)).isChecked()) {
                                sorting.MembersCountSort(0, sorted_list.size() - 1, -1);
                            }
                            dialog.cancel();
                            setGroupListAdapter(sorted_list);
                        }
                    });
                    dialog.show();
                    break;
                }
                case R.id.menu_filterByYear: {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.filter);
                    LinearLayout list = ((LinearLayout) dialog.findViewById(R.id.list));
                    list.removeAllViews();
                    TreeSet<Integer> years = new TreeSet<>();
                    for (int i = 0; i < groupArrayList.size(); i++) {
                        years.add(groupArrayList.get(i).getCreate_year());
                    }
                    Iterator iterator = years.iterator();
                    while (iterator.hasNext()) {
                        CheckBox checkBox = new CheckBox(GroupListActivity.this);
                        checkBox.setText(iterator.next().toString());
                        list.addView(checkBox);
                    }
                    ((Button) dialog.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    ((Button) dialog.findViewById(R.id.ok_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ArrayList<Integer> checkedYears = new ArrayList<>();
                            for (int i = 0; i < list.getChildCount(); i++) {
                                CheckBox checkBox = (CheckBox) list.getChildAt(i);
                                if (checkBox.isChecked()) {
                                    checkedYears.add(Integer.valueOf(checkBox.getText().toString()));
                                }
                            }
                            dialog.cancel();
                            setGroupListAdapter(filter.YearFilter(checkedYears));
                        }
                    });
                    dialog.show();
                    break;
                }
                case R.id.menu_filterByLabel: {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.filter);
                    LinearLayout list = ((LinearLayout) dialog.findViewById(R.id.list));
                    list.removeAllViews();
                    for (int i = 0; i < labelArrayList.size(); i++) {
                        CheckBox checkBox = new CheckBox(GroupListActivity.this);
                        checkBox.setText(labelArrayList.get(i).getLabel_name());
                        list.addView(checkBox);
                    }
                    ((Button) dialog.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    ((Button) dialog.findViewById(R.id.ok_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ArrayList<String> checkedLabels = new ArrayList<>();
                            for (int i = 0; i < list.getChildCount(); i++) {
                                CheckBox checkBox = (CheckBox) list.getChildAt(i);
                                if (checkBox.isChecked()) {
                                    checkedLabels.add(checkBox.getText().toString());
                                }
                            }
                            dialog.cancel();
                            setGroupListAdapter(filter.LabelFilter(checkedLabels));
                        }
                    });
                    dialog.show();
                    break;
                }
                case R.id.menu_filterByMembersCount: {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.filter);
                    LinearLayout list = ((LinearLayout) dialog.findViewById(R.id.list));
                    list.removeAllViews();
                    TreeSet<Integer> count = new TreeSet<>();
                    for (int i = 0; i < groupArrayList.size(); i++) {
                        count.add(groupArrayList.get(i).getMembers_count(compositionArrayList));
                    }
                    Iterator iterator = count.iterator();
                    while (iterator.hasNext()) {
                        CheckBox checkBox = new CheckBox(GroupListActivity.this);
                        checkBox.setText(iterator.next().toString());
                        list.addView(checkBox);
                    }
                    ((Button) dialog.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    ((Button) dialog.findViewById(R.id.ok_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ArrayList<Integer> checkedCount = new ArrayList<>();
                            for (int i = 0; i < list.getChildCount(); i++) {
                                CheckBox checkBox = (CheckBox) list.getChildAt(i);
                                if (checkBox.isChecked()) {
                                    checkedCount.add(Integer.valueOf(checkBox.getText().toString()));
                                }
                            }
                            dialog.cancel();
                            setGroupListAdapter(filter.MembersCountFilter(checkedCount));
                        }
                    });
                    dialog.show();
                    break;
                }
                case R.id.menu_filterByGender: {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.filter);
                    LinearLayout list = ((LinearLayout) dialog.findViewById(R.id.list));
                    list.removeAllViews();
                    TreeSet<String> genders = new TreeSet<>();
                    for (int i = 0; i < groupArrayList.size(); i++) {
                        genders.add(groupArrayList.get(i).getGender());
                    }
                    Iterator iterator = genders.iterator();
                    while (iterator.hasNext()) {
                        CheckBox checkBox = new CheckBox(GroupListActivity.this);
                        checkBox.setText(iterator.next().toString());
                        list.addView(checkBox);
                    }
                    ((Button) dialog.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    ((Button) dialog.findViewById(R.id.ok_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ArrayList<String> checkedGenders = new ArrayList<>();
                            for (int i = 0; i < list.getChildCount(); i++) {
                                CheckBox checkBox = (CheckBox) list.getChildAt(i);
                                if (checkBox.isChecked()) {
                                    checkedGenders.add(checkBox.getText().toString());
                                }
                            }
                            dialog.cancel();
                            setGroupListAdapter(filter.GenderFilter(checkedGenders));
                        }
                    });
                    dialog.show();
                    break;
                }
                case R.id.menu_goToAdminPage:{
                    if(LoginActivity.user.getRole().equals("admin")){
                        Intent intent = new Intent(GroupListActivity.this, AdminActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(GroupListActivity.this, "You do not have sufficient privileges to perform this action", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                case R.id.menu_Logout: {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(GroupListActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        } catch (
                Exception ex) {
            Toast.makeText(GroupListActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}