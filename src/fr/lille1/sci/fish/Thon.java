package fr.lille1.sci.fish;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;
import fr.lille1.sci.core.Position;
import fr.lille1.sci.main.PoissonSimulation;

import java.awt.*;

public class Thon extends Agent {

    private int age;
    private int tempsReproduction;

    public Thon(Environnement env, int numero, int x, int y,
                int tempsReproduction) {
        super(env, 0, 0);
        this.numero = numero;
        this.color = Color.BLUE;
        this.x = x;
        this.y = y;
        this.tempsReproduction = tempsReproduction;
        this.age = 1;
    }

    @Override
    public void decide() {

        Position newThonPosition = this.env.getPlaceLibre(x, y);

        if (this.age % this.tempsReproduction == 0 && newThonPosition != null) {
            Thon t = new Thon(env, this.numero * 2, newThonPosition.getX(),
                    newThonPosition.getY(), tempsReproduction);

            this.env.addAgent(newThonPosition.getX(), newThonPosition.getY(), t);

            if (PoissonSimulation.DEBUG) {
                System.out.println("Le poisson " + numero + " s'est reproduit.");
            }
        } else if (newThonPosition != null) {
            bouger(newThonPosition);
            if (PoissonSimulation.DEBUG) {
                System.out.println("Le poisson " + numero + " s'est déplacé en " + x + ":" + y);
            }
        }

        this.age++;
    }

    @Override
    public String toString() {
        return "Thon [age=" + age + ", x=" + x + ", y=" + y + ", numero="
                + numero + "]";
    }

}
