package com.example.jishnu.tappydefender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by Jishnu on 6/13/2016.
 */
public class PlayerShip {
    // A hit box for collision detection
    private int shieldStrength;
    private Rect hitBox;
    private final int GRAVITY = -10;
    // Stop ship leaving the screen
    private int maxY;
    private int minY;
    //Limit the bounds of the ship's speed
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 15;
    private boolean boosting;
    private Bitmap bitmap;
    private int x, y;
    private int speed = 0;

    // Constructor
    public PlayerShip(Context context,  int screenX, int screenY) {
        shieldStrength = 75;
        boosting = false;
        this.x = 50;
        this.y = 50;
        speed = 1;
        bitmap = BitmapFactory.decodeResource
                (context.getResources(), R.drawable.ship_3);
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        // Initialize the hit box
        hitBox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }
    public void setShield(int shield){shieldStrength=shield;}
    public Rect getHitbox(){
        return hitBox;
    }
    public void setBoosting() {
        boosting = true;
    }
    public void stopBoosting() {
        boosting = false;
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void reduceShieldStrength_1(){
        shieldStrength =shieldStrength-25;
    }
    public void reduceShieldStrength_2(){
        shieldStrength =shieldStrength-35;
    }
    public void reduceShieldStrength_3(){
        shieldStrength =shieldStrength-45;
    }

    public void update() {
        // Are we boosting?
        if (boosting) {
            // Speed up
            speed += 1.5;
        } else {
            // Slow down
            speed -= 5;
        }
        // Constrain top speed
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        // Never stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        // move the ship up or down
        y -= speed + GRAVITY;
        // But don't let ship stray off screen
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }
        // Refresh hit box location
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + bitmap.getWidth();
        hitBox.bottom = y + bitmap.getHeight();
    }



    //Getters
    public Bitmap getBitmap() {
        return bitmap;
    }
    public int getSpeed() {
        return speed;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}

