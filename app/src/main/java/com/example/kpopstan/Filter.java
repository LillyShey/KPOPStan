package com.example.kpopstan;

import com.example.kpopstan.db.Tables.Group;

import java.util.ArrayList;

public class Filter {
    ArrayList<Group> groupArrayList;

    public Filter() {
        groupArrayList = new ArrayList<>();
    }

    public Filter(ArrayList<Group> groupArrayList) {
        this.groupArrayList = groupArrayList;
    }

    public ArrayList<Group> YearFilter(ArrayList<Integer> arrayList) {
        ArrayList<Group> result = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < groupArrayList.size(); j++) {
                if (arrayList.get(i) == groupArrayList.get(j).getCreate_year())
                    result.add(groupArrayList.get(j));
            }
        }
        return result;
    }

    public ArrayList<Group> LabelFilter(ArrayList<String> arrayList) {
        ArrayList<Group> result = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < groupArrayList.size(); j++) {
                if (arrayList.get(i).equals(groupArrayList.get(j).getLabel(GroupListActivity.labelArrayList).getLabel_name()))
                    result.add(groupArrayList.get(j));
            }
        }
        return result;
    }

    public ArrayList<Group> MembersCountFilter(ArrayList<Integer> arrayList) {
        ArrayList<Group> result = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < groupArrayList.size(); j++) {
                if (arrayList.get(i).equals(groupArrayList.get(j).getMembers_count(GroupListActivity.compositionArrayList)))
                    result.add(groupArrayList.get(j));
            }
        }
        return result;
    }

    public ArrayList<Group> GenderFilter(ArrayList<String> arrayList) {
        ArrayList<Group> result = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < groupArrayList.size(); j++) {
                if (arrayList.get(i).equals(groupArrayList.get(j).getGender()))
                    result.add(groupArrayList.get(j));
            }
        }
        return result;
    }
}
