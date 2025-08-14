package data_access;

import entity.Review;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DBReviewDataAccessObjectTest {
    @Test
    public void saveReturnsFalseWhenOffline() {
        DBReviewDataAccessObject dao = new DBReviewDataAccessObject();
        Review review = new Review(1, 5, "a", "text", LocalDateTime.now());
        assertFalse(dao.save(review));
    }

    @Test
    public void fetchByRecipeIdReturnsEmptyWhenOffline() {
        DBReviewDataAccessObject dao = new DBReviewDataAccessObject();
        assertTrue(dao.fetchByRecipeId("1").isEmpty());
    }

    @Test
    public void fetchAllReturnsEmptyWhenOffline() {
        DBReviewDataAccessObject dao = new DBReviewDataAccessObject();
        assertTrue(dao.fetchAll().isEmpty());
    }
}
