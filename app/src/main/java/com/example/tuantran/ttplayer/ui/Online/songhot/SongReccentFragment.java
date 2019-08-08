package com.example.tuantran.ttplayer.ui.Online.songhot;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.Contanst;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.data.model.event.SendDataSongRecent;
import com.example.tuantran.ttplayer.data.network.APIService;
import com.example.tuantran.ttplayer.data.network.Dataservice;
import com.example.tuantran.ttplayer.ui.adapter.RecentSongAdapter;
import com.example.tuantran.ttplayer.ui.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongReccentFragment extends BaseFragment {

    @BindView(R.id.rvc_playlist)
    RecyclerView recyclerView;

    List<SongModel> list;

    public SongReccentFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initControls() {
        super.initControls();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<SongModel>> call = dataservice.getBaiHatFavorite();
        call.enqueue(new Callback<List<SongModel>>() {
            @Override
            public void onResponse(Call<List<SongModel>> call, Response<List<SongModel>> response) {
                list = response.body();
                RecentSongAdapter recentSongAdapter = new RecentSongAdapter(list);
                addDataToRecyclerView(recentSongAdapter);
                recentSongAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        EventBus.getDefault().post(new SendDataSongRecent(list, Contanst.INTENT_SONG, position));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<SongModel>> call, Throwable t) {

            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_song_reccent;
    }

    private void addDataToRecyclerView(RecentSongAdapter albumAdapter){
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(albumAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }



}
