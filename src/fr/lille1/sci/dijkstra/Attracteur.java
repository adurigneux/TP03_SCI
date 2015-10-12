package fr.lille1.sci.dijkstra;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;
import fr.lille1.sci.core.Position;

import java.awt.*;

public class Attracteur extends Agent {

    public Attracteur(Environnement env, int x, int y) {
        super(env, x, y);
        this.x = x;
        this.y = y;

        this.color = Color.GREEN;
    }

    @Override
    public void decide() {
         Position newPosition = this.env.getPlaceLibre(x, y);
		 this.bouger(newPosition);
    }

}
