package com.example.firebaseregistrationexample;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.firebase.ui.firestore.paging.LoadingState;

public class RecyclerAdapter extends FirestorePagingAdapter<PostModel, RecyclerAdapter.PostViewHolder> {

    public RecyclerAdapter(@NonNull FirestorePagingOptions options) {
        super(options);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull PostModel model) {
        holder.post_email.setText("Created by : " + model.getUser_email());
        holder.post_title.setText(model.getPost_title());
        holder.post_content.setText(model.getPost_content());
    }

    @Override
    protected void onLoadingStateChanged(@NonNull LoadingState state) {
        super.onLoadingStateChanged(state);
        switch (state) {
            case LOADED:

            case FINISHED:

            case LOADING_MORE:

            case LOADING_INITIAL:

            case ERROR:
                Log.d("", "");
                break;
        }
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView post_email, post_title, post_content;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            post_email = itemView.findViewById(R.id.post_email);
            // post_email.setPaintFlags(post_email.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            post_title = itemView.findViewById(R.id.post_title);
            post_content = itemView.findViewById(R.id.post_content);
        }
    }

}
