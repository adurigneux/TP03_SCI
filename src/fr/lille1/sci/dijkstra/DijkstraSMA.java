package fr.lille1.sci.dijkstra;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;
import fr.lille1.sci.core.SMA;

import java.util.ArrayList;
import java.util.Random;

public class DijkstraSMA extends SMA {

    private boolean isFinished = false;

    @Override
    public void init(int tailleX, int tailleY, int nombreAgents) {

        this.env = new Environnement(tailleX, tailleY);
        this.env.setSMA(this);

        this.setAgents(new ArrayList<Agent>(nombreAgents + (tailleX - 1) * 2 + (tailleY - 1) * 2));
    }


    public void initMurs(int nombreMurs) {

        //contour
        for (int i = 0; i < this.env.getTailleX(); i++) {
            for (int j = 0; j < this.env.getTailleY(); j++) {
                Mur mur = new Mur(this.env, i, j);
                if (this.env.estVide(i, j)) {
                    if (i == 0 || i + 1 == this.env.getTailleX()) {
                        this.env.put(i, j, mur);
                        this.getAgents().add(mur);
                    } else if (j == 0 || j + 1 == this.env.getTailleY()) {
                        this.env.put(i, j, mur);
                        this.getAgents().add(mur);
                    }
                }
            }
        }

        for (int i = 0; i < nombreMurs; i++) {
            int x, y;
            do {
                Random random = new Random();
                x = random.nextInt(env.getTailleX());
                y = random.nextInt(env.getTailleY());
            } while (!this.env.estVide(x, y));

            Mur mur = new Mur(this.env, x, y);
            this.env.put(mur.getX(), mur.getY(), mur);
            this.getAgents().add(mur);
        }

    }

    public void initAttracteurs(int nombreAttracteurs) {
        for (int i = 0; i < nombreAttracteurs; i++) {
            int x, y;
            do {
                Random random = new Random();
                x = random.nextInt(env.getTailleX());
                y = random.nextInt(env.getTailleY());
            } while (!this.env.estVide(x, y));

            Attracteur attracteur = new Attracteur(this.env, x, y);
            this.env.put(attracteur.getX(), attracteur.getY(), attracteur);
            this.getAgents().add(attracteur);
        }
    }

    public void initPoursuivants(int nombrePoursuivants) {
        for (int i = 0; i < nombrePoursuivants; i++) {
            int x, y;
            do {
                Random random = new Random();
                x = random.nextInt(env.getTailleX());
                y = random.nextInt(env.getTailleY());
            } while (!this.env.estVide(x, y));

            Poursuivant poursuivant = new Poursuivant(this.env, x, y);
            this.env.put(poursuivant.getX(), poursuivant.getY(), poursuivant);
            this.getAgents().add(poursuivant);
        }
    }


    public boolean isFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    @Override
    public void run(int sleepTime) {
        while (!isFinished) {
            for (Agent a : getAgents()) {
                a.decide();
            }

            //changer
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            setChanged();
            notifyObservers();
        }
    }
}
