package fr.lille1.sci.dijkstra;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;

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
        //bouger vers l'attracteur

    }

}
