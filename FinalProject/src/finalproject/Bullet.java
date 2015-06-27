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
import javax.imageio.ImageIO;

/**
 *
 * @author Sina Mp
 */
public class Bullet {

    private int x;
    private int y;
    public BufferedImage[] bullets;
    public int boxNumber;
    private int numberOfBox;

    public Bullet() throws IOException, URISyntaxException {
        bullets = new BufferedImage[5];
        try {
            bullets[0] = ImageIO.read(new File(getClass().getClassLoader().getResource("data\\bt.png").toURI()));
        } catch (IOException | URISyntaxException ex) {
            
        }

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int boxCrashedNumber() {
        return boxNumber;
    }
    
    public boolean isCrashed(Box[] box, int map[][], int numberOfBox) throws URISyntaxException, IOException {
        boxNumber = 0;
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
        if ((x < -20) || (y < 0) || (x > 1000) || (y > 700)) {
            return true;
        } else if (isCrashed) {
            return true;
        } else {
            return false;
        }
    }
    
}
