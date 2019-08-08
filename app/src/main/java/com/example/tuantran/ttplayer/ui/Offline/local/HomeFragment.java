package com.example.tuantran.ttplayer.ui.Offline.local;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.Item;
import com.example.tuantran.ttplayer.ui.Offline.playlistoffline.PlaylistOfflineFragment;
import com.example.tuantran.ttplayer.ui.Offline.songlocal.SongLocalFragment;
import com.example.tuantran.ttplayer.ui.adapter.DashBoardMultiItemAdapter;
import com.example.tuantran.ttplayer.ui.base.fragment.FragmentMVP;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends FragmentMVP<HomePresenter,IHome> implements IHome {

    private HomeViewModel mViewModel;
    DashBoardMultiItemAdapter mAdapter;
    @BindView(R.id.recyclerviewlib)
    RecyclerView mRecyclerViewlib;

    List<Item> mListItemDashBoard = new ArrayList<>();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, rootView);
        setupRecycleView();
        return rootView;
    }

    @Override
    protected void makeView() {
        mView = this;
    }

    @Override
    protected void makePresenter() {
        mPresenter = new HomePresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        // TODO: Use the ViewModel
    }

    private void setupRecycleView() {

        mListItemDashBoard = mPresenter.getLocalItem();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewlib.setLayoutManager(layoutManager);

        mAdapter = new DashBoardMultiItemAdapter(mListItemDashBoard);
        mRecyclerViewlib.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        SongLocalFragment songLocalFragment = new SongLocalFragment();
                        getFragNav().pushFragment(songLocalFragment);
                        break;
                    case 1:
                        PlaylistOfflineFragment fragment = new PlaylistOfflineFragment();
                        getFragNav().pushFragment(fragment);
                        break;
                    case 2:
                        Toast.makeText(mActivity, "favorite", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }



}
