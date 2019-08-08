package com.example.tuantran.ttplayer.ui.Offline.songlocal;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.Contanst;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.AppDataManager;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.data.model.event.SendDataSongRecent;
import com.example.tuantran.ttplayer.ui.adapter.ListSongLocalAdapter;
import com.example.tuantran.ttplayer.ui.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongLocalFragment extends BaseFragment {

    ListSongLocalAdapter mAdapter;
    static String KEY = "LIST_SONG";

    List<SongModel> dataRecyclerView = new ArrayList<>();
    @OnClick(R.id.icon_back)
    void back(){
        getActivity().onBackPressed();
    }
    public static SongLocalFragment getInstance(List<SongModel> list){
        SongLocalFragment fragment = new SongLocalFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY, (ArrayList<? extends Parcelable>) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            dataRecyclerView = getArguments().getParcelableArrayList(KEY);
            Log.d("123iiii", "onCreate: "+dataRecyclerView.size());
        }else {
            dataRecyclerView = AppDataManager.getInstance().getDataLocalSong();
        }
    }

    @BindView(R.id.rvc_list_song_local)
    RecyclerView mRecyclerView;
    public SongLocalFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_song_local;
    }

    @Override
    protected void initControls() {
        super.initControls();
        setUpRecyclerView();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().post(new SendDataSongRecent(dataRecyclerView, Contanst.INTENT_SONG, position));
            }
        });
    }

    private void setUpRecyclerView() {
        mAdapter = new ListSongLocalAdapter(dataRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
