package com.scode.douzidemo.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 书的实体类
 *
 * Created by 知らないのセカイ on 2017/6/13.
 */
@Entity
public class Book_db {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String book_id;

    @NotNull
    private String title;
    @NotNull
    private String imgurl;

    private float rating;

    private String author;

    private String publisher;

    private String time;

    @Generated(hash = 1026737697)
    public Book_db(Long id, @NotNull String book_id, @NotNull String title,
            @NotNull String imgurl, float rating, String author, String publisher,
            String time) {
        this.id = id;
        this.book_id = book_id;
        this.title = title;
        this.imgurl = imgurl;
        this.rating = rating;
        this.author = author;
        this.publisher = publisher;
        this.time = time;
    }

    @Generated(hash = 995350502)
    public Book_db() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBook_id() {
        return this.book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return this.imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
