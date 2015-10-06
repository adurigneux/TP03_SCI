package fr.lille1.sci.main;

import fr.lille1.sci.dijkstra.DijkstraSMA;


public class DijkstraSimulation {

    public static final boolean DEBUG = false;


    public static void main(String[] args) {

        if (args.length < 9) {
            System.out.println("Usage : java Simulation tailleX tailleY tailleCase nombreAttracteur nombrePoursuivant nombreMur sleepTime");
            return;
        }

        int tailleX = Integer.parseInt(args[0]);
        int tailleY = Integer.parseInt(args[1]);
        int tailleCase = Integer.parseInt(args[2]);

        int nombreAttracteur = Integer.parseInt(args[3]);
        int nombrePoursuivant = Integer.parseInt(args[4]);
        int nombreMur = Integer.parseInt(args[5]);

        int sleepTime = Integer.parseInt(args[6]);


        DijkstraSMA sma = new DijkstraSMA();
        
        sma.init(tailleX, tailleY, nombreAttracteur + nombrePoursuivant + nombreMur);
        
        sma.initMurs(nombreMur);
        sma.initAttracteurs(nombreAttracteur);
        sma.initPoursuivants(nombrePoursuivant);

        PixelCanvas canvas = new PixelCanvas(tailleX, tailleY, tailleCase);
        sma.addObserver(canvas);
        sma.run(sleepTime);

    }


}