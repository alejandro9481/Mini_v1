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

import javax.swing.*;

/**
 * Class Dado generate a Random value between 1 and 6
 * @author Alejandro Villamil
 * @author Javier Castrillon
 * @version v.1.0.0 date 09/12/2021
 */

public class Dado extends JLabel {
    /**
     *	The attributes
     */
    private int cara, id;
    private ImageIcon image ;
    private boolean zonaActiva, zonaInactiva, dadosUtilizados;
    private String nombreCara;

    /**
     * Method that generate
     *
     */
    public Dado(int idDado) {
        this.id = idDado;

        for(int u=0;u<50;u++) {
            getCara();
        }

        this.setIcon(getImage());

    }

    /**
     * Method that generate an random value to cara
     * @return number between (1,6)
     */
    public int getCara() {
        Random aleatorio = new Random();
        this.cara = aleatorio.nextInt(6) + 1;
        this.image = new ImageIcon("src/resources/caras/"+cara+".png");
        this.setIcon(getImage());
        return cara;
    }

    /**
     * Method that generate an random value to cara
     * @return number between (1,6)
     */
    public int getSuperHeroe() {
        if(cara <=3){
            this.cara = cara+3;
            this.image = new ImageIcon("src/resources/caras/"+cara+".png");
            this.setIcon(getImage());
            return cara;
        }else{
            this.cara = cara-3;
            this.image = new ImageIcon("src/resources/caras/"+cara+".png");
            this.setIcon(getImage());
            return cara;
        }
    }
    public ImageIcon getNada() {
        this.setIcon(new ImageIcon("src/resources/nada.png"));
        return image = new ImageIcon("src/resources/nada.png");
    }

    public String nombreDado(int i) {
        switch (i) {
            // MEEPLE                               // DRAGON
            case 1: nombreCara = "Meeple";break;       case 2: nombreCara =  "Dragon";break;

            // CORAZON                              // COHETE
            case 3: nombreCara = "Corazón";break;      case 4: nombreCara =  "Cohete";break;

            // SUPERHEROE                           // 42
            case 5: nombreCara = "Superheroe";break;   case 6: nombreCara = "42"; break;

            default:
        }
        return nombreCara;

    }

    public ImageIcon getImage() {
        return image = new ImageIcon("src/resources/caras/"+cara+".png");
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
    public int getCaraOriginal() { return cara; }

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public boolean getZonaActiva() { return zonaActiva; }

    public void setZonaActiva(boolean zonaActiva) { this.zonaActiva = zonaActiva; }

    public boolean getZonaInactiva() { return zonaInactiva; }

    public void setZonaInactiva(boolean zonaInactiva) { this.zonaInactiva = zonaInactiva; }

    public boolean getDadosUtilizados() { return dadosUtilizados; }

    public void setDadosUtilizados(boolean dadosUtilizados) { this.dadosUtilizados = dadosUtilizados; }

}
