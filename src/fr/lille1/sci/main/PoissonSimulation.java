package fr.lille1.sci.main;

import fr.lille1.sci.core.SMA;
import fr.lille1.sci.fish.PoissonSMA;
import fr.lille1.sci.fish.Requin;
import fr.lille1.sci.fish.Thon;

import java.io.*;

public class PoissonSimulation {

    public static final boolean DEBUG = false;


    public static void main(String[] args) {

        if (args.length < 9) {
            System.out.println("Usage : java Simulation tailleX tailleY tailleCase nombrePoissons nombreRequins tempsReproductionPoisson tempsReproductionRequin tempsSansMangerRequin sleepTime");
            return;
        }

        int tailleX = Integer.parseInt(args[0]);
        int tailleY = Integer.parseInt(args[1]);
        int tailleCase = Integer.parseInt(args[2]);

        int nombrePoissons = Integer.parseInt(args[3]);
        int nombreRequins = Integer.parseInt(args[4]);

        int tempsReproductionPoisson = Integer.parseInt(args[5]);
        int tempsReproductionRequin = Integer.parseInt(args[6]);
        int tempsSansManger = Integer.parseInt(args[7]);

        int sleepTime = Integer.parseInt(args[8]);


        removeFile();


        System.out.println(sleepTime);

        PoissonSMA sma = new PoissonSMA();

        sma.init(tailleX, tailleY, nombrePoissons + nombreRequins);

        sma.initThons(nombrePoissons, tempsReproductionPoisson);

        sma.initRequins(nombreRequins, tempsReproductionRequin, tempsSansManger);

        PixelCanvas canvas = new PixelCanvas(tailleX, tailleY, tailleCase);
        sma.addObserver(canvas);
        createFile();
        sma.run(sleepTime);

    }

    public static void createFile() {

        try {
            File requin = new File("requin.txt");
            File thon = new File("thon.txt");

            requin.createNewFile();
            thon.createNewFile();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeFile() {

        File requin = new File("requin.txt");
        File thon = new File("thon.txt");

        requin.delete();
        thon.delete();
    }

    public static void enregisterPopulations(SMA sma){
        PrintWriter outRequin = null;
        PrintWriter outThon = null;
        try{
            outRequin = new PrintWriter(new BufferedWriter(new FileWriter("requin.txt", true)));
            outThon = new PrintWriter(new BufferedWriter(new FileWriter("thon.txt", true)));

            outRequin.println( sma.getPopulationOf(Requin.class));
            outThon.println( sma.getPopulationOf(Thon.class));

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            outRequin.close();
            outThon.close();
        }
    }
}