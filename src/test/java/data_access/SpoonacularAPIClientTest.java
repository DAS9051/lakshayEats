package data_access;

import entity.FilterOptions;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.*;

public class SpoonacularAPIClientTest {
    @Test
    public void searchByDishThrowsOnNetworkError() {
        SpoonacularAPIClient client = new SpoonacularAPIClient("bad");
        try {
            client.searchByDish("pasta", new FilterOptions());
            fail();
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void loadIDsThrowsOnNetworkError() {
        SpoonacularAPIClient client = new SpoonacularAPIClient("bad");
        try {
            client.loadIDs(Collections.singletonList(1));
            fail();
        } catch (IOException e) {
            assertTrue(true);
        }
    }
}
