
package ai.worlds.vacuum;
import java.awt.*;
import ai.worlds.*;

/**
 * Dirt that appears in the Vacuum world.
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

public class Dirt extends Obj
{
    public void draw(Graphics g, Point p, int cellSize)
    {
	g.setColor(Color.gray);
	g.fillRect(p.x+1,p.y+1,cellSize-1,cellSize-1);
    }
}
