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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
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
import javax.swing.event.MouseInputListener;

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
    private Thread player;

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
        BufferedImage backGround;
        JButton singleplayerBtn;
        JButton multiplayerBtn;
        ActionListener al2;

        public Menu2() {
            setLayout(null);
            screenDimention = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(1000, 700);
            try {
                backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\smbg3.jpg").toURI()));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            al2 = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == singleplayerBtn) {
                        Game g = new Game();
                        player = new Thread(g);
                        player.start();
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
            g.drawImage(backGround, 0, 0, null);
            repaintComponents();
        }

        private void repaintComponents() {
            singleplayerBtn.repaint();
            multiplayerBtn.repaint();
        }
    }

    private class Game extends JPanel implements Runnable, MouseInputListener, MouseMotionListener {

        BufferedImage backGround;
        BufferedImage bufferedScene;
        private Graphics2D bufferedGraphics;
        private int mapMatrix[][];
        private Robot myRobot;
        private double alfa;
        private boolean left;
        private boolean right;
        private boolean down;
        private boolean up;
        private AffineTransform identityL;
        private AffineTransform identityB;
        private int time;
        private boolean shot;
//        MouseMotionListener mml;
//        MouseInputListener mil;
        private Thread player;

        public Game() {
            screenDimention = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(1000, 700);
//            setLocation((screenDimention.width - 1000) / 2, (screenDimention.height - 700) / 2 - 15);
            mapMatrix = new int[13][19];
            try {
                backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\bg01.jpg").toURI()));
            } catch (URISyntaxException | IOException ex) {
                System.out.println("Problems in loading pictures of the game.");
            }

            bufferedScene = new BufferedImage(1000, 700, BufferedImage.TYPE_INT_RGB);
            bufferedGraphics = (Graphics2D) bufferedScene.createGraphics();

            myRobot = new Robot();
            myRobot.setX(500);
            myRobot.setY(600);
            this.addMouseMotionListener(this);
            this.addMouseMotionListener(this);
            loadMap();
            setLayout(null);
            
        }

        @Override
        public void paint(Graphics g) {
            bufferedScene = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        bufferedGraphics = (Graphics2D) bufferedScene.createGraphics();
            super.paint(g);
            render(bufferedGraphics);
            g.drawImage(bufferedScene, 0, 0, null);
        }

        private void render(Graphics2D g2) {
            g2.drawImage(backGround, 0, 0, null);
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 19; j++) {
//                    if (mapMatrix[i][j] != 0) {
//                        g2.fillRect(j * 52, i * 52, 52, 52);
//                        g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\tile0" + mapMatrix[i][j] + ".png")).getImage(), j * 52, i * 52, null);
//                    }
                    if (mapMatrix[i][j] != 0) {
                        if (mapMatrix[i][j] < 6) {
                            g2.fillRect(j * 52, i * 52, 52, 52);
                            g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\tile0" + mapMatrix[i][j] + ".png")).getImage(), j * 52, i * 52, null);
                        } else if (mapMatrix[i][j] < 10) {
                            g2.fillRect(j * 52, i * 52, 52, 52);
                            g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\box0" + (mapMatrix[i][j] - 5) + ".png")).getImage(), j * 52, i * 52, null);
                        } else if (mapMatrix[i][j] < 15) {
                            g2.fillRect(j * 52, i * 52, 52, 52);
                            g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\item0" + (mapMatrix[i][j] - 10) + ".png")).getImage(), j * 52, i * 52, null);
                        } else {
                            g2.fillRect(j * 52, i * 52, 52, 52);
                            g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\wall0" + (mapMatrix[i][j] - 19) + ".png")).getImage(), j * 52, i * 52, null);
                        }
                    }

                }
            }
