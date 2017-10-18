package com.example.muslimbeibytuly.myfeed.Database.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Type;
import java.sql.Blob;
import java.sql.Date;

/**
 * Created by muslimbeibytuly on 10/9/17.
 */
@Entity(tableName = "posts",
        foreignKeys = @ForeignKey(
                entity = Category.class,
                parentColumns = "category_id",
                childColumns = "category_id"))
public class Post implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "post_id")
    private int postId;

    @ColumnInfo(name = "text")
    private String text;

//    sqlite cannot store date
    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    public Post() {
    }

    @Ignore
    public Post(String text, String date) {
        this.text = text;
        this.date = date;
    }

    @Ignore
    public Post(String text, String date, byte[] image, int categoryId) {
        this.text = text;
        this.date = date;
        this.image = image;
        this.categoryId = categoryId;
    }

    protected Post(Parcel in) {
        postId = in.readInt();
        text = in.readString();
        date = in.readString();
        image = in.createByteArray();
        categoryId = in.readInt();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
        parcel.writeString(date);
    }
}
