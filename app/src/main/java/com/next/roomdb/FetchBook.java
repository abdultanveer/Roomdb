package com.next.roomdb;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            JSONObject firstJsonObject = itemsArray.getJSONObject(0);
            String title=null;
            String authors=null;
            JSONObject volumeInfo = firstJsonObject.getJSONObject("volumeInfo");
            title = volumeInfo.getString("title");
            authors = volumeInfo.getString("authors");
            mTitleText.setText(title);
            mAuthorText.setText(authors);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
