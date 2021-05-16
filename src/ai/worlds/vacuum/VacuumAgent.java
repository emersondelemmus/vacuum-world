package ai.worlds.vacuum;

import ai.worlds.*;
import java.util.*;

/**
 * A generic vacuum agent. 
 *
 */
public abstract class VacuumAgent extends Agent
{  
	public final int OFF =0;
	public final int SUCK = 1;
	public final int LEFT = 2;
	public final int RIGHT = 3;
	public final int FORWARD = 4;

	public HashMap<Integer,String> map = new HashMap<Integer,String>();

	public  String lastAction = null;

	public VacuumAgent(){
		super();
		map.put(OFF, "shut-off");
		map.put(SUCK, "suck");
		map.put(LEFT, "turn left");
		map.put(RIGHT, "turn right");
		map.put(FORWARD, "forward");
	}

	/**
	 * shut off the vacuum.
	 */
	public void shutOff( )
	{
		body.alive=false;
	}

	/**
	 * suck dirt.
	 * @param vw is the vacuum world environment.
	 */
	void suck(VacuumWorld vw)
	{
		vw.grab(body);
	}

	/**
	 * take the next action.
	 * @param e is the environment.
	 */
	public void takeAction(Environment e)
	{
		VacuumWorld vw = (VacuumWorld) e;
		if (action.equals("suck")) suck(vw);
		else if (action.equals("forward")) vw.forward(body);
		else if (action.equals("turn right")) vw.turn(body,"right");
		else if (action.equals("turn left")) vw.turn(body,"left");
		else if (action.equals("shut-off")) shutOff();
	} 

	public boolean bumped(){
		Vector p = (Vector)percept;
		return p.elementAt(0).equals("bump");
	}

	public boolean seesDirt(){
		Vector p = (Vector)percept;
		return p.elementAt(1).equals("dirt");
	}

	public boolean isHome(){
		Vector p = (Vector)percept;
		return p.elementAt(2).equals("home");
	}

	public void determineAction(){
		int act = this.getAction();
		action = map.get(act);
		lastAction = this.action;//****************
	}

	public abstract int getAction();
}
