package data_access;

import entity.User;
import entity.UserFactory;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DBFriendRequestDataAccessObjectTest {
    private static class SimpleUser implements User {
        private final String username;
        private final String password;
        SimpleUser(String username, String password) { this.username = username; this.password = password; }
        @Override public String getUsername() { return username; }
        @Override public String getPassword() { return password; }
    }
    private static class InMemoryUserDAO extends DBUserDataAccessObject {
        private final Map<String, User> store = new HashMap<>();
        InMemoryUserDAO() { super(new UserFactory() {
            @Override public User createUser(String name, String password) { return new SimpleUser(name, password); }
            @Override public User createUserWithHashedPassword(String name, String hashedPassword) { return new SimpleUser(name, hashedPassword); }
        }); }
        @Override public void save(User user) { store.put(user.getUsername(), user); }
        @Override public User get(String username) { return store.get(username); }
    }

    @Test
    public void getUserDelegatesToInnerDao() {
        InMemoryUserDAO userDao = new InMemoryUserDAO();
        SimpleUser u = new SimpleUser("alice", "pw");
        userDao.save(u);
        DBFriendRequestDataAccessObject dao = new DBFriendRequestDataAccessObject(userDao);
        assertEquals(u, dao.getUser("alice"));
    }

    @Test
    public void areFriendsReturnsFalseWhenOffline() {
        DBFriendRequestDataAccessObject dao = new DBFriendRequestDataAccessObject(new InMemoryUserDAO());
        assertFalse(dao.areFriends("a", "b"));
    }

    @Test
    public void hasFriendRequestReturnsFalseWhenOffline() {
        DBFriendRequestDataAccessObject dao = new DBFriendRequestDataAccessObject(new InMemoryUserDAO());
        assertFalse(dao.hasFriendRequest("a", "b"));
    }

    @Test
    public void sendFriendRequestThrowsWhenOffline() {
        DBFriendRequestDataAccessObject dao = new DBFriendRequestDataAccessObject(new InMemoryUserDAO());
        assertThrows(IOException.class, () -> dao.sendFriendRequest("a", "b"));
    }

    @Test
    public void saveMessageDoesNotThrow() {
        DBFriendRequestDataAccessObject dao = new DBFriendRequestDataAccessObject(new InMemoryUserDAO());
        try {
            dao.saveMessage("a", "b", "hi", LocalDateTime.now());
        } catch (Exception e) {
            fail("Should not throw");
        }
    }

    @Test
    public void isBlockedReturnsFalseWhenOffline() {
        DBFriendRequestDataAccessObject dao = new DBFriendRequestDataAccessObject(new InMemoryUserDAO());
        assertFalse(dao.isBlocked("a", "b"));
    }
}
