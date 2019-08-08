package com.example.tuantran.ttplayer.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.TheLoaiModel;
import com.example.tuantran.ttplayer.utils.glide.ImageHelper;

import java.util.ArrayList;
import java.util.List;

public class AllTopicAdapter extends BaseQuickAdapter<TheLoaiModel, BaseViewHolder> {
    List<TheLoaiModel> list = new ArrayList<>();

    public AllTopicAdapter(@Nullable List<TheLoaiModel> data) {
        super(R.layout.line_topic,data);
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, TheLoaiModel item) {
        ImageView icon = helper.getView(R.id.imageiconplaylist);
        ImageView background = helper.getView(R.id.imageviewbackground_playlist);
        ImageHelper.load(mContext,icon,item.getHinhTheLoai());
        ImageHelper.load(mContext,background,item.getHinhTheLoai());
        helper.setText(R.id.tv_name_playlist,item.getTenTheLoai());
    }
}
