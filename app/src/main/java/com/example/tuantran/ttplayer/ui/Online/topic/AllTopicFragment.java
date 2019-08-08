package com.example.tuantran.ttplayer.ui.Online.topic;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.Contanst;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.model.TheLoaiModel;
import com.example.tuantran.ttplayer.data.network.APIService;
import com.example.tuantran.ttplayer.data.network.Dataservice;
import com.example.tuantran.ttplayer.ui.Online.playlistonline.ListFragment;
import com.example.tuantran.ttplayer.ui.adapter.AllTopicAdapter;
import com.example.tuantran.ttplayer.ui.adapter.TopicAdapter;
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
public class AllTopicFragment extends BaseFragment {


    @BindView(R.id.rvc_playlist)
    RecyclerView mRecyclerView;

    @BindView(R.id.text_view_title)
    TextView title;

    List<TheLoaiModel> data;
    String idChude;
    String nameChude;

    @OnClick(R.id.icon_back)
    void back(){
        getActivity().onBackPressed();
    }

    public static AllTopicFragment getInstance(String id, String nameChude){
        AllTopicFragment fragment = new AllTopicFragment();
        Bundle args = new Bundle();
        args.putString("ID",id);
        args.putString("Name",nameChude);
        fragment.setArguments(args);
        return fragment;
    }

    public static AllTopicFragment getInstanceAllTopic(List<TheLoaiModel> data){
        AllTopicFragment fragment = new AllTopicFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("Data", (ArrayList<? extends Parcelable>) data);
        fragment.setArguments(args);
        return fragment;
    }

    public AllTopicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            idChude = getArguments().getString("ID");
            nameChude = getArguments().getString("Name");
            data = getArguments().getParcelableArrayList("Data");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_playlist, container, false);
        ButterKnife.bind(this,view);

        if(data!= null){
            title.setText("Tất cả thể loại");
            AllTopicAdapter adapter = new AllTopicAdapter(data);
            addDataRecyclerViewAllTopic(adapter);

            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String id = data.get(position).getIDTheLoai();
                    String name = data.get(position).getTenTheLoai();
                    String urlImg = data.get(position).getHinhTheLoai();
                    getFragNav().pushFragment(ListFragment.newInstance(id,Contanst.INTENT_THELOAI,name,urlImg));
                }
            });
        }else {
            initControl();
        }
        return view;
    }

    private void initControl() {
        title.setText(nameChude);
        data = new ArrayList<>();

        GetData(idChude);
    }

    private void GetData(String idChude) {
        Dataservice dataservice = APIService.getService();
        Call<List<TheLoaiModel>> callBack = dataservice.getTheLoaiTheoChuDe(idChude);
        callBack.enqueue(new Callback<List<TheLoaiModel>>() {
            @Override
            public void onResponse(Call<List<TheLoaiModel>> call, Response<List<TheLoaiModel>> response) {
                data = response.body();
                TopicAdapter adapter = new TopicAdapter(data);
                addDataToRecyclerView(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String id = data.get(position).getIDTheLoai();
                        String name = data.get(position).getTenTheLoai();
                        String urlImg = data.get(position).getHinhTheLoai();
                        getFragNav().pushFragment(ListFragment.newInstance(id, Contanst.INTENT_THELOAI,name,urlImg));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<TheLoaiModel>> call, Throwable t) {

            }
        });
    }

    private void addDataToRecyclerView(TopicAdapter adapter){
        GridLayoutManager layoutManager
                = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    private void addDataRecyclerViewAllTopic(AllTopicAdapter adapter){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }






}
