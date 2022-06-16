package com.example.kpopstan.db.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kpopstan.R;
import com.example.kpopstan.db.Tables.Award;

import java.util.ArrayList;

public class AwardListAdapter extends RecyclerView.Adapter<AwardListAdapter.ViewHolder> {
    private final ArrayList<Award> awardArrayList;
    private final Context context;
    private final LayoutInflater inflater;

    public AwardListAdapter(Context context, ArrayList<Award> awardArrayList) {
        this.awardArrayList = awardArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.award_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Award award = awardArrayList.get(position);
        holder.Award_number.setText(String.valueOf(position + 1));
        holder.Award_name.setText(award.getAward() + "(" + award.getDaesang_name() + ")");
        holder.Year.setText(String.valueOf(award.getReceipt_year()));
    }

    @Override
    public int getItemCount() {
        return awardArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView Award_number, Award_name, Year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Award_number = itemView.findViewById(R.id.textViewAwardNumber);
            Award_name = itemView.findViewById(R.id.textViewAwardName);
            Year = itemView.findViewById(R.id.textViewYear);
        }
    }
}
