package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SelectPseudo extends AppCompatActivity {

    EditText chosenPseudo;
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pseudo);
        Bundle parametersReceived = getIntent().getExtras();

        String name=parametersReceived.getString("name");
        String id=parametersReceived.getString("id");
        btn_send = (Button) findViewById(R.id.buttonSend);
        chosenPseudo = (EditText) findViewById(R.id.chosenPseudo);
        if(name!=""){
            chosenPseudo.setText(name);
        }
        btn_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                //send infos to database
                //start connected activity

                startActivity(new Intent(getApplicationContext(), Connected.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_pseudo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
