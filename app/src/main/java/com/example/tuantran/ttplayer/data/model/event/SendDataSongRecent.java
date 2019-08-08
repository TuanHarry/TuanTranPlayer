package com.example.tuantran.ttplayer.data.model.event;

import com.example.tuantran.ttplayer.data.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class SendDataSongRecent {
    String KeySend;
    int position;

    List<SongModel> listSong = new ArrayList<>();


    public List<SongModel> getListSong() {
        return listSong;
    }

    public void setListSong(List<SongModel> listSong) {
        this.listSong = listSong;
    }

    public String getKeySend() {
        return KeySend;
    }

    public void setKeySend(String keySend) {
        KeySend = keySend;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public SendDataSongRecent(List<SongModel> listSong, String keySend, int position) {
        this.listSong = listSong;
        KeySend = keySend;
        this.position = position;
    }
}
