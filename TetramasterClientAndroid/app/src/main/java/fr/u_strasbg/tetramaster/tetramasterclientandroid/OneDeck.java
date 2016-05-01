package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import fr.u_strasbg.tetramaster.shared.Deck;

public class OneDeck extends AppCompatActivity {

    Button btn_return;
    ListAdapter lAdapter;
    ListView list;
    TextView deckName;
    PopupWindow popUp;

    private int popUpWidth, popUpHeight, cardWidth, cardHeight,windowWidth, windowHeight;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle parametersReceived = getIntent().getExtras();
        final Deck deck = (Deck) parametersReceived.getSerializable("deck");
        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        windowWidth = size.x;
        windowHeight = size.y;
        cardWidth = MyCollection.min(windowWidth,windowHeight)/3;
        cardHeight = MyCollection.min(windowWidth,windowHeight)/3;
        popUpWidth = MyCollection.min(windowWidth,windowHeight)-200;
        popUpHeight = MyCollection.min(windowWidth,windowHeight)-200;
        setContentView(R.layout.activity_onedeck);
        btn_return = (Button) findViewById(R.id.btn_return);
        list = (ListView) findViewById(R.id.list_cards);
        deckName = (TextView) findViewById(R.id.deckName);
        deckName.setText(deckName.getText()+deck.getName());
        deckName.setGravity(Gravity.CENTER);
        lAdapter=new ListAdapter(this);
        lAdapter.setCardWidth(cardWidth);
        lAdapter.setCardHeight(cardHeight);
        lAdapter.setWindowWidth(windowWidth);
        lAdapter.setDeck(deck);
        lAdapter.setTeam(0);
        lAdapter.setViewCardName(true);
        lAdapter.setViewButtonAddCard(false);
        list.setAdapter(lAdapter);
        btn_return.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyDecks.class));
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v,
                                           int position, long id) {
                popUp=new PopupWindow(new CardViewFullScreen(getApplicationContext(),deck.getListCards()[position],popUpWidth,popUpHeight),popUpWidth,popUpHeight+100);
                popUp.setOutsideTouchable(true);
                popUp.setTouchable(true);
                popUp.setBackgroundDrawable(new ColorDrawable());
                if(!popUp.isShowing())
                {
                    popUp.showAtLocation(v, Gravity.CENTER, 0, 0);
                    return true;
                }
                else
                {
                    popUp.dismiss();
                    return false;
                }
            }
        });
    }
}
