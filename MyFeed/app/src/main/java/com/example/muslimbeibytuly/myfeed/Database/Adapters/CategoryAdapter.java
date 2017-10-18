package com.example.muslimbeibytuly.myfeed.Database.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.muslimbeibytuly.myfeed.Database.Models.Category;
import com.example.muslimbeibytuly.myfeed.Database.Models.Post;
import com.example.muslimbeibytuly.myfeed.PostActivity;
import com.example.muslimbeibytuly.myfeed.R;

import java.util.List;

/**
 * Created by muslimbeibytuly on 10/11/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    public void insertCategory(Category category){
        categories.add(categories.size(), category);
    }

    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        return new CategoryAdapter.CategoryViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.CategoryViewHolder holder, int position) {
        holder.setPosition(position);
        holder.name.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        int position;
        public CategoryViewHolder(View view, final Context event) {
            super(view);
            name = view.findViewById(R.id.name);
        }



        public void setPosition(int position) {
            this.position = position;
        }
    }
}
