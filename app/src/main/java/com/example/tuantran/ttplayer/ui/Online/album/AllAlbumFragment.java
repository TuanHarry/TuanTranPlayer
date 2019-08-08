package com.example.tuantran.ttplayer.ui.Online.album;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllAlbumFragment extends BaseFragment {


    @BindView(R.id.rvc_album)
    RecyclerView recyclerView;

    @BindView(R.id.text_view_title)
    TextView title;
    List<AlbumModel> data;

    @OnClick(R.id.icon_back)
    void back(){
        getActivity().onBackPressed();
    }

    public AllAlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_album, container, false);
        ButterKnife.bind(this,view);
        initControl();
        return view;
    }

    private void initControl() {
        data = new ArrayList<>();
        title.setText("Tất cả ablum");
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<AlbumModel>> call = dataservice.getAllAlbum();
        call.enqueue(new Callback<List<AlbumModel>>() {
            @Override
            public void onResponse(Call<List<AlbumModel>> call, Response<List<AlbumModel>> response) {
                data = response.body();
                AlbumAdapter adapter = new AlbumAdapter(data);
                addDataToRecyclerViewVertical(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String idAlbum = data.get(position).getIDAlbum();
                        String nameAlbum = data.get(position).getTenAlbum();
                        String urlImg = data.get(position).getHinhAnh();
                        getFragNav().pushFragment(ListFragment.newInstance(idAlbum, Contanst.INTENT_ALBUM,nameAlbum,urlImg));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<AlbumModel>> call, Throwable t) {

            }
        });
    }

    private void addDataToRecyclerViewVertical(AlbumAdapter albumAdapter){
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(albumAdapter);
    }

}
