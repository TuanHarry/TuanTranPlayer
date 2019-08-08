package com.example.tuantran.ttplayer.ui.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.Item;
import com.example.tuantran.ttplayer.ui.adapter.DashBoardMultiItemAdapter;
import com.example.tuantran.ttplayer.ui.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DashBoardActivity extends BaseActivity implements IView {

    DashBoardMultiItemAdapter mAdapter;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    List<Item> mListItemDashBoard = new ArrayList<>();

    IPresenter<IView> mPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_dash_board;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        mPresenter = new DashBoardPresenter<>();
        mPresenter.onAttach(this);
        setupRecycleView();
    }

    @Override
    protected void initControls() {

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 1:
                        break;
                    case 2:
                        mPresenter.goPlaylist();
                        break;
                    case 3:
                        mPresenter.goFavorite();
                        break;
                    case 4:
                        mPresenter.goArtist();
                        break;
                    case 6:
                        mPresenter.goWifiTransfer();
                        break;
                }
            }
        });
    }

    private void setupRecycleView() {

        mListItemDashBoard = mPresenter.getAllItemDashBoard();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.HORIZONTAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mAdapter = new DashBoardMultiItemAdapter(mListItemDashBoard);
        mRecyclerView.setAdapter(mAdapter);
    }
}
