package interface_adapter;

import org.junit.Test;

import static org.junit.Assert.*;

public class ViewManagerModelTest {
    @Test
    public void startsWithEmptyState() {
        ViewManagerModel model = new ViewManagerModel();
        assertEquals("", model.getState());
    }
}
