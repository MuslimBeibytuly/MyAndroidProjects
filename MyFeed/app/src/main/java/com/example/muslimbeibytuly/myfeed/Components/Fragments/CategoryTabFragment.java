package com.example.muslimbeibytuly.myfeed.Components.Fragments;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muslimbeibytuly.myfeed.Database.Adapters.CategoryAdapter;
import com.example.muslimbeibytuly.myfeed.Database.Adapters.PostAdapter;
import com.example.muslimbeibytuly.myfeed.Database.AppDatabase;
import com.example.muslimbeibytuly.myfeed.Database.Models.Category;
import com.example.muslimbeibytuly.myfeed.Database.Models.Post;
import com.example.muslimbeibytuly.myfeed.R;

import java.util.List;

/**
 * Created by muslimbeibytuly on 10/9/17.
 */

public class CategoryTabFragment extends Fragment {
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<Category> categories;
    AppDatabase database;
    public CategoryTabFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        database = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "feed.db").build();
        View view = inflater.inflate(R.layout.category_tab_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        new GetCategoriesAsyncTask().execute();
        return view;
    }

    private class GetCategoriesAsyncTask extends AsyncTask<Void, Void, List<Category>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Category> doInBackground(Void... voids) {
            return database.CategoryDao().getAllCategories();
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            super.onPostExecute(categories);
            adapter = new CategoryAdapter(getContext(), categories);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }
}
