package myProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ayuda extends JFrame{

    private BufferedImage ejemplo;
    private JLabel image;
    private ImageIcon adaptImage;
    private JButton volver;
    private Escucha escucha;
    private JFrame GUI;

    public Ayuda (JFrame GUI) {
        try {
            ejemplo = ImageIO.read(new File(myProject.GUI.ayudita));
            this.GUI = GUI;

            intitGUI();

            //default window

            this.setUndecorated(true);
            pack();
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setVisible(false);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void intitGUI() {
        //window container y layout

        //crear escucha
        escucha = new Escucha();

        //crear GUI
        BufferedImage subImagen = ejemplo.getSubimage(0, 0, 659, 490);
        adaptImage = new ImageIcon(subImagen);
        image = new JLabel(adaptImage);
        add(image,BorderLayout.CENTER);

        volver = new JButton("volver");
        volver.addActionListener(escucha);
        add(volver,BorderLayout.SOUTH);
    }

    private class Escucha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            //evento boton volver
            //activar la ventana GUI y hacer invisible ayuda
            GUI.setEnabled(true);
            setVisible(false);
        }
    }

}