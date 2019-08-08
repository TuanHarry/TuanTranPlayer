package com.example.tuantran.ttplayer.data;


import com.example.tuantran.ttplayer.Application;
import com.example.tuantran.ttplayer.data.database.AppDatabaseHelper;
import com.example.tuantran.ttplayer.data.model.Item;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.data.model.realm.PlaylistRealm;
import com.example.tuantran.ttplayer.data.model.realm.SongRealm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class AppDataManager implements DataManager {
    private List<SongModel> dataLocalSong;
    private List<SongModel> dataPlaylist;
    private static AppDataManager INSTANCE;
    private final Gson mGson;
    private List<Item> itemListDashBoard;
    private AppDatabaseHelper mDatabaseHeleper;
    private AppDataManager() {
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz")
                .create();
        mDatabaseHeleper = new AppDatabaseHelper(Application.Context);
    }

    public static AppDataManager getInstance() {
        if (INSTANCE == null) {
            return INSTANCE = new AppDataManager();
        }
        return INSTANCE;
    }

    public List<SongModel> getDataLocalSong() {
        return this.dataLocalSong;
    }

    public void setDataLocalSong(List<SongModel> dataLocalSong) {
        this.dataLocalSong = dataLocalSong;
    }

    public List<SongModel> getDataPlaylist() {
        return dataPlaylist;
    }

    public void setDataPlaylist(List<SongModel> dataPlaylist) {
        this.dataPlaylist = dataPlaylist;
    }

    @Override
    public List<Item> getAllItem() {
        if (itemListDashBoard == null) {
            try {
                String jsonLetters = "";
                InputStream inputStream = Application.Context.getAssets().open("data.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                jsonLetters = new String(buffer, "UTF-8");

                Type listType = new TypeToken<List<Item>>() {
                }.getType();

                return itemListDashBoard = mGson.fromJson(jsonLetters, listType);
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        return itemListDashBoard;
    }

    public RealmList<SongRealm> parseSongModelToRealm(List<SongModel> listSong){
        RealmList<SongRealm> realms = new RealmList<>();
        for(int i = 0 ; i < listSong.size(); i++) {
            SongRealm songRealm = new SongRealm();
            songRealm.setIdSong(listSong.get(i).getId());
            songRealm.setArtis(listSong.get(i).getArtist());
            songRealm.setNameSong(listSong.get(i).getTitle());
            songRealm.setStreamUri(listSong.get(i).getStreamUri());
            realms.add(songRealm);
        }
        return realms;
    }

    @Override
    public List<SongModel> parseSongRealmToSongModel(RealmList<SongRealm> realms) {
        List<SongModel> listSong = new ArrayList<>();
        for(int i = 0 ; i < realms.size(); i++){
            SongModel song = new SongModel();
            song.setId(realms.get(i).getIdSong());
            song.setArtist(realms.get(i).getArtis());
            song.setTitle(realms.get(i).getNameSong());
            song.setStreamUri(realms.get(i).getStreamUri());
            listSong.add(song);
        }
        return listSong;
    }


    @Override
    public List<PlaylistRealm> getAllPlaylist() {
        return mDatabaseHeleper.getAllPlaylist();
    }

    @Override
    public void createNewPlaylist(PlaylistRealm playlistRealm) {
        mDatabaseHeleper.createNewPlaylist(playlistRealm);
    }

    @Override
    public void deleteRowPlayList(String namePlayList) {
        mDatabaseHeleper.deletePlaylist(namePlayList);
    }

    @Override
    public RealmList<SongRealm> getSongInPlaylist(String namePlaylist) {
        return mDatabaseHeleper.getSonginPlayList(namePlaylist);
    }
}
