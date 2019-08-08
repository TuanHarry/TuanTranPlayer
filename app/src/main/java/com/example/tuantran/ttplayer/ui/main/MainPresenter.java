package com.example.tuantran.ttplayer.ui.main;

import com.example.tuantran.ttplayer.data.AppDataManager;
import com.example.tuantran.ttplayer.data.DataManager;
import com.example.tuantran.ttplayer.ui.base.activity.BasePresenter;


public class MainPresenter<V extends IMain> extends BasePresenter<V> implements IMainPresenter<V> {
    private DataManager mDataManager;


    MainPresenter() {
        mDataManager = AppDataManager.getInstance();
    }


}
