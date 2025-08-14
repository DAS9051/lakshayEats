package view;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class AppShellTest {
    static {
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    public void switchesPagesOnButtonClick() {
        JPanel search = new JPanel();
        JPanel saved = new JPanel();
        JPanel feed = new JPanel();
        JPanel friends = new JPanel();
        JPanel account = new JPanel();

        AppShell shell = new AppShell(search, saved, feed, friends, account);

        assertTrue(search.isVisible());

        JToolBar tb = (JToolBar) shell.getComponent(0);
        JButton savedBtn = (JButton) tb.getComponent(1);
        savedBtn.doClick();

        assertTrue(saved.isVisible());
        assertFalse(search.isVisible());
    }
}
