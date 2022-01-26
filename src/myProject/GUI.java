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
    public static final String fondito = "src/resources/fondo.png";

    private JPanel panelPuntaje,panelZonaInactiva, panelZonaActiva,panelDadosUtilizados,panelAyuda;
    private JLabel puntuacion, valorPuntuacion, turno, valorTurno;

    private JButton tirar, ayudaImagen;
    private JTextArea mensajes;
    private ImageIcon imagenDado;
    private Header headerProject;
    private Icon icono;

    private GeekOutMasters control = new GeekOutMasters();
    private Dado [] bolsaDados = control.rellenado();

    private Escucha escucha;
    private BufferedImage bufferImage ;
    private JFrame miMismo = this;
    private Ayuda ventanaAyuda = new Ayuda(miMismo);

    /**
     * Constructor of GUI class
     */
    public GUI(){

        try {
            bufferImage = ImageIO.read(new File(ayudita));

            initGUI();

            //Default JFrame configuration
            this.setTitle("The Title app");
            this.setSize(1050,600);
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

        //Zona del puntaje y de la marza
        panelPuntaje = new JPanel();

            //Puntajes
            turno = new JLabel("Turno:");
            valorTurno = new JLabel("  "+"5"+"  ");// puntajes 1 3 6 10 15 21

            panelPuntaje.add(turno);
            panelPuntaje.add(valorTurno);

        panelPuntaje.setPreferredSize(new Dimension(300,120));
        //panelPuntaje.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelPuntaje.setBorder(BorderFactory.createTitledBorder("Tablero de Memoria"));
        panelPuntaje.add(new JLabel( new ImageIcon ("src/resources/puntajes/MarcadorPuntaje_0.png")));

            //Puntajes
            puntuacion = new JLabel("   Puntuacion:");
            valorPuntuacion = new JLabel("     "+"50");// puntajes 1 3 6 10 15 21

            panelPuntaje.add(puntuacion);
            panelPuntaje.add(valorPuntuacion);

        //panelPuntaje.setEnabled(false);//zona en donde no hay interacción
        this.add(panelPuntaje,BorderLayout.NORTH);

        //Zona de los dados Utilizados
        panelDadosUtilizados = new JPanel();
        panelDadosUtilizados.setPreferredSize(new Dimension(400,300));
        //panelDadosUtilizados.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelDadosUtilizados.setBorder(BorderFactory.createTitledBorder("Dados Utilizados"));

        //panelDadosUtilizados.setEnabled(false);//zona en donde no hay interacción
        this.add(panelDadosUtilizados,BorderLayout.WEST);

        //Zona de los dados activos
        panelZonaActiva = new JPanel();
        panelZonaActiva.setPreferredSize(new Dimension(400,300));
        //panelZonaActiva.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelZonaActiva.setBorder(BorderFactory.createTitledBorder("Zona activa"));

        /*
        for(int i=0; i<7; i++){
            panelZonaActiva.add(new JLabel(bolsaDados[i].getImage()));
            //System.out.println (bolsaDados[i].getId());
        }*/

        this.add(panelZonaActiva,BorderLayout.CENTER);

        //Zona de los dados inactivos
        panelZonaInactiva = new JPanel();
        panelZonaInactiva.setPreferredSize(new Dimension(200,300));
        //panelZonaInactiva.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelZonaInactiva.setBorder(BorderFactory.createTitledBorder("Zona inactiva"));
        /*
        for(int i=10; i>7; i--){
            panelZonaInactiva.add(new JLabel(bolsaDados[i-1].getImage()));
            //System.out.println (bolsaDados[i-1].getId());
        }*/

        this.add(panelZonaInactiva,BorderLayout.EAST);


        //panel Ayuda que contiene 2 botones y el JText
        panelAyuda = new JPanel();
        panelAyuda.setPreferredSize(new Dimension(300,200));
        //panelAyuda.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelAyuda.setBorder(BorderFactory.createTitledBorder("Zona ayuda"));

            //Trirar los dados
            tirar = new JButton("tirar");
            tirar.addActionListener(escucha);
            panelAyuda.add(tirar);

            //Area de texto
            mensajes = new JTextArea(10,22);
            mensajes.setText("Presiona en el botón ' tirar ' para iniciar.\n");
            mensajes.setEditable(false);
            JScrollPane scroll = new JScrollPane(mensajes);
            panelAyuda.add(scroll);

            //Mostrar la ayuda al usuario
            ayudaImagen = new JButton("ayuda");
            ayudaImagen.addActionListener(escucha);
            panelAyuda.add(ayudaImagen);

        this.add(panelAyuda,BorderLayout.SOUTH);
    }

    private void iniciarJuego() {
        //this.remove(panelZonaInactiva);
        //panelZonaInactiva.com

        //this.remove(panelZonaActiva);
        //
        for(int i=0; i<7; i++){
            panelZonaActiva.add(new JLabel(bolsaDados[i].getImage()));
            System.out.println (bolsaDados[i].getId());
        }
        this.add(panelZonaActiva,BorderLayout.CENTER);

        for(int i=7; i<10; i++){
            panelZonaInactiva.add(new JLabel(bolsaDados[i].getImage()));
            System.out.println (bolsaDados[i].getId());
        }
        this.add(panelZonaInactiva,BorderLayout.EAST);
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
    //, MouseListener, MouseMotionListener
    private class Escucha extends MouseAdapter implements ActionListener{

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
                tirar.setEnabled(false);

            }else if(eventAction.getSource() == ayudaImagen){
                //llamar a la ventana ayuda
                miMismo.setEnabled(false);
                ventanaAyuda.setVisible(true);

            }
            revalidate();
            repaint();
        }

        /**
         * Mouse clicked.
         *
         * @param eventMouse the event mouse
         */
        @Override
        public void mouseClicked(MouseEvent eventMouse) {
            // TODO Auto-generated method stub
            Dado dadoSeleccionado = (Dado) eventMouse.getSource();
        }

        private class EscuchaMeeple extends MouseAdapter{
            /**
             * Mouse clicked.
             *
             * @param eventMouse the event mouse
             */
            @Override
            public void mouseClicked(MouseEvent eventMouse) {
                // TODO Auto-generated method stub
                Dado dadoSeleccionado = (Dado) eventMouse.getSource();
            }
        }

        private class EscuchaDragon extends MouseAdapter{
            /**
             * Mouse clicked.
             *
             * @param eventMouse the event mouse
             */
            @Override
            public void mouseClicked(MouseEvent eventMouse) {
                // TODO Auto-generated method stub
                Dado dadoSeleccionado = (Dado) eventMouse.getSource();
            }
        }

        private class EscuchaCorazon extends MouseAdapter{
            /**
             * Mouse clicked.
             *
             * @param eventMouse the event mouse
             */
            @Override
            public void mouseClicked(MouseEvent eventMouse) {
                // TODO Auto-generated method stub
                Dado dadoSeleccionado = (Dado) eventMouse.getSource();
            }
        }

        private class EscuchaCohete extends MouseAdapter{
            /**
             * Mouse clicked.
             *
             * @param eventMouse the event mouse
             */
            @Override
            public void mouseClicked(MouseEvent eventMouse) {
                // TODO Auto-generated method stub
                Dado dadoSeleccionado = (Dado) eventMouse.getSource();
            }
        }

        private class EscuchaSuperheroe extends MouseAdapter{
            /**
             * Mouse clicked.
             *
             * @param eventMouse the event mouse
             */
            @Override
            public void mouseClicked(MouseEvent eventMouse) {
                // TODO Auto-generated method stub
                Dado dadoSeleccionado = (Dado) eventMouse.getSource();
            }
        }

        private class Escucha42 extends MouseAdapter{
            /**
             * Mouse clicked.
             *
             * @param eventMouse the event mouse
             */
            @Override
            public void mouseClicked(MouseEvent eventMouse) {
                // TODO Auto-generated method stub
                Dado dadoSeleccionado = (Dado) eventMouse.getSource();
            }
        }
    }
}
