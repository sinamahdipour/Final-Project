/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author Sina Mp
 */
public class Box {

    public int x;
    public int y;
    public int insideObject;
    private boolean isDamaged;
    boolean isDamagable;
    int mapMatrix[][];
    int health;
    int boxType;

    public Box(int x, int y, int inObject) {
        isDamaged = false;
        isDamagable = true;
        health = 5;
        this.x = x;
        this.y = y;
        mapMatrix = new int[13][19];
        this.insideObject = inObject;
    }

    public int[][] damage(int mapMx[][]) {
        this.mapMatrix = mapMx;
        isDamaged = true;
        mapMatrix[x][y] = insideObject + 10;
        System.out.println("wwwwwwwwwwwwwwwwww  " + mapMatrix[x][y]);
        return mapMatrix;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getInsideObject() {
        return insideObject;
    }
}
