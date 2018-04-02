package services;

import io.ebean.Ebean;
import models.Note;
import play.Logger;

import java.util.List;

public class EbeanNoteRepository {

    public List<Note> getNotes() {
        return Ebean.find(Note.class).findList();
    }

    public Note getNote(int id) {
        return Ebean.find(Note.class)
                .where()
                .eq("id", id)
                .findOne();
    }

    public boolean saveNote(Note note) {

        try {
            note.setLastEdited((int) (System.currentTimeMillis() / 1000L));
            if (note.getId() > 0) {
                Ebean.update(note);
            } else {
                Ebean.save(note);
            }
        } catch (Exception e) {
            Logger.error("Error - SaveNote: " + e);
            return false;
        }
        return true;
    }

    public boolean deleteNote(int id) {

        try {
        Ebean.delete(Note.class, id);
        } catch (Exception e) {
            Logger.error("Error - deleteNote: " + e);
            return false;
        }
        return true;
    }

}
