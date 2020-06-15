package ai.worlds.vacuum;


import java.util.Vector;
import ai.worlds.*;
import javax.swing.*;
/**
 * A Vacuum environment. 
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */
public class VacuumWorld extends GridEnvironment
{
    public VacuumWorld(Agent[] a, int xsize, int ysize, double probDirt, JFrame f) {
    	super(a,xsize,ysize, f);
    	//fillGrid(probDirt,(new Dirt()).getClass());
    	revisedFillGrid(xsize*ysize/3,(new Dirt()).getClass());
    }
    
    /**
     * Determine if an action is legal.
     * @param a is an action string.
     */   
    public boolean legalAction(String a) {
    	return (a.equals("suck"))||(a.equals("forward"))||(a.equals("turn right"))||(a.equals("turn left"))||(a.equals("shut-off"));
    }
    
    /**
     * Get the next percept.
     * @param a is the agent.
     */  
    public Object getPercept(Agent a) {
    	Location loc = a.body.loc;
    	Vector v = new Vector(4);
    	Vector gr = (Vector) grid[loc.x][loc.y];
    	if (a.body.bump) v.addElement("bump");
    		else v.addElement("");
    	if (contains(gr,(new Dirt()).getClass())) v.addElement("dirt");
    		else v.addElement("");
    	if (loc.x == 1 && loc.y == 1) v.addElement("home");
    		else v.addElement("");
    	return v;
    }
   
    /**
     * Determine performance of the agent.
     * @param a is the agent.
     */  
    public int performanceMeasure(Agent a) {
    	AgentBody body = a.body;
    	int score = 100 * body.container.size() - 10*step;
    	if (! body.alive && !(body.loc.x==1 && body.loc.y==1)) score = score - 1000;
    	a.score=score;
    	return score;
    }
    
}

