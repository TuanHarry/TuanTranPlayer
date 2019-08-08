package com.example.tuantran.ttplayer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class Application extends android.app.Application {
    public static android.content.Context Context;
    public static MediaController mService;

    @Override
    public void onCreate() {
        super.onCreate();
        Context = this;
        Intent intent = new Intent(Context, MediaController.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }



    // Connect service between activity and background
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MediaController.MediaBinder binder = (MediaController.MediaBinder) service;
            mService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };





}
