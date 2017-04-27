package com.b.kang.retrofit.database.entity;

import com.b.kang.retrofit.network.model.BaseDailyItem;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kang on 17-4-26.
 */
@Entity
public class ZhiHuItem extends BaseDailyItem {

    @Id(autoincrement = true)
    public Long id;

    public long newsId;

    public String title;

    public String imageUrl;

    public String date;

    @Generated(hash = 728070466)
    public ZhiHuItem(Long id, long newsId, String title, String imageUrl,
            String date) {
        this.id = id;
        this.newsId = newsId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.date = date;
    }

    @Generated(hash = 1538425255)
    public ZhiHuItem() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
