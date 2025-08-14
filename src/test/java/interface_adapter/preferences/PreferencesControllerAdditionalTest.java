package interface_adapter.preferences;

import entity.Preferences;
import org.junit.Test;
import use_case.preferences.get_preferences.GetPreferencesInputBoundary;
import use_case.preferences.save_preferences.SavePreferencesInputBoundary;

import static org.junit.Assert.*;

public class PreferencesControllerAdditionalTest {
    private static class FakeGetInteractor implements GetPreferencesInputBoundary {
        String lastUsername;
        @Override
        public void execute(String username) { lastUsername = username; }
    }
    private static class FakeSaveInteractor implements SavePreferencesInputBoundary {
        String user; Preferences prefs;
        @Override
        public void execute(String username, Preferences preferences) { user = username; prefs = preferences; }
    }
    @Test
    public void loadDelegatesToGetInteractor() {
        FakeGetInteractor get = new FakeGetInteractor();
        FakeSaveInteractor save = new FakeSaveInteractor();
        PreferencesController controller = new PreferencesController(get, save);
        controller.loadPreferences("bob");
        assertEquals("bob", get.lastUsername);
    }
    @Test
    public void saveDelegatesToSaveInteractor() {
        FakeGetInteractor get = new FakeGetInteractor();
        FakeSaveInteractor save = new FakeSaveInteractor();
        PreferencesController controller = new PreferencesController(get, save);
        Preferences prefs = new Preferences(new java.util.HashMap<>(), new java.util.HashMap<>());
        controller.savePreferences("bob", prefs);
        assertEquals("bob", save.user);
        assertEquals(prefs, save.prefs);
    }
}
