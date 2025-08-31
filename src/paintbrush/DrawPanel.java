package paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

enum DrawingMode { FREE_HAND, RECTANGLE, OVAL, LINE, ERASER}

public class DrawPanel extends JPanel
{
    private int x1, y1, x2, y2;
    private ArrayList<Shape> shapesMemory = new ArrayList<>();
    private ArrayList<Shape> redoMemory = new ArrayList<>();
    private boolean isDragging = false;
    private boolean fillMode = false;
    private Color currentColor = Color.BLACK;
    private FreeHandShape currentFreeHand;
    DrawingMode drawingMode = DrawingMode.FREE_HAND;
    
    
    public DrawPanel() { 
        setFocusable(true);
        requestFocusInWindow();
               
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                isDragging = true;
                
                if (drawingMode == DrawingMode.FREE_HAND || drawingMode == DrawingMode.ERASER) {
                    Color colorToUse = (drawingMode == DrawingMode.ERASER) ? getBackground() : currentColor;
                    currentFreeHand = new FreeHandShape(x1, y1, colorToUse);
                    shapesMemory.add(currentFreeHand);
                    isDragging = false;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {                          
                if (drawingMode != DrawingMode.FREE_HAND) {
                    x2 = e.getX();
                    y2 = e.getY();

                    switch (drawingMode) {
                        case DrawingMode.RECTANGLE:
                            shapesMemory.add(new RectShape(x1, y1, x2, y2, fillMode, currentColor));
                            break;
                        case DrawingMode.OVAL:
                            shapesMemory.add(new OvalShape(x1, y1, x2, y2, fillMode, currentColor));
                            break;
                        case DrawingMode.LINE:
                            shapesMemory.add(new LineShape(x1, y1, x2, y2, fillMode, currentColor));
                            break;
                    }
                }      
                
                currentFreeHand = null;
                isDragging = false;
                repaint();
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {               
                if ((drawingMode == DrawingMode.FREE_HAND || drawingMode == DrawingMode.ERASER) && currentFreeHand != null) {
                    currentFreeHand.addPoint(e.getX(), e.getY());
                }
                else {
                    x2 = e.getX();
                    y2 = e.getY();
                }
                
                repaint();                          
            }
        });
    }
    
    public void setCurrentMode(DrawingMode drawingMode) {
        this.drawingMode = drawingMode;
    }
        
    public void setFillMode(boolean isFilled) {
        this.fillMode = isFilled;
    }
    
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape shape : shapesMemory) {
            shape.draw(g);
        }
        
        // Preview while dragging
        if (isDragging) {
            g.setColor(currentColor); 
            switch (drawingMode) {
                case DrawingMode.RECTANGLE:
                    if (fillMode)
                        g.fillRect(Math.min(x1, x2), Math.min(y1, y2),
                                   Math.abs(x2 - x1), Math.abs(y2 - y1));
                    else
                        g.drawRect(Math.min(x1, x2), Math.min(y1, y2),
                                   Math.abs(x2 - x1), Math.abs(y2 - y1));
                    break;
                case DrawingMode.OVAL:
                    if (fillMode)
                        g.fillOval(Math.min(x1, x2), Math.min(y1, y2),
                                   Math.abs(x2 - x1), Math.abs(y2 - y1));
                    else
                        g.drawOval(Math.min(x1, x2), Math.min(y1, y2),
                                   Math.abs(x2 - x1), Math.abs(y2 - y1));
                    break;
                case DrawingMode.LINE:
                    g.drawLine(x1, y1, x2, y2);
                    break;
            }
        }
    }
    
    public void undo() {
        if (!shapesMemory.isEmpty()) {
            Shape lastShape = shapesMemory.remove(shapesMemory.size() - 1);
            redoMemory.add(lastShape);
            repaint();
        }
    }
    
    public void redo() {
        if (!redoMemory.isEmpty()) {
            Shape lastShape = redoMemory.remove(redoMemory.size() - 1);
            shapesMemory.add(lastShape);
            repaint();
        }
    }
    
    public void clearAll() {
        int option = JOptionPane.showOptionDialog(null, "Are you sure you want to clear all?", "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, 0);
        if (option == 0) {
            shapesMemory.clear();
            redoMemory.clear();
            repaint();
        }
    }
        
    public void saveDrawing(File file) {
        try {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            paint(g);
            ImageIO.write(image, "png", file);
            g.dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void openDrawing(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            shapesMemory.clear();
            shapesMemory.add(new ImageShape(image, 0, 0));
            repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}