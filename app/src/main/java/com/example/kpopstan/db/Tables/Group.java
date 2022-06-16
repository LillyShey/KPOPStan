package com.example.kpopstan.db.Tables;

import com.example.kpopstan.GroupListActivity;

import java.util.ArrayList;
import java.util.Locale;

public class Group {
    private int _id;
    private String group_name;
    private int create_year;
    private String fandom_name;
    private int label_id;
    private String group_photo;
    private String gender;
    private Label label;

    public Group() {
        this._id = 0;
        this.group_name = "None";
        this.create_year = 0;
        this.fandom_name = "None";
        this.label_id = 0;
        this.group_photo = "None";
        this.gender = "None";
    }

    public Group(int _id, String group_name, int create_year, String fandom_name, int label_id, String group_photo, String gender) {
        this._id = _id;
        this.group_name = group_name;
        this.create_year = create_year;
        this.fandom_name = fandom_name;
        this.label_id = label_id;
        this.group_photo = group_photo;
        this.gender = gender;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getCreate_year() {
        return create_year;
    }

    public void setCreate_year(int create_year) {
        this.create_year = create_year;
    }

    public String getFandom_name() {
        return fandom_name;
    }

    public void setFandom_name(String fandom_name) {
        this.fandom_name = fandom_name;
    }

    public int getLabel_id() {
        return label_id;
    }

    public void setLabel_id(int label_id) {
        this.label_id = label_id;
    }

    public String getGroup_photo() {
        return group_photo;
    }

    public void setGroup_photo(String group_photo) {
        this.group_photo = group_photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean startsOn(String search_name) {
        int equals = 0;
        for (int j = 0; j < search_name.length(); j++) {
            if (search_name.toLowerCase(Locale.ROOT).charAt(j) == getGroup_name()
                    .toLowerCase(Locale.ROOT).charAt(j)) {
                equals++;
            }
        }
        if (equals == search_name.length()) {
            return true;
        } else {
            return false;
        }
    }

    public Label getLabel(ArrayList<Label> labelArrayList) {
        Label result = new Label();
        for (Label label : labelArrayList) {
            if (this.label_id == label.get_id())
                result = label;
        }
        return result;
    }

    public void setLabel(int label_id, ArrayList<Label> labelArrayList) {
        for (Label label : labelArrayList) {
            if (label_id == label.get_id()) {
                this.label = label;
            }
        }
    }

    public void setLabel(String label_name, ArrayList<Label> labelArrayList) {
        for (Label label : labelArrayList) {
            if (label_name.equals(label.getLabel_name())) {
                this.label = label;
                this.label_id = label.get_id();
            }
        }
    }

    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public int getMembers_count(ArrayList<Composition> compositionArrayList) {
        int member_count = 0;
        for (Composition composition : compositionArrayList) {
            if (this._id == composition.getGroup(GroupListActivity.groupArrayList).get_id()
                    && composition.getLeaving_date().equals("0001-01-01")) {
                member_count++;
            }
        }
        return member_count;
    }

    public ArrayList<Member> getMembers(ArrayList<Composition> compositionArrayList, ArrayList<Member> memberArrayList) {
        ArrayList<Member> result = new ArrayList<>();
        for (Composition composition : compositionArrayList) {
            if (composition.getGroup_id() == this.get_id()
                    && composition.getLeaving_date().equals("0001-01-01") == true
                    && composition.getMember(memberArrayList).get_id() == composition.getMember_id()) {
                result.add(composition.getMember(memberArrayList));
            }
        }
        return result;
    }

    public ArrayList<Member> getExMembers(ArrayList<Composition> compositionArrayList, ArrayList<Member> memberArrayList) {
        ArrayList<Member> result = new ArrayList<>();
        for (Composition composition : compositionArrayList) {
            if (composition.getGroup_id() == this.get_id()
                    && composition.getLeaving_date().equals("0001-01-01") == false
                    && composition.getMember(memberArrayList).get_id() == composition.getMember_id()) {
                result.add(composition.getMember(memberArrayList));
            }
        }
        return result;
    }

    public ArrayList<Album> getAlbums(ArrayList<Album> albumArrayList) {
        ArrayList<Album> result = new ArrayList<>();
        for (Album album : albumArrayList) {
            if (album.getGroup_id() == this.get_id()) {
                result.add(album);
            }
        }
        return result;
    }

    public ArrayList<Award> getAwards(ArrayList<Award> awardArrayList) {
        ArrayList<Award> result = new ArrayList<>();
        for (Award award : awardArrayList) {
            if (award.getGroup_id() == this.get_id()) {
                result.add(award);
            }
        }
        return result;
    }
}
