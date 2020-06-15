package ai.worlds;

import java.awt.*;

/**
 * A location within the environment.
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

public class Location extends Point
{
	public Location(int xint, int yint) {x=xint; y=yint;}

	public Location forward(Location heading) 
	// The location which is one cell forward in the given heading
	{
		Location p = new Location(0,0);
		p.x = x + heading.x;
		p.y = y + heading.y;
		return p;
	}
	
	/**
	 * Determine if location equals given location
	 * @param loc is a given location
	 */
	public boolean equals(Location loc) {
		return loc.x == x && loc.y == y;
	}
}