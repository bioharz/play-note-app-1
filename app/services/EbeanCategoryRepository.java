package services;

import io.ebean.Ebean;
import models.Category;
import play.Logger;

import java.util.List;

public class EbeanCategoryRepository {

    public List<Category> getCategories() { return Ebean.find(Category.class).findList(); }

    public boolean saveCategorie(Category category) {

        try {
            Ebean.save(category);
        } catch (Exception e) {
            Logger.error("Error while saving Category: "+e);
            return false;
        }
        return true;

    }
}
