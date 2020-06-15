package ai.worlds;
/**
 * A generic environment.
 * All environments must implement getPercept, performanceMeasure, 
 * snapshot, termination, and legalAction methods
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

public abstract class Environment implements Runnable
{
	/**
	 * The agents within the environment.
	 */
	public Agent[] agents = new Agent[0];
	/**
	 * The current step number.
	 */
	public int step = 0;	 
	/**
	 * The maximum number of steps.
	 */
	public int maxSteps = 1000;	    
	/**
	 * Flag to indicate whether to display
	 */
	public boolean display = true;    
	/**
	 * The animation thread.
	 */
	public Thread runner;	    
    
	/**
	 * Get the current percept.
	 * @param a Agent in the environment
	 */
	public abstract Object getPercept(Agent a);
	/**
	 * Score the performance of the agent.
	 * @param a Agent in the environment
	 */
	public abstract int performanceMeasure(Agent a);
	/**
	 * A snapshot of the environment.
	 */
	public abstract void snapshot();
	/**
	 * Determine if all agents have terminated.
	 */
	public abstract boolean termination();
	/**
	 * Determine if action is legal.
	 * @param a Possible action.
	 */
	public abstract boolean legalAction(String a);


	public void start()
	{
	    runner = new Thread(this);
	    runner.start();
	}
	
	public void stop()
	{
	    runner = null;
	}
	
	/**
	 * Run the environment until maximum steps or termination
	 * criteria are met.
	 */	
	public void run()
	{
		Simulator mySim = new Simulator();
		mySim.start();
	}
	
	/**
	 * Each agent determines and performs one action.
	 */
	public void takeStep()
	{
	    if (step < maxSteps && !termination()) {
			step++;
			// update the percept and action of each agent
			for (int j=0; j<agents.length; j++)
			{
				Agent a = agents[j];
				a.percept= getPercept(a);
				a.determineAction();
			}
			// update the environment
			updateEnv();
			// set the scores of the agents
			for (int j=0; j<agents.length; j++)
				agents[j].score=performanceMeasure(agents[j]);
			displayEnv();
		}
	}

	/**
	 * Perform one animation step if the display flag is set.
	 */
	public void displayEnv()
	{
		if (display) {
		   snapshot();
		   try { Thread.sleep(300); }
		   catch(Exception e) {}
		}
	}

	/**
	 * Update the environment after each step.
	 */
	public void updateEnv()
	{
	    executeAgentActions();
	}

	/**
	 * Each agent executes its current action.
	 */
	public void executeAgentActions()
	{
	    // each agent takes an action if legal
	    for (int i=0; i<agents.length; i++) {
		String act = agents[i].action;
		if (legalAction(act))
		    agents[i].takeAction(this);
	    }
	}
	protected class Simulator extends Thread {
		public void run() {
			displayEnv();
			while (step < maxSteps && !termination()) {
				takeStep();
			}
		}
	}
}