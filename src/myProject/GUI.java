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
    private JLabel puntuacion, valorPuntuacion, turno, valorTurno, marcadorPuntaje;
    private JButton tirar, ayudaImagen;
    private JTextArea mensajes;
    private ImageIcon imagenDado;
    private Header headerProject;
    private Icon icono;

    private GeekOutMasters control = new GeekOutMasters();
    private Dado [] bolsaDados = control.rellenado();

    private Escucha escucha;
    private EscuchaMeeple EscuchaMeeple;
    private ActivoEscuchaMeeple ActivoEscuchaMeeple;
    private EscuchaDragon EscuchaDragon;
    private EscuchaCorazon EscuchaCorazon;
    private ActivoEscuchaCorazon ActivoEscuchaCorazon;
    private EscuchaCohete EscuchaCohete;
    private ActivoEscuchaCohete ActivoEscuchaCohete;
    private EscuchaSuperheroe EscuchaSuperheroe;
    private Escucha42 Escucha42;

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
        EscuchaMeeple = new EscuchaMeeple();
        ActivoEscuchaMeeple = new ActivoEscuchaMeeple();
        EscuchaDragon = new EscuchaDragon();
        EscuchaCorazon = new EscuchaCorazon();
        ActivoEscuchaCorazon = new ActivoEscuchaCorazon();
        EscuchaCohete = new EscuchaCohete();
        ActivoEscuchaCohete = new ActivoEscuchaCohete();
        EscuchaSuperheroe = new EscuchaSuperheroe();
        Escucha42 = new Escucha42();

        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        //Set up JComponents
        headerProject = new Header("Header ...", Color.BLACK);

        //Zona del puntaje y de la marza
        panelPuntaje = new JPanel();

            //Puntajes
            turno = new JLabel("Turno:");
            valorTurno = new JLabel(String.valueOf("  "+control.getContTurno()+"  "));// turno 1 2 3 4 5

            panelPuntaje.add(turno);
            panelPuntaje.add(valorTurno);

        panelPuntaje.setPreferredSize(new Dimension(300,120));
        //panelPuntaje.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelPuntaje.setBorder(BorderFactory.createTitledBorder("Tablero de Memoria"));
        marcadorPuntaje = control.getImagenPuntaje();
        panelPuntaje.add(marcadorPuntaje);//añado la imagen

            //Puntajes
            puntuacion = new JLabel("   Puntuacion:");
            valorPuntuacion = new JLabel(String.valueOf("     "+control.getContPuntaje()));// puntajes 1 3 6 10 15 21

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

        this.add(panelZonaActiva,BorderLayout.CENTER);

        //Zona de los dados inactivos
        panelZonaInactiva = new JPanel();
        panelZonaInactiva.setPreferredSize(new Dimension(200,300));
        //panelZonaInactiva.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelZonaInactiva.setBorder(BorderFactory.createTitledBorder("Zona inactiva"));

            //System.out.println (bolsaDados[i].getId());

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

    private void agregaEscuchas(int i){
        //con este switch se agregaran los escuchas según el dado
        switch(bolsaDados[i].getCaraOriginal())
        {
            // MEEPLE
            case 1 : bolsaDados[i].addMouseListener(EscuchaMeeple); break;

            // DRAGON
            case 2 : bolsaDados[i].addMouseListener(EscuchaDragon); break;

            // CORAZON
            case 3 : bolsaDados[i].addMouseListener(EscuchaCorazon); break;

            // COHETE
            case 4 : bolsaDados[i].addMouseListener(EscuchaCohete); break;

            // SUPERHEROE
            case 5 : bolsaDados[i].addMouseListener(EscuchaSuperheroe); break;

            // 42
            case 6 : bolsaDados[i].addMouseListener(Escucha42); break;

            default :
                // Declaraciones
        }
    }

    private void revolverJuego() {
        if(control.getTirar()) {
            bolsaDados = control.rellenado();
            panelZonaActiva.removeAll();
            panelZonaInactiva.removeAll();
            //System.out.println (control.getTirar());
            for(int i=0; i<=9; i++){
                if (i < 7){

                    bolsaDados[i].getCara();
                    agregaEscuchas(i);
                    bolsaDados[i].setZonaActiva(true);
                    panelZonaActiva.add(bolsaDados[i]);
                    //System.out.println ("");
                }
                //System.out.println (bolsaDados[i].getId());
                else{
                    bolsaDados[i].getCara();
                    agregaEscuchas(i);
                    //zonaActiva, zonaInactiva, dadosUtilizados
                    bolsaDados[i].setZonaInactiva(true);
                    panelZonaInactiva.add(bolsaDados[i]);
                //System.out.println (bolsaDados[i].getId());
                }
            }
        }
    }

    private void reinicioJuego() {

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
                revolverJuego();
                mensajes.append("Ha realizado un tiro!! \n");
                //tirar.setEnabled(false);

            }else if(eventAction.getSource() == ayudaImagen){
                //llamar a la ventana ayuda
                miMismo.setEnabled(false);
                ventanaAyuda.setVisible(true);
                mensajes.append("Ha solicitado ayuda!! \n");
            }
            revalidate();
            repaint();
        }

        /**
         * Mouse clicked.
         *
         * @param eventMouse the event mouse
         */

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

            //zonaActiva, zonaInactiva, dadosUtilizados
            if(dadoSeleccionado.isDadosUtilizados()){
                //mensajes.append("Ha seleccionado un MEEPLE!! \n");
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(true);
                panelZonaInactiva.add(dadoSeleccionado);

               // panelZonaInactiva.add(dadoSeleccionado);
            }else if (dadoSeleccionado.isZonaActiva()){
                mensajes.append("Ha seleccionado un MEEPLE!! \n");
                //control.setTirar(false);

                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);

                panelDadosUtilizados.add(dadoSeleccionado);
                System.out.println(dadoSeleccionado.getCaraOriginal());
                JOptionPane.showMessageDialog(null,
                        "Meeple \n",
                        "Meeple",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if (dadoSeleccionado.isZonaInactiva()){
                //mensajes.append("Ha seleccionado un MEEPLE!! \n");
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(true);
                dadoSeleccionado.setZonaInactiva(false);
            }

            for(int e=0; e<=6; e++){
                bolsaDados[e].addMouseListener(ActivoEscuchaMeeple);
                //System.out.println (bolsaDados[i].getId());
            }
            revalidate();
            repaint();
        }
    }

    private class ActivoEscuchaMeeple extends MouseAdapter{
        /**
         * Mouse clicked.
         *
         * @param eventMouse the event mouse
         */
        @Override
        public void mouseClicked(MouseEvent eventMouse) {
            // TODO Auto-generated method stub
            Dado dadoSeleccionado = (Dado) eventMouse.getSource();
            //System.out.println(dadoSeleccionado.getCaraOriginal());

            System.out.println ("estoy en meeple");
            //if (dadoSeleccionado.getCaraOriginal() == 4){
            if(dadoSeleccionado.isDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(true);
                panelZonaInactiva.add(dadoSeleccionado);
            }else if (dadoSeleccionado.isZonaActiva()){
                //control.setTirar(false);
                dadoSeleccionado.setDadosUtilizados(true);
                System.out.println (dadoSeleccionado.getCaraOriginal());

                switch(dadoSeleccionado.getCaraOriginal())
                {
                    // MEEPLE
                    case 1 : dadoSeleccionado.removeMouseListener(EscuchaMeeple);
                        dadoSeleccionado.removeMouseListener(ActivoEscuchaMeeple); break;

                    // DRAGON
                    case 2 : dadoSeleccionado.removeMouseListener(EscuchaDragon);
                        //dadoSeleccionado.removeMouseListener(ActivoEscuchaDragon); break;

                        // CORAZON
                    case 3 : dadoSeleccionado.removeMouseListener(EscuchaCorazon);
                        dadoSeleccionado.removeMouseListener(ActivoEscuchaCorazon);
                        System.out.println ("saque switch corazon");

                        break;

                    // COHETE
                    case 4 :dadoSeleccionado.removeMouseListener(EscuchaCohete);
                        dadoSeleccionado.removeMouseListener(ActivoEscuchaCohete);break;


                    // SUPERHEROE
                    case 5 : dadoSeleccionado.removeMouseListener(EscuchaSuperheroe);
                        //dadoSeleccionado.removeMouseListener(ActivoEscuchaMeeple);break;

                        // 42
                    case 6 : dadoSeleccionado.removeMouseListener(Escucha42);
                        //dadoSeleccionado.removeMouseListener(ActivoEscuchaMeeple);break;

                    default :
                        // Declaraciones
                }

                dadoSeleccionado.getCara();
                System.out.println (dadoSeleccionado.getCaraOriginal());

                switch(dadoSeleccionado.getCaraOriginal())
                {

                    // MEEPLE
                    case 1 : dadoSeleccionado.addMouseListener(EscuchaMeeple); break;

                    // DRAGON
                    case 2 : dadoSeleccionado.addMouseListener(EscuchaDragon); break;

                    // CORAZON
                    case 3 : dadoSeleccionado.addMouseListener(EscuchaCorazon);
                        System.out.println ("entre switch corazon");
                        break;

                    // COHETE
                    case 4 :dadoSeleccionado.addMouseListener(EscuchaCohete); break;


                    // SUPERHEROE
                    case 5 : dadoSeleccionado.addMouseListener(EscuchaSuperheroe); break;

                    // 42
                    case 6 : dadoSeleccionado.addMouseListener(Escucha42); break;

                    default :
                        // Declaraciones
                }

                //panelZonaActiva.add(dadoSeleccionado);

                //System.out.println(dadoSeleccionado.getCaraOriginal());
                JOptionPane.showMessageDialog(null,
                        "Corazon activo \n",
                        "Meeple",
                        JOptionPane.INFORMATION_MESSAGE);


            }else if (dadoSeleccionado.isZonaInactiva()){
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(true);
                dadoSeleccionado.setZonaInactiva(false);
            }
            //}

            for(int e=0; e<=6; e++){
                bolsaDados[e].removeMouseListener(ActivoEscuchaMeeple);
                //System.out.println (bolsaDados[i].getId());
            }
            revalidate();
            repaint();
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

            if(dadoSeleccionado.isDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);
            }else if (dadoSeleccionado.isZonaActiva()){
                mensajes.append("Ha seleccionado un DRAGON!! \n");
                //control.setTirar(false);
                control.setContPuntaje(0);
                control.setContPuntaje2(0);

                control.setImagenPuntaje(control.getImagenPuntaje());
                control.getImagenPuntaje();

                control.setContMarcadorPuntaje(0);
                marcadorPuntaje.setIcon(control.getImagenMarcador());
                //valorPuntuacion.setText(String.valueOf("     "+control.getContPuntaje()));

                dadoSeleccionado.setDadosUtilizados(true);
                panelDadosUtilizados.add(dadoSeleccionado);
                System.out.println(dadoSeleccionado.getCaraOriginal());
                JOptionPane.showMessageDialog(null,
                        "Dragon \n",
                        "Dragon",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if (dadoSeleccionado.isZonaInactiva()){
                //mensajes.append("Ha seleccionado un DRAGON!! \n");
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(true);
                dadoSeleccionado.setZonaInactiva(false);
            }

            revalidate();
            repaint();
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

            if(dadoSeleccionado.isDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(true);
                panelZonaInactiva.add(dadoSeleccionado);
            }else if (dadoSeleccionado.isZonaActiva()){
                mensajes.append("Ha seleccionado un CORAZON!! \n");
                //control.setTirar(false);
                dadoSeleccionado.setDadosUtilizados(true);
                panelDadosUtilizados.add(dadoSeleccionado);
                System.out.println(dadoSeleccionado.getCaraOriginal());
                JOptionPane.showMessageDialog(null,
                        "Corazon \n",
                        "Corazon",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if (dadoSeleccionado.isZonaInactiva()){
                //mensajes.append("Ha seleccionado un CORAZON!! \n");
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(true);
                dadoSeleccionado.setZonaInactiva(false);
            }

            for(int e=7; e<=9; e++){
                bolsaDados[e].addMouseListener(ActivoEscuchaCorazon);
                //System.out.println (bolsaDados[i].getId());
            }
            //dadoSeleccionado.removeMouseListener(ActivoEscuchaCorazon);
            revalidate();
            repaint();
        }
    }

    private class ActivoEscuchaCorazon extends MouseAdapter{
        /**
         * Mouse clicked.
         *
         * @param eventMouse the event mouse
         */
        @Override
        public void mouseClicked(MouseEvent eventMouse) {
            // TODO Auto-generated method stub
            Dado dadoSeleccionado = (Dado) eventMouse.getSource();
            System.out.println ("estoy en corazon");
            //if (dadoSeleccionado.getCaraOriginal() == 4){
            if(dadoSeleccionado.isDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(true);
                panelZonaInactiva.add(dadoSeleccionado);
            }else if (dadoSeleccionado.isZonaInactiva()){
                //control.setTirar(false);
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.getCara();
                panelZonaActiva.add(dadoSeleccionado);

                System.out.println(dadoSeleccionado.getCaraOriginal());
                JOptionPane.showMessageDialog(null,
                        "Corazon activo \n",
                        "Cohete",
                        JOptionPane.INFORMATION_MESSAGE);


            }else if (dadoSeleccionado.isZonaInactiva()){
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(true);
                dadoSeleccionado.setZonaInactiva(false);
            }
            //}

            for(int e=7; e<=9; e++){
                bolsaDados[e].removeMouseListener(ActivoEscuchaCorazon);
                //System.out.println (bolsaDados[i].getId());
            }
            revalidate();
            repaint();
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

            if(dadoSeleccionado.isDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(true);
                panelZonaInactiva.add(dadoSeleccionado);
            }else if (dadoSeleccionado.isZonaActiva()){
                mensajes.append("Ha seleccionado un COHETE!! \n");
                //control.setTirar(false);
                dadoSeleccionado.setDadosUtilizados(true);
                panelDadosUtilizados.add(dadoSeleccionado);
                System.out.println(dadoSeleccionado.getCaraOriginal());
                JOptionPane.showMessageDialog(null,
                        "Cohete \n",
                        "Cohete",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if (dadoSeleccionado.isZonaInactiva()){
                //mensajes.append("Ha seleccionado un COHETE!! \n");
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(true);
                dadoSeleccionado.setZonaInactiva(false);
            }

            for(int e=0; e<=6; e++){
                bolsaDados[e].addMouseListener(ActivoEscuchaCohete);
                //System.out.println (bolsaDados[i].getId());
            }
            dadoSeleccionado.removeMouseListener(ActivoEscuchaCohete);

            revalidate();
            repaint();
        }
    }

    private class ActivoEscuchaCohete extends MouseAdapter{
        /**
         * Mouse clicked.
         *
         * @param eventMouse the event mouse
         */
        @Override
        public void mouseClicked(MouseEvent eventMouse) {
            // TODO Auto-generated method stub
            Dado dadoSeleccionado = (Dado) eventMouse.getSource();
            System.out.println ("estoy en cohete");
            //if (dadoSeleccionado.getCaraOriginal() == 4){
            if(dadoSeleccionado.isDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(true);
                panelZonaInactiva.add(dadoSeleccionado);
            }else if (dadoSeleccionado.isZonaActiva()){
                //control.setTirar(false);
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(true);
                panelZonaInactiva.add(dadoSeleccionado);

                System.out.println(dadoSeleccionado.getCaraOriginal());
                JOptionPane.showMessageDialog(null,
                        "Cohete \n",
                        "Cohete",
                        JOptionPane.INFORMATION_MESSAGE);


            }else if (dadoSeleccionado.isZonaInactiva()){
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(true);
                dadoSeleccionado.setZonaInactiva(false);
            }
            //}

            for(int e=0; e<=6; e++){
                bolsaDados[e].removeMouseListener(ActivoEscuchaCohete);
                //System.out.println (bolsaDados[i].getId());
            }
            revalidate();
            repaint();
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

            if(dadoSeleccionado.isDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);
            }else if (dadoSeleccionado.isZonaActiva()){
                mensajes.append("Ha seleccionado un SUPERHEROE!! \n");
                //control.setTirar(false);
                dadoSeleccionado.setDadosUtilizados(true);
                panelDadosUtilizados.add(dadoSeleccionado);
                System.out.println(dadoSeleccionado.getCaraOriginal());
                JOptionPane.showMessageDialog(null,
                        "superheroe \n",
                        "superheroe",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if (dadoSeleccionado.isZonaInactiva()){
                //mensajes.append("Ha seleccionado un SUPERHEROE!! \n");
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(true);
                dadoSeleccionado.setZonaInactiva(false);
            }

            revalidate();
            repaint();
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

            if(dadoSeleccionado.isDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);
            }else if (dadoSeleccionado.isZonaActiva()){
                mensajes.append("Ha seleccionado un 42!! \n");
                //control.setTirar(false);
                dadoSeleccionado.getNada();
                control.setContPuntaje(control.getContPuntaje());

                control.setContMarcadorPuntaje(control.getContMarcadorPuntaje()+1);

                marcadorPuntaje.setIcon(control.getImagenMarcador());
                valorPuntuacion.setText(String.valueOf("     "+control.getContPuntaje()));
                System.out.println(control.getContMarcadorPuntaje());

                dadoSeleccionado.setDadosUtilizados(true);
                panelDadosUtilizados.add(dadoSeleccionado);

                JOptionPane.showMessageDialog(null,
                        "42 \n",
                        "42",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if (dadoSeleccionado.isZonaInactiva()){
                //mensajes.append("Ha seleccionado un 42!! \n");
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(true);
                dadoSeleccionado.setZonaInactiva(false);
            }

            revalidate();
            repaint();
        }
    }
}
