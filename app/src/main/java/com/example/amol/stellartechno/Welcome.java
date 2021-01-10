package com.example.amol.stellartechno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    private TextView Welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        String email = getIntent().getStringExtra("NAME");
        Welcome = findViewById(R.id.welcome);
        Welcome.setText(email);
    }
}