package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OneEvent extends AppCompatActivity {
    Button btn_return, btn_modify, btn_delete;
    EditText name, begin, end;
    Grid grid_infos;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneevent);
        btn_return = (Button) findViewById(R.id.btn_return);
        btn_modify = (Button) findViewById(R.id.btn_modify);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        name = (EditText) findViewById(R.id.eventNameText);
        begin = (EditText) findViewById(R.id.eventBeginText);
        end = (EditText) findViewById(R.id.eventEndText);
        Bundle parametersReceived = getIntent().getExtras();
        String eventName=parametersReceived.getString("name");
        String eventId=parametersReceived.getString("id");
        String eventBegin=parametersReceived.getString("dateDebut");
        String eventEnd=parametersReceived.getString("dateFin");
        //ICI RECUPERER LES DONNEES DE LA BASE DE DONNEES
        //SELECT NOM, DATEDEBUT, DATEFIN FROM EVENT WHERE ID=eventId
        //ET ENSUITE STOCKER DANS LES VARIABLES
        //eventName, eventBegin, eventEnd=resultQuery

        name.setText(eventName);
        begin.setText(eventBegin);
        end.setText(eventEnd);
        btn_return.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyEvents.class));
            }
        });

        btn_modify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //Mettre les nouvelles valeurs des champs en BDD
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //Supprimer l'evenement en BDD
            }
        });
    }
}
