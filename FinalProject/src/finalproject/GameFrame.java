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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 *
 * @author Sina Mp
 */
public class GameFrame extends JFrame {

    Dimension screenDimention;
    BufferedImage backGround;
    JButton startBtn;
    JButton settingsBtn;
    JButton quitBtn;
    ActionListener al;
    StartMenu sm;

    public GameFrame() {
        super("ROBO KILL");
        screenDimention = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1000, 700);
        setLocation((screenDimention.width - 1000) / 2, (screenDimention.height - 700) / 2 - 15);
        sm = new StartMenu();
        sm.setSize(1000, 700);
        getContentPane().add(sm);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
//        setResizable(false);
    }

    /*   private class SettingsMenu extends JPanel {

     @Override
     public void paint(Graphics g) {

     }
     }
     */
    private class StartMenu extends JPanel {

        public StartMenu() {
            setLayout(null);
            setOpaque(false);

            try {
                backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\sb4.jpg").toURI()));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            al = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == startBtn) {
                        Menu2 m2 = new Menu2();
                        getContentPane().remove(sm);
                        getContentPane().add(m2);
                        getContentPane().repaint();
                    }
                    if (e.getSource() == settingsBtn) {
                        getContentPane().remove(sm);
                        getContentPane().repaint();

                    }
                    if (e.getSource() == quitBtn) {
                        System.exit(0);
                    }
                }
            };

            startBtn = new JButton("Start");
            settingsBtn = new JButton("Settings");
            quitBtn = new JButton("Quit");

            startBtn.setSize(100, 40);
            startBtn.setLocation(1000 * 1 / 15, 700 * 26 / 50);
            startBtn.addActionListener(al);
//        getContentPane().
            add(startBtn);

            settingsBtn.setSize(100, 40);
            settingsBtn.setLocation(1000 * 1 / 15, 700 * 26 / 50 + 45);
            settingsBtn.addActionListener(al);
//        getContentPane().
            add(settingsBtn);

            quitBtn.setSize(100, 40);
            quitBtn.setLocation(1000 * 1 / 15, 700 * 26 / 50 + 90);
//        quitBtn.addActionListener(al);
//        getContentPane().
            add(quitBtn);
        }

        @Override
        public void paint(Graphics g) {
//             try {
//             backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("/data/smbg.jpg").toURI()));
//             } catch (URISyntaxException | IOException ex) {
//             Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
//             }
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

    private class Menu2 extends JPanel {

        Dimension screenDimention;
        BufferedImage bg;
        JButton singleplayerBtn;
        JButton multiplayerBtn;
        ActionListener al2;

        public Menu2() {
            setLayout(null);
            screenDimention = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(1000, 700);
            try {
                bg = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\smbg3.jpg").toURI()));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            al2 = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == singleplayerBtn) {
                        Map m = new Map();
                    }
                    if (e.getSource() == multiplayerBtn) {

                    }
                }
            };

            singleplayerBtn = new JButton("Single Player");
            multiplayerBtn = new JButton("Multi Player");

            singleplayerBtn.setSize(130, 40);
            singleplayerBtn.setLocation(1000 * 1 / 15, 700 * 26 / 50);
            singleplayerBtn.addActionListener(al2);
//        getContentPane().
            add(singleplayerBtn);

            multiplayerBtn.setSize(130, 40);
            multiplayerBtn.setLocation(1000 * 1 / 15, 700 * 26 / 50 + 45);
            settingsBtn.addActionListener(al2);
//        getContentPane().
            add(multiplayerBtn);

//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//        setResizable(false);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(bg, 0, 0, null);
            repaintComponents();
        }

        private void repaintComponents() {
            singleplayerBtn.repaint();
            multiplayerBtn.repaint();
        }
    }

}
