package com.example.tuantran.ttplayer.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tuantran.ttplayer.R;

import java.util.ArrayList;
import java.util.List;

public class SettingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    List<String> list = new ArrayList<>();
    public SettingAdapter(@Nullable List<String> data) {
        super(R.layout.item_setting,data);
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.content_text,item);
    }
}
