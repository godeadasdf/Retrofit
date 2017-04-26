package com.b.kang.retrofit.network.model;

/**
 * Created by kang on 17-4-21.
 */
public class DailyLatestTopDailyItem extends BaseDailyItem {
   /* {
        "image": "https://pic1.zhimg.com/v2-b33f30ab4af4e8339465fba5d077b5a8.jpg",
            "type": 0,
            "id": 9372230,
            "ga_prefix": "042107",
            "title": "iOS 版微信不能赞赏公众号，腾讯和苹果较上劲了"
    }*/
    public String image;
    public int type;
    public long id;
    public String ga_prefix;
    public String title;
}
