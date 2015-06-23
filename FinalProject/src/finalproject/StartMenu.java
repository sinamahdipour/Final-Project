/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Sina Mp
 */
public class StartMenu extends JFrame {

    Dimension screenDimention;
    BufferedImage backGround;
    JButton startBtn;
    JButton settingsBtn;
    JButton quitBtn;

    public StartMenu() {
        screenDimention = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1000, 750);
        setLocation((screenDimention.width - 1000) / 2, (screenDimention.height - 750) / 2);
        try {
            backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\smbg1.jpg").toURI()));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        startBtn = new JButton("Start");
        settingsBtn = new JButton("Settings");
        quitBtn = new JButton("Quit");

        startBtn.setSize(100, 40);
        startBtn.setLocation(getWidth() * 1 / 15, getHeight() * 26 / 50);
//        startBtn.addActionListener(al);
        getContentPane().add(startBtn);

        settingsBtn.setSize(100, 40);
        settingsBtn.setLocation(getWidth() * 1 / 15 , getHeight() * 26 / 50 + 45);
//        settingsBtn.addActionListener(al);
        getContentPane().add(settingsBtn);

        quitBtn.setSize(100, 40);
        quitBtn.setLocation(getWidth() * 1 / 15, getHeight() * 26 / 50 + 90);
//        quitBtn.addActionListener(al);
        getContentPane().add(quitBtn);

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
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
        startBtn.repaint();
        settingsBtn.repaint();
        quitBtn.repaint();
    }
}
