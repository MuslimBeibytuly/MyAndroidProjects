package com.example.muslimbeibytuly.myfeed.Components.Fragments;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.muslimbeibytuly.myfeed.Database.Adapters.CategorySpinnerAdapter;
import com.example.muslimbeibytuly.myfeed.Database.Adapters.PostAdapter;
import com.example.muslimbeibytuly.myfeed.Database.AppDatabase;
import com.example.muslimbeibytuly.myfeed.Database.Models.Category;
import com.example.muslimbeibytuly.myfeed.Database.Models.Post;
import com.example.muslimbeibytuly.myfeed.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by muslimbeibytuly on 10/9/17.
 */

public class PostFragment extends Fragment implements OnClickListener {
    AppDatabase database;
    PostAdapter postAdapter;
    CategorySpinnerAdapter categorySpinnerAdapter;
    RecyclerView recyclerView;
    AlertDialog.Builder alertDialog;
    Spinner spinner;
    String json;

    public PostFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "feed.db").build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.post_fragment, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(this);
        new getPostsFromAPIASyncTask().execute();
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.floatingActionButton) {
            view = getLayoutInflater().inflate(R.layout.post_create_fragment, null);
            alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setView(view);
            spinner = view.findViewById(R.id.spinner);
            final EditText text = view.findViewById(R.id.createText);
            alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate = simpleDateFormat.format(Calendar.getInstance().getTime());
                    Post post = new Post(text.getText().toString(), formattedDate);
                    CategorySpinnerAdapter tempAdapter = (CategorySpinnerAdapter) spinner.getAdapter();
                    post.setCategoryId(tempAdapter.getItem(spinner.getSelectedItemPosition()).getCategoryId());
                    postAdapter.insertPost(post);
                    new InsertPostAsyncTask().execute(post);
                    postAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });
            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.setTitle("add post");
            alertDialog.show();
            new GetCategoriesAsyncTask().execute();
        }
    }


    private class GetPostsAsyncTask extends AsyncTask<Void, Void, List<Post>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Post> doInBackground(Void... voids) {
            return database.PostDao().getAllPosts();
        }

        @Override
        protected void onPostExecute(List<Post> posts) {
            super.onPostExecute(posts);
            postAdapter = new PostAdapter(getContext(), posts);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(postAdapter);
        }
    }

    private class InsertPostAsyncTask extends AsyncTask<Post, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Post... posts) {
            if (database.CategoryDao().getAllCategories().size() < 1) {
                Category category = new Category("entertainment");
                database.CategoryDao().insert(category);
                category = new Category("politics");
                database.CategoryDao().insert(category);
                category = new Category("sport");
                database.CategoryDao().insert(category);
                category = new Category("technologies");
                database.CategoryDao().insert(category);
            }
            database.PostDao().insert(posts[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
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
            categorySpinnerAdapter = new CategorySpinnerAdapter(getContext(), R.layout.spinner_category_card, categories);
            categorySpinnerAdapter.setDropDownViewResource(R.layout.spinner_category_card);
            spinner.setAdapter(categorySpinnerAdapter);
        }
    }

    private class getPostsFromAPIASyncTask extends AsyncTask<Void, Void, List<Post>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Post> doInBackground(Void... voids) {
            try {
                URL url = new URL("https://jsonblob.com/api/f9127855-ae60-11e7-9b68-31b8ea421c45");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    json = stringBuilder.toString();
                    Log.d("json", json);
                    Post[] posts = new Gson().fromJson(json, Post[].class);
                    return Arrays.asList(posts);
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Post> posts) {
            super.onPostExecute(posts);
            postAdapter = new PostAdapter(getContext(), posts);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(postAdapter);
        }

    }
}
