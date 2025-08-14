package data_access;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DBMessageDataAccessObjectTest {
    @Test
    public void fetchAllMessagesReturnsEmptyListWhenOffline() {
        DBMessageDataAccessObject dao = new DBMessageDataAccessObject();
        assertTrue(dao.fetchAllMessages().isEmpty());
    }

    @Test
    public void saveMessageDoesNotThrow() {
        DBMessageDataAccessObject dao = new DBMessageDataAccessObject();
        try {
            dao.saveMessage("a", "b", "hi", LocalDateTime.now());
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }
}
