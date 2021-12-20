package tn.medbh.pointage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

public class Pointage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage);
        Button pe= (Button) findViewById(R.id.p_entree);
        Button ps= (Button) findViewById(R.id.p_sortie);
        ImageView param= (ImageView) findViewById(R.id.param);
        Button reclamation= (Button) findViewById(R.id.reclamer);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", MODE_PRIVATE);
        String name =   pref.getString("name", "") ;
        String pi =   pref.getString("ipRpi", "") ;
        Snackbar.make(findViewById(android.R.id.content), "Bienvenue "+name , Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.green)).show();


        param.setOnClickListener(v -> {
            Intent i = new Intent(Pointage.this, Parametres.class);
            startActivity(i);
        });


        pe.setOnClickListener(v -> {
            Snackbar.make(findViewById(android.R.id.content), "Lancement de processus de pointage ...", Snackbar.LENGTH_LONG).show();

            new AsyncTask<Integer, Void, Void>(){
                @Override
                protected Void doInBackground(Integer... params) {
                    try {
                        executeRemoteCommand("pi", "0000",pi, 22,"export DISPLAY=:0 && cd Desktop && python pointage.py entree");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute(1);
            ps.setEnabled(true);
            pe.setEnabled(false);
        });
        ps.setOnClickListener(v -> {
            Snackbar.make(findViewById(android.R.id.content), "Lancement de processus de pointage ...", Snackbar.LENGTH_LONG).show();

            new AsyncTask<Integer, Void, Void>(){
                @Override
                protected Void doInBackground(Integer... params) {
                    try {
                        executeRemoteCommand("pi", "0000",pi, 22,"export DISPLAY=:0 && cd Desktop && python pointage.py sortie");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute(1);
            pe.setEnabled(true);
            ps.setEnabled(false);
        });
        reclamation.setOnClickListener(v -> {
            Intent i = new Intent(Pointage.this, Reclamation.class);
            startActivity(i);
        });
    }
    public static String executeRemoteCommand(String username,String password,String hostname,int port,String cmd)
            throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, hostname, port);
        session.setPassword(password);

        // Avoid asking for key confirmation
        Properties prop = new Properties();
        prop.put("StrictHostKeyChecking", "no");
        session.setConfig(prop);

        session.connect();

        // SSH Channel
        ChannelExec channelssh = (ChannelExec)
                session.openChannel("exec");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        channelssh.setOutputStream(baos);

        // Execute command
        channelssh.setCommand(cmd);
        channelssh.connect();
        try{Thread.sleep(1000);}catch(Exception ee){}
        String out = new String(baos.toByteArray());
        Log.e("ssh Command",out);
        channelssh.disconnect();

        return baos.toString();
    }

}