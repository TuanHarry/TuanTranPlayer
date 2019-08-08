package com.example.tuantran.ttplayer.data.database;

import android.content.Context;

import com.example.tuantran.ttplayer.data.model.realm.PlaylistRealm;
import com.example.tuantran.ttplayer.data.model.realm.SongRealm;

import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public class AppDatabaseHelper implements DatabaseHelper {

    private Realm mRealm;

    public AppDatabaseHelper(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .schemaVersion(2)
                .migration(new Migration())
                .build();
        mRealm = Realm.getInstance(config);

    }

    // Create new playlist
    @Override
    public void createNewPlaylist(final PlaylistRealm playlistRealm) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(playlistRealm);
            }
        });
    }

    // Get all playlist from database
    @Override
    public List<PlaylistRealm> getAllPlaylist() {
        return mRealm.where(PlaylistRealm.class).findAll();
    }

    @Override
    public void deletePlaylist(String namePlayList) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<PlaylistRealm> results = realm.where(PlaylistRealm.class).equalTo("namePlaylist",namePlayList).findAll();
                results.deleteAllFromRealm();
            }
        });
    }

    @Override
    public RealmList<SongRealm> getSonginPlayList(String namePlayList) {
        return Objects.requireNonNull(mRealm.where(PlaylistRealm.class).equalTo("namePlaylist", namePlayList).findFirst()).getList();
    }


}
