package com.example.jishnu.tappydefender;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Jishnu on 6/13/2016.
 */
public class EnemyShip_3{
    // A hit box for collision detection
    private Rect hitBox;
    private Bitmap bitmap;
    private int x, y;
    private double speed =0.5 ;
    // Detect enemies leaving the screen
    private int maxX;
    private int minX;
    // Spawn enemies within screen bounds
    private int maxY;
    private int minY;

    // Constructor
    public EnemyShip_3(Context context, int screenX, int screenY){

        bitmap = BitmapFactory.decodeResource
                (context.getResources(), R.drawable.enemy3);


        //    scaleBitmap(screenX);
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;
        Random generator =new Random();
        speed = generator.nextInt(6)+10;
        x = screenX;
        y = generator.nextInt(maxY) - bitmap.getHeight();
        // Initialize the hit box
        hitBox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public Rect getHitbox(){
        return hitBox;
    }

    //Getters and Setters
    public Bitmap getBitmap(){
        return bitmap;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void update(int playerSpeed){

        // Move to the left
        x -= playerSpeed;
        x -= speed;
        //respawn when off screen
        if(x < minX-bitmap.getWidth()){
            Random generator = new Random();
            speed = generator.nextInt(10)+10;
            x = maxX;
            y = generator.nextInt(maxY) - bitmap.getHeight();
        }
        // Refresh hit box location
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + bitmap.getWidth();
        hitBox.bottom = y + bitmap.getHeight();
    }
    // This is used by the TDView update() method to
// Make an enemy out of bounds and force a re-spawn
    public void setX(int x) {
        this.x = x;
    }
}
