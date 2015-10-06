package fr.lille1.sci.billes;

import fr.lille1.sci.core.Agent;
import fr.lille1.sci.core.Environnement;

import java.awt.*;
import java.util.Random;

public class Bille extends Agent {

    public static final int UPPER_LEFT = 1;
    public static final int UPPER_RIGHT = 2;
    public static final int UPPER_CENTER = 3;
    public static final int LOWER_LEFT = 4;
    public static final int LOWER_RIGHT = 5;
    public static final int LOWER_CENTER = 6;
    public static final int RIGHT = 7;
    public static final int LEFT = 8;
    private int vitesse; //(vitesse = nombre de cases par "tour")
    private int sens;


    public Bille(Environnement env, int numero) {
        super(env, 0, 0);
        Random random = new Random();

        this.x = random.nextInt(env.getTailleX());
        this.y = random.nextInt(env.getTailleY());

        this.color = randomColor();

        this.numero = numero;
        this.vitesse = 1;
        this.sens = random.nextInt(7) + 1;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getSens() {
        return sens;
    }

    public void setSens(int sens) {
        this.sens = sens;
    }

    @Override
    public void decide() {
        int newX, newY;

        switch (sens) {
            case Bille.UPPER_CENTER:

                newY = y - vitesse;

                if (this.getEnv().estDehorsY(newY)) {
                    sens = Bille.LOWER_CENTER;
                    y = y + vitesse;
                } else {
                    y = newY;
                }

                break;
            case Bille.UPPER_LEFT:

                newX = x - vitesse;
                newY = y - vitesse;

                if (this.getEnv().estEnDehors(newX, newY)) {
                    sens = Bille.LOWER_RIGHT;
                    x = x + vitesse;
                    y = y + vitesse;
                } else if (this.getEnv().estDehorsX(newX)) {
                    sens = Bille.UPPER_RIGHT;
                    x = x + vitesse;
                    y = newY;
                } else if (this.getEnv().estDehorsY(newY)) {
                    sens = Bille.LOWER_LEFT;
                    y = y + vitesse;
                    x = newX;
                } else {
                    x = newX;
                    y = newY;
                }

                break;
            case Bille.UPPER_RIGHT:

                newX = x + vitesse;
                newY = y - vitesse;


                if (this.getEnv().estEnDehors(newX, newY)) {
                    sens = Bille.LOWER_LEFT;
                    x = x - vitesse;
                    y = y + vitesse;
                } else if (this.getEnv().estDehorsX(newX)) {
                    sens = Bille.UPPER_LEFT;
                    x = x - vitesse;
                    y = newY;
                } else if (this.getEnv().estDehorsY(newY)) {
                    sens = Bille.LOWER_RIGHT;
                    y = y + vitesse;
                    x = newX;
                } else {
                    x = newX;
                    y = newY;
                }


                break;
            case Bille.LOWER_CENTER:

                newY = y + vitesse;

                if (this.getEnv().estDehorsY(newY)) {
                    sens = Bille.UPPER_CENTER;
                    y = y - vitesse;
                } else {
                    y = newY;
                }

                break;
            case Bille.LOWER_LEFT:

                newX = x - vitesse;
                newY = y + vitesse;

                if (this.getEnv().estEnDehors(newX, newY)) {
                    sens = Bille.UPPER_RIGHT;
                    x = x + vitesse;
                    y = y - vitesse;
                } else if (this.getEnv().estDehorsX(newX)) {
                    sens = Bille.LOWER_RIGHT;
                    x = x + vitesse;
                    y = newY;
                } else if (this.getEnv().estDehorsY(newY)) {
                    sens = Bille.UPPER_LEFT;
                    y = y - vitesse;
                    x = newX;
                } else {
                    x = newX;
                    y = newY;
                }

                break;
            case Bille.LOWER_RIGHT:

                newX = x + vitesse;
                newY = y + vitesse;

                if (this.getEnv().estEnDehors(newX, newY)) {
                    sens = Bille.UPPER_LEFT;
                    x = x - vitesse;
                    y = y - vitesse;
                } else if (this.getEnv().estDehorsX(newX)) {
                    sens = Bille.LOWER_LEFT;
                    x = x - vitesse;
                    y = newY;
                } else if (this.getEnv().estDehorsY(newY)) {
                    sens = Bille.UPPER_RIGHT;
                    y = y - vitesse;
                    x = newX;
                } else {
                    x = newX;
                    y = newY;
                }

                break;
            case Bille.RIGHT:

                newX = x + vitesse;

                if (this.getEnv().estDehorsX(newX)) {
                    sens = Bille.LEFT;
                    x = x - vitesse;
                } else {
                    x = newX;
                }


                break;
            case Bille.LEFT:

                newX = x - vitesse;

                if (this.getEnv().estDehorsX(newX)) {
                    sens = Bille.RIGHT;
                    x = x + vitesse;
                } else {
                    x = newX;
                }

                break;
        }

    }

    private Color randomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    @Override
    public String toString() {
        return "Bille{" +
                "numero=" + numero +
                ", vitesse=" + vitesse +
                ", sens=" + sens +
                ", couleur=" + color +
                "} " + super.toString();
    }
}
