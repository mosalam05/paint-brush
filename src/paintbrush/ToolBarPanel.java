package paintbrush;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ToolBarPanel extends JPanel {
    private JCheckBox fillCheckBox = new JCheckBox("Filled");

    public ToolBarPanel(DrawPanel drawPanel) {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        // Left panel
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);

        // Save button
        leftPanel.add(makeButton("Save", "save.png", () -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                drawPanel.saveDrawing(fileChooser.getSelectedFile());
            }
        }));

        // Open button
        leftPanel.add(makeButton("Open", "open.png", () -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                drawPanel.openDrawing(fileChooser.getSelectedFile());
            }
        }));

        // Center panel 
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);

        // Shape buttons 
        centerPanel.add(makeButton("Free Hand", "free_hand.png", () -> {
            drawPanel.setCurrentMode(DrawingMode.FREE_HAND);
            updateFillCheckboxState(DrawingMode.FREE_HAND);
        }));

        centerPanel.add(makeButton("Rectangle", "rectangle.png", () -> {
            drawPanel.setCurrentMode(DrawingMode.RECTANGLE);
            updateFillCheckboxState(DrawingMode.RECTANGLE);
        }));

        centerPanel.add(makeButton("Oval", "oval.png", () -> {
            drawPanel.setCurrentMode(DrawingMode.OVAL);
            updateFillCheckboxState(DrawingMode.OVAL);
        }));

        centerPanel.add(makeButton("Line", "line.png", () -> {
            drawPanel.setCurrentMode(DrawingMode.LINE);
            updateFillCheckboxState(DrawingMode.LINE);
        }));

        centerPanel.add(makeButton("Eraser", "eraser.png", () -> {
            drawPanel.setCurrentMode(DrawingMode.ERASER);
            updateFillCheckboxState(DrawingMode.ERASER);
        }));

        
        // Command buttons
        centerPanel.add(makeButton("Undo", "undo.png", drawPanel::undo));
        centerPanel.add(makeButton("Redo", "redo.png", drawPanel::redo));
        centerPanel.add(makeButton("Clear All", "clear.png", drawPanel::clearAll));

        
        // Color button
        JButton colorButton = new JButton("Color");
        colorButton.setBackground(Color.BLACK);
        colorButton.addActionListener(e -> {
            Color chosenColor = JColorChooser.showDialog(null, "Pick a Color", Color.BLACK);
            if (chosenColor != null) {
                drawPanel.setCurrentColor(chosenColor);
                colorButton.setBackground(chosenColor);
            }
        });
        centerPanel.add(colorButton);

        // Fill checkbox
        fillCheckBox.setContentAreaFilled(false);
        fillCheckBox.setEnabled(false);
        fillCheckBox.addActionListener(e -> drawPanel.setFillMode(fillCheckBox.isSelected()));
        centerPanel.add(fillCheckBox);

        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    private ImageIcon getScaledIcon(String iconName) {
        ImageIcon icon = new ImageIcon("src\\paintbrush\\icons\\" + iconName);
        return new ImageIcon(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
    }

    private JButton makeButton(String toolTip, String iconName, Runnable action) {
        JButton button = new JButton();
        button.setIcon(getScaledIcon(iconName));
        button.setToolTipText(toolTip);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(e -> action.run());
        return button;
    }

    private void updateFillCheckboxState(DrawingMode mode) {
        boolean isEnabled = (mode == DrawingMode.RECTANGLE || mode == DrawingMode.OVAL);
        fillCheckBox.setEnabled(isEnabled);
    }
}
