/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Sina Mp
 */
/**
 * defining the frame of the game, to which all the panels will be added
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
    public static int c;

    /**
     * here is the constructor of frame
     */
    public GameFrame() {
        super("ROBO KILL");
        c = 1;
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

    /**
     * defining the settings menu in order to customize program
     */
    private class SettingsMenu extends JPanel {

//        public int c;
        JRadioButton on;
        JRadioButton off;
        JLabel sound;
        JButton s;
        JButton st;

        /**
         * here is the constructor of settings panel
         */
        public SettingsMenu() {
            setLayout(null);
            setOpaque(false);
            setSize(1000, 700);
//            c = 1;
            try {
                backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\i.jpg").toURI()));
            } catch (IOException ex) {
                Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            s = new JButton("Sounds On/Off");
            st = new JButton("Start Game");
            s.setSize(200,40);
            st.setSize(100,40);
            s.setLocation(1000 * 1 / 15, 700 * 26 / 50);
            st.setLocation(1000 * 1 / 15, 700 * 26 / 50 + 45);
            add(s);
            add(st);
            /**
             * defining the action listener for radio buttons
             */
            al = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == s) {
                        if(c == 1){
                            c = 0;
                        }else {
                            c = 1;
                        }
                        System.out.println(c);
                    }
                    if(e.getSource() == st){
                        Game g = new Game();
                        player = new Thread(g);
                        player.start();
                        getContentPane().removeAll();
                        getContentPane().add(g);
                        getContentPane().repaint();
                        g.requestFocus();
                    }
                    
                   }

            };
            s.addActionListener(al);
            st.addActionListener(al);
            
//            on.addActionListener(al);
//            off.addActionListener(al);

//            on = new JRadioButton("On",false);
//            off = new JRadioButton("Off",false);
//            on.setSize(100, 50);
//            on.setLocation(600, 300);
//            off.setSize(100, 50);
//            off.setLocation(100, 600);
//            sound = new JLabel();
//            sound.setSize(100, 50);
//            sound.setLocation(0, 0);

//            ButtonGroup group = new ButtonGroup();
//            group.add(on);
//            group.add(off);

//            on.addActionListener(al);
//            off.addActionListener(al);
//            group.setSize(200,100);
//            group.setLocation(500,500);
//            sound.setLocation(1000 * 1 / 15 - 20, 700 * 26 / 50);
//            add(sound);
//
//            on.setLocation(1000 * 1 / 15, 700 * 26 / 50);
//
//            add(on);
//
//            off.setLocation(1000 * 1 / 15 + 20, 700 * 26 / 50);
//
//            add(off);

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(backGround, 0, 0, null);
            repaintComponents();
        }

        private void repaintComponents() {
            s.repaint();
            st.repaint();
            
        }

