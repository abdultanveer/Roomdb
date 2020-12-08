package com.next.roomdb.background;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class DownloadAsyncTask extends AsyncTask<String,Integer, Bitmap> {//urlstring, progressint, resultimage
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
    protected Bitmap doInBackground(String... strings) {
        Log.i(TAG,"starting download from url"+ strings[0]);


        try {
            for(int i=1;i<20;i++) {
                publishProgress(i*5);

                Thread.sleep(200);
            }
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
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
