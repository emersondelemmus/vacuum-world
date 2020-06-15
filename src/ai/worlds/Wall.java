package ai.worlds;

import java.awt.*;

/**
 * A wall object inside a GridEnvironment.
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

public class Wall extends Obj
{
	/**
	 * Draw the wall.
	 * @param g is the graphics object.
	 * @param p is the position to draw.
	 * @param cellSize is the size of the cell in which it is drawn.
	 */
    public void draw(Graphics g, Point p, int cellSize)
    {
	g.setColor(Color.black);
	g.fillRect(p.x,p.y,cellSize,cellSize);
    }
}