package myProject;

import javax.swing.*;
import java.util.Random;

public class GeekOutMasters extends JFrame{
    public String[] rutaF = {"src/resources/caras/1.png", "src/resources/caras/2.png", "src/resources/caras/3.png",
            "src/resources/caras/4.png", "src/resources/caras/5.png", "src/resources/caras/6.png",
            "src/resources/caras/7.png", "src/resources/caras/8.png", "src/resources/caras/9.png",
            "src/resources/caras/10.png"};
    private Dado[] inicio;
    private JLabel imagenPuntaje;
    private ImageIcon imagenMarcador;
    private int contPuntaje =0,contPuntaje2 =0, contTurno=0,contMarcadorPuntaje=0, contGuardadoPuntuacion=0,recuerdoCara=0;
    private boolean tirar=true;

    public GeekOutMasters(){
        this.imagenPuntaje = new JLabel( new ImageIcon ("src/resources/puntajes/MarcadorPuntaje_"+contMarcadorPuntaje+".png"));

        this.inicio = new Dado[10];
    }

    public Dado[] rellenado() {

        for(int i=0; i<10; i++)inicio[i] = new Dado(i+1);

        return inicio;
    }

    public JLabel getImagenPuntaje() {
        return imagenPuntaje = new JLabel(
                new ImageIcon ("src/resources/puntajes/MarcadorPuntaje_"+contMarcadorPuntaje+".png"));
    }

    public void setImagenPuntaje(JLabel imagenPuntaje) {
        this.imagenPuntaje = new JLabel( new ImageIcon ("src/resources/puntajes/MarcadorPuntaje_"+contMarcadorPuntaje+".png"));
    }

    public int getContGuardadoPuntuacion() { return contGuardadoPuntuacion; }

    public void setContGuardadoPuntuacion(int contGuardadoPuntuacion) { this.contGuardadoPuntuacion = contGuardadoPuntuacion; }

    public int getContTurno() {return contTurno;}

    public void setContTurno(int contTurno) {this.contTurno = contTurno;}

    public int getContPuntaje() { return contPuntaje; }

    public void setContPuntaje(int contPuntaje) {this.contPuntaje = contPuntaje; }

    public int getContPuntaje2() { return contPuntaje2; }

    public void setContPuntaje2(int contPuntaje2) { this.contPuntaje2 = contPuntaje2; }

    public boolean getTirar() { return tirar; }

    public void setTirar(boolean tirar) { this.tirar = tirar;}

    public ImageIcon getImagenMarcador() { return imagenMarcador = new ImageIcon ("src/resources/puntajes/MarcadorPuntaje_"+contMarcadorPuntaje+".png"); }

    public void setImagenMarcador(ImageIcon imagenMarcador) { this.imagenMarcador = imagenMarcador; }

    public int getContMarcadorPuntaje() { return contMarcadorPuntaje; }

    public void setContMarcadorPuntaje(int contMarcadorPuntaje) { this.contMarcadorPuntaje = contMarcadorPuntaje; }

    public int getRecuerdoCara() { return recuerdoCara; }

    public void setRecuerdoCara(int recuerdoCara) { this.recuerdoCara = recuerdoCara; }

}