//        public int returnSoundStatus() {
//            return c;
//        }
    }

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
                        SettingsMenu s = new SettingsMenu();
                        getContentPane().add(s);
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

    /**
     * defining the second menu for selecting single or multi player
     */
    private class Menu2 extends JPanel {

        Dimension screenDimention;
        BufferedImage backGround;
        JButton singleplayerBtn;
        JButton multiplayerBtn;
        ActionListener al2;

        /**
         * here is the constructor of second menu
         */
        public Menu2() {
            setLayout(null);
            screenDimention = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(1000, 700);
            try {
                backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\smbg3.jpg").toURI()));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            /**
             * defining the action listener for buttons
             */
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
                        g.requestFocus();
                    }
                    if (e.getSource() == multiplayerBtn) {

                    }
                }
            };
            /**
             * defining buttons and setting their size and feature
             */
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

    /**
     * the panel in which the game is played
     */
    private class Game extends JPanel implements ActionListener, Runnable, MouseInputListener, MouseMotionListener {

        BufferedImage backGround;
        BufferedImage bufferedScene;
        private Graphics2D bufferedGraphics;
        private BufferedImage crash;
        private int mapMatrix[][];
        private Robot myRobot;
        private double alfa;
        private boolean left;
        private boolean right;
        private boolean down;
        private boolean up;
        private AffineTransform legRotater;
        private AffineTransform bulletRotater;
        private int time;
        private boolean shot;
        private Thread player;
        private ArrayList<Bullet> bulletArray;
        private ArrayList<Double> bulletAngleArray;
        private boolean fall;
        private int roomNumber;
        private boolean[] arrayOfRooms;
        private boolean hasKey;
        int counter = 0;
        int numberOfBoxes;
        Box[] box;
        AffineTransform robotRotater;
        AffineTransform enemyRotater;
        private ArrayList<Enemy> enemyArrayList;
        double enemyX;
        double enemyY;
        double mouseX;
        double mouseY;
        private String ramz;

        public Game() {
            ramz = " ";
            setFocusable(true);
            setLayout(null);

            screenDimention = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(1000, 700);
            fall = false;
            mapMatrix = new int[13][19];
            try {
                backGround = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\bg01.jpg").toURI()));
            } catch (URISyntaxException | IOException ex) {
                System.out.println("Problems in loading pictures of the game.");
            }
            bufferedScene = new BufferedImage(1000, 700, BufferedImage.TYPE_INT_RGB);
            bufferedGraphics = (Graphics2D) bufferedScene.createGraphics();
            arrayOfRooms = new boolean[12];
            roomNumber = 1;
            for (int i = 0; i < 12; i++) {
                arrayOfRooms[i] = true;
            }
            myRobot = new Robot();
            myRobot.setX(515);
            myRobot.setY(565);

            enemyArrayList = new ArrayList<>();
            try {
                enemyArrayList.add(new Enemy());
            } catch (URISyntaxException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            enemyArrayList.get(0).setX(350);
            enemyArrayList.get(0).setY(100 + 0.25);

            bulletArray = new ArrayList<>();
            bulletAngleArray = new ArrayList<>();
            try {
                crash = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\crash01.png").toURI()));
            } catch (IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.addMouseMotionListener(this);
            this.addMouseListener(this);
            loadMap(1);
            createBox();
        }

        @Override
        public void paint(Graphics g) {
            bufferedScene = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            bufferedGraphics = (Graphics2D) bufferedScene.createGraphics();
            super.paint(g);
            try {
                render(bufferedGraphics);
            } catch (URISyntaxException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            g.drawImage(bufferedScene, 0, 0, null);
        }

        private void render(Graphics2D g2) throws URISyntaxException, IOException {

            g2.drawImage(backGround, 0, 0, null);
            if (hasKey) {
                g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\key.png")).getImage(), 870, 20, null);
            }
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 19; j++) {
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
                        } else if (mapMatrix[i][j] == 20) {
                            g2.fillRect(j * 52, i * 52, 52, 52);
                            g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\wall0" + (mapMatrix[i][j] - 19) + ".png")).getImage(), j * 52, i * 52, null);
                        } else if (mapMatrix[i][j] == 30) {
                            g2.fillRect(j * 52, i * 52, 52, 52);
                            if (!arrayOfRooms[roomNumber]) {
                                g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\openUp.png")).getImage(), j * 52, i * 52, null);
                            } else {
                                g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\closedUp.png")).getImage(), j * 52, i * 52, null);
                            }
                        } else if (mapMatrix[i][j] == 40) {
                            g2.fillRect(j * 52, i * 52, 52, 52);
                            if (!arrayOfRooms[roomNumber]) {
                                g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\openDown.png")).getImage(), j * 52, i * 52, null);
                            } else {
                                g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\closedDown.png")).getImage(), j * 52, i * 52, null);
                            }
                        } else if (mapMatrix[i][j] == 31) {
                            g2.fillRect(j * 52, i * 52, 52, 52);
                            g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\closedUp.png")).getImage(), j * 52, i * 52, null);
                            g2.setColor(Color.CYAN);
                            g2.setFont(new Font("Tahoma", Font.BOLD, 10));
                            g2.drawString("LOCKED", j * 52 + 5, i * 52 + 22);
                            g2.setColor(Color.WHITE);
                        }

                    }
                }
            }
            int robotX = (int) ((myRobot.getX() + 28) / 52);
            int robotY = (int) ((myRobot.getY() + 24) / 52);
            legRotater = new AffineTransform();
            bulletRotater = new AffineTransform();

            if ((myRobot.getX() > -20) && (myRobot.getY() > 0) && (myRobot.getX() < 1000) && (myRobot.getY() < 700) && (!fall)) {
                for (int i = 0; i < enemyArrayList.size(); i++) {
                    enemyX = enemyArrayList.get(i).getX();
                    enemyY = enemyArrayList.get(i).getY();
                    enemyArrayList.get(i).teta = Math.atan((myRobot.getX() - enemyX) / (enemyY - myRobot.getY()));
                    if (enemyArrayList.get(i).getX() > myRobot.getX()) {
                        enemyArrayList.get(i).setX(enemyArrayList.get(i).getX() - 3.25);
                    } else if (enemyArrayList.get(i).getX() < myRobot.getX()) {
                        enemyArrayList.get(i).setX(enemyArrayList.get(i).getX() + 3.25);
                    }

                    if (enemyArrayList.get(i).getY() > myRobot.getY()) {
                        enemyArrayList.get(i).setY(enemyArrayList.get(i).getY() - 3.25);
                    } else if (enemyArrayList.get(i).getY() < myRobot.getY()) {
                        enemyArrayList.get(i).setY(enemyArrayList.get(i).getY() + 3.25);
                    }
                }

/////////////////////////            
                if (left && up) {
                    robotX = (int) ((myRobot.getX() - 13 + 28) / 52);
                    robotY = (int) ((myRobot.getY() - 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() - 13);
                        myRobot.setY(myRobot.getY() - 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            if (mapMatrix[robotY][robotX] == 11) {
                                myRobot.money = myRobot.money + 50;
                            }
                            if (mapMatrix[robotY][robotX] == 12) {
                                myRobot.energy += 20;
                                if (myRobot.energy > 100) {
                                    myRobot.energy = 100;
                                }
                            }
                            if (mapMatrix[robotY][robotX] == 13) {
                                hasKey = true;
                            }
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        resetGame();
                        fall = true;
                        myRobot.setX(myRobot.getX() - 13);
                        myRobot.setY(myRobot.getY() - 13);
                    } else if (mapMatrix[robotY][robotX] == 30 && !arrayOfRooms[roomNumber]) {
                        roomNumber++;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(505);
                        myRobot.setY(565);
                    } else if (mapMatrix[robotY][robotX] == 40 && !arrayOfRooms[roomNumber]) {
                        roomNumber--;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(515);
                        myRobot.setY(30);
                    } else if (mapMatrix[robotY][robotX] == 31 && hasKey) {
                        hasKey = false;
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() + 13);
                        mapMatrix[robotY][robotX] = 30;
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    legRotater.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    legRotater.rotate(-Math.PI / 4, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], legRotater, null);

                } else if (right && up) {
                    robotX = (int) ((myRobot.getX() + 13 + 28) / 52);
                    robotY = (int) ((myRobot.getY() - 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() - 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            if (mapMatrix[robotY][robotX] == 11) {
                                myRobot.money = myRobot.money + 50;
                            }
                            if (mapMatrix[robotY][robotX] == 12) {
                                myRobot.energy += 20;
                                if (myRobot.energy > 100) {
                                    myRobot.energy = 100;
                                }
                            }
                            if (mapMatrix[robotY][robotX] == 13) {
                                hasKey = true;
                                System.out.println("key");
                            }
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        resetGame();
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() - 13);
                        fall = true;
                    } else if (mapMatrix[robotY][robotX] == 30 && !arrayOfRooms[roomNumber]) {
                        roomNumber++;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(505);
                        myRobot.setY(565);
                    } else if (mapMatrix[robotY][robotX] == 40 && !arrayOfRooms[roomNumber]) {
                        roomNumber--;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(515);
                        myRobot.setY(30);
                    } else if (mapMatrix[robotY][robotX] == 31 && hasKey) {
                        hasKey = false;
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() + 13);
                        mapMatrix[robotY][robotX] = 30;
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    legRotater.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    legRotater.rotate(Math.PI / 4, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], legRotater, null);

                } else if (right && down) {
                    robotX = (int) ((myRobot.getX() + 13 + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() + 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            if (mapMatrix[robotY][robotX] == 11) {
                                myRobot.money = myRobot.money + 50;
                            }
                            if (mapMatrix[robotY][robotX] == 12) {
                                myRobot.energy += 20;
                                if (myRobot.energy > 100) {
                                    myRobot.energy = 100;
                                }
                            }
                            if (mapMatrix[robotY][robotX] == 13) {
                                hasKey = true;
                                System.out.println("key");
                            }
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        resetGame();
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() + 13);
                        fall = true;
                    } else if (mapMatrix[robotY][robotX] == 30 && !arrayOfRooms[roomNumber]) {
                        roomNumber++;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(505);
                        myRobot.setY(565);
                    } else if (mapMatrix[robotY][robotX] == 40 && !arrayOfRooms[roomNumber]) {
                        roomNumber--;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(515);
                        myRobot.setY(30);
                    } else if (mapMatrix[robotY][robotX] == 31 && hasKey) {
                        hasKey = false;
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() + 13);
                        mapMatrix[robotY][robotX] = 30;
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    legRotater.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    legRotater.rotate(3 * Math.PI / 4, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], legRotater, null);
                } else if (left && down) {
                    robotX = (int) ((myRobot.getX() - 13 + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() - 13);
                        myRobot.setY(myRobot.getY() + 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            if (mapMatrix[robotY][robotX] == 11) {
                                myRobot.money = myRobot.money + 50;
                            }
                            if (mapMatrix[robotY][robotX] == 12) {
                                myRobot.energy = myRobot.energy + 20;
                                if (myRobot.energy > 100) {
                                    myRobot.energy = 100;
                                }
                            }
                            if (mapMatrix[robotY][robotX] == 13) {
                                hasKey = true;
                                System.out.println("key");
                            }
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        resetGame();
                        myRobot.setX(myRobot.getX() - 13);
                        myRobot.setY(myRobot.getY() + 13);
                        fall = true;
                    } else if (mapMatrix[robotY][robotX] == 30 && !arrayOfRooms[roomNumber]) {
                        roomNumber++;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(505);
                        myRobot.setY(565);
                    } else if (mapMatrix[robotY][robotX] == 40 && !arrayOfRooms[roomNumber]) {
                        roomNumber--;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(515);
                        myRobot.setY(30);
                    } else if (mapMatrix[robotY][robotX] == 31 && hasKey) {
                        hasKey = false;
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() + 13);
                        mapMatrix[robotY][robotX] = 30;
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    legRotater.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    legRotater.rotate(-3 * Math.PI / 4, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], legRotater, null);
                } else if (left) {
                    robotX = (int) ((myRobot.getX() - 13 + 28) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() - 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            if (mapMatrix[robotY][robotX] == 11) {
                                myRobot.money = myRobot.money + 50;
                            }
                            if (mapMatrix[robotY][robotX] == 12) {
                                myRobot.energy = myRobot.energy + 20;
                                if (myRobot.energy > 100) {
                                    myRobot.energy = 100;
                                }
                            }
                            if (mapMatrix[robotY][robotX] == 13) {
                                hasKey = true;
                                System.out.println("key");
                            }
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        resetGame();
                        myRobot.setX(myRobot.getX() - 13);
                        fall = true;

                    } else if (mapMatrix[robotY][robotX] == 30 && !arrayOfRooms[roomNumber]) {
                        roomNumber++;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(505);
                        myRobot.setY(565);
                    } else if (mapMatrix[robotY][robotX] == 40 && !arrayOfRooms[roomNumber]) {
                        roomNumber--;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(515);
                        myRobot.setY(30);
                    } else if (mapMatrix[robotY][robotX] == 31 && hasKey) {
                        hasKey = false;
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() + 13);
                        mapMatrix[robotX][robotX] = 30;
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    legRotater.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    legRotater.rotate(-Math.PI / 2, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], legRotater, null);
                } else if (right) {
                    robotX = (int) ((myRobot.getX() + 13 + 28) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setX(myRobot.getX() + 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            if (mapMatrix[robotY][robotX] == 11) {
                                myRobot.money = myRobot.money + 50;
                            }
                            if (mapMatrix[robotY][robotX] == 12) {
                                myRobot.energy = myRobot.energy + 20;
                                if (myRobot.energy > 100) {
                                    myRobot.energy = 100;
                                }
                            }
                            if (mapMatrix[robotY][robotX] == 13) {
                                hasKey = true;
                            }
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        resetGame();
                        myRobot.setX(myRobot.getX() + 13);
                        fall = true;
                    } else if (mapMatrix[robotY][robotX] == 30 && !arrayOfRooms[roomNumber]) {
                        roomNumber++;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(505);
                        myRobot.setY(565);
                    } else if (mapMatrix[robotY][robotX] == 40 && !arrayOfRooms[roomNumber]) {
                        roomNumber--;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(515);
                        myRobot.setY(30);
                    } else if (mapMatrix[robotY][robotX] == 31 && hasKey) {
                        hasKey = false;
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() + 13);
                        mapMatrix[robotY][robotX] = 30;
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    legRotater.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    legRotater.rotate(Math.PI / 2, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], legRotater, null);
                } else if (up) {
                    robotY = (int) ((myRobot.getY() - 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setY(myRobot.getY() - 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            if (mapMatrix[robotY][robotX] == 11) {
                                myRobot.money = myRobot.money + 50;
                            }
                            if (mapMatrix[robotY][robotX] == 12) {
                                myRobot.energy = myRobot.energy + 20;
                                if (myRobot.energy > 100) {
                                    myRobot.energy = 100;
                                }
                            }
                            if (mapMatrix[robotY][robotX] == 13) {
                                hasKey = true;
                            }
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        resetGame();
                        myRobot.setY(myRobot.getY() - 13);
                        fall = true;
                    } else if (mapMatrix[robotY][robotX] == 30 && !arrayOfRooms[roomNumber]) {
                        roomNumber++;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(505);
                        myRobot.setY(565);
                    } else if (mapMatrix[robotY][robotX] == 40 && !arrayOfRooms[roomNumber]) {
                        roomNumber--;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(515);
                        myRobot.setY(30);
                    } else if (mapMatrix[robotY][robotX] == 31 && hasKey) {
                        hasKey = false;
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() + 13);
                        mapMatrix[robotY][robotX] = 30;
                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    legRotater.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    legRotater.rotate(0, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], legRotater, null);
                } else if (down) {
                    robotY = (int) ((myRobot.getY() + 13 + 24) / 52);
                    if (mapMatrix[robotY][robotX] != 0 && (mapMatrix[robotY][robotX] < 20) && ((mapMatrix[robotY][robotX] < 6) || (mapMatrix[robotY][robotX] > 9))) {
                        myRobot.setY(myRobot.getY() + 13);
                        if ((mapMatrix[robotY][robotX] > 10) && (mapMatrix[robotY][robotX] < 20)) {
                            if (mapMatrix[robotY][robotX] == 11) {
                                myRobot.money = myRobot.money + 50;
                            }
                            if (mapMatrix[robotY][robotX] == 12) {
                                myRobot.energy = myRobot.energy + 20;
                                if (myRobot.energy > 100) {
                                    myRobot.energy = 100;
                                }
                            }
                            if (mapMatrix[robotY][robotX] == 13) {
                                hasKey = true;
                            }
                            mapMatrix[robotY][robotX] = 10;
                        }
                    } else if (mapMatrix[robotY][robotX] == 0) {
//                        resetGame();
                        myRobot.setY(myRobot.getY() + 13);
                        fall = true;
                    } else if (mapMatrix[robotY][robotX] == 30 && !arrayOfRooms[roomNumber]) {
                        roomNumber++;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(505);
                        myRobot.setY(565);
                    } else if (mapMatrix[robotY][robotX] == 40 && !arrayOfRooms[roomNumber]) {
                        roomNumber--;
                        loadMap(roomNumber);
                        createEnemies();
                        createBox();
                        myRobot.setX(515);
                        myRobot.setY(30);
                    } else if (mapMatrix[robotY][robotX] == 31 && hasKey) {
                        hasKey = false;
                        myRobot.setX(myRobot.getX() + 13);
                        myRobot.setY(myRobot.getY() + 13);
                        mapMatrix[robotY][robotX] = 30;

                    }
                    robotX = (int) ((myRobot.getX() + 28) / 52);
                    robotY = (int) ((myRobot.getY() + 24) / 52);
                    legRotater.setToTranslation(myRobot.getX() + 9, myRobot.getY());
                    legRotater.rotate(Math.PI, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);
                    g2.drawImage(myRobot.legMovingImages[Math.abs(time % 18)], legRotater, null);
                }

                counter = 0;
            }
            int xFire = 0, yFire = 0;
            double tang;

            for (int i = 0; i < bulletArray.size(); i++) {
                for (int j = 0; j < enemyArrayList.size(); j++) {
                    if (!enemyArrayList.get(j).isAlive) {
                        enemyArrayList.remove(j);
                    }
                    if (enemyArrayList.size() == 0) {
                        arrayOfRooms[roomNumber] = false;
                    }
                }

//            System.out.println("22222222222222222");
                System.out.println("h   " + numberOfBoxes);
                boolean f = bulletArray.get(i).isCrashed(box, mapMatrix, numberOfBoxes, enemyArrayList);
//                repaint();
                if (f) {
//                    System.out.println("siiiiiiiiiiiiiiiinaaaaaaaaaaaaaaaaaa");
                    if ((bulletArray.get(i).boxCrashedNumber() >= 0) && (box[bulletArray.get(i).boxCrashedNumber()].boxType > 0)) {
                        System.out.println("zzzzzzzzzzzzzzzz   " + box[bulletArray.get(i).boxCrashedNumber()].boxType);
                        g2.drawImage(new ImageIcon(getClass().getClassLoader().getResource("\\data\\box0" + box[bulletArray.get(i).boxCrashedNumber()].boxType + "hitted.png")).getImage(), box[bulletArray.get(i).boxCrashedNumber()].y * 52, box[bulletArray.get(i).boxCrashedNumber()].x * 52, this);
                        System.out.println("faaaaaaaaaaaaaaaaaaaaaaaaar");
                    }
                    if (bulletArray.get(i).enemyCrashedNumber() >= 0) {
                        enemyRotater.setToTranslation(enemyArrayList.get(bulletArray.get(i).enemyCrashedNumber()).getX() + 9, enemyArrayList.get(bulletArray.get(i).enemyCrashedNumber()).getY());
                        enemyRotater.rotate(enemyArrayList.get(bulletArray.get(i).enemyCrashedNumber()).teta, enemyArrayList.get(bulletArray.get(i).enemyCrashedNumber()).bodyImage.getWidth() / 2, enemyArrayList.get(bulletArray.get(i).enemyCrashedNumber()).bodyImage.getHeight() / 2);
                        g2.drawImage(enemyArrayList.get(bulletArray.get(i).enemyCrashedNumber()).bodyHitted, (int) enemyArrayList.get(bulletArray.get(i).enemyCrashedNumber()).getX() + 9, (int) enemyArrayList.get(bulletArray.get(i).enemyCrashedNumber()).getY(), this);
                        g2.drawImage(enemyArrayList.get(bulletArray.get(i).enemyCrashedNumber()).bodyHitted, enemyRotater, this);
                        enemyArrayList.get(bulletArray.get(i).enemyCrashedNumber()).isCrashed = true;
                    }
                    g2.drawImage(crash, (int) bulletArray.get(i).getX() - 8, (int) bulletArray.get(i).getY() - 8, this);
                    bulletArray.remove(i);
                    bulletAngleArray.remove(i);
                } else {
//                bulletArray.get(i).setX(bulletArray.get(i).getX());
//                bulletArray.get(i).setY(bulletArray.get(i).getY() - 20);

//                System.out.println(Math.tan(-(teta - (Math.PI / 2))));
                    tang = Math.tan(-(bulletAngleArray.get(i) - (Math.PI / 2)));

                    if ((0 <= tang) && (tang <= 1) && (-(bulletAngleArray.get(i) - (Math.PI / 2)) >= 0)) {
                        xFire = 20;
                        yFire = (int) (20 * tang);
                    } else if ((0 <= tang) && (tang >= 1) && (-(bulletAngleArray.get(i) - (Math.PI / 2)) >= 0)) {
                        yFire = 20;
                        xFire = (int) (20 / tang);
                    } else if ((0 >= tang) && (tang >= -1) && (-(bulletAngleArray.get(i) - (Math.PI / 2)) >= 0)) {
                        xFire = -20;
                        yFire = -(int) (20 * tang);
                    } else if ((-1 >= tang) && (-(bulletAngleArray.get(i) - (Math.PI / 2)) >= 0)) {
                        yFire = 20;
                        xFire = (int) (20 / tang);
                    } else if ((0 >= tang) && (tang >= -1) && (-(bulletAngleArray.get(i) - (Math.PI / 2)) <= 0)) {
                        xFire = 20;
                        yFire = (int) (20 * tang);
                    } else if ((tang <= -1) && (-(bulletAngleArray.get(i) - (Math.PI / 2)) <= 0)) {
                        yFire = -20;
                        xFire = -(int) (20 / tang);
                    } else if ((0 <= tang) && (tang <= 1) && (-(bulletAngleArray.get(i) - (Math.PI / 2)) <= 0)) {
                        xFire = -20;
                        yFire = -(int) (20 * tang);
                    } else {
                        yFire = -20;
                        xFire = -(int) (20 / tang);
                    }

                    bulletArray.get(i).setX(bulletArray.get(i).getX() + xFire);
                    bulletArray.get(i).setY(bulletArray.get(i).getY() - yFire);
                    bulletRotater.setToTranslation(bulletArray.get(i).getX(), bulletArray.get(i).getY());
                    bulletRotater.rotate(bulletAngleArray.get(i), bulletArray.get(i).bullets[0].getWidth() / 2, bulletArray.get(i).bullets[0].getHeight() / 2);
                    g2.drawImage(bulletArray.get(i).bullets[0], bulletRotater, null);
                }
            }

            if (shot) {
                bulletArray.add(myRobot.makeNewBullet());
                bulletAngleArray.add(alfa);
                bulletArray.get(bulletArray.size() - 1).setX(myRobot.getX() + 15);
                bulletArray.get(bulletArray.size() - 1).setY(myRobot.getY() + 18);
            }

            robotRotater = new AffineTransform();
            robotRotater.setToTranslation(myRobot.getX() + 9, myRobot.getY());
            robotRotater.rotate(alfa, myRobot.bodyImage.getWidth() / 2, myRobot.bodyImage.getHeight() / 2);

            enemyRotater = new AffineTransform();

            // drawing enemies
            for (int i = 0; i < enemyArrayList.size(); i++) {
                enemyRotater.setToTranslation(enemyArrayList.get(i).getX() + 9, enemyArrayList.get(i).getY());
                enemyRotater.rotate(enemyArrayList.get(i).teta, enemyArrayList.get(i).bodyImage.getWidth() / 2, enemyArrayList.get(i).bodyImage.getHeight() / 2);
                if (!enemyArrayList.get(i).isCrashed) {
                    g2.drawImage(enemyArrayList.get(i).bodyImage, enemyRotater, this);
                } else {
                    enemyArrayList.get(i).isCrashed = false;
                }
            }
            if (!fall) {
                g2.drawImage(myRobot.bodyImage, robotRotater, null);
            } else {
                g2.drawImage(myRobot.fall[counter], robotRotater, null);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                counter++;
                if (counter == 7) {
                    fall = false;
                    myRobot.setX(515);
                    myRobot.setY(565);
                }
            }
        }

        private void loadMap(int k) {
            Scanner fromFileReader = null;
            try {
                fromFileReader = new Scanner(new File("src/data/maps/map0" + k + ".txt"));
            } catch (FileNotFoundException ex) {
                System.out.println("Problems in reading the map file.");
            }
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 19; j++) {
                    mapMatrix[i][j] = fromFileReader.nextInt();
                }
            }
