package com.example.jishnu.tappydefender;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    // This is the entry point to our game
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Here we set our UI layout as the view
        setContentView(R.layout.activity_main);
        // Prepare to load fastest time
        SharedPreferences prefs;
        SharedPreferences.Editor editor;
        prefs = getSharedPreferences("HiScores", MODE_PRIVATE);

        // Get a reference to the button in our layout
        final Button buttonPlay =
                (Button)findViewById(R.id.buttonPlay);

        // Get a reference to the TextView in our layout
        final TextView textFastestTime =
                (TextView)findViewById(R.id.textHighScore);

        // Listen for clicks
        buttonPlay.setOnClickListener(this);

        // Load fastest time
        // if not available our high score = 1000000
        long fastestTime = prefs.getLong("fastestTime", 1000000);
        // Put the high score in our TextView
        textFastestTime.setText("Fastest Time:" + formatTime(fastestTime));
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

    @Override
    public void onClick(View v) {
        // must be the Play button.
// Create a new Intent object
       // Intent i = new Intent(this, GameActivity.class);
        Intent i = new Intent(this, LevelActivity.class);
// Start our GameActivity class via the Intent
        startActivity(i);
// Now shut this activity down
        finish();
    }
    // If the player hits the back button, quit the app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}