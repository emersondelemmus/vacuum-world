package ai.worlds.vacuum;


public class DummyVacuum extends VacuumAgent {

	public int getAction() {		
		if(bumped()) 
			return this.LEFT;
		
		return this.FORWARD;
	}

}

