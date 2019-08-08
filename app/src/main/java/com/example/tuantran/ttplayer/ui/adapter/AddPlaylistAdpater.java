package com.example.tuantran.ttplayer.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.utils.glide.ImageHelper;

import java.util.List;

public class AddPlaylistAdpater extends BaseQuickAdapter<SongModel, BaseViewHolder> {
    List<SongModel> list;
    public AddPlaylistAdpater(@Nullable List<SongModel> data) {
        super(R.layout.item_add_song_to_playlist,data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, SongModel item) {
        ImageView imageView = helper.getView(R.id.icon);
        ImageHelper.load(mContext,imageView, R.drawable.no_image);
        helper.setText(R.id.tv_name, item.getTitle());
        helper.setText(R.id.tv_singer,item.getArtist());
        helper.addOnClickListener(R.id.daucong);
    }

    @Override
    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        super.setOnItemChildClickListener(listener);
    }
}
