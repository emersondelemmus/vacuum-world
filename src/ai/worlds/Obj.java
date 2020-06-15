package ai.worlds;


import java.awt.*;
import java.util.Vector;

/**
 * A generic object that is contained in an environment.
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

public abstract class Obj
{
	/**
	 * The name of the object.
	 */
	public String name = new String();  
	/**
	 * Is the object alive?
	 */
	public boolean alive = false;	    
	/**
	 * The location of the object.
	 */
	public Location loc = new Location(1,1); 
	/**
	 * The heading of the object.
	 */
	public Location heading = new Location(1,0); 
	/**
	 * Status of bumps to the object.
	 */
	public boolean bump = false;	
	/**
	 * Some objects may contain other objects.
	 */
	public Vector container = new Vector();  
	/**
	 * Status of sounds from the object.
	 */
	public boolean sound = false;	
	
	/**
	 * Draw the object.
	 * @param g is the graphics object.
	 * @param p is the position to draw.
	 * @param cellSize is the size of the cell in which it is drawn.
	 */
	public abstract void draw(Graphics g, Point p, int cellSize);
	
}