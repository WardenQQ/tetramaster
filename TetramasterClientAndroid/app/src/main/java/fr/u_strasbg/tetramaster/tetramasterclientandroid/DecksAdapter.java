package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import fr.u_strasbg.tetramaster.shared.Card;
import fr.u_strasbg.tetramaster.shared.Deck;

import java.util.ArrayList;

public class DecksAdapter extends BaseAdapter {
    private ArrayList<Deck> listDecks = new ArrayList<Deck>();
    private Context context;



    public DecksAdapter(ArrayList<Deck> listDecks, Context context) {
        this.listDecks = listDecks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listDecks.size();
    }

    @Override
    public Deck getItem(int pos) {
        return listDecks.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return listDecks.get(pos).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.deckitem, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(listDecks.get(position).getName());

        //Handle buttons and add onClickListeners
        Button btn_view = (Button)view.findViewById(R.id.btn_viewDeck);
        Button btn_delete = (Button)view.findViewById(R.id.btn_deleteDeck);

        btn_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("Voulez vous vraiment supprimer ce deck ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                listDecks.remove(position);
                                notifyDataSetChanged();
                            }})
                        .setNegativeButton("Non", null).show();
            }
        });
        btn_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle parameters = new Bundle();
                parameters.putSerializable("deck",listDecks.get(position));
                Intent goToOneDeck = new Intent(context,OneDeck.class);
                goToOneDeck.putExtras(parameters);
                context.startActivity(goToOneDeck);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
