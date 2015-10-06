package fr.lille1.sci.dijkstra;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;

import java.awt.*;

public class Mur extends Agent {

    public Mur(Environnement env, int x, int y) {
        super(env, x, y);
        this.x = x;
        this.y = y;

        this.color = Color.black;
    }

    @Override
    public void decide() {
        //ne rien faire
    }

}
