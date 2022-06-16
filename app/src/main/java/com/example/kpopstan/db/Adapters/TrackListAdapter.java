package com.example.kpopstan.db.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kpopstan.R;
import com.example.kpopstan.db.Tables.Track;

import java.util.ArrayList;

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.ViewHolder> {
    private final ArrayList<Track> trackArrayList;
    private final Context context;
    private final LayoutInflater inflater;

    public TrackListAdapter(Context context, ArrayList<Track> trackArrayList) {
        this.trackArrayList = trackArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.track_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Track track = trackArrayList.get(position);
        holder.Track_number.setText(String.valueOf(position + 1));
        holder.Track_name.setText(track.getTrack_name());
        holder.Continuance.setText(track.getContinuance());
    }

    @Override
    public int getItemCount() {
        return trackArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView Track_number, Track_name, Continuance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Track_number = itemView.findViewById(R.id.textViewTrackNumber);
            Track_name = itemView.findViewById(R.id.textViewTrackName);
            Continuance = itemView.findViewById(R.id.textViewTrackContinuance);
        }
    }
}
