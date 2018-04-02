package controllers;

import models.Category;
import models.Note;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.ValidationError;
import play.mvc.*;
import services.EbeanCategoryRepository;
import services.EbeanNoteRepository;

import javax.inject.Inject;
import java.util.List;

public class HomeController extends Controller {

    protected EbeanNoteRepository noteRepository;

    protected EbeanCategoryRepository categoryRepository;

    protected Form<Note> noteForm;

    @Inject
    public HomeController(EbeanNoteRepository noteRepository, EbeanCategoryRepository categoryRepository, FormFactory formFactory) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
        this.noteForm = formFactory.form(Note.class);
    }

    public Result index() {

        createCategories();
        createNotes();

        List<Note> notes = noteRepository.getNotes();

        String message = flash("success");
        if(message == null) {
            //message = "Welcome!";
            return ok(views.html.index.render(notes, null));
        } else {

        return ok(views.html.index.render(notes, message));
        }

    }

    public Result form(int id) {
        Note note = noteRepository.getNote(id);

        if (note == null) {
            note = new Note();
        }

        return ok(views.html.form.render(noteForm.fill(note), categoryRepository.getCategories()));
    }

    public Result save() {
        Form<Note> form = noteForm.bindFromRequest();

        if(form.hasErrors()){
            for (ValidationError valErr: form.allErrors()
                 ) {
                Logger.error(valErr.message());

            }
            return badRequest(views.html.form.render(form, categoryRepository.getCategories()));
        }else{

            boolean noError = noteRepository.saveNote(form.get());

            if(noError) {
                flash("success", "The note was successfully saved");
            } else {
                flash("error", "Can't save the note");

            }
            return redirect( "/");
            //return ok();
        }

    }

    public Result delete(int id) {
        noteRepository.deleteNote(id);
        return ok();
    }



    private void createCategories() {

        List<Category> categories = categoryRepository.getCategories();

        if(categories.isEmpty()) {
            Category holiday = new Category();
            holiday.setTitle("Holiday");
            categoryRepository.saveCategorie(holiday);

            Category work = new Category();
            work.setTitle("Work");
            categoryRepository.saveCategorie(work);


            Category study = new Category();
            study.setTitle("Study");
            categoryRepository.saveCategorie(study);
        }

    }

    private void createNotes() {

        List<Note> notes = noteRepository.getNotes();
        List<Category> categories = categoryRepository.getCategories();


        if(notes.isEmpty() && !categories.isEmpty()) {

            for(int i = 0; i < 3; i++){
                Note note = new Note();
                note.setCategory(categories.get(i));
                note.setTitle("Title-"+i);
                note.setDescription("Note-"+i);
                noteRepository.saveNote(note);
            }
        }

    }

}
