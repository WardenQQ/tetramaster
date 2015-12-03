package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String addr;
    int port;
    Button btn_envois;
    Button btn_geoloc;
    EditText txt_connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addr = "130.79.206.217";
        port = 1024;

        btn_envois     = (Button) findViewById(R.id.btn_envois);
        btn_geoloc     = (Button) findViewById(R.id.btn_geoloc);
        txt_connect    = (EditText) findViewById(R.id.txt_connect);

        btn_envois.setOnClickListener(new OnClickListener(){
            @Override
                    public void onClick(View arg0){
                        Client clt = new Client(addr, port, txt_connect);
                        clt.execute();
            }
        });
        btn_geoloc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Geolocalisation.class));
            }
        });
    }
}