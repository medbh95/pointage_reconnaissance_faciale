package tn.medbh.pointage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class Parametres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        Button logout= (Button) findViewById(R.id.logout);
        ImageView editIp= (ImageView) findViewById(R.id.editIp);
        ImageView editIp2= (ImageView) findViewById(R.id.editIp2);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", MODE_PRIVATE);
        boolean connected =   pref.getBoolean("connected", false) ;
        String name =   pref.getString("name", "") ;
        if(name != ""){
            logout.setVisibility(View.VISIBLE);
        }
        String ipRPI =   pref.getString("ipRpi", "") ;
        String ipx =   pref.getString("ipweb", getResources().getString(R.string.ip)) ;
        TextView name2 =(TextView) findViewById(R.id.name2);
        EditText ipr =(EditText) findViewById(R.id.ipR);
        EditText ipxedit =(EditText) findViewById(R.id.ipx);
        name2.setText(name);
        ipr.setText(ipRPI);
        ipxedit.setText(ipx);
        logout.setOnClickListener(v -> {
            SharedPreferences pref1 = getApplicationContext().getSharedPreferences("Pref", MODE_PRIVATE);
            SharedPreferences.Editor edit = pref1.edit();
            edit.putString("name","");
            edit.putBoolean("connected",false);
            edit.commit();
            Intent i = new Intent(Parametres.this, MainActivity.class);
            finish();
            startActivity(i);
        });
        editIp.setOnClickListener(v -> {
            String value =ipr.getText().toString();
            SharedPreferences pref1 = getApplicationContext().getSharedPreferences("Pref", MODE_PRIVATE);
            SharedPreferences.Editor edit = pref1.edit();
            edit.putString("ipRpi",value);
            edit.commit();
            Snackbar.make(findViewById(android.R.id.content), "Modification enregistré", Snackbar.LENGTH_LONG).show();


        });
        editIp2.setOnClickListener(v -> {
            String value =ipxedit.getText().toString();
            SharedPreferences pref1 = getApplicationContext().getSharedPreferences("Pref", MODE_PRIVATE);
            SharedPreferences.Editor edit = pref1.edit();
            edit.putString("ipweb",value);
            edit.commit();
            Snackbar.make(findViewById(android.R.id.content), "Modification enregistré", Snackbar.LENGTH_LONG).show();


        });
    }
}