package com.example.tuantran.ttplayer.ui.Online.playlistonline;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.Contanst;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.PlayListOnlineModel;
import com.example.tuantran.ttplayer.data.network.APIService;
import com.example.tuantran.ttplayer.data.network.Dataservice;
import com.example.tuantran.ttplayer.ui.adapter.PlayListOnlineAdapter;
import com.example.tuantran.ttplayer.ui.base.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayListOnlineFragment extends BaseFragment {

    @BindView(R.id.rvc_playlist)
    RecyclerView recyclerView;
    @BindView(R.id.tv_viewmore_playlist)
    TextView tvViewMore;
    @BindView(R.id.linear_title_playlist)
    LinearLayout linearLayout;

    List<PlayListOnlineModel> listData;
    PlayListOnlineAdapter playListAdapter;
    PlayListViewModel playListViewModel;
    public PlayListOnlineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        playListViewModel = ViewModelProviders.of(getActivity()).get(PlayListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_list_online, container, false);
        ButterKnife.bind(this, view);
        initControls();
        return view;
    }

    private void initControls() {
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<PlayListOnlineModel>> call= dataservice.getPlayList() ;
        call.enqueue(new Callback<List<PlayListOnlineModel>>() {
            @Override
            public void onResponse(Call<List<PlayListOnlineModel>> call, Response<List<PlayListOnlineModel>> response) {
                listData = response.body();
                playListAdapter = new PlayListOnlineAdapter(listData);
                addDataToRecyclerViewHorizantal(playListAdapter);
                playListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                        String idPlaylist = listData.get(position).getIDPlayList();
                        String namePlaylis = listData.get(position).getTenPlayList();
                        String urlImg = listData.get(position).getHinhPlayList();
                        //EventBus.getDefault().post(new SendIDEventBus(listData.get(position).getIDPlayList(),Contanst.INTENT_PLAYLIST));
                        getFragNav().pushFragment(ListFragment.newInstance(idPlaylist, Contanst.INTENT_PLAYLIST,namePlaylis,urlImg));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<PlayListOnlineModel>> call, Throwable t) {

            }
        });
    }

    public void addDataToRecyclerViewHorizantal(PlayListOnlineAdapter adapter){
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



}
