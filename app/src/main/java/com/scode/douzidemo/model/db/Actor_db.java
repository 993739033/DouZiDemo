package com.scode.douzidemo.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 演员的实体类
 *
 * Created by 知らないのセカイ on 2017/6/13.
 */
@Entity
public class Actor_db {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String actor_id;

    public String getActor_id() {
        return actor_id;
    }

    public void setActor_id(String actor_id) {
        this.actor_id = actor_id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBorn_place() {
        return this.born_place;
    }

    public void setBorn_place(String born_place) {
        this.born_place = born_place;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @NotNull
    private String title;
    @NotNull
    private String imgurl;

    private String gender;

    private String born_place;

    private String time;

    @Generated(hash = 1006026375)
    public Actor_db(Long id, @NotNull String actor_id, @NotNull String title,
            @NotNull String imgurl, String gender, String born_place, String time) {
        this.id = id;
        this.actor_id = actor_id;
        this.title = title;
        this.imgurl = imgurl;
        this.gender = gender;
        this.born_place = born_place;
        this.time = time;
    }

    @Generated(hash = 87567351)
    public Actor_db() {
    }
}
