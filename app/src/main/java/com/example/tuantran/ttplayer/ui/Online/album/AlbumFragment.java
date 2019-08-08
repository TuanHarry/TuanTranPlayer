package com.example.tuantran.ttplayer.ui.Online.album;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.Contanst;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.AlbumModel;
import com.example.tuantran.ttplayer.data.network.APIService;
import com.example.tuantran.ttplayer.data.network.Dataservice;
import com.example.tuantran.ttplayer.ui.Online.playlistonline.ListFragment;
import com.example.tuantran.ttplayer.ui.adapter.AlbumAdapter;
import com.example.tuantran.ttplayer.ui.base.fragment.BaseFragment;
import com.example.tuantran.ttplayer.ui.main.Main2Activity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends BaseFragment {

    @BindView(R.id.rvc_playlist)
    RecyclerView recyclerView;
    @BindView(R.id.tv_viewmore_playlist)
    TextView tvViewMore;

    Main2Activity main;
    List<AlbumModel> list;


    public AlbumFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        ButterKnife.bind(this, view);
        initControls();
        return view;

    }

    private void initControls() {
        main = (Main2Activity) getActivity();
        GetData();

        tvViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllAlbumFragment fragment = new AllAlbumFragment();
                getFragNav().pushFragment(fragment);
            }
        });
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<AlbumModel>> call = dataservice.getAlbum();
        call.enqueue(new Callback<List<AlbumModel>>() {
            @Override
            public void onResponse(Call<List<AlbumModel>> call, Response<List<AlbumModel>> response) {
                list = response.body();
                AlbumAdapter adapter = new AlbumAdapter(list);
                addDataToRecyclerViewHorizontal(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        //EventBus.getDefault().post(new SendIDEventBus(list.get(position).getIDAlbum(), Contanst.INTENT_ALBUM));
                        String idAlbum = list.get(position).getIDAlbum();
                        String nameAlbum = list.get(position).getTenAlbum();
                        String urlImg = list.get(position).getHinhAnh();
                        getFragNav().pushFragment(ListFragment.newInstance(idAlbum, Contanst.INTENT_ALBUM,nameAlbum,urlImg));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<AlbumModel>> call, Throwable t) {

            }
        });
    }

    private void addDataToRecyclerViewHorizontal(AlbumAdapter albumAdapter){
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(albumAdapter);
    }

}
