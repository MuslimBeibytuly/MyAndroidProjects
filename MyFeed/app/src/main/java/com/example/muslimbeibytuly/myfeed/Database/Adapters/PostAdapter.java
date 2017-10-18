package com.example.muslimbeibytuly.myfeed.Database.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.muslimbeibytuly.myfeed.Components.Fragments.PostFragment;
import com.example.muslimbeibytuly.myfeed.Database.Models.Post;
import com.example.muslimbeibytuly.myfeed.PostActivity;
import com.example.muslimbeibytuly.myfeed.R;

import java.util.List;

/**
 * Created by muslimbeibytuly on 10/9/17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    public void insertPost(Post post){
        posts.add(posts.size(), post);
        notifyItemInserted(posts.size());
    }

    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);
        return new PostViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(PostAdapter.PostViewHolder holder, int position) {
        holder.setPosition(position);
        holder.text.setText(posts.get(position).getText());
        holder.date.setText(posts.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView text, date;
        int position;
        public PostViewHolder(View view, final Context event) {
            super(view);
            text = view.findViewById(R.id.text);
            date = view.findViewById(R.id.date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PostActivity.class);
                    intent.putExtra("text", posts.get(position).getText());
                    intent.putExtra("date", posts.get(position).getDate());
                    context.startActivity(intent);
                }
            });
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

}
