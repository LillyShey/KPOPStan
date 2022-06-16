package com.example.kpopstan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Calendar;
import java.util.Locale;

public class AdminActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private Button exit;
    private Button goToMain;
    private Button addGroup;
    private Button editGroup;
    private Button addLabel;
    private Button editLabel;
    private Button addMember;
    private Button editMember;
    private Button addAlbum;
    private Button editAlbum;
    private Button addAward;
    private Button editAward;
    private Button addTrack;
    private Button editTrack;
    private ArrayList<City> cityArrayList;
    private ArrayList<Region> regionArrayList;
    private ArrayList<Group> groupArrayList;
    private ArrayList<Label> labelArrayList;
    private ArrayList<Member> memberArrayList;
    private ArrayList<Composition> compositionArrayList;
    private ArrayList<Album> albumArrayList;
    private ArrayList<Track> trackArrayList;
    private ArrayList<AlbumTrack> albumTrackArrayList;
    private ArrayList<Award> awardArrayList;
    private Group group;
    private Label label;
    private Member member;
    private Composition composition;
    private Album album;
    private Award award;
    private Track track;
    private AlbumTrack albumTrack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        dbHelper = new DBHelper(this);
        labelArrayList = dbHelper.getAllLabels();
        groupArrayList = dbHelper.getAllGroups();
        compositionArrayList = dbHelper.getAllCompositions();
        memberArrayList = dbHelper.getAllMembers();
        cityArrayList = dbHelper.getAllCites();
        regionArrayList = dbHelper.getAllRegions();
        albumArrayList = dbHelper.getAllAlbums();
        trackArrayList = dbHelper.getAllTracks();
        albumTrackArrayList = dbHelper.getAllAlbumTracks();
        awardArrayList = dbHelper.getAllAward();
        exit = findViewById(R.id.exit_button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        goToMain = findViewById(R.id.go_to_main_button);
        goToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, GroupListActivity.class);
                startActivity(intent);
            }
        });
        addGroup = findViewById(R.id.add_group_button);
        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGroup();
            }
        });
        editGroup = findViewById(R.id.edit_group_button);
        editGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editGroup();
            }
        });
        addLabel = findViewById(R.id.add_label_button);
        addLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLabel();
            }
        });
        editLabel = findViewById(R.id.edit_label_button);
        editLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editLabel();
            }
        });
        addMember = findViewById(R.id.add_member_button);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMember();
            }
        });
        editMember = findViewById(R.id.edit_member_button);
        editMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMember();
            }
        });
        addAlbum = findViewById(R.id.add_album_button);
        addAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlbum();
            }
        });
        editAlbum = findViewById(R.id.edit_album_button);
        editAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAlbum();
            }
        });
        addAward = findViewById(R.id.add_award_button);
        addAward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAward();
            }
        });
        editAward = findViewById(R.id.edit_award_button);
        editAward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAward();
            }
        });
        addTrack = findViewById(R.id.add_track_button);
        addTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTrack();
            }
        });
        editTrack = findViewById(R.id.edit_track_button);
        editTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTrack();
            }
        });
    }

    private void addGroup() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_group_info);
            dialog.show();
            dialog.findViewById(R.id.group_spinner).setVisibility(View.GONE);
            dialog.findViewById(R.id.group_title).setVisibility(View.GONE);
            dialog.findViewById(R.id.delete_button).setVisibility(View.GONE);
            Spinner spinner = dialog.findViewById(R.id.label_spinner);
            String[] labels = new String[labelArrayList.size()];
            int i = 0;
            for (Label lab : labelArrayList) {
                labels[i++] = lab.getLabel_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, labels);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                Group group_ = new Group();
                group_.set_id(groupArrayList.get(groupArrayList.size() - 1).get_id() + 1);
                group_.setGroup_name(((TextView) dialog.findViewById(R.id.editGroup_name)).getText().toString());
                group_.setCreate_year(Integer.valueOf(((TextView) dialog.findViewById(R.id.editCreate_year)).getText().toString()));
                group_.setFandom_name(((TextView) dialog.findViewById(R.id.editFandom)).getText().toString());
                group_.setLabel(((Spinner) dialog.findViewById(R.id.label_spinner)).getSelectedItem().toString(), labelArrayList);
                group_.setGender(((TextView) dialog.findViewById(R.id.editGender)).getText().toString());
                dbHelper.updateGroup(group_);
                groupArrayList = dbHelper.getAllGroups();
                Toast.makeText(AdminActivity.this, "Group successfully added", Toast.LENGTH_LONG).show();
                dialog.cancel();
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t add this group\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void editGroup() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_group_info);
            dialog.show();

            Spinner group_spinner = dialog.findViewById(R.id.group_spinner);
            Spinner label_spinner = dialog.findViewById(R.id.label_spinner);
            String[] groups = new String[groupArrayList.size()];
            int i = 0;
            for (Group grp : groupArrayList) {
                groups[i++] = grp.getGroup_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            group_spinner.setAdapter(adapter);
            group = groupArrayList.get(group_spinner.getSelectedItemPosition());
            group_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    group = groupArrayList.get(i);
                    ((TextView) dialog.findViewById(R.id.editGroup_name))
                            .setText(group.getGroup_name());
                    ((TextView) dialog.findViewById(R.id.editCreate_year))
                            .setText(String.valueOf(group.getCreate_year()));
                    ((TextView) dialog.findViewById(R.id.editFandom))
                            .setText(group.getFandom_name());
                    ((TextView) dialog.findViewById(R.id.editGender))
                            .setText(group.getGender());
                    label_spinner.setSelection(group.getLabel_id() - 1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            String[] labels = new String[labelArrayList.size()];
            i = 0;
            for (Label lab : labelArrayList) {
                labels[i++] = lab.getLabel_name();
            }
            adapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_spinner_item, labels);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            label_spinner.setAdapter(adapter);
            label_spinner.setSelection(group.getLabel_id() - 1);
            dialog.findViewById(R.id.change_button).setOnClickListener(view2 -> {
                Group group_ = new Group();
                group_.set_id(groupArrayList.get(group_spinner.getSelectedItemPosition()).get_id());
                group_.setGroup_photo(groupArrayList.get(group_spinner.getSelectedItemPosition()).getGroup_photo());
                group_.setGroup_name(((TextView) dialog.findViewById(R.id.editGroup_name)).getText().toString());
                group_.setCreate_year(Integer.valueOf(((TextView) dialog.findViewById(R.id.editCreate_year)).getText().toString()));
                group_.setFandom_name(((TextView) dialog.findViewById(R.id.editFandom)).getText().toString());
                group_.setLabel(label_spinner.getSelectedItem().toString(), labelArrayList);
                group_.setGender(((TextView) dialog.findViewById(R.id.editGender)).getText().toString());
                dbHelper.updateGroup(group_);
                groupArrayList = dbHelper.getAllGroups();
                Toast.makeText(AdminActivity.this, "Group successfully edited", Toast.LENGTH_LONG).show();
                dialog.cancel();
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view3 -> dialog.cancel());
            dialog.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog del_dialog = new Dialog(AdminActivity.this);
                    del_dialog.setContentView(R.layout.delete_layout);
                    del_dialog.show();
                    del_dialog.findViewById(R.id.yes_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteAllGroupInfo(group);
                            del_dialog.cancel();
                            dialog.cancel();
                            Toast.makeText(AdminActivity.this, "Group successfully deleted", Toast.LENGTH_LONG).show();
                        }
                    });
                    del_dialog.findViewById(R.id.no_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            del_dialog.cancel();
                        }
                    });
                    dialog.cancel();
                }
            });
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t edit this group\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void addLabel() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_label_info);
            dialog.show();
            dialog.findViewById(R.id.Llabel_spinner).setVisibility(View.GONE);
            dialog.findViewById(R.id.Llabel_title).setVisibility(View.GONE);
            Spinner region_spinner = dialog.findViewById(R.id.Lregion_spiner);
            String[] regions = new String[regionArrayList.size()];
            int i = 0;
            for (Region reg : regionArrayList) {
                regions[i++] = reg.getRegion_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, regions);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            region_spinner.setAdapter(adapter);
            dialog.findViewById(R.id.delete_button).setVisibility(View.GONE);
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                Label label_ = new Label();
                label_.set_id(labelArrayList.get(labelArrayList.size() - 1).get_id() + 1);
                label_.setLabel_name(((TextView) dialog.findViewById(R.id.editLabel_name)).getText().toString());
                label_.setAverage_annual_income(Double.valueOf(((TextView) dialog.findViewById(R.id.editIncome)).getText().toString()));
                label_.setCity_id(1);
                label_.setRegion_id(region_spinner.getSelectedItemPosition() + 1);
                label_.setAvenu(((TextView) dialog.findViewById(R.id.editAvenue)).getText().toString());
                label_.setBuilding(((TextView) dialog.findViewById(R.id.editBuilding)).getText().toString());
                label_.setDirector_last_name(((TextView) dialog.findViewById(R.id.editDirectorLname)).getText().toString());
                label_.setDirector_first_name(((TextView) dialog.findViewById(R.id.editDirectorFname)).getText().toString());
                dbHelper.updateLabel(label_);
                labelArrayList = dbHelper.getAllLabels();
                Toast.makeText(AdminActivity.this, "Label successfully added", Toast.LENGTH_LONG).show();
                dialog.cancel();
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t add this label\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void editLabel() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_label_info);
            dialog.show();
            Spinner label_spinner = dialog.findViewById(R.id.Llabel_spinner);
            String[] labels = new String[labelArrayList.size()];
            int i = 0;
            for (Label lab : labelArrayList) {
                labels[i++] = lab.getLabel_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, labels);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            label_spinner.setAdapter(adapter);
            Spinner region_spinner = dialog.findViewById(R.id.Lregion_spiner);
            String[] regions = new String[regionArrayList.size()];
            i = 0;
            for (Region reg : regionArrayList) {
                regions[i++] = reg.getRegion_name();
            }
            adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, regions);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            region_spinner.setAdapter(adapter);
            label = labelArrayList.get(label_spinner.getSelectedItemPosition());
            label_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    label = labelArrayList.get(i);
                    ((TextView) dialog.findViewById(R.id.editLabel_name))
                            .setText(label.getLabel_name());
                    ((TextView) dialog.findViewById(R.id.editIncome))
                            .setText(String.valueOf(label.getAverage_annual_income()));
                    ((TextView) dialog.findViewById(R.id.editAvenue))
                            .setText(label.getAvenu());
                    ((TextView) dialog.findViewById(R.id.editBuilding))
                            .setText(label.getBuilding());
                    ((TextView) dialog.findViewById(R.id.editDirectorLname))
                            .setText(label.getDirector_last_name());
                    ((TextView) dialog.findViewById(R.id.editDirectorFname))
                            .setText(label.getDirector_first_name());
                    region_spinner.setSelection(label.getRegion_id() - 1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                Label label_ = new Label();
                label_.set_id(labelArrayList.get(label_spinner.getSelectedItemPosition()).get_id());
                label_.setLabel_name(((TextView) dialog.findViewById(R.id.editLabel_name)).getText().toString());
                label_.setAverage_annual_income(Double.valueOf(((TextView) dialog.findViewById(R.id.editIncome)).getText().toString()));
                label_.setCity_id(1);
                label_.setRegion_id(region_spinner.getSelectedItemPosition() + 1);
                label_.setAvenu(((TextView) dialog.findViewById(R.id.editAvenue)).getText().toString());
                label_.setBuilding(((TextView) dialog.findViewById(R.id.editBuilding)).getText().toString());
                label_.setDirector_last_name(((TextView) dialog.findViewById(R.id.editDirectorLname)).getText().toString());
                label_.setDirector_first_name(((TextView) dialog.findViewById(R.id.editDirectorFname)).getText().toString());
                dbHelper.updateLabel(label_);
                labelArrayList = dbHelper.getAllLabels();
                Toast.makeText(AdminActivity.this, "Label successfully edited", Toast.LENGTH_LONG).show();
                dialog.cancel();
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
            dialog.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog del_dialog = new Dialog(AdminActivity.this);
                    del_dialog.setContentView(R.layout.delete_layout);
                    ((TextView) del_dialog.findViewById(R.id.textViewQuestion)).setText("Delete this label?");
                    del_dialog.show();
                    del_dialog.findViewById(R.id.yes_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (Group group : groupArrayList) {
                                if (group.getLabel_id() == label.get_id()) {
                                    deleteAllGroupInfo(group);
                                }
                            }
                            dbHelper.deleteLabel(label);
                            labelArrayList = dbHelper.getAllLabels();
                            del_dialog.cancel();
                            dialog.cancel();
                            Toast.makeText(AdminActivity.this, "Label successfully deleted", Toast.LENGTH_LONG).show();
                        }
                    });
                    del_dialog.findViewById(R.id.no_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            del_dialog.cancel();
                        }
                    });
                    dialog.cancel();
                }
            });
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t edit this label\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    View.OnClickListener datePick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Locale.setDefault(Locale.ENGLISH);
            Calendar calendar = Calendar.getInstance();
            int year, month, day;
            if (((EditText) view).getText().toString().equals("")) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
            } else {
                String today;
                today = ((EditText) view).getText().toString();
                year = Integer.valueOf(today.substring(0, 4));
                month = Integer.valueOf(today.substring(5, 7));
                day = Integer.valueOf(today.substring(9, 10));

            }
            DatePickerDialog datePickerDialog = new DatePickerDialog(AdminActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    if (i1 < 10 && i2 < 10) {
                        ((TextView) view).setText(i + "-0" + i1 + "-0" + i2);
                    } else if (i1 < 10) {
                        ((TextView) view).setText(i + "-0" + i1 + "-" + i2);
                    } else if (i2 < 10) {
                        ((TextView) view).setText(i + "-" + i1 + "-0" + i2);
                    } else {
                        ((TextView) view).setText(i + "-" + i1 + "-" + i2);
                    }
                }
            }, year, month, day);
            datePickerDialog.show();
        }
    };

    private void addMember() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_member_info);
            dialog.show();
            dialog.findViewById(R.id.Gmember_title).setVisibility(View.GONE);
            dialog.findViewById(R.id.member_spinner).setVisibility(View.GONE);
            dialog.findViewById(R.id.group_title).setVisibility(View.GONE);
            dialog.findViewById(R.id.group_spinner).setVisibility(View.GONE);
            Spinner group_choose_spinner = dialog.findViewById(R.id.groupChoose_spinner);
            String[] groups = new String[groupArrayList.size()];
            int i = 0;
            for (Group grp : groupArrayList) {
                groups[i++] = grp.getGroup_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            group_choose_spinner.setAdapter(adapter);
            group = groupArrayList.get(group_choose_spinner.getSelectedItemPosition());
            ((TextView) dialog.findViewById(R.id.editBirthday)).setOnClickListener(datePick);
            ((TextView) dialog.findViewById(R.id.editAccessing)).setOnClickListener(datePick);
            dialog.findViewById(R.id.delete_button).setVisibility(View.GONE);
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                try {
                    Member member_ = new Member();
                    member_.set_id(memberArrayList.get(memberArrayList.size() - 1).get_id() + 1);
                    member_.setStage_name(((TextView) dialog.findViewById(R.id.editStage_name)).getText().toString());
                    member_.setMember_last_name(((TextView) dialog.findViewById(R.id.editLast_name)).getText().toString());
                    member_.setMember_first_name(((TextView) dialog.findViewById(R.id.editFirst_name)).getText().toString());
                    member_.setBirth_date(((TextView) dialog.findViewById(R.id.editBirthday)).getText().toString());
                    if(member_.getBirth_date().isEmpty()){
                        member_.setBirth_date("0001-01-01");
                    }
                    member_.setPosition(((TextView) dialog.findViewById(R.id.editPosition)).getText().toString());
                    dbHelper.updateMember(member_);
                    Composition composition_ = new Composition();
                    composition_.set_id(compositionArrayList.get(compositionArrayList.size() - 1).get_id() + 1);
                    composition_.setMember_id(member_.get_id());
                    composition_.setGroup_id(group_choose_spinner.getSelectedItemPosition()+1);
                    composition_.setLeaving_date(((TextView) dialog.findViewById(R.id.editLeaving)).getText().toString());
                    composition_.setAccession_date(((TextView) dialog.findViewById(R.id.editAccessing)).getText().toString());
                    dbHelper.updateComposition(composition_);
                    memberArrayList = dbHelper.getAllMembers();
                    compositionArrayList = dbHelper.getAllCompositions();
                    Toast.makeText(AdminActivity.this, "Member successfully added", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                } catch (Exception e) {
                    Toast.makeText(AdminActivity.this, "We can`t add this member\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.println(Log.ERROR, "ERROR", e.getMessage());
                }
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t add this member\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void editMember() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_member_info);
            dialog.show();
            Spinner group_spinner = dialog.findViewById(R.id.group_spinner);
            Spinner member_spinner = dialog.findViewById(R.id.member_spinner);
            Spinner group_choose_spinner= dialog.findViewById(R.id.groupChoose_spinner);
            String[] groups = new String[groupArrayList.size()];
            int i = 0;
            for (Group grp : groupArrayList) {
                groups[i++] = grp.getGroup_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            group_spinner.setAdapter(adapter);
            group_choose_spinner.setAdapter(adapter);
            group = groupArrayList.get(group_spinner.getSelectedItemPosition());
            group_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    group = groupArrayList.get(i);
                    ArrayList<Member> memberArrayList1 = group.getMembers(compositionArrayList, memberArrayList);
                    String[] members = new String[memberArrayList1.size()];
                    i = 0;
                    for (Member mem : memberArrayList1) {
                        members[i++] = mem.getStage_name();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, members);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    member_spinner.setAdapter(adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            member_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    member = (group.getMembers(compositionArrayList, memberArrayList)).get(i);
                    ((TextView) dialog.findViewById(R.id.editStage_name)).setText(member.getStage_name());
                    ((TextView) dialog.findViewById(R.id.editLast_name)).setText(member.getMember_last_name());
                    ((TextView) dialog.findViewById(R.id.editFirst_name)).setText(member.getMember_first_name());
                    ((TextView) dialog.findViewById(R.id.editBirthday)).setText(member.getBirth_date());
                    ((TextView) dialog.findViewById(R.id.editPosition)).setText(member.getPosition());
                    ((TextView) dialog.findViewById(R.id.editAccessing)).setText(member.getAccessingDate(compositionArrayList));
                    ((TextView) dialog.findViewById(R.id.editLeaving)).setText(member.getLeavingDate(compositionArrayList));
                    group_choose_spinner.setSelection(group_spinner.getSelectedItemPosition());
                    for(Composition composition_: compositionArrayList){
                        if(member.get_id()==composition_.getMember_id() && group.get_id()==composition_.getGroup_id()){
                            composition=composition_;
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            ((TextView) dialog.findViewById(R.id.editBirthday)).setOnClickListener(datePick);
            ((TextView) dialog.findViewById(R.id.editAccessing)).setOnClickListener(datePick);
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                try {
                    Member member_ = new Member();
                    member_.set_id(member.get_id());
                    member_.setStage_name(((TextView) dialog.findViewById(R.id.editStage_name)).getText().toString());
                    member_.setMember_last_name(((TextView) dialog.findViewById(R.id.editLast_name)).getText().toString());
                    member_.setMember_first_name(((TextView) dialog.findViewById(R.id.editFirst_name)).getText().toString());
                    member_.setBirth_date(((TextView) dialog.findViewById(R.id.editBirthday)).getText().toString());
                    member_.setPosition(((TextView) dialog.findViewById(R.id.editPosition)).getText().toString());
                    dbHelper.updateMember(member_);
                    Composition composition_ = new Composition();
                    composition_.set_id(composition.get_id());
                    composition_.setMember_id(member_.get_id());
                    composition_.setGroup_id(group_choose_spinner.getSelectedItemPosition()+1);
                    composition_.setLeaving_date(((TextView) dialog.findViewById(R.id.editLeaving)).getText().toString());
                    composition_.setAccession_date(((TextView) dialog.findViewById(R.id.editAccessing)).getText().toString());
                    dbHelper.updateComposition(composition_);
                    memberArrayList = dbHelper.getAllMembers();
                    compositionArrayList = dbHelper.getAllCompositions();
                    Toast.makeText(AdminActivity.this, "Member successfully edited", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                } catch (Exception e) {
                    Toast.makeText(AdminActivity.this, "We can`t edit this member\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.println(Log.ERROR, "ERROR", e.getMessage());
                }
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
            dialog.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog del_dialog = new Dialog(AdminActivity.this);
                    del_dialog.setContentView(R.layout.delete_layout);
                    ((TextView) del_dialog.findViewById(R.id.textViewQuestion)).setText("Delete this member?");
                    del_dialog.show();
                    del_dialog.findViewById(R.id.yes_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (Composition composition : compositionArrayList) {
                                if (composition.getMember_id() == member.get_id()) {
                                    dbHelper.deleteComposition(composition);
                                }
                            }
                            dbHelper.deleteMember(member);
                            memberArrayList = dbHelper.getAllMembers();
                            compositionArrayList = dbHelper.getAllCompositions();
                            del_dialog.cancel();
                            dialog.cancel();
                            Toast.makeText(AdminActivity.this, "Member successfully deleted", Toast.LENGTH_LONG).show();
                        }
                    });
                    del_dialog.findViewById(R.id.no_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            del_dialog.cancel();
                        }
                    });
                    dialog.cancel();
                }
            });
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t edit this member\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void addAlbum() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_album_info);
            dialog.show();
            dialog.findViewById(R.id.GAlbum_title).setVisibility(View.GONE);
            dialog.findViewById(R.id.album_spinner).setVisibility(View.GONE);
            dialog.findViewById(R.id.group_title).setVisibility(View.GONE);
            dialog.findViewById(R.id.group_spinner).setVisibility(View.GONE);
            Spinner choose_group_spinner = dialog.findViewById(R.id.groupChoose_spinner);
            String[] groups = new String[groupArrayList.size()];
            int i = 0;
            for (Group grp : groupArrayList) {
                groups[i++] = grp.getGroup_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            choose_group_spinner.setAdapter(adapter);
            dialog.findViewById(R.id.delete_button).setVisibility(View.GONE);
            ((TextView) dialog.findViewById(R.id.editRelease)).setOnClickListener(datePick);
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                try {
                    Album album_ = new Album();
                    album_.set_id(albumArrayList.get(albumArrayList.size() - 1).get_id() + 1);
                    album_.setAlbum_name(((TextView) dialog.findViewById(R.id.editAlbum_name)).getText().toString());
                    album_.setRelease_date(((TextView) dialog.findViewById(R.id.editRelease)).getText().toString());
                    album_.setSales_number(Integer.valueOf(((TextView) dialog.findViewById(R.id.editSales)).getText().toString()));
                    album_.setMv(((TextView) dialog.findViewById(R.id.edit_mv)).getText().toString());
                    album_.setGroup_id(groupArrayList.get(choose_group_spinner.getSelectedItemPosition()).get_id());
                    dbHelper.updateAlbum(album_);
                    albumArrayList = dbHelper.getAllAlbums();
                    Toast.makeText(AdminActivity.this, "Album successfully added", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                } catch (Exception e) {
                    Toast.makeText(AdminActivity.this, "We can`t add this album\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.println(Log.ERROR, "ERROR", e.getMessage());
                }
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t add this album\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void editAlbum() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_album_info);
            dialog.show();
            Spinner group_spinner = dialog.findViewById(R.id.group_spinner);
            Spinner choose_group_spinner = dialog.findViewById(R.id.groupChoose_spinner);
            Spinner album_spinner = dialog.findViewById(R.id.album_spinner);
            String[] groups = new String[groupArrayList.size()];
            int i = 0;
            for (Group grp : groupArrayList) {
                groups[i++] = grp.getGroup_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            group_spinner.setAdapter(adapter);
            choose_group_spinner.setAdapter(adapter);
            group = groupArrayList.get(group_spinner.getSelectedItemPosition());
            group_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    group = groupArrayList.get(i);
                    ArrayList<Album> albumArrayList1 = group.getAlbums(albumArrayList);
                    String[] albums = new String[group.getAlbums(albumArrayList).size()];
                    i = 0;
                    for (Album alb : group.getAlbums(albumArrayList)) {
                        albums[i++] = alb.getAlbum_name();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, albums);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    album_spinner.setAdapter(adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            album_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    album = (group.getAlbums(albumArrayList)).get(i);
                    ((TextView) dialog.findViewById(R.id.editAlbum_name)).setText(album.getAlbum_name());
                    ((TextView) dialog.findViewById(R.id.editRelease)).setText(album.getRelease_date());
                    ((TextView) dialog.findViewById(R.id.editSales)).setText(String.valueOf(album.getSales_number()));
                    ((TextView) dialog.findViewById(R.id.edit_mv)).setText(album.getMv());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            ((TextView) dialog.findViewById(R.id.editRelease)).setOnClickListener(datePick);
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                try {
                    Album album_ = new Album();
                    album_.set_id(album.get_id());
                    album_.setAlbum_name(((TextView) dialog.findViewById(R.id.editAlbum_name)).getText().toString());
                    album_.setRelease_date(((TextView) dialog.findViewById(R.id.editRelease)).getText().toString());
                    album_.setSales_number(Integer.valueOf(((TextView) dialog.findViewById(R.id.editSales)).getText().toString()));
                    album_.setMv(((TextView) dialog.findViewById(R.id.edit_mv)).getText().toString());
                    album_.setGroup_id(groupArrayList.get(choose_group_spinner.getSelectedItemPosition()).get_id());
                    dbHelper.updateAlbum(album_);
                    albumArrayList = dbHelper.getAllAlbums();
                    Toast.makeText(AdminActivity.this, "Album successfully edited", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                } catch (Exception e) {
                    Toast.makeText(AdminActivity.this, "We can`t edit this album\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.println(Log.ERROR, "ERROR", e.getMessage());
                }
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
            dialog.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog del_dialog = new Dialog(AdminActivity.this);
                    del_dialog.setContentView(R.layout.delete_layout);
                    ((TextView) del_dialog.findViewById(R.id.textViewQuestion)).setText("Delete this album?");
                    del_dialog.show();
                    del_dialog.findViewById(R.id.yes_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteAllAlbumInfo(album);
                            del_dialog.cancel();
                            dialog.cancel();
                            Toast.makeText(AdminActivity.this, "Album successfully deleted", Toast.LENGTH_LONG).show();
                        }
                    });
                    del_dialog.findViewById(R.id.no_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            del_dialog.cancel();
                        }
                    });
                    dialog.cancel();
                }
            });
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t edit this album\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void addAward() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_award_info);
            dialog.show();
            dialog.findViewById(R.id.GAward_title).setVisibility(View.GONE);
            dialog.findViewById(R.id.award_spinner).setVisibility(View.GONE);
            dialog.findViewById(R.id.group_title).setVisibility(View.GONE);
            dialog.findViewById(R.id.group_spinner).setVisibility(View.GONE);
            Spinner choose_group_spinner = dialog.findViewById(R.id.groupChoose_spinner);
            String[] groups = new String[groupArrayList.size()];
            int i = 0;
            for (Group grp : groupArrayList) {
                groups[i++] = grp.getGroup_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            choose_group_spinner.setAdapter(adapter);
            group = groupArrayList.get(choose_group_spinner.getSelectedItemPosition());
            dialog.findViewById(R.id.delete_button).setVisibility(View.GONE);
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                try {
                    Award award_ = new Award();
                    award_.set_id(awardArrayList.get(awardArrayList.size() - 1).get_id() + 1);
                    award_.setAward(((TextView) dialog.findViewById(R.id.editAward_name)).getText().toString());
                    award_.setDaesang_name(((TextView) dialog.findViewById(R.id.editDaesang)).getText().toString());
                    award_.setReceipt_year(Integer.valueOf(((TextView) dialog.findViewById(R.id.editReceipt)).getText().toString()));
                    award_.setGroup_id(groupArrayList.get(choose_group_spinner.getSelectedItemPosition()).get_id());
                    dbHelper.updateAward(award_);
                    awardArrayList = dbHelper.getAllAward();
                    Toast.makeText(AdminActivity.this, "Award successfully added", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                } catch (Exception e) {
                    Toast.makeText(AdminActivity.this, "We can`t add this award\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.println(Log.ERROR, "ERROR", e.getMessage());
                }
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t add this album\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void editAward() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_award_info);
            dialog.show();
            Spinner group_spinner = dialog.findViewById(R.id.group_spinner);
            Spinner choose_group_spinner = dialog.findViewById(R.id.groupChoose_spinner);
            Spinner award_spinner = dialog.findViewById(R.id.award_spinner);
            String[] groups = new String[groupArrayList.size()];
            int i = 0;
            for (Group grp : groupArrayList) {
                groups[i++] = grp.getGroup_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            group_spinner.setAdapter(adapter);
            choose_group_spinner.setAdapter(adapter);
            group = groupArrayList.get(group_spinner.getSelectedItemPosition());

            String[] awards = new String[group.getAwards(awardArrayList).size()];
            i = 0;
            for (Award awd : group.getAwards(awardArrayList)) {
                awards[i++] = awd.getAward() + "(" + awd.getDaesang_name() + ")";
            }
            adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, awards);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            award_spinner.setAdapter(adapter);
            group_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        group = groupArrayList.get(i);
                        String[] awards = new String[group.getAwards(awardArrayList).size()];
                        i = 0;
                        for (Award awd : group.getAwards(awardArrayList)) {
                            awards[i++] = awd.getAward() + "(" + awd.getDaesang_name() + ")";
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, awards);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        award_spinner.setAdapter(adapter);
                    } catch (Exception e) {
                        Toast.makeText(AdminActivity.this, "We can`t add this award\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.println(Log.ERROR, "ERROR", e.getMessage());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            award_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    award = (group.getAwards(awardArrayList)).get(i);
                    ((TextView) dialog.findViewById(R.id.editAward_name)).setText(award.getAward());
                    ((TextView) dialog.findViewById(R.id.editDaesang)).setText(award.getDaesang_name());
                    ((TextView) dialog.findViewById(R.id.editReceipt)).setText(String.valueOf(award.getReceipt_year()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                try {
                    Award award_ = new Award();
                    award_.set_id(award.get_id());
                    award_.setAward(((TextView) dialog.findViewById(R.id.editAward_name)).getText().toString());
                    award_.setDaesang_name(((TextView) dialog.findViewById(R.id.editDaesang)).getText().toString());
                    award_.setReceipt_year(Integer.valueOf(((TextView) dialog.findViewById(R.id.editReceipt)).getText().toString()));
                    award_.setGroup_id(groupArrayList.get(choose_group_spinner.getSelectedItemPosition()).get_id());
                    dbHelper.updateAward(award_);
                    awardArrayList = dbHelper.getAllAward();
                    Toast.makeText(AdminActivity.this, "Award successfully edited", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                } catch (Exception e) {
                    Toast.makeText(AdminActivity.this, "We can`t edit this award\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.println(Log.ERROR, "ERROR", e.getMessage());
                }
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
            dialog.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog del_dialog = new Dialog(AdminActivity.this);
                    del_dialog.setContentView(R.layout.delete_layout);
                    ((TextView) del_dialog.findViewById(R.id.textViewQuestion)).setText("Delete this award?");
                    del_dialog.show();
                    del_dialog.findViewById(R.id.yes_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dbHelper.deleteAward(award);
                            awardArrayList = dbHelper.getAllAward();
                            del_dialog.cancel();
                            dialog.cancel();
                            Toast.makeText(AdminActivity.this, "Award successfully deleted", Toast.LENGTH_LONG).show();
                        }
                    });
                    del_dialog.findViewById(R.id.no_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            del_dialog.cancel();
                        }
                    });
                    dialog.cancel();
                }
            });
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t edit this award\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void addTrack() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_track_info);
            dialog.show();
            dialog.findViewById(R.id.GAlbum_title).setVisibility(View.GONE);
            dialog.findViewById(R.id.album_spinner).setVisibility(View.GONE);;
            dialog.findViewById(R.id.GTrack_title).setVisibility(View.GONE);
            dialog.findViewById(R.id.track_spinner).setVisibility(View.GONE);
            Spinner group_spinner = dialog.findViewById(R.id.group_spinner);
            Spinner choose_album_spinner = dialog.findViewById(R.id.albumChoose_spinner);
            String[] groups = new String[groupArrayList.size()];
            int i = 0;
            for (Group grp : groupArrayList) {
                groups[i++] = grp.getGroup_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            group_spinner.setAdapter(adapter);
            group = groupArrayList.get(group_spinner.getSelectedItemPosition());
            group_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    group = groupArrayList.get(i);
                    String[] albums = new String[group.getAlbums(albumArrayList).size()];
                    i = 0;
                    for (Album alb : group.getAlbums(albumArrayList)) {
                        albums[i++] = alb.getAlbum_name();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, albums);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    choose_album_spinner.setAdapter(adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            dialog.findViewById(R.id.delete_button).setVisibility(View.GONE);
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                try {
                    album = group.getAlbums(albumArrayList).get(choose_album_spinner.getSelectedItemPosition());
                    Track track_ = new Track();
                    track_.set_id(trackArrayList.get(trackArrayList.size() - 1).get_id() + 1);
                    track_.setTrack_name(((TextView) dialog.findViewById(R.id.editTrack_name)).getText().toString());
                    track_.setContinuance(((TextView) dialog.findViewById(R.id.editContinuance)).getText().toString());
                    dbHelper.updateTrack(track_);
                    AlbumTrack albumTrack_ = new AlbumTrack();
                    albumTrack_.set_id(albumTrackArrayList.get(albumTrackArrayList.size() - 1).get_id() + 1);
                    albumTrack_.setTrack_id(track_.get_id());
                    albumTrack_.setAlbum_id(album.get_id());
                    dbHelper.updateAlbumTrack(albumTrack_);
                    trackArrayList = dbHelper.getAllTracks();
                    albumTrackArrayList = dbHelper.getAllAlbumTracks();
                    Toast.makeText(AdminActivity.this, "Track successfully added", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                } catch (Exception e) {
                    Toast.makeText(AdminActivity.this, "We can`t add this track\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.println(Log.ERROR, "ERROR", e.getMessage());
                }
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t add this track\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void editTrack() {
        try {
            Dialog dialog = new Dialog(AdminActivity.this);
            dialog.setContentView(R.layout.edit_track_info);
            dialog.show();

            Spinner group_spinner = dialog.findViewById(R.id.group_spinner);
            Spinner album_spinner = dialog.findViewById(R.id.album_spinner);
            Spinner track_spinner = dialog.findViewById(R.id.track_spinner);
            Spinner choose_album_spinner = dialog.findViewById(R.id.albumChoose_spinner);
            String[] groups = new String[groupArrayList.size()];
            int i = 0;
            for (Group grp : groupArrayList) {
                groups[i++] = grp.getGroup_name();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, groups);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            group_spinner.setAdapter(adapter);
            group = groupArrayList.get(group_spinner.getSelectedItemPosition());
            group_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    group = groupArrayList.get(i);
                    String[] albums = new String[group.getAlbums(albumArrayList).size()];
                    i = 0;
                    for (Album alb : group.getAlbums(albumArrayList)) {
                        albums[i++] = alb.getAlbum_name();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, albums);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    album_spinner.setAdapter(adapter);
                    choose_album_spinner.setAdapter(adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            album_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    album = (group.getAlbums(albumArrayList)).get(i);
                    String[] tracks = new String[album.getTracks(albumTrackArrayList, trackArrayList).size()];
                    i = 0;
                    for (Track trk : album.getTracks(albumTrackArrayList, trackArrayList)) {
                        tracks[i++] = trk.getTrack_name();
                    }
                    ArrayAdapter<String>adapter = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_item, tracks);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    track_spinner.setAdapter(adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            track_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    track=(album.getTracks(albumTrackArrayList, trackArrayList)).get(i);
                    ((TextView) dialog.findViewById(R.id.editTrack_name)).setText(track.getTrack_name());
                    ((TextView) dialog.findViewById(R.id.editContinuance)).setText(track.getContinuance());
                    for(AlbumTrack albumTrack_: albumTrackArrayList){
                        if(track.get_id()==albumTrack_.getTrack_id() && album.get_id()==albumTrack_.getAlbum_id()){
                            albumTrack=albumTrack_;
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            dialog.findViewById(R.id.change_button).setOnClickListener(view1 -> {
                try {
                    Track track_ = new Track();
                    track_.set_id(track.get_id());
                    track_.setTrack_name(((TextView) dialog.findViewById(R.id.editTrack_name)).getText().toString());
                    track_.setContinuance(((TextView) dialog.findViewById(R.id.editContinuance)).getText().toString());
                    dbHelper.updateTrack(track_);
                    AlbumTrack albumTrack_ = new AlbumTrack();
                    albumTrack_.set_id(albumTrack.get_id());
                    albumTrack_.setTrack_id(track_.get_id());
                    albumTrack_.setAlbum_id(album.get_id());
                    dbHelper.updateAlbumTrack(albumTrack_);
                    albumTrackArrayList = dbHelper.getAllAlbumTracks();
                    trackArrayList = dbHelper.getAllTracks();
                    Toast.makeText(AdminActivity.this, "Track successfully edited", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                } catch (Exception e) {
                    Toast.makeText(AdminActivity.this, "We can`t edit this track\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.println(Log.ERROR, "ERROR", e.getMessage());
                }
            });
            dialog.findViewById(R.id.cancel_button).setOnClickListener(view1 -> dialog.cancel());
            dialog.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog del_dialog = new Dialog(AdminActivity.this);
                    del_dialog.setContentView(R.layout.delete_layout);
                    ((TextView) del_dialog.findViewById(R.id.textViewQuestion)).setText("Delete this track?");
                    del_dialog.show();
                    del_dialog.findViewById(R.id.yes_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for(AlbumTrack albumTrack: albumTrackArrayList){
                                if(albumTrack.getTrack_id()==track.get_id() && albumTrack.getAlbum_id()==album.get_id()){
                                    dbHelper.deleteAlbumTrack(albumTrack);
                                }
                            }
                            dbHelper.deleteTrack(track);
                            albumTrackArrayList= dbHelper.getAllAlbumTracks();
                            trackArrayList = dbHelper.getAllTracks();
                            del_dialog.cancel();
                            dialog.cancel();
                            Toast.makeText(AdminActivity.this, "Track successfully deleted", Toast.LENGTH_LONG).show();
                        }
                    });
                    del_dialog.findViewById(R.id.no_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            del_dialog.cancel();
                        }
                    });
                    dialog.cancel();
                }
            });
        } catch (Exception e) {
            Toast.makeText(AdminActivity.this, "We can`t edit this track\n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void deleteAllGroupInfo(Group group) {
        for (Composition composition : compositionArrayList) {
            if (composition.getGroup_id() == group.get_id()) {
                for (Member member : group.getMembers(compositionArrayList, memberArrayList)) {
                    dbHelper.deleteMember(member);
                }
                dbHelper.deleteComposition(composition);
            }
        }
        for (Award award : group.getAwards(awardArrayList)) {
            dbHelper.deleteAward(award);
        }
        for (Album album : group.getAlbums(albumArrayList)) {
            deleteAllAlbumInfo(album);
        }
        dbHelper.deleteGroup(group);
        groupArrayList = dbHelper.getAllGroups();
        memberArrayList = dbHelper.getAllMembers();
        compositionArrayList = dbHelper.getAllCompositions();
        awardArrayList = dbHelper.getAllAward();
        trackArrayList = dbHelper.getAllTracks();
        albumTrackArrayList = dbHelper.getAllAlbumTracks();
        albumArrayList = dbHelper.getAllAlbums();
    }

    private void deleteAllAlbumInfo(Album album) {
        for (AlbumTrack albumTrack : albumTrackArrayList) {
            if (album.get_id() == albumTrack.getAlbum_id()) {
                for (Track track : trackArrayList) {
                    if (albumTrack.getTrack_id() == track.get_id()) {
                        dbHelper.deleteTrack(track);
                    }
                }
                dbHelper.deleteAlbumTrack(albumTrack);
            }
        }
        dbHelper.deleteAlbum(album);
        trackArrayList = dbHelper.getAllTracks();
        albumTrackArrayList = dbHelper.getAllAlbumTracks();
        albumArrayList = dbHelper.getAllAlbums();
    }
}