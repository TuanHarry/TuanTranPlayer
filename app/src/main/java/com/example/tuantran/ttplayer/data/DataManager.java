package com.example.tuantran.ttplayer.data;

import com.example.tuantran.ttplayer.data.model.Item;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.data.model.realm.PlaylistRealm;
import com.example.tuantran.ttplayer.data.model.realm.SongRealm;

import java.util.List;

import io.realm.RealmList;

public interface DataManager {

    List<Item> getAllItem();

    // Synchronized method in database
    List<PlaylistRealm> getAllPlaylist();
    void createNewPlaylist(PlaylistRealm playlistRealm);
    void deleteRowPlayList(String namePlayList);
    RealmList<SongRealm> getSongInPlaylist(String namePlaylist);
    RealmList<SongRealm> parseSongModelToRealm(List<SongModel> listSong);
    List<SongModel> parseSongRealmToSongModel (RealmList<SongRealm> realms);
}
