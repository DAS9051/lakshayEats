package interface_adapter.signup;

import org.junit.Test;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

import static org.junit.Assert.*;

public class SignupControllerAdditionalTest {
    private static class FakeInteractor implements SignupInputBoundary {
        SignupInputData lastData;
        boolean switched;
        @Override
        public void execute(SignupInputData data) { lastData = data; }
        @Override
        public void switchToLoginView() { switched = true; }
    }

    @Test
    public void signupDelegatesToInteractor() {
        FakeInteractor interactor = new FakeInteractor();
        SignupController controller = new SignupController(interactor);
        controller.signup("user", "p1", "p2");
        assertEquals("user", interactor.lastData.getUsername());
        assertEquals("p1", interactor.lastData.getPassword());
        assertEquals("p2", interactor.lastData.getRepeatPassword());
    }

    @Test
    public void switchToLoginDelegates() {
        FakeInteractor interactor = new FakeInteractor();
        SignupController controller = new SignupController(interactor);
        controller.switchToLoginView();
        assertTrue(interactor.switched);
    }
}
