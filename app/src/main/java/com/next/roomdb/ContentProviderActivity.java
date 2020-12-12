package com.next.roomdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContentProviderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
ListView contentListView;
    CursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        contentListView = findViewById(R.id.contentproviderlist);
        LoaderManager loaderManager = getSupportLoaderManager();//coolie manager
        loaderManager.initLoader(123,null,this); //hiring a coolie




        /*DbQueryTask dbQueryTask = new DbQueryTask(contentListView,this);
        dbQueryTask.execute(uriSms);*/
       /* Cursor dataCursor = getContentResolver().query(uriSms, null,null,null,null);*/

         adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,                    //layout of each row
                null,                                             //data
                new String[]{"body","address"},                         //column names
                new int[]{android.R.id.text1,android.R.id.text2},       //textview ids
                0);

        contentListView.setAdapter(adapter);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int coolieId, @Nullable Bundle instructionsLoaderManager) {//onboarding  -- from which warehourse/coach fetch the data/goods

        Uri uriSms = Uri.parse("content://sms/inbox"); //location of warehourse/ bogie
        return new CursorLoader(this,uriSms,null,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) { //for salary
            adapter.swapCursor(data); //swap out the null cursor with loaded cursor
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}