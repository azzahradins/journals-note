package com.example.journals.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.journals.data.AppDatabase;
import com.example.journals.data.DatabaseProvider;
import com.example.journals.data.Notes;
import com.example.journals.data.NotesDao;

import java.util.List;

public class NotesRepository{
    public AppDatabase database;
    public LiveData<List<Notes>> notesList;

    public NotesRepository(Context context) {
        this.database = DatabaseProvider.getAppDbInstance(context);
    }

    // get notes list.
    public LiveData<List<Notes>> getNotesList(){
        this.getNotesFromDb();
        return this.notesList;
    }
    private void getNotesFromDb() {
        NotesDao dao = this.database.notesDao();
        this.notesList = dao.showAll();
    }

    // save notes list
    public void saveNote(Notes notes){
        new SaveNoteTask().execute(notes);
    }
    private class SaveNoteTask extends AsyncTask<Notes,Void,Void> {
        @Override
        protected Void doInBackground(Notes... notes) {
            NotesDao dao = database.notesDao();
            for(int i = 0; i < notes.length; i++){
                Notes n = notes[i];
                dao.insertData(n);
            }
            return null;
        }
    }

    public void deleteNote(Notes notes){new DeleteNoteTask().execute(notes);}
    private class DeleteNoteTask extends  AsyncTask<Notes, Void, Void>{
        @Override
        protected Void doInBackground(Notes... notes) {
            NotesDao dao = database.notesDao();
            dao.deleteData(notes[0]);
            return null;
        }
    }

    public void updateNote(Notes notes){
        NotesDao dao = this.database.notesDao();
        new UpdateNoteTask(dao).execute(notes);
    }
    private class UpdateNoteTask extends  AsyncTask<Notes, Void, Void>{
        private NotesDao dao;

        private UpdateNoteTask(NotesDao notesDao) {
            this.dao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            dao.updateData(notes[0]);
            return null;
        }
    }

    public void deleteOne(int id){
        NotesDao dao = this.database.notesDao();
        new DeleteOneTask(dao).execute(id);
    }
    private class DeleteOneTask extends  AsyncTask<Integer, Void, Void>{
        NotesDao dao;
        public DeleteOneTask(NotesDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            dao.deleteOne(integers);
            return null;
        }
    }
}
