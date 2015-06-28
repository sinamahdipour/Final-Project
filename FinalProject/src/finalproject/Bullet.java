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
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Sina Mp
 */
public class Bullet {

    private double x;
    private double y;
    public BufferedImage[] bullets;
    public int boxNumber;
    private int numberOfBox;
    private int enemyNumber;

    public Bullet() throws IOException, URISyntaxException {
        bullets = new BufferedImage[5];
        try {
            bullets[0] = ImageIO.read(new File(getClass().getClassLoader().getResource("data\\bt.png").toURI()));
        } catch (IOException | URISyntaxException ex) {
            
        }

    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public int boxCrashedNumber() {
        return boxNumber;
    }
    public int enemyCrashedNumber() {
        return enemyNumber;
    }
    
    public boolean isCrashed(Box[] box, int map[][], int numberOfBox,ArrayList<Enemy> enemy) throws URISyntaxException, IOException {
        boxNumber = -1;
        enemyNumber = -1;
        boolean isCrashed = false;
        this.numberOfBox = numberOfBox;
        for (int i = 0; i < numberOfBox; i++) {
            if (box[i].health != 0) {
                if ((box[i].y == x / 52) && (box[i].x == y / 52)) {
                    if (box[i].isDamagable) {
                        box[i].health--;
                        boxNumber = i;
                    }
                    isCrashed = true;
                    if (box[i].health == 0) {
                        box[i].damage(map);
                    }
                }
            }
        }
        if (!isCrashed) {
            for (int i = 0; i < enemy.size(); i++) {
                if (((int) enemy.get(i).getX() / 52 == (int) x / 52 - 1) && ((int) enemy.get(i).getY() / 52 == (int) y / 52 - 1)) {
                    enemyNumber = i;
                    enemy.get(i).health -= 10;
                    if (enemy.get(i).health == 0) {
                        enemy.get(i).isAlive = false;
                        enemy.get(i).isCrashed = true;
                    }
                    isCrashed = true;
                }
            }
        }
        if ((x < -20) || (y < 0) || (x > 1000) || (y > 700)) {
            return true;
        } else if (isCrashed) {
            return true;
        } else {
            return false;
        }
    }
    
}
