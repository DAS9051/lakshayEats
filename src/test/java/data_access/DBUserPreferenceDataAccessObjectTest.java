package data_access;

import entity.Preferences;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DBUserPreferenceDataAccessObjectTest {
    private static class TestableDAO extends DBUserPreferenceDataAccessObject {
        JSONObject savedRestrictions;
        JSONObject savedIntolerances;
        JSONObject fetchResponse;

        @Override
        public void saveRestrictionsAndIntolerances(String username, JSONObject r, JSONObject i) {
            this.savedRestrictions = r;
            this.savedIntolerances = i;
        }

        @Override
        public JSONObject fetchRestrictionsAndIntolerances(String username) {
            return fetchResponse;
        }
    }

    @Test
    public void savePreferencesSendsCorrectJSON() {
        TestableDAO dao = new TestableDAO();
        Map<String, Integer> diets = new HashMap<>();
        diets.put("Vegan", 1);
        Map<String, Integer> ints = new HashMap<>();
        ints.put("Peanut", 1);
        Preferences prefs = new Preferences(diets, ints);
        dao.savePreferences("alice", prefs);
        assertEquals(1, dao.savedRestrictions.getInt("Vegan"));
        assertEquals(1, dao.savedIntolerances.getInt("Peanut"));
    }

    @Test
    public void getPreferencesParsesResponse() {
        TestableDAO dao = new TestableDAO();
        JSONObject restrictions = new JSONObject().put("Vegan", 1);
        JSONObject intolerances = new JSONObject().put("Peanut", 2);
        dao.fetchResponse = new JSONObject()
                .put("preferences", restrictions)
                .put("intolerances", intolerances);
        Preferences p = dao.getPreferences("alice");
        assertEquals(Integer.valueOf(1), p.getDiets().get("Vegan"));
        assertEquals(Integer.valueOf(2), p.getIntolerances().get("Peanut"));
    }
}
