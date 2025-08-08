package view.search;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.logging.Logger;

import entity.SearchResult;
import interface_adapter.save.SaveController;

public class RecipeCardPanel extends JPanel {
    private static final Logger LOGGER = Logger.getLogger(RecipeCardPanel.class.getName());

    private final SearchResult result;
    private final JLabel imageLabel = new JLabel();
    private final SaveController saveController; // nullable
    private final String username;               // nullable

    // Used by SearchView (no save controller / username)
    public RecipeCardPanel(SearchResult result) {
        this(result, null, null);
    }

    // Used by SavedPage (has save controller and username)
    public RecipeCardPanel(SearchResult result, SaveController saveController, String username) {
        this.result = result;
        this.saveController = saveController;
        this.username = username;

        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // --- Left: image (loaded async) ---
        imageLabel.setPreferredSize(new Dimension(100, 100));
        add(imageLabel, BorderLayout.WEST);
        loadImageAsync();

        // --- Center: info ---
        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        JLabel titleLabel = new JLabel(result.getTitle());
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        infoPanel.add(titleLabel);
        infoPanel.add(new JLabel("Ready in: " + result.getReadyInMinutes() + " mins"));
        add(infoPanel, BorderLayout.CENTER);

        // --- Interaction: open details (don’t hide parent) ---
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openDetails();
            }
        });
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void openDetails() {
        Window owner = SwingUtilities.getWindowAncestor(this);
        if (saveController != null && username != null) {
            new RecipeDetailDialog(owner, result, saveController, username).setVisible(true);
        } else {
            new RecipeDetailDialog(owner, result).setVisible(true);
        }
    }

    /**
     * Loads the recipe image on a background thread,
     * then sets it on the imageLabel when done.
     */
    private void loadImageAsync() {
        final String imgUrl = result.getImage();
        if (imgUrl == null || imgUrl.isBlank()) {
            // optional: a placeholder
            imageLabel.setText("No image");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            return;
        }

        new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                URL url = new URL(imgUrl);
                BufferedImage img = ImageIO.read(url);
                if (img == null) return null;
                Dimension preferredSize = imageLabel.getPreferredSize();
                Image scaled = img.getScaledInstance(
                        preferredSize.width, preferredSize.height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }

            @Override
            protected void done() {
                try {
                    ImageIcon icon = get();
                    if (icon != null) {
                        imageLabel.setText(null);
                        imageLabel.setIcon(icon);
                    }
                } catch (Exception e) {
                    LOGGER.warning("Failed to load recipe image: " + e.getMessage());
                    // keep placeholder if loading fails
                }
            }
        }.execute();
    }
}
