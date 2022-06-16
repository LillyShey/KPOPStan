package com.example.kpopstan.db.Tables;

import java.util.ArrayList;

public class Composition {
    private int _id;
    private int group_id;
    private int member_id;
    private String accession_date;
    private String leaving_date;

    public Composition() {
        this._id = 0;
        this.group_id = 0;
        this.member_id = 0;
        this.accession_date = "0001-01-01";
        this.leaving_date = "0001-01-01";
    }

    public Composition(int _id, String accession_date, int group_id, String leaving_date, int member_id) {
        this._id = _id;
        this.group_id = group_id;
        this.member_id = member_id;
        this.accession_date = accession_date;
        this.leaving_date = leaving_date;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getAccession_date() {
        return accession_date;
    }

    public void setAccession_date(String accession_date) {
        this.accession_date = accession_date;
    }

    public String getLeaving_date() {
        return leaving_date;
    }

    public void setLeaving_date(String leaving_date) {
        this.leaving_date = leaving_date;
    }

    public Group getGroup(ArrayList<Group> groupArrayList) {
        Group result = new Group();
        for (Group group : groupArrayList) {
            if (group.get_id() == this.group_id) {
                result = group;
            }
        }
        return result;
    }

    public Member getMember(ArrayList<Member> memberArrayList) {
        Member result = new Member();
        for (Member member : memberArrayList) {
            if (member.get_id() == this.member_id) {
                result = member;
            }
        }
        return result;
    }
}
