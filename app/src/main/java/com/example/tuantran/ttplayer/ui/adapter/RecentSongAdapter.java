package com.example.tuantran.ttplayer.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.utils.glide.ImageHelper;

import java.util.ArrayList;
import java.util.List;

public class RecentSongAdapter extends BaseQuickAdapter<SongModel, BaseViewHolder> {
    List<SongModel> list = new ArrayList<>();
    public RecentSongAdapter(@Nullable List<SongModel> data) {
        super(R.layout.item_playlist,data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, SongModel item) {
        ImageView imageView = helper.getView(R.id.image);
        ImageHelper.load(mContext,imageView,item.getHinhBaiHat());
        helper.setText(R.id.song_name, item.getTitle());
        helper.setText(R.id.song_artist,"Ca sĩ: "+item.getArtist());
        helper.setText(R.id.duration,"Tổng số lượt thích: "+ item.getLuotThich());
    }
}
