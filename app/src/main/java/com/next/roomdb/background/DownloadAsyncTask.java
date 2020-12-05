package com.next.roomdb.background;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadAsyncTask extends AsyncTask<String,Integer,Void> {//urlstring, progressint, resultimage
    public static String TAG = DownloadAsyncTask.class.getSimpleName();


    @Override//i'll download a file
    protected Void doInBackground(String... strings) {
        Log.i(TAG,"starting download from url"+ strings[0]);
        return null;
    }
}
