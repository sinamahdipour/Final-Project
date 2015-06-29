/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Sina Mp
 */
public class Robot {

    private double x;
    private double y;
    public BufferedImage bodyImage;
    public BufferedImage[] legMovingImages;
    public BufferedImage[] fall;
    public BufferedImage bodyHitted;
    private Bullet bullet;
    protected boolean isAlive;
    protected int health;
    private Gun gun;
    public int energy;
    public int xpLevel;
    public int money;
    public int xp;
    public int roomPlace;
    public Robot() {
        gun = new Gun();
        health = 100;
        roomPlace = 0;
        money = 0;
        energy = 5;
        xp = 90;
        xpLevel = 0;
        legMovingImages = new BufferedImage[18];
        fall = new BufferedImage[7];
        try {
            bodyImage = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\body.png").toURI()));
            for (int i = 0; i < 18; i++) {
                legMovingImages[i] = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\move\\" + i + ".png").toURI()));
            }
            for (int i = 0; i < 7; i++) {
                fall[i] = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\fall\\0" + (i + 1) + ".png").toURI()));
            }
            bodyHitted = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\bodyHitted.png").toURI()));
        } catch (IOException ex) {
            System.out.println("Problems in loading the image of the robot.");
        } catch (URISyntaxException ex) {
            System.out.println("Problems in loading the image of the robot.");
        }

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Bullet newBullet() throws IOException, URISyntaxException {
        bullet = new Bullet();
        return bullet;
    }
}
