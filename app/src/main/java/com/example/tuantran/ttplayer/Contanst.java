package com.example.tuantran.ttplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Contanst {
    public static final String INTENT_PLAYLIST = "playlist";
    public static final String INTENT_BANNER  = "banner";
    public static final String INTENT_THELOAI = "theloai";
    public static final String INTENT_CHUDE = "chude";
    public static final String INTENT_ALBUM = "album";

    public static final String INTENT_ARRAY_SONGS = "arraySong";
    public static final String INTENT_SONG = "song";
    public static final int REQUEST_CODE_BROADCAST = 200;

    public interface ACTION {
        public static String PAUSE_ACTION = "com.example.deantotnghiep.action.pause";
        public static String MAIN_ACTION = "com.example.deantotnghiep.action.main";
        public static String INIT_ACTION = "com.example.deantotnghiep.action.init";
        public static String PREV_ACTION = "com.example.deantotnghiep.action.prev";
        public static String PLAY_ACTION = "com.example.deantotnghiep.action.play";
        public static String NEXT_ACTION = "com.example.deantotnghiep.action.next";
        public static String STARTFOREGROUND_ACTION = "com.example.deantotnghiep.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.example.deantotnghiep.action.stopforeground";

    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }

    public static Bitmap getDefaultAlbumArt(Context context, int resId) {
        Bitmap bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            bm = BitmapFactory.decodeResource(context.getResources(),
                    resId, options);
        } catch (Error ee) {
        } catch (Exception e) {
        }
        return bm;
    }

}
