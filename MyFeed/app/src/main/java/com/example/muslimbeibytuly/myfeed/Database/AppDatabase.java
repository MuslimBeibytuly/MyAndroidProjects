package com.example.muslimbeibytuly.myfeed.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.muslimbeibytuly.myfeed.Database.DAO.CategoryDao;
import com.example.muslimbeibytuly.myfeed.Database.DAO.PostDao;
import com.example.muslimbeibytuly.myfeed.Database.Models.Category;
import com.example.muslimbeibytuly.myfeed.Database.Models.Post;

/**
 * Created by muslimbeibytuly on 10/9/17.
 */

@Database(entities = {Post.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PostDao PostDao();
    public abstract CategoryDao CategoryDao();
}
