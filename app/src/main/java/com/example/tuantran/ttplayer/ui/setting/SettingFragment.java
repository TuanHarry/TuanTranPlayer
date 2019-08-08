package com.example.tuantran.ttplayer.ui.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.ui.adapter.ListSongLocalAdapter;
import com.example.tuantran.ttplayer.ui.adapter.SettingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    SettingAdapter adapter;

    @BindView(R.id.rvc_setting)
    RecyclerView recyclerView;

    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment getInstance(){
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this,view);
        setUpRecyclerView();
        return view;
    }


    // setup adapter
    private void setUpRecyclerView() {
        adapter = new SettingAdapter(HardData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


//TODO Return list add to recyclerview
    private List<String> HardData(){
        List<String> data = new ArrayList<>();
        data.add("Giới thiệu về ứng dụng");
        data.add("Chia sẻ ứng dụng");
        data.add("Đánh giá ứng dụng");
        data.add("Theo dõi chúng tôi trên facebook");
        data.add("Đóng góp của bạn về ứng dụng");
        return  data;
    }
}
