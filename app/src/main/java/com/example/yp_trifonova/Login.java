package com.example.yp_trifonova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
    }
    public void Profil(View v) {
        if(email.getText().toString().equals("") || password.getText().toString().equals(""))
        {
            Toast.makeText(Login.this, "Обязательные поля не заполнены!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Pattern pattern = Pattern.compile("@", Pattern.CASE_INSENSITIVE);
            Matcher m = pattern.matcher(email.getText().toString());
            boolean b = m.find();
            if(b)
            {
                Intent intent = new Intent( this, Main.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(Login.this, "Поле для Email обязательно должно содержать символ '@'", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void Register(View v) {
        Intent intent = new Intent( this, Register.class);
        startActivity(intent);
    }
}