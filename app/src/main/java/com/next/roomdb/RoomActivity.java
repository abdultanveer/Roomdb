package com.next.roomdb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.next.roomdb.data.Word;
import com.next.roomdb.data.source.WordDao;
import com.next.roomdb.data.source.WordViewModel;
import com.next.roomdb.data.source.local.WordRoomDatabase;

import java.util.List;

public class RoomActivity extends AppCompatActivity {
EditText wordEditText;
    WordRoomDatabase db;
    private WordDao mWordDao;
    TextView mNameTextView;
    WordViewModel mWordViewModel;
    List<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        wordEditText = findViewById(R.id.editTextWord);
        db = WordRoomDatabase.getDatabase(this);
        mWordDao = db.wordDao();
        mNameTextView = findViewById(R.id.textViewNAme);
      //  ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
                //new ViewModelProvider( ).get(WordViewModel.class);


        mWordViewModel.getAllWords().observe(this,words1 -> {
            mNameTextView.setText(words1.get(2).word);
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        final Observer<String> nameObserver =
                new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable final String newName) {
                        // Update the UI, in this case, a TextView.
                        mNameTextView.setText(newName);
                    }
                };


       // mWordViewModel.observe(this, nameObserver);

    }

    public void dbHandler(View view) {
        commitDataDb();
    }

    private void commitDataDb() {
        //write data to db on a diff thread
        String data = wordEditText.getText().toString();
        Word word = new Word(data);
        mWordViewModel.insert(word);
        //new insertAsyncTask(mWordDao).execute(word);
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