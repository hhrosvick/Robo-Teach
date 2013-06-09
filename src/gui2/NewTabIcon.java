package gui2;

import java.awt.*;
import javax.swing.*;

public class NewTabIcon implements Icon{

    private int width = 10;
    private int height = 10;
    private boolean mouseover = false;

    private BasicStroke stroke = new BasicStroke(4);
    
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        int xhalf = (x + width)/2;
        int yhalf = (y + height)/2;
        
        if(mouseover)
        	 g2d.setColor(Color.green);
        else
        	g2d.setColor(Color.GRAY);
        
        g2d.setStroke(stroke);
        g2d.drawLine(xhalf, y, xhalf, y + height);
        g2d.drawLine(x, yhalf, x+width, yhalf);

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
