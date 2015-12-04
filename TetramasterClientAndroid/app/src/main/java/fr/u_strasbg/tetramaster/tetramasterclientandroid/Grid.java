package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Created by Ricardo on 02/12/2015.
 */
public class Grid extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity);
        GridView grid= (GridView) findViewById(R.id.gameGrid);
        ListView list= (ListView) findViewById(R.id.deckList);
        grid.setAdapter(new GridAdapter(this));
        list.setAdapter(new ListAdapter(this));

        //grid.addView(new CardView(this));
    }

}
