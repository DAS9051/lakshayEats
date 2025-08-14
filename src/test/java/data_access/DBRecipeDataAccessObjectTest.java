package data_access;

import entity.Recipe;
import entity.SearchResult;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DBRecipeDataAccessObjectTest {
    @Test
    public void saveReturnsTrueEvenWhenOffline() {
        DBRecipeDataAccessObject dao = new DBRecipeDataAccessObject();
        SearchResult sr = new SearchResult();
        sr.setId(1);
        assertTrue(dao.save("user", sr));
    }

    @Test
    public void fetchSavedRecipesThrowsWhenOffline() {
        DBRecipeDataAccessObject dao = new DBRecipeDataAccessObject();
        assertThrows(IOException.class, () -> dao.fetchSavedRecipes("user"));
    }

    @Test
    public void unsaveReturnsFalseWhenOffline() {
        DBRecipeDataAccessObject dao = new DBRecipeDataAccessObject();
        assertFalse(dao.unsave("user", 1));
    }

    @Test
    public void saveSharedRecipeDoesNotThrow() {
        DBRecipeDataAccessObject dao = new DBRecipeDataAccessObject();
        Recipe recipe = new Recipe();
        recipe.id = 1;
        recipe.name = "r";
        try {
            dao.saveSharedRecipe("a", "b", recipe);
        } catch (Exception e) {
            fail("Should not throw");
        }
    }
}
