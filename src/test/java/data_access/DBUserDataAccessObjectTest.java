package data_access;

import entity.User;
import entity.UserFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DBUserDataAccessObjectTest {
    private static class SimpleUser implements User {
        private final String username;
        private final String password;
        SimpleUser(String username, String password) {
            this.username = username; this.password = password;
        }
        @Override public String getUsername() { return username; }
        @Override public String getPassword() { return password; }
    }
    private static class SimpleFactory implements UserFactory {
        @Override public User createUser(String name, String password) { return new SimpleUser(name, password); }
        @Override public User createUserWithHashedPassword(String name, String hashedPassword) { return new SimpleUser(name, hashedPassword); }
    }

    @Test
    public void existsByNameReturnsFalseWhenOffline() {
        DBUserDataAccessObject dao = new DBUserDataAccessObject(new SimpleFactory());
        assertFalse(dao.existsByName("unknown"));
    }

    @Test
    public void getAllUsersReturnsEmptyListWhenOffline() {
        DBUserDataAccessObject dao = new DBUserDataAccessObject(new SimpleFactory());
        assertTrue(dao.getAllUsers().isEmpty());
    }

    @Test
    public void saveDoesNotThrow() {
        DBUserDataAccessObject dao = new DBUserDataAccessObject(new SimpleFactory());
        try {
            dao.save(new SimpleUser("alice", "pw"));
        } catch (Exception e) {
            fail("Should not throw");
        }
    }

    @Test
    public void getReturnsNullWhenOffline() {
        DBUserDataAccessObject dao = new DBUserDataAccessObject(new SimpleFactory());
        assertNull(dao.get("alice"));
    }

    @Test
    public void currentUsernameRoundTrip() {
        DBUserDataAccessObject dao = new DBUserDataAccessObject(new SimpleFactory());
        dao.setCurrentUsername("bob");
        assertEquals("bob", dao.getCurrentUsername());
    }

    @Test
    public void changePasswordThrowsRuntimeOnFailure() {
        DBUserDataAccessObject dao = new DBUserDataAccessObject(new SimpleFactory());
        try {
            dao.changePassword(new SimpleUser("alice","pw"));
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }
}
