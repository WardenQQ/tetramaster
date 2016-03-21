package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.*;
import fr.u_strasbg.tetramaster.shared.Card;

public class MyCollection extends AppCompatActivity {
    ListAdapter lAdapter;
    ListView list;
    Card[] collection;
    PopupWindow popUp;
    Button btn_return, btn_newdeck;
    boolean clicked=false;
    boolean[] arrows = {true,false,true,true,true,false,true,false};
    private static final String TAG = "MyDebug";
    Card card = new Card(arrows,2,3,4,"Magic");
    Card card2 = new Card(arrows,5,1,7,"Physical");
    Card card3 = new Card(arrows,10,1,12,"Toto");
    Card card4 = new Card(arrows,1,13,14,"TOTO");
    private int popUpWidth, popUpHeight, cardWidth, cardHeight,windowWidth, windowHeight;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
        btn_return = (Button) findViewById(R.id.btn_return);
        btn_newdeck = (Button) findViewById(R.id.btn_newdeck);
        list = (ListView) findViewById(R.id.list_cards);
        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        windowWidth = size.x;
        windowHeight = size.y;
        cardWidth = min(windowWidth,windowHeight)/3;
        cardHeight = min(windowWidth,windowHeight)/3;
        popUpWidth = min(windowWidth,windowHeight)-200;
        popUpHeight = min(windowWidth,windowHeight)-200;
        lAdapter=new ListAdapter(this);
        lAdapter.setCardWidth(cardWidth);
        lAdapter.setCardHeight(cardHeight);
        list.setAdapter(lAdapter);
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
        lAdapter.setDeck(collection);
        lAdapter.setTeam(0);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                popUp=new PopupWindow(new CardViewFullScreen(getApplicationContext(),collection[position],popUpWidth,popUpHeight),popUpWidth,popUpHeight);
                popUp.setOutsideTouchable(true);
                popUp.setTouchable(true);
                popUp.setBackgroundDrawable(new ColorDrawable());
                if(!clicked)
                {
                    popUp.showAtLocation(v, Gravity.CENTER, 0, 0);
                    clicked=true;
                }
                else
                {
                    popUp.dismiss();
                    clicked=false;
                }

                /*if(popUp.isOutsideTouchable()&&popUp.isShowing())
                {
                    popUp.dismiss();
                }*/
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Connected.class));
            }
        });
    }

    public int min(int width, int height) {
        if(width<height){
            return width;
        }
        else{
            return height;
        }
    }
}
