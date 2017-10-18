package com.example.muslimbeibytuly.myfeed.Database.DAO;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.muslimbeibytuly.myfeed.Database.Models.Post;
import java.util.List;

/**
 * Created by muslimbeibytuly on 10/9/17.
 */
@Dao
public interface PostDao {
    @Query("SELECT * FROM posts")
    List<Post> getAllPosts();

    @Query("SELECT * FROM posts where post_id=:id")
    Post getPostById(int id);

    @Query("SELECT * FROM posts where category_id=:id")
    List<Post> getPostsByCategoryId(int id);

    @Insert
    void insert(Post post);

    @Delete
    void delete(Post post);
}