package com.example.roommvvmdemo.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.roommvvmdemo.data.local.Note;
import com.example.roommvvmdemo.data.local.NoteDao;
import com.example.roommvvmdemo.data.local.NoteDatabase;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private final NoteDao noteDao;
    private final MutableLiveData<List<Note>> allNotes = new MutableLiveData<>();
    private final ExecutorService executorService;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        executorService = Executors.newSingleThreadExecutor();
        loadNotes();
    }

    private void loadNotes() {
        executorService.execute(() -> {
            List<Note> notes = noteDao.getAllNotes();
            allNotes.postValue(notes);
        });
    }

    public void insert(Note note) {
        executorService.execute(() -> {
            noteDao.insert(note);
            loadNotes(); // Recharge après insertion
        });
    }

    public void delete(Note note) {
        executorService.execute(() -> {
            noteDao.delete(note);
            loadNotes(); // Recharge après suppression
        });
    }

    public void deleteAllNotes() {
        executorService.execute(() -> {
            noteDao.deleteAllNotes();
            loadNotes(); // Recharge après suppression totale
        });
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}