package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import fr.u_strasbg.tetramaster.shared.Card;

public class MyCollection extends AppCompatActivity {
    ListAdapter lAdapter;
    ListView list;
    Card[] collection;
    boolean[] arrows = {true,false,true,false,true,false,true,false};
    Card card = new Card(arrows,2,3,4,"Magic");
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
        list = (ListView) findViewById(R.id.list_cards);
        lAdapter=new ListAdapter(this);
        list.setAdapter(lAdapter);
        collection = new Card[20];
        collection[0]=card;
        collection[1]=card;
        collection[2]=card;
        collection[3]=card;
        collection[4]=card;
        collection[5]=card;
        collection[6]=card;
        collection[7]=card;
        collection[8]=card;
        collection[9]=card;
        collection[10]=card;
        collection[11]=card;
        collection[12]=card;
        collection[13]=card;
        collection[14]=card;
        collection[15]=card;
        collection[16]=card;
        collection[17]=card;
        collection[18]=card;
        collection[19]=card;
        lAdapter.setDeck(collection);
        lAdapter.setTeam(0);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                new CardViewFullScreen(getApplicationContext(),collection[position]);
            }
        });
    }
}
