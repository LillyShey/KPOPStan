package com.example.kpopstan;

import com.example.kpopstan.db.Tables.Group;

import java.util.ArrayList;
import java.util.Locale;

public class Sorting {
    private ArrayList<Group> groupArrayList;

    public Sorting() {
        this.groupArrayList = new ArrayList<>();
    }

    public Sorting(ArrayList<Group> groupArrayList) {
        this.groupArrayList = groupArrayList;
    }

    private void Swap(int i, int j) {
        Group temp;
        temp = groupArrayList.get(i);
        groupArrayList.set(i, groupArrayList.get(j));
        groupArrayList.set(j, temp);
    }

    public void NameSort(int left, int right, int parameter) {
        int i = left, j = right;
        Group pivot = groupArrayList.get(left + (right - left) / 2);
        while (i <= j) {
            if (parameter == 1) {
                while (groupArrayList.get(i).getGroup_name().toLowerCase(Locale.ROOT).compareTo(pivot.getGroup_name().toLowerCase(Locale.ROOT)) < 0)
                    i++;
                while (groupArrayList.get(j).getGroup_name().toLowerCase(Locale.ROOT).compareTo(pivot.getGroup_name().toLowerCase(Locale.ROOT)) > 0)
                    j--;
            } else {
                while (groupArrayList.get(i).getGroup_name().toLowerCase(Locale.ROOT).compareTo(pivot.getGroup_name().toLowerCase(Locale.ROOT)) > 0)
                    i++;
                while (groupArrayList.get(j).getGroup_name().toLowerCase(Locale.ROOT).compareTo(pivot.getGroup_name().toLowerCase(Locale.ROOT)) < 0)
                    j--;
            }
            if (i <= j) {

                this.Swap(i, j);
                i++;
                j--;
            }
        }
        if (j > left)
            NameSort(left, j, parameter);
        if (i < right)
            NameSort(i, right, parameter);
    }

    public void DebutYearSort(int left, int right, int parameter) {
        int i = left, j = right;
        Group pivot = groupArrayList.get(left + (right - left) / 2);
        while (i <= j) {
            if (parameter == 1) {
                while (groupArrayList.get(i).getCreate_year() > pivot.getCreate_year())
                    i++;
                while (groupArrayList.get(j).getCreate_year() < pivot.getCreate_year())
                    j--;
            } else {
                while (groupArrayList.get(i).getCreate_year() < pivot.getCreate_year())
                    i++;
                while (groupArrayList.get(j).getCreate_year() > pivot.getCreate_year())
                    j--;
            }
            if (i <= j) {

                this.Swap(i, j);
                i++;
                j--;
            }
        }
        if (j > left)
            DebutYearSort(left, j, parameter);
        if (i < right)
            DebutYearSort(i, right, parameter);
    }

    public void LabelSort(int left, int right, int parameter) {
        int i = left, j = right;
        Group pivot = groupArrayList.get(left + (right - left) / 2);
        while (i <= j) {
            if (parameter == 1) {
                while (groupArrayList.get(i).getLabel(GroupListActivity.labelArrayList).getLabel_name().toLowerCase(Locale.ROOT).compareTo(pivot.getLabel(GroupListActivity.labelArrayList).getLabel_name().toLowerCase(Locale.ROOT)) < 0)
                    i++;
                while (groupArrayList.get(j).getLabel(GroupListActivity.labelArrayList).getLabel_name().toLowerCase(Locale.ROOT).compareTo(pivot.getLabel(GroupListActivity.labelArrayList).getLabel_name().toLowerCase(Locale.ROOT)) > 0)
                    j--;
            } else {
                while (groupArrayList.get(i).getLabel(GroupListActivity.labelArrayList).getLabel_name().toLowerCase(Locale.ROOT).compareTo(pivot.getLabel(GroupListActivity.labelArrayList).getLabel_name().toLowerCase(Locale.ROOT)) > 0)
                    i++;
                while (groupArrayList.get(j).getLabel(GroupListActivity.labelArrayList).getLabel_name().toLowerCase(Locale.ROOT).compareTo(pivot.getLabel(GroupListActivity.labelArrayList).getLabel_name().toLowerCase(Locale.ROOT)) < 0)
                    j--;
            }
            if (i <= j) {

                this.Swap(i, j);
                i++;
                j--;
            }
        }
        if (j > left)
            LabelSort(left, j, parameter);
        if (i < right)
            LabelSort(i, right, parameter);
    }

    public void MembersCountSort(int left, int right, int parameter) {
        int i = left, j = right;
        Group pivot = groupArrayList.get(left + (right - left) / 2);
        while (i <= j) {
            if (parameter == 1) {
                while (groupArrayList.get(i).getMembers_count(GroupListActivity.compositionArrayList) > pivot.getMembers_count(GroupListActivity.compositionArrayList))
                    i++;
                while (groupArrayList.get(j).getMembers_count(GroupListActivity.compositionArrayList) < pivot.getMembers_count(GroupListActivity.compositionArrayList))
                    j--;
            } else {
                while (groupArrayList.get(i).getMembers_count(GroupListActivity.compositionArrayList) < pivot.getMembers_count(GroupListActivity.compositionArrayList))
                    i++;
                while (groupArrayList.get(j).getMembers_count(GroupListActivity.compositionArrayList) > pivot.getMembers_count(GroupListActivity.compositionArrayList))
                    j--;
            }
            if (i <= j) {

                this.Swap(i, j);
                i++;
                j--;
            }
        }
        if (j > left)
            MembersCountSort(left, j, parameter);
        if (i < right)
            MembersCountSort(i, right, parameter);
    }
}