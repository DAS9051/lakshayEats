package interface_adapter.save;

import org.junit.Test;
import use_case.save.SaveOutputData;

import static org.junit.Assert.*;

public class SavePresenterAdditionalTest {
    @Test
    public void successSetsMessage() {
        SaveViewModel vm = new SaveViewModel();
        SavePresenter presenter = new SavePresenter(vm);
        presenter.prepareSuccessView(new SaveOutputData("ok", null));
        assertEquals("Saved!", vm.getSaveState().getMessage());
    }

    @Test
    public void errorSetsMessage() {
        SaveViewModel vm = new SaveViewModel();
        SavePresenter presenter = new SavePresenter(vm);
        presenter.prepareErrorView("bad");
        assertEquals("bad", vm.getSaveState().getMessage());
    }
}
