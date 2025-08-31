package paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Shape
{
    protected int x, y, width, height;
    protected boolean isFilled;
    protected Color color;
    
    public Shape(int x1, int y1, int x2, int y2, boolean isFilled, Color color) {
        this.x = Math.min(x1, x2);
        this.y = Math.min(y1, y2);
        this.width = Math.abs(x2 - x1);
        this.height = Math.abs(y2 - y1);
        this.isFilled = isFilled;
        this.color = color;
        
    }
    
    public abstract void draw(Graphics g);

}

class RectShape extends Shape
{
    public RectShape(int x1, int y1, int x2, int y2, boolean isFilled, Color color) {
        super(x1, y1, x2, y2, isFilled, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (isFilled)
            g.fillRect(x, y, width, height);
        else
            g.drawRect(x, y, width, height);
    }
}

class OvalShape extends Shape
{
    public OvalShape(int x1, int y1, int x2, int y2, boolean isFilled, Color color) {
        super(x1, y1, x2, y2, isFilled, color);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (isFilled)
            g.fillOval(x, y, width, height);
        else
            g.drawOval(x, y, width, height);
    }
}

class LineShape extends Shape
{
    private int x2, y2;

    public LineShape(int x1, int y1, int x2, int y2, boolean isFilled, Color color) {
        super(x1, y1, x2, y2, isFilled, color);
            this.x = x1;
        this.y = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.isFilled = isFilled;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x, y, x2, y2);
    }
}

class FreeHandShape extends Shape
{
    private ArrayList<Point> points = new ArrayList<>();

    public FreeHandShape(int x, int y, Color color) {
        super(x, y, x, y, false, color);
        points.add(new Point(x, y));
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}

class ImageShape extends Shape {
    private BufferedImage image;

    public ImageShape(BufferedImage image, int x, int y) {
        super(x, y, x, y, false, Color.BLACK);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}