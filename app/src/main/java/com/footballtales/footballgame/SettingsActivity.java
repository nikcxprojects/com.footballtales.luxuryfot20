package com.footballtales.footballgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    TextView tvGameTimer;
    TextView tvTimerOn;
    TextView tvMainMenu;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tvGameTimer = findViewById(R.id.tvGameTimer);
        tvTimerOn = findViewById(R.id.tvTimerOnOff);
        tvMainMenu = findViewById(R.id.tvMainMenuSet);

        SharedPreferences spTimerOn = getSharedPreferences("timerOn", 0);


        String timer = spTimerOn.getString("timerOn","");
        if (timer.equals("ON")){

            tvTimerOn.setText("TIMER: ON");

        }else if (timer.equals("OFF")){
            tvTimerOn.setText("TIMER: OFF");
        }

        SharedPreferences sp = getSharedPreferences("gameTimer", 0);
        int tValue = sp.getInt("timeValue",0);

        if (tValue == 0){
            tvGameTimer.setText("GAME TIMER: "+ "5" + "MIN");
        }else {

            tvGameTimer.setText("GAME TIMER: " + tValue + "MIN");
        }

        tvGameTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPicker();
            }
        });

        tvTimerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        tvMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingsActivity.this);
        alertDialog.setTitle("AlertDialog");
        String[] items = {"Timer ON","Timer OFF"};
        SharedPreferences spTimerOn = getSharedPreferences("timerOn", 0);
        SharedPreferences.Editor sedt = spTimerOn.edit();
        int checkedItem = 0 ;
        String timer = spTimerOn.getString("timerOn","");
        if (timer.equals("ON")){

            checkedItem = 0;
            tvTimerOn.setText("TIMER: ON");
            Toast.makeText(this, "On selected", Toast.LENGTH_SHORT).show();
        }else if (timer.equals("OFF")){

            checkedItem = 1;
            tvTimerOn.setText("TIMER: OFF");
            Toast.makeText(this, "Off selected", Toast.LENGTH_SHORT).show();
        }

        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:

                        sedt.putString("timerOn","ON");
                        sedt.commit();

                        Toast.makeText(SettingsActivity.this, "Clicked on ON", Toast.LENGTH_LONG).show();
                        break;
                    case 1:


                        sedt.putString("timerOn","OFF");
                        sedt.commit();
                        Toast.makeText(SettingsActivity.this, "Clicked on OFF", Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }


    public void numberPicker(){
        final NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMaxValue(5); //Maximum value to select
        numberPicker.setMinValue(1); //Minimum value to select



        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(numberPicker);
        builder.setTitle("Number picker");
        builder.setMessage("Choose a value :");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                tvGameTimer.setText("GAME TIMER: "+ numberPicker.getValue() +"MIN");
                SharedPreferences sp = getSharedPreferences("gameTimer", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putInt("timeValue",numberPicker.getValue());
                sedt.commit();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SettingsActivity.this, "You have not selected anything", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }
}