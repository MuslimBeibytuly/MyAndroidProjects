package com.example.muslimbeibytuly.myfeed;

import android.arch.persistence.room.Room;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.muslimbeibytuly.myfeed.Components.Adapters.PageAdapter;
import com.example.muslimbeibytuly.myfeed.Components.Fragments.CategoryTabFragment;
import com.example.muslimbeibytuly.myfeed.Components.Fragments.MainTabFragment;
import com.example.muslimbeibytuly.myfeed.Components.Fragments.PostFragment;
import com.example.muslimbeibytuly.myfeed.Database.AppDatabase;
import com.example.muslimbeibytuly.myfeed.Database.Models.Category;
import com.example.muslimbeibytuly.myfeed.Database.Models.Post;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PageAdapter pageAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new PostFragment(), "news");
        pageAdapter.addFragment(new CategoryTabFragment(), "categories");
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pageAdapter);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

//        AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "feed.db").allowMainThreadQueries().build();
//        Category category = new Category("category");
//        database.CategoryDao().insert(category);
//        List<Category> categories = database.CategoryDao().getAllCategories();
//        Post post = new Post("post", "12.01.2015");
//        post.setCategoryId(categories.get(0).getCategoryId());
//        database.PostDao().insert(post);
//        ArrayList<Post> posts = (ArrayList<Post>) database.PostDao().getAllPosts();
//        Log.d("posts: ", String.valueOf(posts.size()));
    }
}
