package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Connected extends AppCompatActivity {
    String addr;
    int port;

    Button btn_envois, btn_grid, btn_geoloc;
    EditText txt_connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);
        addr = "192.168.0.134";//"130.79.206.217";
        port = 1024;

        btn_envois     = (Button) findViewById(R.id.btn_envois);
        btn_geoloc     = (Button) findViewById(R.id.btn_geoloc);
        txt_connect    = (EditText) findViewById(R.id.txt_connect);
        btn_grid       = (Button) findViewById(R.id.buttonGrid);

        btn_grid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Grid.class));
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
    }
}