/////////////////////////            
            int robotX = (int) ((myRobot.getX() + 28) / 52);
            int robotY = (int) ((myRobot.getY() + 24) / 52);
            identityL = new AffineTransform();
            identityB = new AffineTransform();

            if ((myRobot.getX() > -20) && (myRobot.getY() > 0) && (myRobot.getX() < 940) && (myRobot.getY() < 630)) { //shart bayad vase hame jahata tafkik she
                if (left && up) {
                    robotX = (int) ((myRobot.getX() - 13 + 28) / 52);
                    robotY = (int) ((myRobot.getY() - 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() - 10);
                        myRobot.setY(myRobot.getY() - 10);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
                        myRobot.setX(350);
                        myRobot.setY(500);

                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    identityL.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    identityL.rotate(-Math.PI / 4, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], identityL, null);

                } else if (right && up) {
                    robotX = (int) ((myRobot.getX() + 13 + 28) / 52);
                    robotY = (int) ((myRobot.getY() - 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() + 10);
                        myRobot.setY(myRobot.getY() - 10);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
                        myRobot.setX(350);
                        myRobot.setY(500);
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    identityL.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    identityL.rotate(Math.PI / 4, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], identityL, null);

                } else if (right && down) {
                    robotX = (int) ((myRobot.getX() + 13 + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() + 10);
                        myRobot.setY(myRobot.getY() + 10);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
                        myRobot.setX(350);
                        myRobot.setY(500);
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    identityL.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    identityL.rotate(3 * Math.PI / 4, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], identityL, null);
                } else if (left && down) {
                    robotX = (int) ((myRobot.getX() - 13 + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() - 13);
                        myRobot.setY(myRobot.getY() + 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        robot1.setX(350);
//                        robot1.setY(500);
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    identityL.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    identityL.rotate(-3 * Math.PI / 4, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], identityL, null);
                } else if (left) {
                    robotX = (int) ((myRobot.getX() - 13 + 28) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() - 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        robot1.setX(350);
//                        robot1.setY(500);
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    identityL.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    identityL.rotate(-Math.PI / 2, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], identityL, null);
                } else if (right) {
                    robotX = (int) ((myRobot.getX() + 13 + 28) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() + 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        robot1.setX(350);
//                        robot1.setY(500);
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    identityL.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    identityL.rotate(Math.PI / 2, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], identityL, null);
                } else if (up) {
                    robotY = (int) ((myRobot.getY() - 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setY(myRobot.getY() - 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        myRobot.setX(350);
//                        myRobot.setY(500);
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    identityL.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    identityL.rotate(0, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], identityL, null);
                } else if (down) {
                    robotY = (int) ((myRobot.getY() + 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setY(myRobot.getY() + 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        robot1.setX(350);
//                        robot1.setY(500);
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    identityL.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    identityL.rotate(Math.PI, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], identityL, null);
                }
            } else {
//                robot1.setX(350);
//                robot1.setY(500);
            }

            AffineTransform identity = new AffineTransform();
            identity.setToTranslation(myRobot.getX() + 9, myRobot.getY());
            identity.rotate(alfa, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
            g2.drawImage(myRobot.bodyImage, identity, null);
            
///////////////////////////      
        }

        private void loadMap() {
            Scanner fromFileReader = null;
            try {
                fromFileReader = new Scanner(new File("src/data/maps/map1.txt"));
            } catch (FileNotFoundException ex) {
                System.out.println("Problems in reading the map file.");
            }
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 19; j++) {
                    mapMatrix[i][j] = fromFileReader.nextInt();
                }
            }
// making boxes            
            int numberOfBoxes = 0;
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 19; j++) {
                    if ((mapMatrix[i][j] > 5) && (mapMatrix[i][j] < 10)) {
                        numberOfBoxes++;
                    }
                }
            }
            Box[] box = new Box[numberOfBoxes];
            int boxCounter = 0;
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 19; j++) {
                    if ((mapMatrix[i][j] > 5) && (mapMatrix[i][j] < 10)) {
                        box[boxCounter] = new Box(i, j, (int) (Math.random() * 3));
                        System.out.println((int) (Math.random() * 3));
                        boxCounter++;
                    }
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
// mouse and key listener
        @Override
        protected void processKeyEvent(KeyEvent e) {
            
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                    left = true;
                    System.out.println("checkpoint");
                    time--;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                    right = true;
                    time++;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                    down = true;
                    time--;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                    up = true;
                    time++;
                }
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                    left = false;
                    time = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                    right = false;
                    time = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                    down = false;
                    time = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                    up = false;
                    time = 0;
                }
            }
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            double mouseX = e.getX();
            double mouseY = e.getY();

            alfa = Math.atan((mouseX - myRobot.getX()) / (myRobot.getY() - mouseY));
            if (myRobot.getY() < mouseY) {
                alfa = Math.PI + alfa;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            shot = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            shot = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

}
