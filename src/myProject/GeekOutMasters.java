package myProject;

import javax.swing.*;
import java.util.Random;

public class GeekOutMasters extends JFrame{
    public String[] rutaF = {"src/resources/caras/1.png", "src/resources/caras/2.png", "src/resources/caras/3.png",
            "src/resources/caras/4.png", "src/resources/caras/5.png", "src/resources/caras/6.png",
            "src/resources/caras/7.png", "src/resources/caras/8.png", "src/resources/caras/9.png",
            "src/resources/caras/10.png"};
    private Dado[] inicio, revuelto;


    public GeekOutMasters(){
        revuelto = new Dado[10];
        inicio = new Dado[10];

        this.revuelto = new Dado[10];
        this.inicio = new Dado[10];
    }

    public Dado[] rellenado() {

        for(int i=0; i<10; i++)inicio[i] = new Dado(i+1);

        return inicio;
    }

    public Dado[] revolver() {
        rellenado();

        for(int u=0;u<20;u++) {
            for(int i=0;i<9;i++) {
                Random random = new Random();
                int direction = random.nextInt(9)+1;

                Dado dadoNew = inicio[i];
                revuelto[i] = inicio[direction];
                revuelto[direction] = dadoNew;
            }
        }

        return ordenarID(revuelto);
    }

    public Dado[] ordenarID( Dado[] otro) {
        for(int i=0;i<10;i++){

            otro[i].setId(i+1);

        } return otro;
    }

    public Dado[] getRevuelto() { return revuelto; }

    public void setRevuelto(Dado[] revuelto) { this.revuelto = revuelto; }
}
