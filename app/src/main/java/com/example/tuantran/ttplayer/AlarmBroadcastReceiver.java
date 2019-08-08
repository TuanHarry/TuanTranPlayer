package com.example.tuantran.ttplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    MediaController mService = Application.mService;

    @Override
    public void onReceive(Context context, Intent intent) {
        mService.Pause();
    }
}
