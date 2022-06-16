package com.example.kpopstan.db.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kpopstan.GroupInfoActivity;
import com.example.kpopstan.GroupListActivity;
import com.example.kpopstan.R;
import com.example.kpopstan.db.Tables.Group;

import java.util.ArrayList;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.ViewHolder> {
    private final ArrayList<Group> groupArrayList;
    private final Context context;
    private final LayoutInflater inflater;
    private final OnStateClickListener onClickListener;

    public GroupListAdapter(Context context, ArrayList<Group> groupArrayList, OnStateClickListener onClickListener) {
        this.groupArrayList = groupArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.group_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Group group = groupArrayList.get(position);
        holder.Group_photo.setImageBitmap(GroupInfoActivity.setImage(group.getGroup_photo()));
        holder.Group_name.setText(group.getGroup_name());
        holder.Create_year.setText(String.valueOf(group.getCreate_year()));
        holder.Fandom.setText(group.getFandom_name());
        holder.Label_name.setText(group.getLabel(GroupListActivity.labelArrayList).getLabel_name());
        holder.Gender.setText(group.getGender());
        holder.MembersCount.setText(String.valueOf(group.getMembers_count(GroupListActivity.compositionArrayList)));
        holder.itemView.setOnClickListener(v -> onClickListener.onStateClick(group, position));
    }

    @Override
    public int getItemCount() {
        return groupArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView Group_photo;
        final TextView Group_name, Create_year, Fandom, Label_name, MembersCount, Gender;

        ViewHolder(View view) {
            super(view);
            Group_photo = view.findViewById(R.id.imageViewGroup_photo);
            Group_name = view.findViewById(R.id.textViewGroup_name);
            Create_year = view.findViewById(R.id.textViewCreate_year);
            Fandom = view.findViewById(R.id.textViewFandom);
            Label_name = view.findViewById(R.id.textViewLabel_name);
            MembersCount = view.findViewById(R.id.textViewMembersCount);
            Gender = view.findViewById(R.id.textViewGender);
        }
    }

    public interface OnStateClickListener {
        void onStateClick(Group group, int position);

    }

}
