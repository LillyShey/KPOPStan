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
import com.example.kpopstan.LoginActivity;
import com.example.kpopstan.R;
import com.example.kpopstan.db.Tables.Album;

import java.util.ArrayList;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder> {
    private final ArrayList<Album> albumArrayList;
    private final Context context;
    private final LayoutInflater inflater;
    private final OnStateClickListener onClickListener;

    public AlbumListAdapter(Context context, ArrayList<Album> albumArrayList, AlbumListAdapter.OnStateClickListener onClickListener) {
        this.albumArrayList = albumArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.album_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.Album_photo.setImageBitmap(GroupInfoActivity.setImage(album.getAlbum_photo()));
        holder.Album_name.setText(album.getAlbum_name());
        holder.Release_date.setText(album.getRelease_date());
        holder.Sales_number.setText(String.valueOf(album.getSales_number()));
        holder.itemView.setOnClickListener(v -> onClickListener.onStateClick(album, position));
        if (LoginActivity.user.getRole().equals("admin")) {
            holder.itemView.setOnLongClickListener(view -> {
                onClickListener.onStateLongClick(album, position, view);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView Album_photo;
        final TextView Album_name, Release_date, Sales_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Album_photo = itemView.findViewById(R.id.imageViewAlbum_photo);
            Album_name = itemView.findViewById(R.id.textViewAlbum_name);
            Release_date = itemView.findViewById(R.id.textViewRelease_Date);
            Sales_number = itemView.findViewById(R.id.textViewSales_number);
        }
    }

    public interface OnStateClickListener {
        void onStateClick(Album album, int position);

        boolean onStateLongClick(Album album, int position, View view);
    }
}
