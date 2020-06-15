package ai.worlds;

import java.awt.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
/**
 * An agent's body.
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */
public class AgentBody extends Obj
{
	public boolean grabbed = false;
	
	public AgentBody()
	{
		alive = true;
	}
	
	public void draw(Graphics g, Point p, int cellSize)
	// draw the body at point p in the graphics object
	{
	    Color metalColor = MetalLookAndFeel.getTextHighlightColor();
	    g.setColor(metalColor);
	    Point ruc;
	    if (!bump)
		ruc = new Point (p.x+cellSize/4,p.y+cellSize/4);
	    else
		ruc = new Point (p.x+cellSize/4+heading.x*cellSize/4,
				 p.y+cellSize/4-heading.y*cellSize/4);
	    g.fill3DRect(ruc.x,ruc.y,cellSize/2,cellSize/2,true);
	    
	    if (bump)
	    {
		g.setColor(Color.red);
		int x1 = p.x + cellSize/2 + heading.x*cellSize/2 - Math.abs(heading.y)*cellSize/4;
		int y1 = p.y + cellSize/2 - heading.y*cellSize/2 - Math.abs(heading.x)*cellSize/4;
		int x2 = p.x + cellSize/2 + heading.x*cellSize/2 + Math.abs(heading.y)*cellSize/4;
		int y2 = p.y + cellSize/2 - heading.y*cellSize/2 + Math.abs(heading.x)*cellSize/4;	
		g.drawLine(x1,y1,x2,y2);
	    }
	    if (alive) {
		g.setColor(Color.black);
		Point cp;
		if(!bump)
		    cp = new Point(p.x+cellSize/2,p.y+cellSize/2);
		else
		    cp = new Point(p.x+cellSize/2+heading.x*cellSize/4,
				   p.y+cellSize/2-heading.y*cellSize/4);
		int x1 = cp.x + (-heading.x+1-Math.abs(heading.x))*cellSize/8;
		int y1 = cp.y + (heading.y+1-Math.abs(heading.y))*cellSize/8;
		int x2 = cp.x + (heading.x)*cellSize/8;
		int y2 = cp.y + (-heading.y)*cellSize/8;
		int x3 = cp.x + (-heading.x-1+Math.abs(heading.x))*cellSize/8; 
		int y3 = cp.y + (heading.y-1+Math.abs(heading.y))*cellSize/8;
		g.drawLine(x1,y1,x2,y2);
		g.drawLine(x2,y2,x3,y3);
	    }
	}
	

}