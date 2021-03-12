package com.example.jishnu.tappydefender;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class LevelActivity extends Activity implements View.OnClickListener {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private SharedPreferences comLevprefs;
    private SharedPreferences.Editor comLeveditor;
    Context context ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        final Button button1 =
                (Button)findViewById(R.id.button1);
        final Button button2 =
                (Button)findViewById(R.id.button2);
        final Button button3 =
                (Button)findViewById(R.id.button3);
        final Button button4 =
                (Button)findViewById(R.id.button4);
        final Button button5 =
                (Button)findViewById(R.id.button5);
        final Button button6 =
                (Button)findViewById(R.id.button6);
        final Button button7 =
                (Button)findViewById(R.id.button7);
        final Button button8 =
                (Button)findViewById(R.id.button8);
        final Button button9 =
                (Button)findViewById(R.id.button9);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }



    public void onClick(View v) {
        prefs = getApplicationContext().getSharedPreferences("Level",
                context.MODE_PRIVATE);
        // Initialize the editor ready
        editor = prefs.edit();
        comLevprefs = getApplicationContext().getSharedPreferences("comLevel",context.MODE_PRIVATE);
        comLeveditor = comLevprefs.edit();
        // must be the Play button.
// Create a new Intent object
        if (v.getId() == R.id.button1) {
            long lev=1;
            editor.putLong("LevelCount", lev);
            editor.commit();
            lev = prefs.getLong("LevelCount", 0);
            long comLevel = comLevprefs.getLong("ComLevCount",1);
            if(comLevel>=lev) {
                //Toast.makeText(getApplicationContext(),"Level "+ lev,Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, GameActivity.class);
// Start our GameActivity class via the Intent
                startActivity(i);
// Now shut this activity down
                finish();
            }
        }
        if (v.getId() == R.id.button2) {
            long lev=2;
            editor.putLong("LevelCount", lev);
            editor.commit();
            lev = prefs.getLong("LevelCount", 0);
            long comLevel = comLevprefs.getLong("ComLevCount",1);
            if(lev>0) {
                //Toast.makeText(getApplicationContext(),"Level "+ lev,Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, GameActivity.class);
// Start our GameActivity class via the Intent
                startActivity(i);
// Now shut this activity down
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(),"Complete Previous Level",Toast.LENGTH_LONG).show();
            }
        }
        if (v.getId() == R.id.button3) {
            long lev=3;
            editor.putLong("LevelCount", lev);
            editor.commit();
            lev = prefs.getLong("LevelCount", 0);
            long comLevel = comLevprefs.getLong("ComLevCount",1);
            if(lev>0) {
                //Toast.makeText(getApplicationContext(),"Level "+ lev,Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, GameActivity.class);
// Start our GameActivity class via the Intent
                startActivity(i);
// Now shut this activity down
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Complete Previous Level ",Toast.LENGTH_LONG).show();
            }
        }
        if (v.getId() == R.id.button4) {
            long lev=4;
            editor.putLong("LevelCount", lev);
            editor.commit();
            lev = prefs.getLong("LevelCount", 0);
            long comLevel = comLevprefs.getLong("ComLevCount",1);
            if(lev>0) {
                //Toast.makeText(getApplicationContext(),"Level "+ lev,Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, GameActivity.class);
// Start our GameActivity class via the Intent
                startActivity(i);
// Now shut this activity down
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Complete Previous Level ",Toast.LENGTH_LONG).show();
            }
        }
        if (v.getId() == R.id.button5) {
            long lev=5;
            editor.putLong("LevelCount", lev);
            editor.commit();
            lev = prefs.getLong("LevelCount", 0);
            long comLevel = comLevprefs.getLong("ComLevCount",1);
            if(lev>0) {
                //Toast.makeText(getApplicationContext(),"Level "+ lev,Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, GameActivity.class);
// Start our GameActivity class via the Intent
                startActivity(i);
// Now shut this activity down
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Complete Previous Level ",Toast.LENGTH_LONG).show();
            }
        }
        if (v.getId() == R.id.button6) {
            long lev=6;
            editor.putLong("LevelCount", lev);
            editor.commit();
            lev = prefs.getLong("LevelCount", 0);
            long comLevel = comLevprefs.getLong("ComLevCount",1);
            if(lev>0) {
                //Toast.makeText(getApplicationContext(),"Level "+ lev,Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, GameActivity.class);
// Start our GameActivity class via the Intent
                startActivity(i);
// Now shut this activity down
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Complete Previous Level ",Toast.LENGTH_LONG).show();
            }
        }
        if (v.getId() == R.id.button7) {
            long lev=7;
            editor.putLong("LevelCount", lev);
            editor.commit();
            lev = prefs.getLong("LevelCount", 0);
            long comLevel = comLevprefs.getLong("ComLevCount",1);
            if(lev>0) {
                //Toast.makeText(getApplicationContext(),"Level "+ lev,Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, GameActivity.class);
// Start our GameActivity class via the Intent
                startActivity(i);
// Now shut this activity down
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Complete Previous Level ",Toast.LENGTH_LONG).show();
            }
        }
        if (v.getId() == R.id.button8) {
            long lev=8;
            editor.putLong("LevelCount", lev);
            editor.commit();
            lev = prefs.getLong("LevelCount", 0);
            long comLevel = comLevprefs.getLong("ComLevCount",1);
            if(lev>0) {
                //Toast.makeText(getApplicationContext(),"Level "+ lev,Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, GameActivity.class);
// Start our GameActivity class via the Intent
                startActivity(i);
// Now shut this activity down
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Complete Previous Level ",Toast.LENGTH_LONG).show();
            }
        }
        if (v.getId() == R.id.button9) {
            long lev=9;
            editor.putLong("LevelCount", lev);
            editor.commit();
            lev = prefs.getLong("LevelCount", 0);
            long comLevel = comLevprefs.getLong("ComLevCount",1);
            if(lev>0) {
                //Toast.makeText(getApplicationContext(),"Level "+ lev,Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, GameActivity.class);
// Start our GameActivity class via the Intent
                startActivity(i);
// Now shut this activity down
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Complete Previous Level ",Toast.LENGTH_LONG).show();
            }
        }
    }
}