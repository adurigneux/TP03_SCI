package fr.lille1.sci.fish;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;
import fr.lille1.sci.core.Position;
import fr.lille1.sci.main.PoissonSimulation;

import java.awt.*;

public class Requin extends Agent {

    private int age;
    private int tempsReproduction;
    private int tempsSansManger;
    private int limiteTempsSansManger;

    public Requin(Environnement env, int numero, int x, int y,
                  int tempsReproduction, int limiteTempsSansManger) {
        super(env, 0, 0);

        this.color = Color.RED;
        this.x = x;
        this.y = y;
        this.numero = numero;

        this.tempsReproduction = tempsReproduction;
        this.tempsSansManger = 0;
        this.limiteTempsSansManger = limiteTempsSansManger;

        this.age = 1;
    }

    @Override
    public void decide() {

        if (this.limiteTempsSansManger == this.tempsSansManger) {
            if (PoissonSimulation.DEBUG) {
                System.out.println("Le requin " + numero + " est mort.");
            }
            this.env.remove(x, y);
            return;
        }

        Position newReqPosition = this.env.getPlaceLibre(x, y);
        Position firstThonDispo = this.env.getThonDispo(x, y);

        if (this.age % this.tempsReproduction == 0 && newReqPosition != null) {
            Requin r = new Requin(env, this.numero * 2, newReqPosition.getX(),
                    newReqPosition.getY(), tempsReproduction, tempsSansManger);

            if (PoissonSimulation.DEBUG) {
                System.out.println("Le requin " + numero
                        + "le poisson  s'est reproduit en : " + newReqPosition.getX()
                        + ":" + newReqPosition.getY());
            }
            this.env.addAgent(newReqPosition.getX(), newReqPosition.getY(), r);
            this.tempsSansManger++;

        } else if (firstThonDispo != null) {
            manger(firstThonDispo);

            if (PoissonSimulation.DEBUG) {
                System.out.println("Le requin " + numero
                        + " a mangé le poisson en " + firstThonDispo.getX()
                        + ":" + firstThonDispo.getY());
            }
        } else if (newReqPosition != null) {
            bouger(newReqPosition);
            this.tempsSansManger++;

            if (PoissonSimulation.DEBUG) {
                System.out.println("Le requin " + numero + " s'est déplacé en "
                        + x + ":" + y);
            }

        }

        this.age++;

    }

    private void manger(Position positionThon) {
        this.env.remove(positionThon.getX(), positionThon.getY());
        this.tempsSansManger = 0; // On raz le temps sans manger
        bouger(positionThon);
    }

    @Override
    public String toString() {
        return "Requin [age=" + age + ", tempsSansManger=" + tempsSansManger
                + ", x=" + x + ", y=" + y + ", numero=" + numero;
    }

}
