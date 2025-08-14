package interface_adapter.preferences;

import org.junit.Test;

import static org.junit.Assert.*;

public class SavePreferencesPresenterAdditionalTest {
    @Test
    public void setsMessageOnSuccess() {
        PreferencesViewModel vm = new PreferencesViewModel();
        SavePreferencesPresenter presenter = new SavePreferencesPresenter(vm);
        presenter.prepareSuccessView("ok");
        assertEquals("ok", vm.getMessage());
    }

    @Test
    public void setsMessageOnFailure() {
        PreferencesViewModel vm = new PreferencesViewModel();
        SavePreferencesPresenter presenter = new SavePreferencesPresenter(vm);
        presenter.prepareFailView("error");
        assertEquals("error", vm.getMessage());
    }
}
