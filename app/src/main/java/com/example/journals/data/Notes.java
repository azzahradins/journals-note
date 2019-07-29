package com.example.journals.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {

    @ColumnInfo(name= "id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private  int id;

    @ColumnInfo(name = "NotesTitle")
    private String title;

    @ColumnInfo(name = "NotesBody")
    private String body;

    @ColumnInfo(name = "created_by")
    private String date;

    public Notes(@NonNull int id, String title, String body, String date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
