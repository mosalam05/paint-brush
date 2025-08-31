package paintbrush;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PaintBrush
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Paint Brush");
        DrawPanel drawPanel = new DrawPanel();
        ToolBarPanel toolbar = new ToolBarPanel(drawPanel);

        frame.setLayout(new BorderLayout());
        frame.add(toolbar, BorderLayout.NORTH);
        frame.add(drawPanel, BorderLayout.CENTER);

        ImageIcon icon = new ImageIcon("src\\paintbrush\\icons\\paint.png");
        frame.setIconImage(icon.getImage());

        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
