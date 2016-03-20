package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MyEvents extends AppCompatActivity {
    ListView list ;
    Button btn_return;
    ArrayList<String> showName;
    ArrayList<String> eventsName;
    ArrayList<Integer> eventsIds;
    ArrayList<String> eventsBeginDate, eventsEndDate;
    ArrayAdapter<String> eventsNameAdapter;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevents);
        btn_return = (Button) findViewById(R.id.btn_return);
        populateListView();
        registerClickCallback();
        btn_return.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Connected.class));
            }
        });

    }

    private void populateListView(){
        list = (ListView) findViewById(R.id.listEvents);
        eventsName = new ArrayList<String>();
        eventsBeginDate = new ArrayList<String>();
        eventsEndDate = new ArrayList<String>();
        eventsIds = new ArrayList<Integer>();
        showName = new ArrayList<String>();
        //----------------------A SUPPRIMER, UNIQUEMENT POUR TESTS------------------------------------//
        eventsName.add("Coucou");
        eventsName.add("toto");
        eventsBeginDate.add("01/02/2011");
        eventsBeginDate.add("01/02/2015");
        eventsEndDate.add("01/03/2011");
        eventsEndDate.add("01/03/2015");
        eventsIds.add(2);
        eventsIds.add(7);
        //--------------------------------------------------------------------------------------------//
        //ICI, RECUPERER DONNEES DEPUIS LA BDD
        //SELECT * FROM EVENTS WHERE USER_ID = ...
        //FOREACH RESULT ...
        for(int i=0;i<eventsName.size();i++) {
            showName.add(eventsName.get(i) + " Débute le " + eventsBeginDate.get(i) + ", finit le " + eventsEndDate.get(i));
        }
        eventsNameAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listeventitems, showName) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);
                // Get the Layout Parameters for ListView Current Item View
                ViewGroup.LayoutParams params = view.getLayoutParams();
                // Set the height of the Item View
                params.height = 40;
                view.setLayoutParams(params);
                return view;
            }
        };
        list.setAdapter(eventsNameAdapter);
    }

    private void registerClickCallback(){
        list = (ListView) findViewById(R.id.listEvents);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                Bundle parameters = new Bundle();
                parameters.putString("id",eventsIds.get(position).toString());
                //----------------------A SUPPRIMER, UNIQUEMENT POUR TESTS------------------------------------//
                parameters.putString("name",eventsName.get(position).toString());
                parameters.putString("dateDebut",eventsBeginDate.get(position).toString());
                parameters.putString("dateFin",eventsEndDate.get(position).toString());
                //--------------------------------------------------------------------------------------------//
                Intent goToOneEvent = new Intent(getApplicationContext(),OneEvent.class);
                goToOneEvent.putExtras(parameters);
                startActivity(goToOneEvent);
            }
        });
    }
}
