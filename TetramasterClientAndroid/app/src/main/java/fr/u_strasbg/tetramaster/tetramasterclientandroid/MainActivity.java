package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyDebug";
    private static final int RC_SIGN_IN = 9001;
    private static final String url =  "http://tetramaster.u-strasbg.fr/connectBDD.php";
    private boolean bddCorrectLogin = true;
    Button btn_connect, btn_createLogin;
    EditText txt_pseudo, txt_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /*addr = "192.168.0.134";//"130.79.206.217";
        port = 1024;*/

        txt_pseudo = (EditText) findViewById(R.id.editTextPseudo);
        txt_pass = (EditText) findViewById(R.id.editTextPass);

        btn_connect = (Button) findViewById(R.id.connectButton);
        btn_createLogin = (Button) findViewById(R.id.btn_createLogin);

        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);

        btn_connect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //effectuer demande login bdd ici
                if(!bddCorrectLogin ) {
                    Toast.makeText(getApplicationContext(), "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(getApplicationContext(), Connected.class));
                }
            }
        });

        btn_createLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreateLogin.class));
            }
        });
    }

}
