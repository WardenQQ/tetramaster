package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import fr.u_strasbg.tetramaster.shared.Card;
import fr.u_strasbg.tetramaster.shared.Deck;

import java.util.ArrayList;


public class MyDecks extends AppCompatActivity{

    private Button btn_return, btn_addDeck;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_mydecks);
        btn_return = (Button) findViewById(R.id.btn_return);
        btn_addDeck = (Button) findViewById(R.id.btn_newdeck);
        ListView listView = (ListView) findViewById(R.id.list_decks);

        boolean[] arrows = {true,false,true,true,true,false,true,false};
        Card card = new Card(arrows,2,3,4,"Magic", "Cathedrale de Strasbourg");
        Card[] listCards = new Card[5];
        listCards[0] = card;
        listCards[1] = card;
        listCards[2] = card;
        listCards[3] = card;
        listCards[4] = card;
        Deck testDeck = new Deck(1,listCards,"Mon Deck 1");
        Deck testDeck2 = new Deck(2,listCards,"Mon Deck 2");
        //recuperation des decks depuis la bdd
        ArrayList<Deck> listDecks = new ArrayList<Deck>();
        listDecks.add(testDeck);
        listDecks.add(testDeck2);

        DecksAdapter adapter = new DecksAdapter(listDecks, this);
        listView.setAdapter(adapter);


        btn_return.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyCollection.class));
            }
        });

        btn_addDeck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddCardToDeck.class));
            }
        });

    }
}
