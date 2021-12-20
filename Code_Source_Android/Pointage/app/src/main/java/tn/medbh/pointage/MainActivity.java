package tn.medbh.pointage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView forgetAccount;
    private Button btnLogin;
    boolean isConnected=false;
    private ProgressDialog PD;
    private CheckBox session;
    String name="";
    String urlWebService="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences getip = getApplicationContext().getSharedPreferences("Pref", MODE_PRIVATE);
        String ip  =   getip.getString("ipweb", getResources().getString(R.string.ip)) ;
        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        forgetAccount = findViewById(R.id.forget);
        ImageView params= (ImageView) findViewById(R.id.params);
        params.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Parametres.class);
            startActivity(i);
        });


        session = findViewById(R.id.checkBox);
        btnLogin = findViewById(R.id.enregistrer_button);
        forgetAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3= new Intent(MainActivity.this, Reclamation.class);
                i3.putExtra("object","Probleme de connexion au compte");
                i3.putExtra("message","Bonjour Mr/Mme HR Mananger,Je suis en face d'une probleme d'authentification je vous demande de me corriger ce soucis pour continuer a se pointer,Merci cordialement  ");
                startActivity(i3);
            }
        });
        session.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked)
                {
                    isConnected=true;
                }
                else{
                    isConnected=false;
                }

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                name =email;
                try {

                    if (password.length() > 0 && email.length() > 0) {

                        urlWebService="http://"+ip+"/pointage/login.php?email="+email+"&password="+password;
                        Log.e("url",urlWebService);
                        ExecuteUrl execute=new ExecuteUrl();
                        execute.execute();

                    } else {
                //        Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
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
            PD.hide();
            if(s!=null) {
                if (s.equals("Connexion effectue avec success")) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", MODE_PRIVATE);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString("name",name);
                    edit.putBoolean("connected",isConnected);
                    edit.commit();
                    Intent i = new Intent(MainActivity.this, Pointage.class);
                    finish();
                    startActivity(i);
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Nom d'utilisateur ou mot de passe incorrect", Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show();


                }
            }


        }

    }

}