package com.example.tuantran.ttplayer.ui.player;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuantran.ttplayer.Application;
import com.example.tuantran.ttplayer.Contanst;
import com.example.tuantran.ttplayer.MediaController;
import com.example.tuantran.ttplayer.PlaylistDialogFragment;
import com.example.tuantran.ttplayer.R;
import com.example.tuantran.ttplayer.data.AppDataManager;
import com.example.tuantran.ttplayer.data.model.SongModel;
import com.example.tuantran.ttplayer.data.model.event.SendDataSongRecent;
import com.example.tuantran.ttplayer.ui.Offline.playlistoffline.DialogPlaylistFragment;
import com.example.tuantran.ttplayer.ui.Online.playlistonline.PlayListViewModel;
import com.example.tuantran.ttplayer.ui.base.fragment.FragmentMVP;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PlayerFragment extends FragmentMVP<PlayerPresenter, IPlayer> implements MediaController.PlayerListener, IPlayer {

    MediaController mService = Application.mService;
    boolean mBound = false;

    ObjectAnimator animator;

    @BindView(R.id.seekBar)
    SeekBar mSeekBar;

    @BindView(R.id.image_song)
    CircleImageView circleImageView;

    @BindView(R.id.button_pause)
    AppCompatImageButton mButtonPause;

    @BindView(R.id.button_play)
    AppCompatImageButton mButtonPlay;

    @BindView(R.id.song_name)
    TextView mTextSongName;

    @BindView(R.id.song_artist)
    TextView mTextSongArtist;

    @BindView(R.id.button_favorite)
    AppCompatImageButton mButtonFavorite;

    @BindView(R.id.button_more)
    AppCompatImageButton mButtonMore;

    @BindView(R.id.button_down)
    AppCompatImageButton mButtonDown;

    @BindView(R.id.button_playlist)
    AppCompatImageButton mButtonPlaylist;

    @BindView(R.id.text_duration)
    TextView mTextDuration;

    @BindView(R.id.text_display_time)
    TextView mTextDisplayTime;

    @BindView(R.id.song_artist_playback)
    TextView mTextSongArtistPlayBack;

    @BindView(R.id.song_name_playback)
    TextView mTextSongNamePlayBack;

    @BindView(R.id.button_pause_playback)
    AppCompatImageButton mButtonPausePlayBack;

    @BindView(R.id.button_play_playback)
    AppCompatImageButton mButtonPlayPlayBack;

    List<SongModel> data = new ArrayList<>();
    boolean isRepeat = false;
    boolean isShuffle = false;


    // Service


    private PlayerViewModel mViewModel;
    private PlayListViewModel playListViewModel;
    public static PlayerFragment newInstance() {
        return new PlayerFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player, container, false);
        ButterKnife.bind(this, rootView);
        

        
        // Get data song local
        data.addAll(AppDataManager.getInstance().getDataLocalSong());
        if (data.size() > 0) {
            mService.setListener(this);
            mService.setDataSource(data);
            mService.PlayAtPosition(0);
            SongModel song = data.get(0);
            mService.showNotification(song.getDisplayName(),song.getArtist(),"",mService.isPlaying);
        }

        initControl();

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mService.seekTo(seekBar.getProgress());
            }
        });
        animationStart();


        return rootView;
    }

    private void initControl() {
        mButtonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAlarmFragment alarmFragment = new DialogAlarmFragment();
                getFragNav().showBottomSheetDialogFragment(alarmFragment);
            }
        });
    }

    private void animationStart(){
        animator = ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        animator.setDuration(10000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }
    


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void makeView() {
        mView = this;
    }

    @Override
    protected void makePresenter() {
        mPresenter = new PlayerPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        // TODO: Use the ViewModel
    }

    @OnClick(R.id.button_play)
    void playMusic() {
        runCommand(Contanst.ACTION.PLAY_ACTION);
    }

    @OnClick(R.id.button_next)
    void nextSong() {
        runCommand(Contanst.ACTION.NEXT_ACTION);
    }

    @OnClick(R.id.button_previous)
    void previousSong() {
        runCommand(Contanst.ACTION.PREV_ACTION);
    }

    @OnClick(R.id.button_pause)
    void pauseSong() {
        runCommand(Contanst.ACTION.PAUSE_ACTION);
    }

    @BindView(R.id.button_repeat)
    AppCompatImageButton buttonRepeat;
    @OnClick(R.id.button_repeat)
    void repeatSong() {
        if(!isRepeat){
            isRepeat = true;
            buttonRepeat.setColorFilter(Color.RED);
            mService.setRepeat(true);
        }else {
            isRepeat = false;
            buttonRepeat.setColorFilter(Color.BLACK);
            mService.setRepeat(false);
        }

    }

    @BindView(R.id.button_shuffle)
    AppCompatImageButton buttonShuffle;
    @OnClick(R.id.button_shuffle)
    void shuffleSongs() {
        if(!isShuffle){
            isRepeat = true;
            buttonShuffle.setColorFilter(Color.RED);
            mService.setShuffle(true);
        }else {
            isShuffle = false;
            buttonShuffle.setColorFilter(Color.BLACK);
            mService.setShuffle(false);
        }
    }

    @OnClick(R.id.button_playlist)
    void showPlaylist() {
        PlaylistDialogFragment playlistDialogFragment = PlaylistDialogFragment.newInstance();
        getFragNav().showBottomSheetDialogFragment(playlistDialogFragment);
    }

    @OnClick(R.id.button_pause_playback)
    void clickButtonPausePlayBack() {
        runCommand(Contanst.ACTION.PAUSE_ACTION);
    }

    @OnClick(R.id.button_play_playback)
    void clickButtonPlayPlayBack() {
        runCommand(Contanst.ACTION.PLAY_ACTION);
    }

    @OnClick(R.id.button_next_playback)
    void clickButtonNextPlayBack() {
        runCommand(Contanst.ACTION.NEXT_ACTION);
    }


    @OnClick(R.id.button_previous_playback)
    void clickButtonPreviousPlayBack() {
        runCommand(Contanst.ACTION.PREV_ACTION);
    }


    @Override
    public void onStateChanged(boolean isPlaying) {
        if (isPlaying) {
            mButtonPausePlayBack.setVisibility(View.VISIBLE);
            mButtonPlayPlayBack.setVisibility(View.INVISIBLE);
            mButtonPlay.setVisibility(View.INVISIBLE);
            mButtonPause.setVisibility(View.VISIBLE);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSeekBar.setProgress(mService.getPosition());
                    handler.postDelayed(this, 1000);
                    mTextDisplayTime.setText(new SimpleDateFormat("mm:ss", Locale.US).format(mService.getPosition()));
                }
            }, 1000);
        } else {
            mButtonPlay.setVisibility(View.VISIBLE);
            mButtonPause.setVisibility(View.INVISIBLE);
            mButtonPlayPlayBack.setVisibility(View.VISIBLE);
            mButtonPausePlayBack.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void onDataSourceChange(SongModel song) {
        mTextSongName.setText(song.getDisplayName());
        mTextSongArtist.setText(song.getArtist());
        mSeekBar.setMax((int) mService.getDurationStream());
        mTextDuration.setText(new SimpleDateFormat("mm:ss", Locale.US).format(mService.getDurationStream()));
        mTextSongArtistPlayBack.setText(song.getArtist());
        mTextSongNamePlayBack.setText(song.getDisplayName());
    }

    // Receive id by EventBus
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }




    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onReceiveDataSong(SendDataSongRecent songData){
        if(songData.getKeySend().equalsIgnoreCase(Contanst.INTENT_SONG)){
            data = songData.getListSong();
            if(data.size() > 0){
                mService.setDataSource(data);
                mService.PlayAtPosition(songData.getPosition());
                SongModel song = data.get(songData.getPosition());
                mService.showNotification(song.getDisplayName(),song.getArtist(),"",mService.isPlaying);
            }
        }
    }

    void runCommand(String action){
        Intent intent = new Intent(Application.Context, MediaController.class);
        intent.setAction(action);
        Application.Context.startService(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}


