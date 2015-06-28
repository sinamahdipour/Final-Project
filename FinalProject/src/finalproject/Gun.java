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
 * @author mm
 */
public class Gun {
    
    public BufferedImage[][] gunArray;
    
    public Gun(){
        gunArray = new BufferedImage[6][10];
        for(int i =0;i<8;i++){
            try {
                gunArray[0][i]=ImageIO.read(new File(getClass().getClassLoader().getResource("data/gun/Gun1/" + i + ".png").toURI()));
            } catch (IOException ex) {
                Logger.getLogger(Gun.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(Gun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
