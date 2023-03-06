package com.example.yp_trifonova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {


    private AdapterQuotes QouterAdapter;
    private List<QueteMaska> Quotelist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ListView Product = findViewById(R.id.ListQuotes);

        QouterAdapter = new AdapterQuotes(Main.this, Quotelist);
        Product.setAdapter(QouterAdapter);
        new GetQuotes().execute();


    }

    private class GetQuotes extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/quotes");
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();

                BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result=new StringBuilder();
                String line= "";
                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception exception)
            {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
               JSONObject object=new JSONObject(s);
                JSONArray tempArray= object.getJSONArray("data") ;
                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    QueteMaska tempProduct = new QueteMaska(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getString("image"),
                            productJson.getString("description")
                    );
                    Quotelist.add(tempProduct);
                    QouterAdapter.notifyDataSetInvalidated();
                }
            }
            catch (Exception exception)
            {
                Toast.makeText(Main.this, "При выводе данных возникла ошибка", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Menu (View v) {
        Intent intent = new Intent( this, Menu.class);
        startActivity(intent);
    }
    public void Listen (View v) {
        Intent intent = new Intent( this, Listen.class);
        startActivity(intent);
    }

    public void profile (View v) {
        Intent intent = new Intent( this, Profile.class);
        startActivity(intent);
    }
}