package interface_adapter;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class ViewManagerTest {
    private static class TestCardLayout extends CardLayout {
        String last;
        @Override
        public void show(Container parent, String name) {
            last = name;
        }
    }

    @Test
    public void propertyChangeShowsCard() {
        TestCardLayout layout = new TestCardLayout();
        JPanel panel = new JPanel(layout);
        ViewManagerModel model = new ViewManagerModel();
        new ViewManager(panel, layout, model);
        model.setState("view1");
        model.firePropertyChanged();
        assertEquals("view1", layout.last);
    }
}
