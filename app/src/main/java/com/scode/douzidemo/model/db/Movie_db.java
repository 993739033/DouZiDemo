package com.scode.douzidemo.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 电影的实体类
 *
 * Created by 知らないのセカイ on 2017/6/13.
 */
@Entity
public class Movie_db {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String movieId;
    @NotNull
    private String title;
    @NotNull
    private String imgurl;

    private float rating;

    private String genres;

    private String year;

    private String time;

    @Generated(hash = 525101028)
    public Movie_db(Long id, @NotNull String movieId, @NotNull String title,
            @NotNull String imgurl, float rating, String genres, String year,
            String time) {
        this.id = id;
        this.movieId = movieId;
        this.title = title;
        this.imgurl = imgurl;
        this.rating = rating;
        this.genres = genres;
        this.year = year;
        this.time = time;
    }

    @Generated(hash = 1012879875)
    public Movie_db() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
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

    public String getGenres() {
        return this.genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
