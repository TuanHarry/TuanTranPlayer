package com.example.tuantran.ttplayer.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.AlbumModel;
import com.example.tuantran.ttplayer.utils.glide.ImageHelper;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends BaseQuickAdapter<AlbumModel, BaseViewHolder> {
    List<AlbumModel> list = new ArrayList<>();
    public AlbumAdapter(@Nullable List<AlbumModel> data) {
        super(R.layout.item_topic,data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, AlbumModel item) {
        ImageView imageView = helper.getView(R.id.image_playlist);
        ImageHelper.load(mContext,imageView,item.getHinhAnh());
        helper.setText(R.id.tv_name_playlist, item.getTenAlbum());
        helper.setText(R.id.tv_singer, item.getTenCaSiAl());
    }
}
