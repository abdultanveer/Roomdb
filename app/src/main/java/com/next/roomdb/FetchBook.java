package com.next.roomdb;

import android.os.AsyncTask;
import android.widget.TextView;

public class FetchBook extends AsyncTask<String,Void,String> {
    private TextView mTitleText;
    private TextView mAuthorText;

    public FetchBook(TextView mTitleText, TextView mAuthorText) {
        this.mTitleText = mTitleText;
        this.mAuthorText = mAuthorText;
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);    }

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);
    }
}
