package com.b.kang.retrofit.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.b.kang.retrofit.R;
import com.b.kang.retrofit.model.DailyLatestItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kang on 17-4-21.
 */
public class DailyItemAdapter extends BaseQuickAdapter<DailyLatestItem, BaseViewHolder> {

    private Context context;

    public DailyItemAdapter(List<DailyLatestItem> data, Context context) {
        super(R.layout.list_item_daily, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, DailyLatestItem item) {
        Picasso.with(context).load(item.images[0]).into((ImageView)holder.getView(R.id.image));
        holder.setText(R.id.title,item.title);
    }
}
