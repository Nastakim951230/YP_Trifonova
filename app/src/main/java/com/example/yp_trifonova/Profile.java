package com.example.yp_trifonova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void Menu (View v) {
        Intent intent = new Intent( this, Menu.class);
        startActivity(intent);
    }
    public void Listen (View v) {
        Intent intent = new Intent( this, Listen.class);
        startActivity(intent);
    }

    public void Main (View v) {
        Intent intent = new Intent( this, Main.class);
        startActivity(intent);
    }

    public void login (View v) {
        Intent intent = new Intent( this, Login.class);
        startActivity(intent);
    }
}