/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
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
            add(startBtn);

            settingsBtn.setSize(100, 40);
            settingsBtn.setLocation(1000 * 1 / 15, 700 * 26 / 50 + 45);
            settingsBtn.addActionListener(al);
            add(settingsBtn);

            quitBtn.setSize(100, 40);
            quitBtn.setLocation(1000 * 1 / 15, 700 * 26 / 50 + 90);
            quitBtn.addActionListener(al);
            add(quitBtn);
        }

        @Override
        public void paint(Graphics g) {
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
                        Game g = new Game();
                        getContentPane().removeAll();
                        getContentPane().add(g);
                        getContentPane().repaint();
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
            add(singleplayerBtn);

            multiplayerBtn.setSize(130, 40);
            multiplayerBtn.setLocation(1000 * 1 / 15, 700 * 26 / 50 + 45);
            settingsBtn.addActionListener(al2);
            add(multiplayerBtn);
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

    private class Game extends JPanel implements Runnable {

        BufferedImage backGround;
        BufferedImage bufferedScene;
        private Graphics2D bufferedGraphics;
        private int mapMatrix[][];

        public Game() {
            screenDimention = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(1000, 700);
//            setLocation((screenDimention.width - 1000) / 2, (screenDimention.height - 700) / 2 - 15);
            mapMatrix = new int[13][19];
            try {
                backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\back.jpg").toURI()));
            } catch (URISyntaxException | IOException ex) {
                System.out.println("Problems in loading pictures of the game.");
            }

            bufferedScene = new BufferedImage(1000, 700, BufferedImage.TYPE_INT_RGB);
            bufferedGraphics = (Graphics2D) bufferedScene.createGraphics();

            loadMap();
            setLayout(null);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            render(bufferedGraphics);
            g.drawImage(bufferedScene, 0, 0, null);
        }

        private void render(Graphics2D g2) {
            g2.drawImage(backGround, 0, 0, null);
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 19; j++) {
                    if (mapMatrix[i][j] != 0) {
                        g2.fillRect(j * 52, i * 52, 52, 52);
                        g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\tile0" + mapMatrix[i][j] + ".png")).getImage(), j * 52, i * 52, null);
                    }
                }
            }
        }

        private void loadMap() {
            Scanner fromFileReader = null;
            try {
                fromFileReader = new Scanner(new File("src/data/maps/map01.txt"));
            } catch (FileNotFoundException ex) {
                System.out.println("Problems in reading the map file.");
            }
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 19; j++) {
                    mapMatrix[i][j] = fromFileReader.nextInt();
                }
            }
        }

        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
