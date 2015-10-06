package fr.lille1.sci.dijkstra;

import java.awt.Color;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;

public class Attracteur extends Agent {

	public Attracteur(Environnement env, int x, int y) {
		super(env, x, y);
		this.x = x;
		this.y = y;
		
		this.color = Color.GREEN;
	}

	@Override
	public void decide() {
		// bouger au hasard sans toucher les murs

	}

}
