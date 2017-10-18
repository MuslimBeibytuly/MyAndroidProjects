package com.example.muslimbeibytuly.myfeed.Database.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.ULocale;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.muslimbeibytuly.myfeed.Database.Models.Category;
import com.example.muslimbeibytuly.myfeed.Database.Models.Post;
import com.example.muslimbeibytuly.myfeed.PostActivity;
import com.example.muslimbeibytuly.myfeed.R;

import java.util.List;

/**
 * Created by muslimbeibytuly on 10/11/17.
 */

public class CategorySpinnerAdapter extends ArrayAdapter<Category>{
    private Context context;
    private List<Category> categories;

    public CategorySpinnerAdapter(Context context, int textViewResourceId, List<Category> categories) {
        super(context, textViewResourceId, categories);
        this.context = context;
        this.categories = categories;
    }

    public Category getItem(int position){
        return categories.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(categories.get(position).getName());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(categories.get(position).getName());
        return label;
    }

    public void insertPost(Category category){
        categories.add(categories.size(), category);
    }

    public int getItemCount() {
        return categories.size();
    }
}
