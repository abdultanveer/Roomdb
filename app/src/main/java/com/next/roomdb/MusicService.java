package com.next.roomdb;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
    public static String TAG = MusicService.class.getSimpleName();

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         super.onStartCommand(intent, flags, startId);
        Log.i(TAG,"service started");
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.music);
        mediaPlayer.start();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"service destroyed");

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}