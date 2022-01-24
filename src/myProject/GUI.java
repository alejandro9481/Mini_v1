package myProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {
    public static final String ayudita = "src/resources/help.png";

    private JPanel panelPuntaje,panelZonaInactiva, panelZonaActiva,panelDadosUtilizados,panelAyuda;
    private JLabel dado1, dado2,dado3,dado4,dado5,dado6,dado7,dado8,dado9,dado10,puntuacion;
    private JTextField info;
    private JButton tirar, ayuda;
    private JTextArea mensajes;
    private ImageIcon imagenDado;
    private Header headerProject;
    private Icon icono;
    private Escucha escucha;
    private BufferedImage bufferImage = null;
    private JFrame miMisma = this;
    private Ayuda ventanaAyuda = new Ayuda(miMisma);

    /**
     * Constructor of GUI class
     */
    public GUI(){

        try {
            bufferImage = ImageIO.read(new File(ayudita));

            initGUI();

            //Default JFrame configuration
            this.setTitle("The Title app");
            this.setSize(1000,600);
            //this.pack();
            this.setResizable(true);
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //this.setUndecorated(true);
            //pack();
            //this.setResizable(false);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "No se puede continuar");
        }

    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //definir Window container y layout

        //crear el escucha
        escucha = new Escucha();

        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        //Set up JComponents
        headerProject = new Header("Header ...", Color.BLACK);


        panelZonaActiva = new JPanel();
        panelZonaActiva.setPreferredSize(new Dimension(400,300));
        //panelZonaActiva.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelZonaActiva.setBorder(BorderFactory.createTitledBorder("Zona activa"));

        imagenDado = new ImageIcon(getClass().getResource("/resources/caras/1.png"));
        dado1 = new JLabel(imagenDado);  // transform it back
        dado2 = new JLabel(imagenDado);
        dado3 = new JLabel(imagenDado);
        dado4 = new JLabel(imagenDado);
        dado5 = new JLabel(imagenDado);
        dado6 = new JLabel(imagenDado);
        dado7 = new JLabel(imagenDado);
        dado8 = new JLabel(imagenDado);
        dado9 = new JLabel(imagenDado);
        dado10 = new JLabel(imagenDado);


        panelZonaActiva.add(dado1);
        panelZonaActiva.add(dado2);
        panelZonaActiva.add(dado3);
        panelZonaActiva.add(dado4);
        panelZonaActiva.add(dado5);
        panelZonaActiva.add(dado6);
        panelZonaActiva.add(dado7);
        panelZonaActiva.add(dado8);
        panelZonaActiva.add(dado9);
        panelZonaActiva.add(dado10);

        this.add(panelZonaActiva,BorderLayout.CENTER);

        panelPuntaje = new JPanel();
        panelPuntaje.setPreferredSize(new Dimension(300,120));
        //panelPuntaje.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelPuntaje.setBorder(BorderFactory.createTitledBorder("Tablero de Memoria"));
        panelPuntaje.add(new JButton( new ImageIcon ("src/resources/puntajes/MarcadorPuntaje_0.png")));

        this.add(panelPuntaje,BorderLayout.NORTH);

        panelDadosUtilizados = new JPanel();
        panelDadosUtilizados.setPreferredSize(new Dimension(400,300));
        //panelDadosUtilizados.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelDadosUtilizados.setBorder(BorderFactory.createTitledBorder("Dados Utilizados"));
        panelDadosUtilizados.add(new JButton("Sup.1"));
        panelDadosUtilizados.add(new JButton("Sup.1"));
        panelDadosUtilizados.add(new JButton("Sup.1"));

        this.add(panelDadosUtilizados,BorderLayout.WEST);



        panelZonaInactiva = new JPanel();
        panelZonaInactiva.setPreferredSize(new Dimension(200,300));
        //panelZonaInactiva.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelZonaInactiva.setBorder(BorderFactory.createTitledBorder("Zona inactiva"));

        panelZonaInactiva.add(dado8);
        panelZonaInactiva.add(dado9);
        panelZonaInactiva.add(dado10);
        this.add(panelZonaInactiva,BorderLayout.EAST);

        panelAyuda = new JPanel();
        panelAyuda.setPreferredSize(new Dimension(300,150));
        //panelAyuda.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelAyuda.setBorder(BorderFactory.createTitledBorder("Zona ayuda"));

        panelAyuda.add(new JButton("Lanzar"));
        panelAyuda.add(new JButton("JTextField"));
        panelAyuda.add(new JButton("ayuda"));
        this.add(panelAyuda,BorderLayout.SOUTH);
    }

    private void iniciarJuego() {
        for(int i=0; i<3; i++){

        }
        for(int i=0; i<7; i++){

        }
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * The Class Escucha.
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha extends MouseAdapter implements ActionListener, MouseListener, MouseMotionListener{

        /**
         * Action performed.
         *
         * @param eventAction the event action
         */
        @Override
        public void actionPerformed(ActionEvent eventAction) {
            // TODO Auto-generated method stub
            //responde a los eventos de botones salir, reinicio, periodo e inicio

            if(eventAction.getSource() == tirar){
                //llamar a la funcion revolver los dados
                iniciarJuego();

            }else if(eventAction.getSource() == ayuda){
                //llamar a la ventana ayuda
                ventanaAyuda.setVisible(true);
                miMisma.setEnabled(false);
            }

        }

        /**
         * Mouse clicked.
         *
         * @param eventMouse the event mouse
         */
        @Override
        public void mouseClicked(MouseEvent eventMouse) {
            // TODO Auto-generated method stub

        }
    }
}
