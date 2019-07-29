package com.example.journals.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.journals.data.Notes;
import com.example.journals.repository.NotesRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public NotesRepository notesRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.notesRepository = new NotesRepository(application);
    }

    public LiveData<List<Notes>> getNotesList(){
        return this.notesRepository.getNotesList();
    }

    public void saveNotes(Notes notes){
        this.notesRepository.saveNote(notes);
    }

    public void deleteNotes(Notes notes){
        this.notesRepository.deleteNote(notes);
    }

    public  void updateNotes(Notes notes){this.notesRepository.updateNote(notes);}

    public  void deleteOne(int id){this.notesRepository.deleteOne(id);}
}
