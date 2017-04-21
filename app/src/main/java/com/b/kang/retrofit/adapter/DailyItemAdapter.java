package com.b.kang.retrofit.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.b.kang.retrofit.R;
import com.b.kang.retrofit.model.BaseDailyItem;
import com.b.kang.retrofit.model.DailyLatestDailyItem;
import com.b.kang.retrofit.model.DailyLatestTopDailyItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kang on 17-4-21.
 */
public class DailyItemAdapter<T extends BaseDailyItem> extends BaseQuickAdapter<T, BaseViewHolder> {

    private Context context;

    public DailyItemAdapter(List<T> data, Context context) {
        super(R.layout.list_item_daily, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, T item) {
        Log.d("ToString",item.toString());
        if (item instanceof DailyLatestDailyItem) {
            Picasso.with(context).load(((DailyLatestDailyItem) item).images[0]).into((ImageView) holder.getView(R.id.image));
            holder.setText(R.id.title, ((DailyLatestDailyItem) item).title);
        }else if (item instanceof DailyLatestTopDailyItem) {
            Picasso.with(context).load(((DailyLatestTopDailyItem) item).image).into((ImageView) holder.getView(R.id.image));
            holder.setText(R.id.title, ((DailyLatestTopDailyItem) item).title);
        }
    }
}
