package com.example.tuantran.ttplayer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuantran.ttplayer.data.AppDataManager;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.ui.adapter.NowPlayListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class PlaylistDialogFragment extends BottomSheetDialogFragment {


    private Listener mListener;
    RecyclerView mRecyclerView;
    private List<SongModel> mData = new ArrayList<>();
    private NowPlayListAdapter mAdapter;
    private int currentIndex = 0;

    // TODO: Customize parameters
    public static PlaylistDialogFragment newInstance() {
        final PlaylistDialogFragment fragment = new PlaylistDialogFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
        mRecyclerView = root.findViewById(R.id.list);

        ButterKnife.bind(root);
        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mData.addAll(AppDataManager.getInstance().getDataPlaylist());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NowPlayListAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), "Ahihi", Toast.LENGTH_SHORT).show();
            }
        });

        //currentIndex = MediaController.newInstance().getCurrentIndex();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        final Fragment parent = getParentFragment();
//        if (parent != null) {
//            mListener = (Listener) parent;
//        } else {
//            mListener = (Listener) context;
//        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public interface Listener {
        void onItemClicked(int position);
    }
}
