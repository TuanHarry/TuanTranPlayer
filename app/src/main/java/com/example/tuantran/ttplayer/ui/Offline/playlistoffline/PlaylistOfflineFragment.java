
package com.example.tuantran.ttplayer.ui.Offline.playlistoffline;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.AppDataManager;
import com.example.tuantran.ttplayer.data.DataManager;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.data.model.realm.SongRealm;
import com.example.tuantran.ttplayer.ui.Offline.songlocal.SongLocalFragment;
import com.example.tuantran.ttplayer.ui.adapter.ListSongLocalAdapter;
import com.example.tuantran.ttplayer.ui.adapter.PlayListOfflineAdapter;
import com.example.tuantran.ttplayer.ui.base.fragment.BaseFragment;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistOfflineFragment extends BaseFragment {

    PlayListOfflineAdapter mAdapter;

    @BindView(R.id.rvc_playlist_offline)
    RecyclerView mRecyclerView;

    DataManager mDataManager = AppDataManager.getInstance();

    @SuppressLint("NewApi")
    @OnClick(R.id.btn_float_add)
    public void newPlayList(){
        DialogPlaylistFragment fragment = new DialogPlaylistFragment();
        fragment.show(Objects.requireNonNull(getFragmentManager()),"");
    }


    public PlaylistOfflineFragment() {
        // Required empty public constructor
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist_offline,container,false);
        ButterKnife.bind(this, view);
        initView();
        initControl();

        return view;
    }

    private void initControl() {
        setUpRecyclerView();

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mDataManager.deleteRowPlayList(mAdapter.getData().get(position).getNamePlaylist());
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RealmList<SongRealm> realms = mDataManager.getSongInPlaylist(mAdapter.getData().get(position).getNamePlaylist());
                Log.d("123iii", "onItemClick: DataSonginPlaylist" + realms.size() +"name playlist: "+mAdapter.getData().get(position).getNamePlaylist());
                SongLocalFragment songLocal  = SongLocalFragment.getInstance(mDataManager.parseSongRealmToSongModel(realms));
                Log.d("123iiii", "onItemClick: DataSong was parse : "+ mDataManager.parseSongRealmToSongModel(realms).size());
                getFragNav().pushFragment(songLocal);
            }
        });
    }

    private void initView() {
    }

    private void setUpRecyclerView() {
        mAdapter = new PlayListOfflineAdapter(AppDataManager.getInstance().getAllPlaylist());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }



}
