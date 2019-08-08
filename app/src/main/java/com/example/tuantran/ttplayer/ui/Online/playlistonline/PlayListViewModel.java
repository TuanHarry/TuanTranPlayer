package com.example.tuantran.ttplayer.ui.Online.playlistonline;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class PlayListViewModel extends AndroidViewModel {
    private MutableLiveData<String> idPlayList = new MutableLiveData<>();

    public PlayListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getSelectedItem() {
        return idPlayList;
    }

    public void setSelectedItem(String selectedItem) {
        idPlayList.setValue(selectedItem);
    }
}
