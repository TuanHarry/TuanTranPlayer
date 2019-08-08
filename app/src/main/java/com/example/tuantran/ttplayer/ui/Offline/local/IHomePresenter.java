package com.example.tuantran.ttplayer.ui.Offline.local;

import com.example.tuantran.ttplayer.data.model.Item;

import java.util.List;

public interface IHomePresenter {
    List<Item> getAllItemDashBoard();

    List<Item> getLocalItem();
}
