package com.example.tuantran.ttplayer.ui.base.activity;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();
}
