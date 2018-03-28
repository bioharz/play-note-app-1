package controllers;

import com.sun.media.jfxmedia.logging.Logger;
import models.Note;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import services.InMemoryNoteRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class HomeController extends Controller {

    @Inject
    protected InMemoryNoteRepository noteRepository;

    protected Form<Note> noteForm;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.noteForm = formFactory.form(Note.class);
    }

    public Result index() {
        List<Note> notes = noteRepository.getNotes();
        return ok(views.html.index.render(notes));
    }

    public Result form(int id) {
        Note note = new Note();

        if (id > 0) {
            note = noteRepository.getNote(id);
        }

        return ok(views.html.form.render(note));
    }

    public Result save() {
        Note newNote = noteForm.bindFromRequest().get();

        if (noteForm.hasErrors()) {
            return badRequest(views.html.form.render(noteForm));
        } else {

            noteRepository.saveNote(newNote);
            return redirect("/");
        }
    }

    public Result delete(int id) {

        try {
            play.Logger.info("try to delete id: " + id, id);
            noteRepository.delete(id);


        } catch (Throwable t) {
            play.Logger.error("can't delte item, error:", t);
        }

        return redirect("/#");
    }

}
