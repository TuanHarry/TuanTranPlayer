package com.example.tuantran.ttplayer.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.realm.PlaylistRealm;
import com.example.tuantran.ttplayer.utils.glide.ImageHelper;

import java.util.List;

public class PlayListOfflineAdapter extends BaseQuickAdapter<PlaylistRealm, BaseViewHolder> {
    List<PlaylistRealm> list;
    public PlayListOfflineAdapter(@Nullable List<PlaylistRealm> data) {
        super(R.layout.item_playlist_offline,data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, PlaylistRealm item) {
        ImageView imageView = helper.getView(R.id.icon);
        ImageHelper.load(mContext,imageView,R.drawable.no_image);
        helper.setText(R.id.tv_name, item.getNamePlaylist());
        helper.setText(R.id.tv_singer, item.getList().size()+"");
        helper.addOnClickListener(R.id.imageView);
    }

    @Override
    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        super.setOnItemChildClickListener(listener);
    }
}
