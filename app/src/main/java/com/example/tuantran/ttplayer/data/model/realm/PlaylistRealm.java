package com.example.tuantran.ttplayer.data.model.realm;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PlaylistRealm extends RealmObject implements RealmModel {
    @PrimaryKey
    private String idPlaylist;
    private String namePlaylist;

    public RealmList<SongRealm> getList() {
        return list;
    }

    public void setList(RealmList<SongRealm> list) {
        this.list = list;
    }

    private RealmList<SongRealm> list = new RealmList<>();

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

}
