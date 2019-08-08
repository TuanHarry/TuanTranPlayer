package com.example.tuantran.ttplayer.data.database;

import com.example.tuantran.ttplayer.data.model.realm.PlaylistRealm;
import com.example.tuantran.ttplayer.data.model.realm.SongRealm;

import java.util.List;

import io.realm.RealmList;

public interface DatabaseHelper {
    void createNewPlaylist(PlaylistRealm playlistRealm);
    List<PlaylistRealm> getAllPlaylist();
    void deletePlaylist(String namePlayList);
    RealmList<SongRealm> getSonginPlayList(String namePlayList);
}
