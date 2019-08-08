package com.example.tuantran.ttplayer.ui.adapter;

import android.content.res.Resources;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.Item;

import java.util.List;

public class DashBoardMultiItemAdapter extends BaseMultiItemQuickAdapter<Item, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DashBoardMultiItemAdapter(List<Item> data) {
        super(data);
        addItemType(2, R.layout.item_dashboard_sticky);
        addItemType(3, R.layout.item_lib_subject);
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {
        switch (item.getItemType()) {
            case 2:
                ((TextView) helper.itemView).setText(item.getTitle());
                break;
            case 3:
                setupViewForItem(helper, item);
                break;

        }
    }

    private void setupViewForItem(BaseViewHolder helper, Item item) {

        TextView textViewTitle = helper.getView(R.id.tv_name);
        textViewTitle.setText(item.getTitle());
        Resources resources = mContext.getResources();
        int resourceId = resources.getIdentifier(item.getImage(), "drawable",
                mContext.getPackageName());
        helper.setImageResource(R.id.icon,resourceId);
        helper.setText(R.id.tv_count,item.getSub());

    }
}
