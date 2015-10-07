package fr.lille1.sci.dijkstra;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;
import fr.lille1.sci.core.Position;

import java.awt.*;

public class Poursuivant extends Agent {

	public Poursuivant(Environnement env, int x, int y) {
		super(env, x, y);
		this.x = x;
		this.y = y;

		this.color = Color.red;
	}

	@Override
	public void decide() {
		// bouger vers l'attracteur
		int min = Integer.MAX_VALUE;
		int newX = 0;
		int newY = 0;

		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (min > this.env.getDikstraMap()[i][j]
						&& !this.env.estEnDehors(i, j)
						&& this.env.estVide(i, j)) {
					min = this.env.getDikstraMap()[i][j];
					newX = i;
					newY = j;
				}
			}
		}

		bouger(new Position(newX, newY));
	}

}
