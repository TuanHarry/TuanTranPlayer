package com.example.tuantran.ttplayer.ui.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tuantran.ttplayer.ui.base.activity.MvpView;

public abstract class FragmentMVP<P extends FragmentPresenterMVP, V extends MvpView> extends BaseFragment {

    protected P mPresenter;
    protected V mView;

    public FragmentMVP() {
        makeView();
        makePresenter();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter.onAttach(mView);
        mPresenter.onReadyUI(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDestroyView(this);
        mPresenter.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy(this);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }


    protected abstract void makeView();

    protected abstract void makePresenter();

}
