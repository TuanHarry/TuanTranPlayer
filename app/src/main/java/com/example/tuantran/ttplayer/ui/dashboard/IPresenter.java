package com.example.tuantran.ttplayer.ui.dashboard;

import com.example.tuantran.ttplayer.data.model.Item;
import com.example.tuantran.ttplayer.ui.base.activity.MvpPresenter;

import java.util.List;

public interface IPresenter<V extends IView> extends MvpPresenter<V> {
    List<Item> getAllItemDashBoard();
    void goFavorite();
    void goPlaylist();
    void goWifiTransfer();
    void goArtist();
}
