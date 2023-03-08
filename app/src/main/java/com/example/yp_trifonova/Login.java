package com.example.yp_trifonova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {


    final static String EmailUser = "Email";
    final static String PasswordUser = "Password";
    SharedPreferences sPref;
    public static MaskaUser Users;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        getData();

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
                avtorizatsia();
            }
            else
            {
                Toast.makeText(Login.this, "Поле для Email обязательно должно содержать символ '@'", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void avtorizatsia()
    {
        String email_str = String.valueOf(email.getText());
        String password_str = String.valueOf(password.getText());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mskko2021.mad.hakta.pro/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiRetrofit retrofitAPI = retrofit.create(ApiRetrofit.class);

        UserModel modelSendUser = new UserModel(email_str, password_str);
        Call<MaskaUser> call = retrofitAPI.createUser(modelSendUser);
        call.enqueue(new Callback<MaskaUser>() {
            @Override
            public void onResponse(Call<MaskaUser> call, Response<MaskaUser> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Login.this, "Пользователь с такой почтой и паролем не найден", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.body() != null)
                {
                    if(response.body().getToken() != null)
                    {
                        saveData();
                        Users = response.body();
                        Intent intent = new Intent(Login.this, Main.class);
                        Bundle b = new Bundle();
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<MaskaUser> call, Throwable t) {
                Toast.makeText(Login.this, "При авторизации возникла ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public  void saveData()
    {
        // получаем введенные данные

        sPref=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed=sPref.edit();
        ed.putString(EmailUser,email.getText().toString());
        ed.putString(PasswordUser,password.getText().toString());
       ed.commit();

    }

    public void getData()
    {
        // получаем сохраненные данные
        sPref=getPreferences(MODE_PRIVATE);
        String emailUser=sPref.getString(EmailUser,"");
        String passwordUser=sPref.getString(PasswordUser,"");
        email.setText(emailUser);
        password.setText(passwordUser);

    }



    public void Register(View v) {
        Intent intent = new Intent( this, Register.class);
        startActivity(intent);
    }
}