package com.next.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.next.roomdb.data.Word;
import com.next.roomdb.data.source.WordDao;
import com.next.roomdb.data.source.local.WordRoomDatabase;

public class RoomActivity extends AppCompatActivity {
EditText wordEditText;
    WordRoomDatabase db;
    private WordDao mWordDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        wordEditText = findViewById(R.id.editTextWord);
        db = WordRoomDatabase.getDatabase(this);
        mWordDao = db.wordDao();
    }

    public void dbHandler(View view) {
        commitDataDb();
    }

    private void commitDataDb() {
        //write data to db on a diff thread
        String data = wordEditText.getText().toString();
        Word word = new Word(data);
        new insertAsyncTask(mWordDao).execute(word);
       // new insertAsyncTask(mWordDao).execute(new Word(wordEditText.getText().toString()));
        wordEditText.setText("");

    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);

            return null;
        }
    }

    }