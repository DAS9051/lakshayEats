package data_access;

import entity.User;
import org.junit.Test;

import static org.junit.Assert.fail;

public class DBChangePasswordDataAccessObjectTest {
    @Test
    public void changePasswordDoesNotThrow() {
        DBChangePasswordDataAccessObject dao = new DBChangePasswordDataAccessObject();
        User user = new User() {
            @Override
            public String getUsername() { return "alice"; }
            @Override
            public String getPassword() { return "pass"; }
        };
        try {
            dao.changePassword(user);
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }
}
