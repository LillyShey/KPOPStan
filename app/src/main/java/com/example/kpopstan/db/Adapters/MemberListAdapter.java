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
import com.example.kpopstan.R;
import com.example.kpopstan.db.Tables.Member;

import java.util.ArrayList;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.ViewHolder> {
    private final ArrayList<Member> memberArrayList;
    private final Context context;
    private final LayoutInflater inflater;
    private final OnStateClickListener onClickListener;

    public MemberListAdapter(Context context, ArrayList<Member> memberArrayList, OnStateClickListener onClickListener) {
        this.memberArrayList = memberArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.member_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Member member = memberArrayList.get(position);
        holder.Member_photo.setImageBitmap(GroupInfoActivity.setImage(member.getMember_photo()));
        holder.Member_name.setText(member.getStage_name());
        holder.itemView.setOnClickListener(v -> onClickListener.onStateClick(member, position));
    }

    @Override
    public int getItemCount() {
        return memberArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView Member_photo;
        final TextView Member_name;

        ViewHolder(View view) {
            super(view);
            Member_photo = view.findViewById(R.id.imageViewMember_photo);
            Member_name = view.findViewById(R.id.textViewStage_name);
        }
    }

    public interface OnStateClickListener {
        void onStateClick(Member member, int position);
    }
}