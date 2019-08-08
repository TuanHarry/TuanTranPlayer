package com.example.tuantran.ttplayer.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.PlayListOnlineModel;
import com.example.tuantran.ttplayer.utils.glide.ImageHelper;

import java.util.ArrayList;
import java.util.List;

public class PlayListOnlineAdapter extends BaseQuickAdapter<PlayListOnlineModel, BaseViewHolder> {
    private List<PlayListOnlineModel> list = new ArrayList<>();
    public PlayListOnlineAdapter(@Nullable List<PlayListOnlineModel> data) {
        super(R.layout.line_playlist,data);
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, PlayListOnlineModel item) {
        ImageView imageView = helper.getView(R.id.image);
        ImageHelper.load(mContext,imageView, item.getIconPlayList());
        helper.setText(R.id.tv_name_playlist, item.getTenPlayList());
        helper.setVisible(R.id.tv_singer,false);
    }
}
