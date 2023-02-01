package com.footballtales.footballgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout btnStart;
    TextView tvSettings;
    TextView tvBestResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        tvSettings = findViewById(R.id.tvSettings);
        tvBestResults = findViewById(R.id.tvBestResults);

        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
            }
        });


        final Intent gameIntent = new Intent(this, PuzzleActivity.class);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(gameIntent);  // start game activity
            }
        });
        tvBestResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBestResults();
            }
        });
    }

    public void showBestResults(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Best Results");
        alert.setMessage("High Score");
// Create TextView
        final TextView input = new TextView (this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                input.setText("No Best Results");
                // Do something with value!
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();
    }
}