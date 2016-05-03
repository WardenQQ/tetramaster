package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Intent;
import android.game.tetramaster;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.facebook.*;

import fr.u_strasbg.tetramaster.tetramasterclientandroid.Geolocalisation.Geolocalisation;

public class Connected extends AppCompatActivity {
    String addr;
    int port;
    boolean isAdmin=true;
    Button btn_envois, btn_grid, btn_geoloc, btn_disconnect, btn_mycollection, btn_myevents;
    EditText txt_connect;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);
        addr = "192.168.0.134";//"130.79.206.217";
        port = 1024;
        btn_disconnect = (Button) findViewById(R.id.btn_disconnect);
        btn_envois     = (Button) findViewById(R.id.btn_envois);
        btn_geoloc     = (Button) findViewById(R.id.btn_geoloc);
        btn_grid       = (Button) findViewById(R.id.buttonGrid);
        btn_mycollection = (Button) findViewById(R.id.btn_collection);
        btn_myevents = (Button) findViewById(R.id.btn_myevents);
        if(isAdmin)
        {
            btn_myevents.setVisibility(View.VISIBLE);
        }
        else
        {
            btn_myevents.setVisibility(View.GONE);
        }

        btn_grid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), tetramaster.class));
            }
        });


        btn_envois.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0){
                Client clt = new Client(addr, port, txt_connect);
                clt.execute();
            }
        });
        btn_geoloc.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0){
                startActivity(new Intent(getApplicationContext(), Geolocalisation.class));
            }
        });
        btn_disconnect.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0){
                //FacebookSdk.clearLoggingBehaviors();
                //Auth.GoogleSignInApi.signOut()
                AccessToken.setCurrentAccessToken(null);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        btn_mycollection.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0){
                startActivity(new Intent(getApplicationContext(), MyCollection.class));
            }
        });

        btn_myevents.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0){
                startActivity(new Intent(getApplicationContext(), MyEvents.class));
            }
        });
    }
}