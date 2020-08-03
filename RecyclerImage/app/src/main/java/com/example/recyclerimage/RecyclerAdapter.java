package com.example.recyclerimage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageViewHolder> {

    private int[] images;

    private Context context;

    public RecyclerAdapter(int[] images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view, context, images);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int image_id = images[position];
        holder.album.setImageResource(image_id);
        holder.album_title.setText("Image : " + position);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView album;
        private TextView album_title;
        Context context;
        int[] images;

        public ImageViewHolder(@NonNull View itemView, Context context, int[] images) {
            super(itemView);
            album = itemView.findViewById(R.id.album);
            album_title = itemView.findViewById(R.id.album_title);
            this.context = context;
            this.images = images;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ImageActivity.class);
            intent.putExtra("image_id", images[getAdapterPosition()]);
            context.startActivity(intent);
        }
    }

}
