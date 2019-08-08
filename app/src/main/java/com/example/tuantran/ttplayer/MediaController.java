package com.example.tuantran.ttplayer;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.ui.main.Main2Activity;
import com.example.tuantran.ttplayer.ui.splash.SplashActivity;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MediaController extends Service {
    private static final String CHANNEL_ID = "channel";
    private static MediaController INSTANCE;
    private MediaPlayer mMediaPlayer;
    private boolean isRepeat = false;
    private boolean isShuffle = false;
    private int currentIndex = 0;
    private List<SongModel> mDataSource;
    private PlayerListener mListener;
    public boolean isPlaying = false;

    int count = 0;
    // Service
    private final IBinder mBinder = new MediaBinder();
    Notification status;
    private final String LOG_TAG = "MediaService";


    @Override
    public void onCreate() {
        super.onCreate();
        initPlayer();
        createNotificationChannel();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setListener(PlayerListener mListener) {
        this.mListener = mListener;
    }

    public void setDataSource(List<SongModel> mDataSource) {
        this.mDataSource = mDataSource;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MediaBinder extends Binder {
        MediaController getService(){
            return MediaController.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            switch (Objects.requireNonNull(intent.getAction())){
                case Contanst.ACTION.PLAY_ACTION:
                    Play();
                    break;
                case Contanst.ACTION.PREV_ACTION:
                    Previous();
                    break;
                case Contanst.ACTION.NEXT_ACTION:
                    Next();
                    break;
                case Contanst.ACTION.PAUSE_ACTION:
                    Pause();
                    break;
                case Contanst.ACTION.STOPFOREGROUND_ACTION:
                    Pause();
                    stopForeground(true);
                    stopSelf();
                    return START_STICKY;
            }
        }

        if(mMediaPlayer!= null){
            SongModel song = mDataSource.get(currentIndex);
            showNotification(song.getDisplayName(), song.getArtist(), "", isPlaying);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(count > mDataSource.size()){
                        mMediaPlayer.pause();
                    }
                    onCompleteMedia(isRepeat, isShuffle);
                    count += 1;
                }
            });
        } else {
            mMediaPlayer.release();
        }
    }

    public void setDataSource(SongModel song) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(song.getStreamUri());
            mMediaPlayer.prepare();
            mListener.onDataSourceChange(song);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Play() {
        mMediaPlayer.start();
        while (!isPlaying){
            isPlaying = mMediaPlayer.isPlaying();
        }
        mListener.onStateChanged(isPlaying);
    }

    public void Pause() {
        mMediaPlayer.pause();
        isPlaying = false;
        mListener.onStateChanged(false);
        SongModel song = mDataSource.get(getCurrentIndex());
        showNotification(song.getDisplayName(),song.getArtist(),"",isPlaying);
    }

    public  void setShuffle(boolean isShuffle)
    {
        this.isShuffle = isShuffle;
    }

    public void Next() {
        currentIndex = (currentIndex + 1) % mDataSource.size();
        PlayAtPosition(currentIndex);
    }

    public void Previous() {
        currentIndex = (currentIndex + mDataSource.size()-1) % mDataSource.size();
        PlayAtPosition(currentIndex);
    }

    public void setRepeat(boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    public void seekTo(int position) {
        mMediaPlayer.seekTo(position);
    }

    private void onCompleteMedia(boolean isRepeat, boolean isShuffle) {
        //if isRepeat = true --> MediaPlayer.start()
        //else Next()
        //if isShuffle = true --> setDataSource() (Random)
        if (isRepeat) {
            mMediaPlayer.start();
        } else {
            if (isShuffle) {
                //random index
                int position = new Random().nextInt(mDataSource.size());
                currentIndex = position;
                setDataSource(mDataSource.get(position));
                mMediaPlayer.start();
            } else {
                Next();
            }
        }
        // update status notification
        SongModel song = mDataSource.get(currentIndex);
        updateNotificationBigView(song.getDisplayName());

    }
    public int getPosition(){
        if(mMediaPlayer != null)
        {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void PlayAtPosition(int position) {
        SongModel song = mDataSource.get(position);
        setDataSource(song);
        Play();
    }

    public int getDurationStream(){
        int duration = 0;
        if(mMediaPlayer!= null){
            duration = mMediaPlayer.getDuration();
        }
        return duration;
    }

    public interface PlayerListener{
        void onStateChanged(boolean isPlaying);
        void onDataSourceChange(SongModel duration);
    }


    // Create notification

    @TargetApi(Build.VERSION_CODES.O)
    public void showNotification(String songName,String artName, String albumName, boolean isPlaying) {
        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews views = new RemoteViews(getPackageName(),
                R.layout.notification_bar);
        RemoteViews bigViews = new RemoteViews(getPackageName(),
                R.layout.status_bar_expanded);

        // showing default album image
        views.setViewVisibility(R.id.status_bar_icon, View.VISIBLE);
        views.setViewVisibility(R.id.status_bar_album_art, View.GONE);
        bigViews.setImageViewBitmap(R.id.status_bar_album_art,
                Contanst.getDefaultAlbumArt(this, R.drawable.icon_app));

        Intent notificationIntent = new Intent(this, SplashActivity.class);
        notificationIntent.setAction(Contanst.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent previousIntent = new Intent(this, MediaController.class);
        previousIntent.setAction(Contanst.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(this, MediaController.class);
        playIntent.setAction(Contanst.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);


        Intent pauseIntent = new Intent(this,MediaController.class);
        pauseIntent.setAction(Contanst.ACTION.PAUSE_ACTION);
        PendingIntent ppauseIntent = PendingIntent.getService(this,0,pauseIntent,0);

        Intent nextIntent = new Intent(this, MediaController.class);
        nextIntent.setAction(Contanst.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        Intent closeIntent = new Intent(this, MediaController.class);
        closeIntent.setAction(Contanst.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0,
                closeIntent, 0);


        if (!isPlaying) {
            views.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);
            bigViews.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);
        } else {
            views.setOnClickPendingIntent(R.id.status_bar_play, ppauseIntent);
            bigViews.setOnClickPendingIntent(R.id.status_bar_play, ppauseIntent);
        }


        views.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);

        views.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);

        views.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);

        views.setImageViewResource(R.id.status_bar_play,
                isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
        bigViews.setImageViewResource(R.id.status_bar_play,
                isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);


        views.setTextViewText(R.id.status_bar_track_name, songName);
        bigViews.setTextViewText(R.id.status_bar_track_name, songName);

        views.setTextViewText(R.id.status_bar_artist_name, artName);
        bigViews.setTextViewText(R.id.status_bar_artist_name, artName);

        bigViews.setTextViewText(R.id.status_bar_album_name, albumName);

        status = new Notification.Builder(this,CHANNEL_ID).build();
        status.contentView = views;
        status.bigContentView = bigViews;
        status.flags = Notification.FLAG_FOREGROUND_SERVICE;
        status.icon = R.drawable.icon_app;
        status.contentIntent = pendingIntent;
        startForeground(Contanst.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
    }

    public void updateNotificationBigView (String songName){
        status.contentView.setTextViewText(R.id.status_bar_track_name,songName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            status.bigContentView.setImageViewBitmap(R.id.status_bar_album_art,
                    Contanst.getDefaultAlbumArt(this, R.drawable.default_album_art2));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            status.bigContentView.setTextViewText(R.id.status_bar_track_name, songName);
        }
        startForeground(Contanst.NOTIFICATION_ID.FOREGROUND_SERVICE, status); //use the same ID to update notification
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