// making boxes            
            /*numberOfBoxes = 0;
             for (int i = 0; i < 13; i++) {
             for (int j = 0; j < 19; j++) {
             if ((mapMatrix[i][j] > 5) && (mapMatrix[i][j] < 10)) {
             numberOfBoxes++;
             System.out.println(" ggggggggggg   "+numberOfBoxes);
             }
             }
             }
             Box[] box = new Box[numberOfBoxes];
            
             int boxCounter = 0;
             for (int i = 0; i < 13; i++) {
             for (int j = 0; j < 19; j++) {
             if ((mapMatrix[i][j] > 5) && (mapMatrix[i][j] < 10) || (mapMatrix[i][j] == 20)) {
             box[boxCounter] = new Box(i, j, (int) (Math.random() * 3));
             System.out.println("abcd  "+box[boxCounter].health);
             box[boxCounter].boxType = 10 - mapMatrix[i][j];
             System.out.println("ppppppppppp");
             if (mapMatrix[i][j] == 20) {
             box[boxCounter].isDamagable = false;
             }
             boxCounter++;
             }
             }
             }*/
        }

        private void createBox() {
            numberOfBoxes = 0;
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 15; j++) {
                    if (((mapMatrix[i][j] > 5) && (mapMatrix[i][j] < 10)) || (mapMatrix[i][j] == 20)) {
                        numberOfBoxes++;
                    }
                }
            }
            box = new Box[numberOfBoxes];
            int boxNumber = 0;
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 19; j++) {
                    if (((mapMatrix[i][j] > 5) && (mapMatrix[i][j] < 10)) || (mapMatrix[i][j] == 20)) {
                        System.out.println("check point");
                        box[boxNumber] = new Box(i, j, (int) (Math.random() * 3));

                        box[boxNumber].boxType = 10 - mapMatrix[i][j];
                        System.out.println("zzzzzzzzzzzzzzzz   " + box[boxNumber].boxType);
                        box[0].insideObject = 3;
                        if (mapMatrix[i][j] == 20) {
                            box[boxNumber].isDamagable = false;
                        }
                        boxNumber++;
                    }
                }
            }
        }

        @Override
        public void run() {
            while (true) {
                repaint();
                if (shot) {
                    try {
                        if (c == 1) {
                            Clip clip = AudioSystem.getClip();
                            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(getClass().getClassLoader().getResource("\\data\\shot01.wav").toURI()));
                            clip.open(inputStream);
                            clip.start();
                        }
                    } catch (LineUnavailableException | URISyntaxException | UnsupportedAudioFileException | IOException e) {
                    }

                }
                getWindowFocusListeners();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void createEnemies() throws URISyntaxException, IOException {
            if (arrayOfRooms[roomNumber]) {
                enemyArrayList.add(new Enemy());
                enemyArrayList.get(0).setX(350);
                enemyArrayList.get(0).setY(100 + 0.25);
            }
        }

// mouse and key listener
        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();

            alfa = Math.atan((mouseX - myRobot.getX()) / (myRobot.getY() - mouseY));
            if (myRobot.getY() < mouseY) {
                alfa = Math.PI + alfa;
            }

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

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        protected void processKeyEvent(KeyEvent e) {

            alfa = Math.atan((mouseX - myRobot.getX()) / (myRobot.getY() - mouseY));
            if (myRobot.getY() < mouseY) {
                alfa = Math.PI + alfa;
            }

            if (e.getID() == KeyEvent.KEY_PRESSED) {
                

                ramz = ramz + e.getKeyChar();
                if (ramz.contains("givemekey")) {
                    hasKey = true;
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                    left = true;
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

        private void resetGame() {

        }

    }

}
