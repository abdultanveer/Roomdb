package com.next.roomdb.background;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class DownloadAsyncTask extends AsyncTask<String,Integer,Void> {//urlstring, progressint, resultimage
    public static String TAG = DownloadAsyncTask.class.getSimpleName();
    ProgressBar progressBar;

    public DownloadAsyncTask(ProgressBar mProgressBar) {
        progressBar = mProgressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override//i'll download a file
    protected Void doInBackground(String... strings) {
        Log.i(TAG,"starting download from url"+ strings[0]);
        publishProgress(50);


        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
