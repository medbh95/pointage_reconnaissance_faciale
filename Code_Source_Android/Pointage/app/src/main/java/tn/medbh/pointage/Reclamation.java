package tn.medbh.pointage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Reclamation extends AppCompatActivity {
    private EditText mail;
    private EditText object;
    private EditText message;
    private Button envoyer;

    String urlWebService="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);
        mail = findViewById(R.id.mail);
        object = findViewById(R.id.object);
        message = findViewById(R.id.message);
        envoyer = findViewById(R.id.send);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", MODE_PRIVATE);
        String name =   pref.getString("name", "") ;
        Intent i =getIntent();
        if(i != null) {
            String obj = i.getStringExtra("object");
            String msg = i.getStringExtra("message");
           // Toast.makeText(getApplication(), obj + msg, Toast.LENGTH_SHORT).show();
            object.setText(obj);
            message.setText(msg);

        }
        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailText = mail.getText().toString();
                String objectText = object.getText().toString();
                String messageText = message.getText().toString();

                try {

                    if (mailText.length() > 0) {
                        SharedPreferences getip = getApplicationContext().getSharedPreferences("Pref", MODE_PRIVATE);
                        String ip  =   getip.getString("ipweb", getResources().getString(R.string.ip)) ;
                        urlWebService="http://"+ip+"/pointage/sendMail.php?email="+mailText+"&name="+name+"&object="+objectText+"&message="+messageText;
                        Log.e("url",urlWebService);
                        ExecuteUrl execute=new ExecuteUrl();
                        execute.execute();


                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Veuillez remplir tous les champs", Snackbar.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    class ExecuteUrl extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(urlWebService);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json).append("\n");
                }
                return sb.toString().trim();
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            boolean reponse=s.contains("Message sent!");
            if(s!=null) {
                if (reponse) {
                    Toast.makeText(getApplication(), "Email envoyé", Toast.LENGTH_SHORT).show();
                    //    Snackbar.make(findViewById(android.R.id.content), "Email envoyé", Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.green)).show();
                        finish();
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Echec d'envoie de mail", Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show();

                }
            }


        }

    }
}
