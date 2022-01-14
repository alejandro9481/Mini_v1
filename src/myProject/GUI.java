package myProject;



import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private Header headerProject;
    private JPanel panelDados,panelDados2,panelDados3,panelDados4,panelDados5;
    private ImageIcon imagenDado;
    private JLabel dado1, dado2,dado3,dado4,dado5,dado6;
    private Icon icono;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("The Title app");
        this.setSize(800,600);
        //this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        //Set up JComponents
        headerProject = new Header("Header ...", Color.BLACK);


        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(266,300));
        panelDados.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        imagenDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
       dado1 = new JLabel();  // transform it back
        dado2 = new JLabel(imagenDado);
        dado3 = new JLabel(imagenDado);
        dado4 = new JLabel(imagenDado);
        dado5 = new JLabel(imagenDado);
        dado6 = new JLabel(imagenDado);
        dado1.setFont( new Font("TimesRoman",Font.PLAIN,14));



        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(dado3);
        panelDados.add(dado4);
        panelDados.add(dado5);
        panelDados.add(dado6);
        this.add(panelDados,BorderLayout.CENTER);

        panelDados2 = new JPanel();
        panelDados2.setPreferredSize(new Dimension(300,100));
        panelDados2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelDados2.add(new JButton("Sup.1"));
        panelDados2.add(new JButton("Sup.1"));
        panelDados2.add(new JButton("Sup.1"));
        this.add(panelDados2,BorderLayout.NORTH);

        panelDados3 = new JPanel();
        panelDados3.setPreferredSize(new Dimension(266,300));
        panelDados3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelDados3.add(new JButton("Sup.1"));
        panelDados3.add(new JButton("Sup.1"));
        panelDados3.add(new JButton("Sup.1"));
        this.add(panelDados3,BorderLayout.WEST);

        panelDados4 = new JPanel();
        panelDados4.setPreferredSize(new Dimension(266,300));
        panelDados4.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        panelDados4.add(new JButton("Sup.1"));
        panelDados4.add(new JButton("Sup.1"));
        panelDados4.add(new JButton("Sup.1"));
        this.add(panelDados4,BorderLayout.EAST);

        panelDados5 = new JPanel();
        panelDados5.setPreferredSize(new Dimension(300,150));
        panelDados5.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        panelDados5.add(new JButton("Sup.1"));
        panelDados5.add(new JButton("Sup.1"));
        panelDados5.add(new JButton("Sup.1"));
        this.add(panelDados5,BorderLayout.SOUTH);
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
    private class Escuchas extends MouseAdapter implements ActionListener, MouseListener, MouseMotionListener{

        /**
         * Action performed.
         *
         * @param eventAction the event action
         */
        @Override
        public void actionPerformed(ActionEvent eventAction) {
            // TODO Auto-generated method stub
            //responde a los eventos de botones salir, reinicio, periodo e inicio
            /*
            if(eventAction.getSource() == salir) {
                System.exit(0);
            }else if(eventAction.getSource() == reinicio){


            }else if(eventAction.getSource() == ayuda){

            }else if(eventAction.getSource() == inicio){

            }*/
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
