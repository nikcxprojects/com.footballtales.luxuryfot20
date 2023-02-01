package com.footballtales.footballgame;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity {
    TextView tvPP;
    TextView tvAccept;
    TextView tvDecline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        tvPP = findViewById(R.id.tvPP);
        tvAccept = findViewById(R.id.tvAcceptPP);
        tvDecline = findViewById(R.id.tvDeclinePP);

        tvPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://docs.google.com/document/d/10Re2PqZm6U408quP3d0FumMFNHn5i8WMnn5-epq5fv4/edit?usp=sharing";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                Log.e("abc", "onClick: "+"privacy" );
            }
        });

        tvAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        tvDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }
}