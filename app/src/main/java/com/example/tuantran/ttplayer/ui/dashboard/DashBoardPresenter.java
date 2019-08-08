package com.example.tuantran.ttplayer.ui.dashboard;

import com.example.tuantran.ttplayer.data.model.Item;
import com.example.tuantran.ttplayer.ui.base.activity.BasePresenter;

import java.util.List;

public class DashBoardPresenter<V extends IView> extends BasePresenter<V> implements IPresenter<V> {
    @Override
    public List<Item> getAllItemDashBoard() {
        return getDataManager().getAllItem();
    }

    @Override
    public void goFavorite() {

    }

    @Override
    public void goPlaylist() {

    }

    @Override
    public void goWifiTransfer() {

    }

    @Override
    public void goArtist() {

    }
}
