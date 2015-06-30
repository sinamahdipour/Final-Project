/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Sina Mp
 */
public class Enemy extends Robot {

    double teta;
    boolean isCrashed;

    public Enemy() throws URISyntaxException, IOException {
        isAlive = true;
        isCrashed = false;
        health = 50;
    }
}
