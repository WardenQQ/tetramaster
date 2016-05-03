package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.ListPopupWindow;
import fr.u_strasbg.tetramaster.shared.Card;
import fr.u_strasbg.tetramaster.shared.Deck;

import java.util.*;

public class AddCardToDeck extends AppCompatActivity {
    ListAdapter lAdapter;
    static ListView list;
    Card[] collection;
    static List<Card> listTmp;
    static Activity activity;
    PopupWindow popUp, popUpDeckName;
    public static ListPopupWindow popUpList;
    Button btn_return;
    static Button btn_createDeck, btn_viewdeck;
    boolean[] arrows = {true,false,true,true,true,false,true,false};
    private static final String TAG = "MyDebug";
    Card card = new Card(arrows,2,3,4,"Magic", "Cathedralol");
    Card card2 = new Card(arrows,5,1,7,"Physical", "Cathedralol");
    Card card3 = new Card(arrows,10,1,12,"Toto", "Pole APILOL");
    Card card4 = new Card(arrows,1,13,14,"TOTO", "In hell");
    private int popUpWidth, popUpHeight, cardWidth, cardHeight,windowWidth, windowHeight;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_addcardtodeck);
        btn_return = (Button) findViewById(R.id.btn_return);
        btn_viewdeck = (Button) findViewById(R.id.btn_viewDeck);
        btn_createDeck = (Button) findViewById(R.id.btn_createDeck);
        btn_createDeck.setEnabled(false);
        list = (ListView) findViewById(R.id.list_cards);
        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        windowWidth = size.x;
        windowHeight = size.y;
        cardWidth = MyCollection.min(windowWidth,windowHeight)/3;
        cardHeight = MyCollection.min(windowWidth,windowHeight)/3;
        popUpWidth = MyCollection.min(windowWidth,windowHeight)-200;
        popUpHeight = MyCollection.min(windowWidth,windowHeight)-200;
        lAdapter=new ListAdapter(this);
        lAdapter.setCardWidth(cardWidth);
        lAdapter.setCardHeight(cardHeight);
        lAdapter.setWindowWidth(windowWidth);
        lAdapter.setViewButtonAddCard(true);
        lAdapter.setViewCardName(true);

        Log.d(TAG, "window Width:" + windowWidth + " window height : "+ windowHeight);

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
        collection[10]=card2;
        collection[11]=card2;
        collection[12]=card2;
        collection[13]=card2;
        collection[14]=card2;
        collection[15]=card3;
        collection[16]=card4;
        collection[17]=card2;
        collection[18]=card2;
        collection[19]=card2;
        Deck deck = new Deck(1,collection,"test");
        lAdapter.setDeck(deck);
        lAdapter.setTeam(0);
        list.setAdapter(lAdapter);
        btn_viewdeck.setEnabled(false);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v,
                                           int position, long id) {
                popUp=new PopupWindow(new CardViewFullScreen(getApplicationContext(),collection[position],popUpWidth,popUpHeight),popUpWidth,popUpHeight+100);
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
        btn_return.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                listTmp.clear();
                startActivity(new Intent(getApplicationContext(), MyDecks.class));
            }
        });

        btn_viewdeck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Card[] listNewDeck = new Card[listTmp.size()];
                for(int i=0 ; i < listTmp.size(); i++)
                {
                    listNewDeck[i] = listTmp.get(i);
                }
                Deck deck = new Deck(1,listNewDeck,"test");
                ListAdapter listAdapter = new ListAdapter(getApplicationContext());
                listAdapter.setDeck(deck);
                listAdapter.setTeam(0);
                listAdapter.setCardWidth(cardWidth);
                listAdapter.setCardHeight(cardHeight);
                listAdapter.setWindowWidth(windowWidth);
                listAdapter.setViewButtonAddCard(false);
                listAdapter.setViewCardName(true);
                popUpList=new ListPopupWindow(getApplicationContext(),null, android.support.v7.widget.ListPopupWindow.MATCH_PARENT);
                popUpList.setAdapter(listAdapter);
                popUpList.setWidth(popUpWidth);
                popUpList.setHeight(popUpHeight);
                popUpList.setAnchorView(view);
                popUpList.setDropDownGravity(Gravity.TOP|Gravity.START);
                popUpList.setModal(true);
                popUpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
                            popUpList.dismiss();
                    }
                });

                if (!popUpList.isShowing()) {
                    popUpList.show();
                }
                else
                {
                    popUpList.dismiss();
                }
            }
        });
        LayoutInflater layoutInflater =
                (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popUpView = layoutInflater.inflate(R.layout.popup_namedeck, null);
        btn_createDeck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //add deck to db
                popUpDeckName = new PopupWindow(popUpView);
                popUpDeckName.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                popUpDeckName.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
                popUpDeckName.showAtLocation(popUpView, Gravity.CENTER, 0, 0);
                popUpDeckName.setOutsideTouchable(false);
                popUpDeckName.setFocusable(true);
                popUpDeckName.update();
                Button popUpAddButton = (Button) popUpView.findViewById(R.id.buttonAddDeckName);
                final EditText editTextNameDeck = (EditText) popUpView.findViewById(R.id.editTextNameDeck);
                popUpAddButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(activity,"Le deck "+ editTextNameDeck.getText() +" a bien été ajouté",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MyDecks.class));
                    }
                });
            }
        });
    }

    public static void addToTmpDeck(Card card)
    {
        if(listTmp==null)
        {
            listTmp = new ArrayList<Card>();
        }
        if (listTmp.size()==5)
        {
            Toast.makeText(activity,"Nombre maximal de cartes atteint",Toast.LENGTH_SHORT).show();
        }
        if (listTmp.size()<5) {
            if (listTmp.size()==4)
            {
                btn_createDeck.setEnabled(true);
            }
            btn_viewdeck.setEnabled(true);
            listTmp.add(card);
        }
    }
}
