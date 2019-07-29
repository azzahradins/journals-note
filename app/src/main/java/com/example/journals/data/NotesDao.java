package com.example.journals.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("select * from notes")
    LiveData<List<Notes>> showAll();

    @Insert
    void insertData(Notes... notes);

    @Delete
    void deleteData(Notes notes);

    @Update
    void updateData(Notes notes);

    @Query("delete from notes where id = :id")
    void deleteOne(Integer[] id);
}
