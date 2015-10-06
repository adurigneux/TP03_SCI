package fr.lille1.sci.dijkstra;

import java.util.ArrayList;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;
import fr.lille1.sci.core.SMA;

public class DijkstraSMA extends SMA{

	@Override
	public void init(int tailleX, int tailleY, int nombreAgents) {

		this.env = new Environnement(tailleX, tailleY);
		this.env.setSMA(this);

		this.setAgents(new ArrayList<Agent>(nombreAgents));
	}

	
	public void initMurs(int nombreMurs){
		
	}
	
	public void initAttracteurs(int nombreAttracteurs){
		
	}
	
	public void initPoursuivants(int nombrePoursuivants){
		
	}
	
	@Override
	public void run(int sleepTime) {
		for (Agent a : getAgents()) {
            a.decide();
        }
	}
}
