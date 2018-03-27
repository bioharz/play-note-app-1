package services;

import models.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryNoteRepository {

    protected static Map<String,Note> notes = new HashMap<>();

    static {
        Note note1 = new Note();
        note1.setId(1);
        note1.setTitle("erste Notiz");
        notes.put("1", note1);

        Note note2 = new Note();
        note2.setId(2);
        note2.setTitle("zweite Notiz");
        notes.put("2", note2);
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes.values());
    }

}