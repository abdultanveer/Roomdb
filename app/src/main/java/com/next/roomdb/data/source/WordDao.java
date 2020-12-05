package com.next.roomdb.data.source;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.next.roomdb.data.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    public void insert(Word word);

    @Query("DELETE FROM word_table")
    public void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    public LiveData<List<Word>> getAllWords();
}
