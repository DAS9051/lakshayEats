package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import org.junit.Test;
import use_case.login.LoginOutputData;

import static org.junit.Assert.*;

public class LoginPresenterAdditionalTest {
    @Test
    public void successUpdatesLoggedInView() {
        ViewManagerModel manager = new ViewManagerModel();
        LoginViewModel loginVM = new LoginViewModel();
        LoggedInViewModel loggedInVM = new LoggedInViewModel();
        LoginPresenter presenter = new LoginPresenter(manager, loginVM, loggedInVM);
        LoginOutputData data = new LoginOutputData("bob", "ok");
        presenter.prepareSuccessView(data);
        assertEquals("bob", loggedInVM.getState().getUsername());
        assertEquals(loggedInVM.getViewName(), manager.getState());
    }

    @Test
    public void failureSetsLoginError() {
        ViewManagerModel manager = new ViewManagerModel();
        LoginViewModel loginVM = new LoginViewModel();
        LoggedInViewModel loggedInVM = new LoggedInViewModel();
        LoginPresenter presenter = new LoginPresenter(manager, loginVM, loggedInVM);
        presenter.prepareFailView("bad");
        assertEquals("bad", loginVM.getState().getLoginError());
    }
}
