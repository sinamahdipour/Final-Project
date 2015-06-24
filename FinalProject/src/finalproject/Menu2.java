/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Asus
 */
public class Menu2 extends JFrame{
    
     Dimension screenDimention;
    BufferedImage backGround;
    JButton singleplayerBtn;
    JButton multiplayerBtn;
    ActionListener al;
    JPanel jp = new JPanel();
    
    public Menu2(){
    
        jp.setBackground(Color.red); jp.setSize(800, 600);
        screenDimention = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1000, 700);
        setLocation((screenDimention.width - 1000) / 2, (screenDimention.height - 700) / 2 - 15);
        try {
            backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\smbg2.jpg").toURI()));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         singleplayerBtn = new JButton("singleplayer");
        multiplayerBtn = new JButton("multiplayer");
        

        singleplayerBtn.setSize(100, 40);
        singleplayerBtn.setLocation(getWidth() * 1 / 15, getHeight() * 26 / 50);
//        startBtn.addActionListener(al);
        getContentPane().add(singleplayerBtn);

        multiplayerBtn.setSize(100, 40);
        multiplayerBtn.setLocation(getWidth() * 1 / 15 , getHeight() * 26 / 50 + 45);
//        settingsBtn.addActionListener(al);
        getContentPane().add(multiplayerBtn);

       


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
    

    @Override
    public void paint(Graphics g) {
        /* try {
         backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("/data/smbg.jpg").toURI()));
         } catch (URISyntaxException | IOException ex) {
         Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
         }*/
        super.paint(g);
        g.drawImage(backGround, 0, 0, null);
        repaintComponents();
    }

    private void repaintComponents() {
        singleplayerBtn.repaint();
        multiplayerBtn.repaint();
    }
}
        

    

