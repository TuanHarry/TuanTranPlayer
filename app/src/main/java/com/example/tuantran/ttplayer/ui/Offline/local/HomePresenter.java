package com.example.tuantran.ttplayer.ui.Offline.local;

import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.AppDataManager;
import com.example.tuantran.ttplayer.data.model.Item;
import com.example.tuantran.ttplayer.ui.base.fragment.FragmentPresenterMVP;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter extends FragmentPresenterMVP<IHome> implements IHomePresenter {
    @Override
    public List<Item> getAllItemDashBoard() {
        return AppDataManager.getInstance().getAllItem();
    }

    @Override
    public List<Item> getLocalItem() {
        List<Item> data = new ArrayList<>();
        data.add(new Item(3,"Bài hát", "", ""+ R.drawable.ic_song,AppDataManager.getInstance().getDataLocalSong().size()+""));
        data.add(new Item(3,"Playlist", "", ""+R.drawable.ic_playlist_play,AppDataManager.getInstance().getAllPlaylist().size()+""));
        data.add(new Item(3,"Nhạc yêu thích", "", ""+R.drawable.ic_favorite_border,"1"));
        return data;
    };


}
