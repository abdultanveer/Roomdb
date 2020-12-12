package com.next.roomdb;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class DbQueryTask extends AsyncTask<Uri,Void, Cursor> {

Context context;
ListView mListView;
    public DbQueryTask(ListView contentListView,Context mContext) {
        context = mContext;
        mListView = contentListView;
    }


    @Override
    protected Cursor doInBackground(Uri... uris) {
               Cursor dataCursor = context.getContentResolver().query(uris[0], null,null,null,null);

        return dataCursor;
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        CursorAdapter adapter = new SimpleCursorAdapter(context,
                android.R.layout.simple_list_item_2,                    //layout of each row
                cursor,                                             //data
                new String[]{"body","address"},                         //column names
                new int[]{android.R.id.text1,android.R.id.text2},       //textview ids
                0);

        mListView.setAdapter(adapter);
    }
}
