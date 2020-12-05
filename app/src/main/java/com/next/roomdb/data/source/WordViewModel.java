package com.next.roomdb.data.source;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.next.roomdb.data.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;


    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);

    }

   public LiveData<List<Word>> getAllWords() {
       mAllWords = mRepository.getAllWords();
       return mAllWords;
    }
    public void insert(Word word) {
        mRepository.insert(word);
    }
    public void deleteWord(Word word) {
       // mRepository.deleteWord(word);
    }

}
