package com.next.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContentProviderActivity extends AppCompatActivity {
ListView contentListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        contentListView = findViewById(R.id.contentproviderlist);

        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor dataCursor = getContentResolver().query(uriSms, null,null,null,null);

        CursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,dataCursor,
                new String[]{"body"},
                new int[]{android.R.id.text1},0);

        contentListView.setAdapter(adapter);
    }
}