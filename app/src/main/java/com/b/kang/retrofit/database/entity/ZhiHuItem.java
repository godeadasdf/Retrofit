package com.b.kang.retrofit.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kang on 17-4-26.
 */
@Entity
public class ZhiHuItem {

    @Id(autoincrement = true)
    public long id;

    public long newsId;

    public String title;

    public String imageUrl;

    @Generated(hash = 1618524194)
    public ZhiHuItem(long id, long newsId, String title, String imageUrl) {
        this.id = id;
        this.newsId = newsId;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    @Generated(hash = 1538425255)
    public ZhiHuItem() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNewsId() {
        return this.newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
