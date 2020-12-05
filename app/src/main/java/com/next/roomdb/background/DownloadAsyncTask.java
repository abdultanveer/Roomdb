package com.next.roomdb.background;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

public class DownloadAsyncTask extends AsyncTask<String,Integer,Void> {//urlstring, progressint, resultimage
    public static String TAG = DownloadAsyncTask.class.getSimpleName();
    ProgressBar progressBar;

    public DownloadAsyncTask(ProgressBar mProgressBar) {
        progressBar = mProgressBar;
    }


    @Override//i'll download a file
    protected Void doInBackground(String... strings) {
        Log.i(TAG,"starting download from url"+ strings[0]);
        publishProgress(50);
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }
}
