package com.next.roomdb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.next.roomdb.background.DownloadAsyncTask;
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
            if(words1.size()!=0) {
                int size = words1.size();
                Word word = words1.get(size - 1);
                mNameTextView.setText(word.getWord());
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();




       // mWordViewModel.observe(this, nameObserver);

    }


    public void dbHandler(View view) {
        switch (view.getId()){
            case R.id.buttonCommit:
                commitDataDb();
                break;
            case R.id.buttonasync:
                ProgressBar mProgressBar = findViewById(R.id.progressBar);
                DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask(mProgressBar);
                downloadAsyncTask.execute("https://urlfromimagetobedownloaded.com");
                break;
        }
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

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "promotions";
                    //getString(R.string.channel_name);
            String description = "this is to show ads";
                    //getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("promos", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void showNotification(View view) {
        createNotificationChannel();
        NotificationManager mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        int NOTIFICATION_ID = 123;
         NotificationCompat.Builder mNotifyBuilder;

        mNotifyBuilder = new NotificationCompat.Builder(this,"promos")
                .setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        Notification myNotification = mNotifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID,  myNotification);

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