/*
 * Programación Interactiva
 * Autor:
 * Javier Castrillon 1827486-2711
 * Alejandro Villamil 2042419-
 * Caso 1: Mini proyecto
 */
package myProject;

import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class Dado generate a Random value between 1 and 6
 * @author Alejandro Villamil
 * @author Javier Castrillon
 * @version v.1.0.0 date 09/12/2021
 */

public class Dado extends JButton{
    /**
     *	The attributes
     */
    private static int dadoSize = 80;
    private static int maxDados = 10;
    private int cara;
    private int id;
    private ImageIcon image;



    /**
     * Method that generate
     *
     */
    public Dado(int caraDado, int idDado) {
        this.cara = caraDado;
        this.id = idDado;

        image = new ImageIcon("src/resources/"+caraDado+".png");
    }

    /**
     * Method that generate an random value to cara
     * @return number between (1,6)
     */
    public int getCara() {
        Random aleatorio = new Random();
        cara = aleatorio.nextInt(6) + 1;
        image = new ImageIcon("src/resources/"+cara+".png");
        return cara;
    }

    /**
     * Method that generate an image
     * @return one of six images from the Dado
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * Method that generate an image
     * @param image
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
