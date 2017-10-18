package com.example.muslimbeibytuly.myfeed.Database.DAO;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.muslimbeibytuly.myfeed.Database.Models.Category;
import java.util.List;

/**
 * Created by muslimbeibytuly on 10/9/17.
 */
@Dao
public interface CategoryDao {
    @Query("SELECT * FROM categories")
    List<Category> getAllCategories();

    @Query("SELECT * FROM categories where category_id=:id")
    Category getCategoryById(int id);

    @Insert
    void insert(Category category);

    @Delete
    void delete(Category category);
}
