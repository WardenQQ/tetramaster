package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import fr.u_strasbg.tetramaster.shared.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Ricardo on 02/12/2015.
 */
public class Grid extends AppCompatActivity {
    GridView grid;
    ListView list;

    GridAdapter gAdapter;
    ListAdapter lAdapter;
    AlertDialog dialog;

    int x;
    int y;
    public static final int nbRows = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity);
        grid = (GridView) findViewById(R.id.gameGrid);
        list = (ListView) findViewById(R.id.deckList);
        gAdapter=new GridAdapter(this);
        grid.setAdapter(gAdapter);
        lAdapter=new ListAdapter(this);
        list.setAdapter(lAdapter);
        GameLoop gameLoop = new GameLoop("130.79.206.217", 1024);
        gameLoop.execute();
    }


    private class GameLoop extends AsyncTask<Void, Integer, Void> {
        private final int stateSetDeck = 0;
        private final int stateSetBlockCells = 1;
        private final int stateSetClickableCells = 2;
        private final int statePlaceCard = 3;

        boolean choseCardFromDeck = false;
        int chosenId;

        ObjectOutputStream out;
        ObjectInputStream in;

        String dstAddress;
        int dstPort;
        Card[] deck;
        int[][] cellPositions;
        Card newCard;
        int x;
        int y;
        int team;


        GameLoop(String addr, int port) {
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected void onPreExecute() {
            list.setClickable(false);
            grid.setClickable(false);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    if (list.isClickable()) {
                        chosenId = (int)lAdapter.getItemId(position);
                        choseCardFromDeck = true;

                        grid.setClickable(true);
                    }

                }
            });

            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    if (grid.isClickable()) {
                        if (position<nbRows) {
                            x=position;
                            y=0;
                        } else {
                            x=position%nbRows;
                            y=position/nbRows;
                        }

                        boolean isClickable = false;
                        for (int pos[]:
                                cellPositions) {
                            if (pos[0] == x && pos[1] == y) {
                                isClickable = true;
                                break;
                            }
                        }

                        if (isClickable) {
                            try {
                                out.writeObject(new MessagePlaceCard(x, y, chosenId));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            choseCardFromDeck = false;
                            grid.setClickable(false);
                            list.setClickable(false);
                        }

                    }


                }
            });
        }

        @Override
        protected Void doInBackground(Void... params) {
            Socket socket = null;
            try {
                socket = new Socket(dstAddress, dstPort);

                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                AlertDialog.Builder build = new AlertDialog.Builder(getApplicationContext());
                build.setMessage("En attente d'un autre joueur");
                dialog = build.create();
                dialog.getWindow().setLayout(400,600);
                dialog.show();

                while (true) {
                    Message msg = (Message)in.readObject();

                    if (msg instanceof MessageSetDeckList) {
                        MessageSetDeckList unpack = (MessageSetDeckList)msg;
                        deck = unpack.deck;
                        team = unpack.team;
                        publishProgress(stateSetDeck);
                        if(dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    } else if(msg instanceof MessageSendBlockCells) {
                        MessageSendBlockCells unpack = (MessageSendBlockCells)msg;
                        cellPositions = unpack.blockCells;
                        publishProgress(stateSetBlockCells);
                    } else if (msg instanceof MessageSendReplaceableCells) {
                        MessageSendReplaceableCells unpack = (MessageSendReplaceableCells)msg;
                        cellPositions = unpack.replaceableCells;
                        publishProgress(stateSetClickableCells);
                    } else if (msg instanceof MessagePlaceCard) {
                        MessagePlaceCard unpack = (MessagePlaceCard)msg;
                        newCard = unpack.card;
                        x = unpack.x;
                        y = unpack.y;
                        team = unpack.team;
                        publishProgress(statePlaceCard);
                    }
                }
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            switch (values[0]) {
                case stateSetDeck:
                    lAdapter.setDeck(deck);
                    lAdapter.setTeam(team);
                    lAdapter.notifyDataSetChanged();
                    break;

                case stateSetBlockCells:
                    gAdapter.addBlockCells(cellPositions);
                    gAdapter.notifyDataSetChanged();
                    break;

                case stateSetClickableCells:
                    list.setClickable(true);
                    break;

                case statePlaceCard:
                    gAdapter.placeCardCell(newCard, x, y, team);
                    gAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}
