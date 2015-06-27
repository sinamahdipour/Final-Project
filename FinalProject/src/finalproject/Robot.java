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
    private int x;
    private int y;
    public BufferedImage bodyImage;
    public BufferedImage [] legMovingImages;
    public BufferedImage[] fall;
    private Bullet bullet;

    public Robot(){
        legMovingImages =  new BufferedImage[18];
        try {
            bodyImage = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\body.png").toURI()));
            for(int i=0; i<18; i++){
                legMovingImages[i] = ImageIO.read(new File(getClass().getClassLoader().getResource("\\data\\move\\"+ i +".png").toURI()));
            }
        } catch (IOException ex) {
            System.out.println("Problems in loading the image of the robot.");
        } catch (URISyntaxException ex) {
            System.out.println("Problems in loading the image of the robot.");
        }
        
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    
    public Bullet newBullet() throws IOException, URISyntaxException {
        bullet = new Bullet();
        return bullet;
    }
}
