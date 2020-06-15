package ai.worlds.vacuum;
/**
 * A vacuum agent that simply chooses actions at random
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

public class RandomVacuumAgent extends VacuumAgent
{   
	/**
	 * Determine the next action to be performed.
	 */
	public int getAction()
	{
		int i = (int)Math.floor(Math.random()*5);
		switch (i) {
		case 0: return this.FORWARD;
		case 1: return this.RIGHT;
		case 2: return this.LEFT;
		case 3: return this.OFF;
		case 4: return this.SUCK;
		}
		return this.FORWARD;
	}
}