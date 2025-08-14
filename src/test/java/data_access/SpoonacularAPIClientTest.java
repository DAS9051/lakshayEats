package data_access;

import entity.FilterOptions;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertThrows;

public class SpoonacularAPIClientTest {
    @Test
    public void searchByDishThrowsOnNetworkError() {
        SpoonacularAPIClient client = new SpoonacularAPIClient("bad");
        assertThrows(IOException.class, () -> client.searchByDish("pasta", new FilterOptions()));
    }

    @Test
    public void loadIDsThrowsOnNetworkError() {
        SpoonacularAPIClient client = new SpoonacularAPIClient("bad");
        assertThrows(IOException.class, () -> client.loadIDs(Collections.singletonList(1)));
    }
}
