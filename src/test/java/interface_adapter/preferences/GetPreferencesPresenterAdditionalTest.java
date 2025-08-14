package interface_adapter.preferences;

import entity.Preferences;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class GetPreferencesPresenterAdditionalTest {
    @Test
    public void setsPreferencesOnPresent() {
        PreferencesViewModel vm = new PreferencesViewModel();
        GetPreferencesPresenter presenter = new GetPreferencesPresenter(vm);
        Preferences prefs = new Preferences(new HashMap<>(), new HashMap<>());
        presenter.presentPreferences(prefs);
        assertEquals(prefs, vm.getPreferences());
    }
}
