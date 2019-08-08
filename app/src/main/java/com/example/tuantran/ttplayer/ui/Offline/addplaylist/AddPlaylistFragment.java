package com.example.tuantran.ttplayer.ui.Offline.addplaylist;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.AppDataManager;
import com.example.tuantran.ttplayer.data.DataManager;
import com.example.tuantran.ttplayer.data.database.AppDatabaseHelper;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.data.model.realm.PlaylistRealm;
import com.example.tuantran.ttplayer.data.model.realm.SongRealm;
import com.example.tuantran.ttplayer.ui.Offline.playlistoffline.DialogPlaylistFragment;
import com.example.tuantran.ttplayer.ui.Offline.playlistoffline.PlaylistOfflineFragment;
import com.example.tuantran.ttplayer.ui.adapter.AddPlaylistAdpater;
import com.example.tuantran.ttplayer.ui.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlaylistFragment extends BaseFragment {

    AddPlaylistAdpater mAdapter;

    @BindView(R.id.rvc_add_playlist)
    RecyclerView mRecyclerView;

    @BindView(R.id.text_view_title)
    TextView title;

    String namePlaylist;
    private static String KEY = "name";
    List<SongModel> listSong;

    @OnClick(R.id.icon_back)
    void back(){
        getActivity().onBackPressed();
    }

    DataManager mDataManager = AppDataManager.getInstance();

    @OnClick(R.id.ic_done)
    public void onClickIconDone(){
        // save database
        PlaylistRealm playlistRealm = new PlaylistRealm();
        playlistRealm.setIdPlaylist(System.currentTimeMillis()+"");
        playlistRealm.setList(mDataManager.parseSongModelToRealm(listSong));
        Log.d("123iii", "initControls: "+listSong.size());
        playlistRealm.setNamePlaylist(title.getText().toString());
        mDataManager.createNewPlaylist(playlistRealm);
        PlaylistOfflineFragment fragment = new PlaylistOfflineFragment();
        getFragNav().pushFragment(fragment);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_playlist,container,false);
        ButterKnife.bind(this, view);
        initViews();
        initControls();
        setUpRecyclerView();
        return view;
    }


    public static AddPlaylistFragment getInstance(String namePlaylist){
        AddPlaylistFragment fragment = new AddPlaylistFragment();
        Bundle args = new Bundle();
        args.putString(KEY, namePlaylist);
        fragment.setArguments(args);
        return fragment;
    }

    public AddPlaylistFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            namePlaylist = getArguments().getString(KEY);
        }
    }


    private void initViews() {
        listSong = new ArrayList<>();
    }

    private void initControls() {
        setUpRecyclerView();
        title.setText(namePlaylist);
    }


    private void setUpRecyclerView() {
        mAdapter = new AddPlaylistAdpater(AppDataManager.getInstance().getDataLocalSong());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                listSong.add(mAdapter.getData().get(position));
                ImageView img = view.findViewById(R.id.daucong);
                img.setImageResource(R.drawable.ic_done);
                img.setColorFilter(Color.RED);
            }
        });
    }
}
