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

    private Escucha Escucha;
    private EscuchaDado EscuchaDado;
    private EscuchaMeeple EscuchaMeeple;
    private EscuchaDragon EscuchaDragon;
    private EscuchaCorazon EscuchaCorazon;
    private EscuchaCohete EscuchaCohete;
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
            this.setSize(1250,600);
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
        Escucha = new Escucha();
        EscuchaDado = new EscuchaDado();
        EscuchaMeeple = new EscuchaMeeple();
        EscuchaDragon = new EscuchaDragon();
        EscuchaCorazon = new EscuchaCorazon();
        EscuchaCohete = new EscuchaCohete();
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
            valorTurno = new JLabel("         "+control.getContTurno()+"                ");// turno 1 2 3 4 5

            panelPuntaje.add(turno);
            panelPuntaje.add(valorTurno);

        panelPuntaje.setPreferredSize(new Dimension(300,120));
        //panelPuntaje.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelPuntaje.setBorder(BorderFactory.createTitledBorder("Tablero de Memoria"));
        marcadorPuntaje = control.getImagenPuntaje();
        panelPuntaje.add(marcadorPuntaje);//añado la imagen

            //Puntajes
            puntuacion = new JLabel("         Puntuacion:");
            valorPuntuacion = new JLabel("         "+control.getContPuntaje());// puntajes 1 3 6 10 15 21

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
        panelZonaInactiva.setPreferredSize(new Dimension(400,300));
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
            tirar.addActionListener(Escucha);
            panelAyuda.add(tirar);

            //Area de texto
            mensajes = new JTextArea(10,22);
            mensajes.setText("Presiona en el botón ' tirar ' para iniciar.\n");
            mensajes.setEditable(false);
            JScrollPane scroll = new JScrollPane(mensajes);
            panelAyuda.add(scroll);

            //Mostrar la ayuda al usuario
            ayudaImagen = new JButton("ayuda");
            ayudaImagen.addActionListener(Escucha);
            panelAyuda.add(ayudaImagen);

        this.add(panelAyuda,BorderLayout.SOUTH);
    }

    private void agregaEscuchas(Dado otro){
        //con este switch se agregaran los escuchas según el dado
        switch(otro.getCaraOriginal())
        {
            // MEEPLE
            case 1 : otro.addMouseListener(EscuchaMeeple); break;

            // DRAGON
            case 2 : otro.addMouseListener(EscuchaDragon); break;

            // CORAZON
            case 3 : otro.addMouseListener(EscuchaCorazon); break;

            // COHETE
            case 4 : otro.addMouseListener(EscuchaCohete); break;

            // SUPERHEROE
            case 5 : otro.addMouseListener(EscuchaSuperheroe); break;

            // 42
            case 6 : otro.addMouseListener(Escucha42); break;

            default :
                // Declaraciones
        }
    }
    private void removerEscuchas(Dado dadoSeleccionado){
        switch(dadoSeleccionado.getCaraOriginal())
        {
            // MEEPLE
            case 1 : dadoSeleccionado.removeMouseListener(EscuchaMeeple);break;

            // DRAGON
            case 2 : dadoSeleccionado.removeMouseListener(EscuchaDragon);break;

            // CORAZON
            case 3 : dadoSeleccionado.removeMouseListener(EscuchaCorazon);break;

            // COHETE
            case 4 :dadoSeleccionado.removeMouseListener(EscuchaCohete);break;

            // SUPERHEROE
            case 5 : dadoSeleccionado.removeMouseListener(EscuchaSuperheroe);break;

            // 42
            case 6 : dadoSeleccionado.removeMouseListener(Escucha42);break;

            default :
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
                    agregaEscuchas(bolsaDados[i]);
                    bolsaDados[i].setDadosUtilizados(false);
                    bolsaDados[i].setZonaActiva(true);
                    bolsaDados[i].setZonaInactiva(false);

                    panelZonaActiva.add(bolsaDados[i]);
                    //System.out.println ("");
                }
                //System.out.println (bolsaDados[i].getId());
                else{
                    bolsaDados[i].getCara();
                    agregaEscuchas(bolsaDados[i]);
                    //zonaActiva, zonaInactiva, dadosUtilizados
                    bolsaDados[i].setDadosUtilizados(false);
                    bolsaDados[i].setZonaActiva(false);
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

    }
    private class EscuchaDado extends MouseAdapter{
        /**
         * Mouse clicked.
         *
         * @param eventMouse the event mouse
         */
        @Override
        public void mouseClicked(MouseEvent eventMouse) {
            // TODO Auto-generated method stub
            Dado dadoSeleccionado = (Dado) eventMouse.getSource();

            if(dadoSeleccionado.getDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);

            }else if (dadoSeleccionado.getZonaActiva()){
                segundoEscuchaActivo(dadoSeleccionado);
            }else if (dadoSeleccionado.getZonaInactiva()){
                segundoEscuchaInactivo(dadoSeleccionado);
            }

            revalidate();
            repaint();
        }
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
            if(dadoSeleccionado.getDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);

            }else if (dadoSeleccionado.getZonaActiva()){
                segundoEscuchaActivo(dadoSeleccionado);
            }else if (dadoSeleccionado.getZonaInactiva()){
                System.out.println ("Prueba c Meeple");
                segundoEscuchaInactivo(dadoSeleccionado);
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

            if(dadoSeleccionado.getDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);
            }else if (dadoSeleccionado.getZonaActiva()){
                segundoEscuchaActivo(dadoSeleccionado);
            }else if (dadoSeleccionado.getZonaInactiva()){
                System.out.println ("Prueba c Superheroe");
                segundoEscuchaInactivo(dadoSeleccionado);
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

            if(dadoSeleccionado.getDadosUtilizados()){
                System.out.println ("Prueba a Corazon");
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);
                //panelZonaInactiva.add(dadoSeleccionado);
            }else if (dadoSeleccionado.getZonaActiva()){
                segundoEscuchaActivo(dadoSeleccionado);
            }else if (dadoSeleccionado.getZonaInactiva()){
                System.out.println ("Prueba c Corazón");
                segundoEscuchaInactivo(dadoSeleccionado);
            }
            //dadoSeleccionado.removeMouseListener(ActivoEscuchaCorazon);
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

            if(dadoSeleccionado.getDadosUtilizados()){
                System.out.println ("Prueba a Cohete");
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);
                //panelZonaInactiva.add(dadoSeleccionado);
            }else if (dadoSeleccionado.getZonaActiva()){
                segundoEscuchaActivo(dadoSeleccionado);
            }else if (dadoSeleccionado.getZonaInactiva()){
                System.out.println ("Prueba c Cohete");
                segundoEscuchaInactivo(dadoSeleccionado);
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

            if(dadoSeleccionado.getDadosUtilizados()){
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);
            }else if (dadoSeleccionado.getZonaActiva()){
                segundoEscuchaActivo(dadoSeleccionado);
            }else if (dadoSeleccionado.getZonaInactiva()){
                System.out.println ("Prueba c Superheroe");
                segundoEscuchaInactivo(dadoSeleccionado);
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

            if(dadoSeleccionado.getDadosUtilizados()){
                System.out.println ("Prueba a 42");
                dadoSeleccionado.setDadosUtilizados(true);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(false);
            }else if (dadoSeleccionado.getZonaActiva()){
                segundoEscuchaActivo(dadoSeleccionado);
            }else if (dadoSeleccionado.getZonaInactiva()){
                System.out.println ("Prueba c Superheroe");
                segundoEscuchaInactivo(dadoSeleccionado);
            }

            revalidate();
            repaint();
        }
    }

    private void segundoEscuchaActivo(Dado dadoSeleccionado){
        //Se agrega el nombre del dado clickado en el area del texto
        mensajes.append("Ha seleccionado un "+dadoSeleccionado.nombreDado(dadoSeleccionado.getCaraOriginal())+"!! \n");
        //control.setTirar(false);
        if(control.getRecuerdoCara() == 0){
            if(dadoSeleccionado.getCaraOriginal() == 2){

                control.setContPuntaje(0);control.setContPuntaje2(0);

                control.setImagenPuntaje(control.getImagenPuntaje());
                control.getImagenPuntaje();

                control.setContMarcadorPuntaje(0);
                marcadorPuntaje.setIcon(control.getImagenMarcador());

            }else if(dadoSeleccionado.getCaraOriginal() == 6){

                dadoSeleccionado.getNada();

                control.setContPuntaje(control.getContPuntaje());

                control.setContMarcadorPuntaje(control.getContMarcadorPuntaje()+1);
                marcadorPuntaje.setIcon(control.getImagenMarcador());
                valorPuntuacion.setText("     "+control.getContPuntaje());

            }
            dadoSeleccionado.setDadosUtilizados(true);
            dadoSeleccionado.setZonaActiva(false);
            dadoSeleccionado.setZonaInactiva(false);

            panelDadosUtilizados.add(dadoSeleccionado);

            control.setRecuerdoCara(dadoSeleccionado.getCaraOriginal());
            JOptionPane.showMessageDialog(null,
                    dadoSeleccionado.nombreDado(dadoSeleccionado.getCaraOriginal())+"\n",
                    dadoSeleccionado.nombreDado(dadoSeleccionado.getCaraOriginal()),
                    JOptionPane.INFORMATION_MESSAGE);

        }else if(control.getRecuerdoCara() == 1){
            //MEEPLE
            if(dadoSeleccionado.getZonaActiva()){
                removerEscuchas(dadoSeleccionado);
                dadoSeleccionado.getCara();
                agregaEscuchas(dadoSeleccionado);
                control.setRecuerdoCara(0);
            }else{
                JOptionPane.showMessageDialog(null,"Selecciona un dado de Zona Activa",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }

        }else if(control.getRecuerdoCara() == 3){
            //CORAZON
            if(dadoSeleccionado.getZonaInactiva()){
                //System.out.println ("Prueba b.b.a Corazon");
                dadoSeleccionado.getCara();
                panelZonaActiva.add(dadoSeleccionado);
                control.setRecuerdoCara(0);
            }else{
                JOptionPane.showMessageDialog(null,"Selecciona un dado de Zona Inactiva",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(control.getRecuerdoCara() == 4){
            //COHETE
            if(dadoSeleccionado.getZonaActiva()){
                dadoSeleccionado.setDadosUtilizados(false);
                dadoSeleccionado.setZonaActiva(false);
                dadoSeleccionado.setZonaInactiva(true);
                panelZonaInactiva.add(dadoSeleccionado);
                control.setRecuerdoCara(0);
            }else{
                JOptionPane.showMessageDialog(null,"Selecciona un dado de Zona Activa",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(control.getRecuerdoCara() == 5){
            //SUPERHERORE
            if(dadoSeleccionado.getZonaActiva()){
                System.out.println ("Prueba b.b SuperHeroe");
                removerEscuchas(dadoSeleccionado);
                dadoSeleccionado.getSuperHeroe();
                agregaEscuchas(dadoSeleccionado);
                control.setRecuerdoCara(0);
            }else{
                JOptionPane.showMessageDialog(null,"Selecciona un dado de Zona Activa",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void segundoEscuchaInactivo(Dado dadoSeleccionado){
        dadoSeleccionado.setDadosUtilizados(false);
        dadoSeleccionado.setZonaActiva(false);
        dadoSeleccionado.setZonaInactiva(true);

        if(control.getRecuerdoCara() == 3){
            //CORAZON INACTIVO
            System.out.println ("Prueba inactiva Corazon");
            dadoSeleccionado.getCara();

            dadoSeleccionado.setDadosUtilizados(false);
            dadoSeleccionado.setZonaActiva(true);
            dadoSeleccionado.setZonaInactiva(false);
            panelZonaActiva.add(dadoSeleccionado);
            control.setRecuerdoCara(0);
        }else if(control.getRecuerdoCara() == 4){
            //COHETE INACTIVO
            JOptionPane.showMessageDialog(null,
                    "Selecciona un dado de Zona Activa",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);

        }else if(control.getRecuerdoCara() == 5){
            //SUPERHEROE INACTIVO
            JOptionPane.showMessageDialog(null,
                    "Selecciona un dado de Zona Activa",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);

        }

    }
}
