package view;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class LabelTextPanelTest {
    @Test
    public void constructorSetsLayoutAndBackground() {
        JLabel label = new JLabel("Name");
        JTextField field = new JTextField();
        LabelTextPanel panel = new LabelTextPanel(label, field);
        assertTrue(panel.getLayout() instanceof FlowLayout);
        assertEquals(new Color(238, 255, 238), panel.getBackground());
        assertEquals(panel, label.getParent());
        assertEquals(panel, field.getParent());
    }
}
