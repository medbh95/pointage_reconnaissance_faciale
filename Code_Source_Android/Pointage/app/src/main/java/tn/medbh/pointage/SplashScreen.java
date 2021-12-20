package tn.medbh.pointage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", MODE_PRIVATE);
        boolean connected =   pref.getBoolean("connected", false) ;
        String name =   pref.getString("name", "") ;
        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                if(connected && name !=""){
                    Intent i = new Intent(SplashScreen.this, Pointage.class);

                    startActivity(i);

                }else{
                    Intent i1 = new Intent(SplashScreen.this, MainActivity.class);

                    startActivity(i1);

                }

                // close this activity

                finish();

            }

        }, 2000);
    }
}