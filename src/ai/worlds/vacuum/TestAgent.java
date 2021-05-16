package ai.worlds.vacuum;



public class TestAgent extends VacuumAgent {

	public int getAction() {		
		if(bumped()) 
			return this.LEFT;
		else if(seesDirt())
			return this.SUCK;
		if(Math.random()*10>5)
			return this.LEFT;
		System.out.println("this is the last Action of the " +
				"Agent: " + lastAction);
		return this.FORWARD;
	}
}
