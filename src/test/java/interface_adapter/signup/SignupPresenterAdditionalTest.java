package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;
import use_case.signup.SignupOutputData;

import static org.junit.Assert.*;

public class SignupPresenterAdditionalTest {
    @Test
    public void successUpdatesViewModels() {
        ViewManagerModel manager = new ViewManagerModel();
        SignupViewModel signupVM = new SignupViewModel();
        LoginViewModel loginVM = new LoginViewModel();
        SignupPresenter presenter = new SignupPresenter(manager, signupVM, loginVM);
        SignupOutputData data = new SignupOutputData("alice", "ok");
        presenter.prepareSuccessView(data);
        assertEquals("alice", loginVM.getState().getUsername());
        assertEquals(loginVM.getViewName(), manager.getState());
    }

    @Test
    public void failureSetsErrorMessage() {
        ViewManagerModel manager = new ViewManagerModel();
        SignupViewModel signupVM = new SignupViewModel();
        LoginViewModel loginVM = new LoginViewModel();
        SignupPresenter presenter = new SignupPresenter(manager, signupVM, loginVM);
        presenter.prepareFailView("exists");
        assertEquals("exists", signupVM.getState().getUsernameError());
    }
}
