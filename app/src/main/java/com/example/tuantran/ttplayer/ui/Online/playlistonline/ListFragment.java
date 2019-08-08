package com.example.tuantran.ttplayer.ui.Online.playlistonline;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.Application;
import com.example.tuantran.ttplayer.Contanst;
import com.example.tuantran.ttplayer.MediaController;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.data.model.event.SendIDEventBus;
import com.example.tuantran.ttplayer.data.network.APIService;
import com.example.tuantran.ttplayer.data.network.Dataservice;
import com.example.tuantran.ttplayer.ui.adapter.ListSongLocalAdapter;
import com.example.tuantran.ttplayer.ui.base.BaseFragment;
import com.example.tuantran.ttplayer.utils.glide.ImageHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseFragment {

    List<SongModel> data = new ArrayList<>();
    MediaController mService = Application.mService;

    ListSongLocalAdapter mAdapter;

    @BindView(R.id.rvc_listsong)
    RecyclerView mRecyclerView;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.collasingtoolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbarlistsong)
    Toolbar toolbar;
    @BindView(R.id.imagelistsong)
    ImageView imageListSong;
    @BindView(R.id.profile_image)
    CircleImageView circleImageView;
    String idReceive;
    String keyCompare;
    String name;
    String img;

    Bitmap bitmap;
    Drawable drawable;


    public static ListFragment newInstance(String id, String key, String name, String urlImg){
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString("ID",id);
        args.putString("KEY",key);
        args.putString("NAME",name);
        args.putString("URL_IMG",urlImg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            keyCompare =getArguments().getString("KEY");
            idReceive = getArguments().getString("ID");
            name = getArguments().getString("NAME");
            img = getArguments().getString("URL_IMG");
        }
    }

    private void checkKey(String key){
        if(key.equalsIgnoreCase(Contanst.INTENT_ALBUM)){
            setValue(name,img);
            GetDataAlbum(idReceive);
            new LoadImage().execute(img);
        }if(key.equalsIgnoreCase(Contanst.INTENT_PLAYLIST)){
            setValue(name,img);
            GetDataPlaylist(idReceive);
            new LoadImage().execute(img);
        }if(key.equalsIgnoreCase(Contanst.INTENT_BANNER)){
            setValue(name,img);
            GetDataBanner(idReceive);
            new LoadImage().execute(img);
        }if(key.equalsIgnoreCase(Contanst.INTENT_THELOAI)){
            setValue(name,img);
            GetDataType(idReceive);
            new LoadImage().execute(img);
        }
    }

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initControls() {
        super.initControls();
        checkKey(keyCompare);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mActivity.setSupportActionBar(toolbar);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));

        circleImageView.setEnabled(false);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_list;
    }

    private void GetDataBanner(String idBanner){
        Dataservice dataservice = APIService.getService();
        Call<List<SongModel>> call = dataservice.getListSongBanner(idBanner);
        call.enqueue(new Callback<List<SongModel>>() {
            @Override
            public void onResponse(Call<List<SongModel>> call, Response<List<SongModel>> response) {
                data = response.body();
                setUpRecyclerView(data);
                if(data.size() > 0){
                    mService.setDataSource(data);
                    mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            mService.PlayAtPosition(position);
                            mService.showNotification(data.get(position).getDisplayName(),data.get(position).getArtist(),"",mService.isPlaying);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<SongModel>> call, Throwable t) {

            }
        });

    }

    private void GetDataAlbum (String idAlbum){
        Dataservice dataservice = APIService.getService();
        Call<List<SongModel>> call = dataservice.getBaiHatTheoAlbum(idAlbum);
        call.enqueue(new Callback<List<SongModel>>() {
            @Override
            public void onResponse(Call<List<SongModel>> call, Response<List<SongModel>> response) {
                data = response.body();
                setUpRecyclerView(data);
                if(data.size() > 0){
                    mService.setDataSource(data);
                    mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            mService.PlayAtPosition(position);
                            mService.showNotification(data.get(position).getDisplayName(),data.get(position).getArtist(),"",mService.isPlaying);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<SongModel>> call, Throwable t) {

            }
        });
    }

    private void GetDataType (String idType){
        Dataservice dataservice = APIService.getService();
        Call<List<SongModel>> call = dataservice.getTheLoai(idType);
        call.enqueue(new Callback<List<SongModel>>() {
            @Override
            public void onResponse(Call<List<SongModel>> call, Response<List<SongModel>> response) {
                data = response.body();
                setUpRecyclerView(data);
                if(data.size()> 0){
                    mService.setDataSource(data);
                    mService.PlayAtPosition(0);
                    mService.showNotification(data.get(0).getDisplayName(),data.get(0).getArtist(),"",mService.isPlaying);
                }

            }
            @Override
            public void onFailure(Call<List<SongModel>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist (String idPlaylist){
        Dataservice dataservice = APIService.getService();
        Call<List<SongModel>> call = dataservice.getListSongPlaylist(idPlaylist);
        call.enqueue(new Callback<List<SongModel>>() {
            @Override
            public void onResponse(Call<List<SongModel>> call, Response<List<SongModel>> response) {
                data = response.body();
                setUpRecyclerView(data);
                if(data.size() > 0){
                    mService.setDataSource(data);
                    mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            mService.PlayAtPosition(position);
                            mService.showNotification(data.get(position).getDisplayName(),data.get(position).getArtist(),"",mService.isPlaying);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<SongModel>> call, Throwable t) {

            }
        });
    }

    private void setValue(String title, final String image) {
        collapsingToolbarLayout.setTitle(title);
        ImageHelper.load(getActivity(),imageListSong, image);
    }

    //async task load image background for collapsingToolbarLayout
    private class LoadImage extends AsyncTask<String, String, Drawable> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Drawable doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
                drawable = new BitmapDrawable(getResources(),bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return drawable;
        }

        protected void onPostExecute(Drawable img) {

            if (img != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    collapsingToolbarLayout.setBackground(img);
                }
            } else {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void setUpRecyclerView(List<SongModel> data) {
        mAdapter = new ListSongLocalAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
