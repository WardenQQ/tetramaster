package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
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
    private static final String TAG = "MyDebug";

    int x;
    int y;
    public static final int nbRows = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity);
        grid = (GridView) findViewById(R.id.gameGrid);
        list = (ListView) findViewById(R.id.deckList);
        gAdapter = new GridAdapter(this);
        grid.setAdapter(gAdapter);
        lAdapter = new ListAdapter(this);
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

        private android.widget.RelativeLayout.LayoutParams layoutParams;

        @Override
        protected void onPreExecute() {
            list.setClickable(false);
            grid.setClickable(false);
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View v,
                                               int position, long id) {
                    Log.d(TAG, "Im here :");
                    if (list.isClickable()) {
                        chosenId = (int) lAdapter.getItemId(position);
                        Log.d(TAG, "Im here 2 :");
                        choseCardFromDeck = true;
                        //v.setVisibility(View.INVISIBLE);
                        grid.setClickable(true);
                        View toDrag = lAdapter.getView(position, null, null);
                        myDragEventListener mDragListen = new myDragEventListener();
                        toDrag.setOnDragListener(mDragListen);
                        Log.d(TAG, "Im here 3 :");
                        ClipData data = ClipData.newPlainText("", "");
                        Log.d(TAG, "Im here 4 :");
                        //View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(list);
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(toDrag);
                        Log.d(TAG, "Im here 5 :");
                        //list.startDrag(data, shadowBuilder, v, 0);
                        toDrag.startDrag(data, shadowBuilder, toDrag.getRootView(), 0);
                        Log.d(TAG, "Im here 6 :");
                    }
                    return true;
                }
            });
            //list.setOnDragListener(new View.OnDragListener() {


            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    if (grid.isClickable()) {
                        if (position < nbRows) {
                            x = position;
                            y = 0;
                        } else {
                            x = position % nbRows;
                            y = position / nbRows;
                        }

                        boolean isClickable = false;
                        for (int pos[] :
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
               /* AlertDialog.Builder build = new AlertDialog.Builder(getApplicationContext());
                build.setMessage("En attente d'un autre joueur");
                dialog = build.create();
                dialog.getWindow().setLayout(400,600);
                dialog.show();*/

                while (true) {
                    Message msg = (Message) in.readObject();

                    if (msg instanceof MessageSetDeckList) {
                        MessageSetDeckList unpack = (MessageSetDeckList) msg;
                        deck = unpack.deck;
                        team = unpack.team;
                        publishProgress(stateSetDeck);
                        /*if(dialog.isShowing()) {
                            dialog.dismiss();
                        }*/
                    } else if (msg instanceof MessageSendBlockCells) {
                        MessageSendBlockCells unpack = (MessageSendBlockCells) msg;
                        cellPositions = unpack.blockCells;
                        publishProgress(stateSetBlockCells);
                    } else if (msg instanceof MessageSendReplaceableCells) {
                        MessageSendReplaceableCells unpack = (MessageSendReplaceableCells) msg;
                        cellPositions = unpack.replaceableCells;
                        publishProgress(stateSetClickableCells);
                    } else if (msg instanceof MessagePlaceCard) {
                        MessagePlaceCard unpack = (MessagePlaceCard) msg;
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
                    //lAdapter.setDeck(deck);
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

    protected class myDragEventListener implements View.OnDragListener {
        public boolean onDrag(View v, DragEvent event) {
            Log.d(TAG, "Im here 7 :");
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.d(TAG, "Im here 8 :");
                    //layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
                    Log.d(TAG, "Im here 9 :");
                    Log.d(TAG, "Action is DragEvent.ACTION_DRAG_STARTED");

                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                        // As an example of what your application might do,
                        // applies a blue color tint to the View to indicate that it can accept
                        // data.
                        v.setBackgroundColor(Color.RED);

                        // Invalidate the view to force a redraw in the new tint
                        v.invalidate();

                        // returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false. During the current drag and drop operation, this View will
                    // not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;


                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d(TAG, "Action is DragEvent.ACTION_DRAG_ENTERED");
                    int x_cord = (int) event.getX();
                    int y_cord = (int) event.getY();
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d(TAG, "Action is DragEvent.ACTION_DRAG_EXITED");
                    x_cord = (int) event.getX();
                    y_cord = (int) event.getY();
                    v.setBackgroundColor(Color.BLUE);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d(TAG, "Action is DragEvent.ACTION_DRAG_ENDED");

                    return true;
                //break;

                case DragEvent.ACTION_DROP:
                    Log.d(TAG, "ACTION_DROP event");
                    v.invalidate();
                    return true;
                default:
                    break;
            }
            return true;
        }
    }
}

