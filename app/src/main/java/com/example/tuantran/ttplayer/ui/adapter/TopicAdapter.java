package com.example.tuantran.ttplayer.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.ChuDeModel;
import com.example.tuantran.ttplayer.data.model.TheLoaiModel;
import com.example.tuantran.ttplayer.utils.glide.ImageHelper;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends BaseQuickAdapter<TheLoaiModel, BaseViewHolder> {
    List<TheLoaiModel> list = new ArrayList<>();
    public TopicAdapter(@Nullable List<TheLoaiModel> data) {
        super(R.layout.item_topic,data);
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, TheLoaiModel item) {
        ImageView imageView = helper.getView(R.id.image_playlist);
        ImageHelper.load(mContext,imageView, item.getHinhTheLoai());
        helper.setText(R.id.tv_name_playlist, item.getTenTheLoai());
        helper.setVisible(R.id.tv_singer,false);
    }
}
