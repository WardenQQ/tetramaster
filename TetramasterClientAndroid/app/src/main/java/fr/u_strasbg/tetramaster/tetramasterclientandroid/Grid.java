package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Ricardo on 02/12/2015.
 */
public class Grid extends AppCompatActivity {
    int x;
    int y;
    public static final int nbRows = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity);
        GridView grid= (GridView) findViewById(R.id.gameGrid);
        ListView list= (ListView) findViewById(R.id.deckList);
        GridAdapter gAdapter=new GridAdapter(this);
        grid.setAdapter(gAdapter);
        ListAdapter lAdapter=new ListAdapter(this);
        list.setAdapter(lAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position<nbRows)
                {
                    x=position;
                    y=0;
                }
                else
                {
                    x=position%nbRows;
                    y=position/nbRows;
                }
                Toast.makeText(getApplicationContext(), "x : " + x+" y : "+y, Toast.LENGTH_SHORT).show();

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();

            }
        });

        //grid.addView(new CardView(this));
    }

}
