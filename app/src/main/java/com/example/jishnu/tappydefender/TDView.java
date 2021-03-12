package com.example.jishnu.tappydefender;

/**
 * Created by Jishnu on 6/13/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TDView extends SurfaceView implements
        Runnable{
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private boolean gameEnded;
    private Context context;
    private int screenX;
    private int screenY;
    private float distanceRemaining;
    private long timeTaken;
    private long timeStarted;
    private long fastestTime;
    private long curLevel;
    //Game objects
    private PlayerShip player;
    public EnemyShip enemy1;
    public EnemyShip enemy2;
    public EnemyShip_2 enemy3;
    public EnemyShip_3 enemy4;
//    public EnemyShip enemy3;
    // For drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;
    volatile boolean playing;
    Thread gameThread = null;
    // Make some random space dust
    public ArrayList<SpaceDust> dustList = new
            ArrayList<SpaceDust>();
    public TDView(Context context, int x, int y) {
        super(context);
        this.context = context;
        // Get a reference to a file called HiScores.
        // If id doesn't exist one is created
        prefs = context.getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        editor = prefs.edit();
        // Load fastest time from a entry in the file
        // labeled "fastestTime"
        // if not available highscore = 1000000
        fastestTime = prefs.getLong("fastestTime", 1000000);
        curLevel = prefs.getInt("curLevel",1);
        screenX = x;
        screenY = y;
        // Initialize our drawing objects
        ourHolder = getHolder();
        paint = new Paint();
        startGame();
    }



    private void startGame(){
        //Initialize game objects
        player = new PlayerShip(context, screenX, screenY);
  //      enemy3 = new EnemyShip(context, screenX, screenY);
        curLevel = prefs.getInt("curLevel",1);
        int numSpecs = 40;
        for (int i = 0; i < numSpecs; i++) {
            // Where will the dust spawn?
            SpaceDust spec = new SpaceDust(screenX, screenY);
            dustList.add(spec);
        }
        // Reset time and distance
        if(curLevel==1)
        {
            enemy1 = new EnemyShip(context, screenX, screenY);
            enemy2 = new EnemyShip(context, screenX, screenY);
            distanceRemaining=5000;
            player.setShield(75);
        }
        else if(curLevel==2)
        {
            enemy1 = new EnemyShip(context, screenX, screenY);
            enemy2 = new EnemyShip(context, screenX, screenY);
            distanceRemaining=10000;
            player.setShield(75);
        }
        else if(curLevel==3)
        {
            enemy1 = new EnemyShip(context, screenX, screenY);
            enemy2 = new EnemyShip(context, screenX, screenY);
            distanceRemaining=15000;
            player.setShield(75);
        }
        else if(curLevel==4)
        {
            enemy2 = new EnemyShip(context, screenX, screenY);
            enemy3 = new EnemyShip_2(context, screenX, screenY);
            distanceRemaining=20000;
            player.setShield(95);
        }
        else if(curLevel==5)
        {
            enemy2 = new EnemyShip(context, screenX, screenY);
            enemy3 = new EnemyShip_2(context, screenX, screenY);
            distanceRemaining=25000;
            player.setShield(95);
        }
        else if(curLevel==6)
        {
            enemy2 = new EnemyShip(context, screenX, screenY);
            enemy3 = new EnemyShip_2(context, screenX, screenY);
            distanceRemaining=30000;
            player.setShield(95);
        }
        else if(curLevel==7)
        {
            enemy3 = new EnemyShip_2(context, screenX, screenY);
            enemy4 = new EnemyShip_3(context, screenX, screenY);
            distanceRemaining=35000;
            player.setShield(115);
        }
        else if(curLevel==8)
        {
            enemy3 = new EnemyShip_2(context, screenX, screenY);
            enemy4 = new EnemyShip_3(context, screenX, screenY);
            distanceRemaining=40000;
            player.setShield(115);
        }
        else if(curLevel==9)
        {
            enemy3 = new EnemyShip_2(context, screenX, screenY);
            enemy4 = new EnemyShip_3(context, screenX, screenY);
            distanceRemaining=45000;
            player.setShield(115);
        }
        timeTaken = 0;
        // Get start time
        timeStarted = System.currentTimeMillis();
        gameEnded = false;
    }


    // Clean up our thread if the game is interrupted or the player quits
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }
    // Make a new thread and start it
    // Execution moves to our R
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update(){
        // Collision detection on new positions
        // Before move because we are testing last frames
        // position which has just been drawn
        curLevel = prefs.getInt("curLevel",1);
        boolean hitDetected = false;
        if(curLevel>=1 && curLevel<=3) {
            if (Rect.intersects(player.getHitbox(), enemy1.getHitbox())) {
                hitDetected = true;
                player.reduceShieldStrength_1();
                enemy1.setX(-100);
            }
        }
        if(curLevel>=1 && curLevel<=6) {
            if (Rect.intersects(player.getHitbox(), enemy2.getHitbox())) {
                hitDetected = true;
                player.reduceShieldStrength_1();
                enemy2.setX(-100);
            }
        }
        if(curLevel>=4 && curLevel<=9) {
            if (Rect.intersects(player.getHitbox(), enemy3.getHitbox())) {
                hitDetected = true;
                player.reduceShieldStrength_2();
                enemy3.setX(-100);
            }
        }
        if(curLevel>=7 && curLevel<=9) {
            if (Rect.intersects(player.getHitbox(), enemy4.getHitbox())) {
                hitDetected = true;
                player.reduceShieldStrength_3();
                enemy4.setX(-100);
            }
        }
    //    if(Rect.intersects(player.getHitbox(), enemy3.getHitbox())){
      //      hitDetected = true;
        //    enemy3.setX(-100);
        //}
        if(hitDetected) {
            if (player.getShieldStrength() <= 0) {
                gameEnded = true;
            }
        }
        // Update the player
        player.update();
        // Update the enemies
        if(curLevel>=1 && curLevel<=3){enemy1.update(player.getSpeed());}
        if(curLevel>=1 && curLevel<=6){enemy2.update(player.getSpeed());}
        if(curLevel>=4 && curLevel<=9){enemy3.update(player.getSpeed());}
        if(curLevel>=7 && curLevel<=9){enemy4.update(player.getSpeed());}
        //enemy3.update(player.getSpeed());
        for (SpaceDust sd : dustList) {
            sd.update(player.getSpeed());
        }
        if(!gameEnded) {
            //subtract distance to home planet based on current speed
            distanceRemaining -= player.getSpeed();
            //How long has the player been flying
            timeTaken = System.currentTimeMillis() - timeStarted;
        }
        //Completed the game!
        if(distanceRemaining < 0){
            //check for new fastest time
            if(timeTaken < fastestTime) {
                // Save high score
                editor.putLong("fastestTime", timeTaken);
                editor.commit();
                fastestTime = timeTaken;
            }

            // avoid ugly negative numbers
            // in the HUD
            distanceRemaining = 0;
            // Now end the game
            gameEnded = true;
        }
    }

    private void draw(){
        curLevel = prefs.getInt("curLevel",1);
        if (ourHolder.getSurface().isValid()) {
            //First we lock the area of memory we will be drawing to
            canvas = ourHolder.lockCanvas();
            // Rub out the last frame
            canvas.drawColor(Color.argb(255, 0, 0, 0));
            // White specs of dust
            paint.setColor(Color.argb(255, 255, 255, 255));
            //Draw the dust from our arrayList
            for (SpaceDust sd : dustList) {
                canvas.drawPoint(sd.getX(), sd.getY(), paint);
                // Draw the player
                // ...
            }
            // Draw the player
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);
            if(curLevel>=1 && curLevel<=3) {
                canvas.drawBitmap
                        (enemy1.getBitmap(),
                                enemy1.getX(),
                                enemy1.getY(), paint);
            }
            if(curLevel>=1 && curLevel<=6) {
                canvas.drawBitmap
                        (enemy2.getBitmap(),
                                enemy2.getX(),
                                enemy2.getY(), paint);
            }
            if(curLevel>=4 && curLevel<=9) {
                canvas.drawBitmap
                        (enemy3.getBitmap(),
                                enemy3.getX(),
                                enemy3.getY(), paint);
            }
            if(curLevel>=7 && curLevel<=9) {
                canvas.drawBitmap
                        (enemy4.getBitmap(),
                                enemy4.getX(),
                                enemy4.getY(), paint);
            }
          //  canvas.drawBitmap
            //        (enemy3.getBitmap(),
              //              enemy3.getX(),
                //            enemy3.getY(), paint);

            // Draw the hud
            if(!gameEnded){
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(25);
            //canvas.drawText("Fastest:"+ fastestTime + "s", 10, 20, paint);
                canvas.drawText("Fastest:" +
                        formatTime(fastestTime) +
                        "s", 10, 20, paint);
            //canvas.drawText("Time:" + timeTaken + "s", screenX / 2, 20,
              //      paint);
                canvas.drawText("Time:" +
                        formatTime(timeTaken) +
                        "s", screenX / 2, 20, paint);
            canvas.drawText("Distance:" +
                    distanceRemaining / 1000 +
                    " KM", screenX / 3, screenY - 20, paint);
            canvas.drawText("Shield:" +
                    player.getShieldStrength(), 10, screenY - 20, paint);

            canvas.drawText("Speed:" +
                    player.getSpeed() * 60 +
                    " MPS", (screenX /3 ) * 2, screenY - 20, paint);
            }
            else if(gameEnded && (distanceRemaining >0)){
                // Show pause screen
                paint.setTextSize(80);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText("Game Over", screenX/2, 100, paint);
                paint.setTextSize(25);
                canvas.drawText("Fastest:"+
                       formatTime(fastestTime) + "s", screenX/2, 160, paint);

                canvas.drawText("Time:" + formatTime(timeTaken) +
                        "s", screenX / 2, 200, paint);

                canvas.drawText("Distance remaining:" +
                        distanceRemaining/1000 + " KM",screenX/2, 240, paint);

                paint.setTextSize(80);
                canvas.drawText("Tap to replay!", screenX/2, 350, paint);
            }
            else if(gameEnded && distanceRemaining <=0){
                paint.setTextSize(80);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText("Player Won", screenX/2, 140, paint);
                paint.setTextSize(25);
                canvas.drawText("Fastest:"+
                        formatTime(fastestTime) + "s", screenX/2, 200, paint);

                canvas.drawText("Time:" + formatTime(timeTaken) +
                        "s", screenX / 2, 240, paint);

                //canvas.drawText("Distance remaining:" +
                      //  distanceRemaining/1000 + " KM",screenX/2, 240, paint);

                paint.setTextSize(80);
                canvas.drawText("Tap to replay!", screenX/2, 350, paint);

            }
                    // Unlock and draw the scene
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private String formatTime(long time){
        long seconds = (time) / 1000;
        long thousandths = (time) - (seconds * 1000);
        String strThousandths = "" + thousandths;
        if (thousandths < 100){strThousandths = "0" + thousandths;}
        if (thousandths < 10){strThousandths = "0" + strThousandths;}
        String stringTime = "" + seconds + "." + strThousandths;
        return stringTime;
    }

    // SurfaceView allows us to handle the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // There are many different events in MotionEvent
        // We care about just 2 - for now.
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Has the player lifted their finger up?
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            // Has the player touched the screen?
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                // If we are currently on the pause screen, start a new game
                if(gameEnded && distanceRemaining <= 0){
                    int nextLevel = prefs.getInt("curLevel",1);
                    nextLevel = nextLevel +1;
                    editor.putInt("curLevel",nextLevel);
                    editor.commit();
                    startGame();
                }
                else if(gameEnded && distanceRemaining > 0){
                    startGame();
                }
                break;
        }
        return true;
    }

    private void control(){
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }
        }
