package gui2;

import java.awt.*;
import javax.swing.*;

public class CloseIcon implements Icon{

    private int width = 10;
    private int height = 10;
    private boolean mouseover = false;

    private BasicStroke stroke = new BasicStroke(4);
    
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        if(mouseover)
        	 g2d.setColor(Color.red);
        else
        	g2d.setColor(Color.GRAY);
        
        g2d.setStroke(stroke);
        g2d.drawLine(x, y, x + width, y + height);
        g2d.drawLine(x, y + height, x + width, y);

        g2d.dispose();
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }
    
    public void mouseEnter() {
    	mouseover = true;
    }
    
    public void mouseExit() {
    	mouseover = false;
    }
    
}
