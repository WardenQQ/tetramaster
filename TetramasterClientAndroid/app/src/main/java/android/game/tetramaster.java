package android.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Toast;
import fr.u_strasbg.tetramaster.tetramasterclientandroid.R;
import game.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class tetramaster extends AppCompatActivity {
    TetramasterView view;
    Thread updateThread;

    Network network;
    Thread networkThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new TetramasterView(this);
        setContentView(view);

        network = new Network();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tetramaster, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        view.isPlaying = true;

        updateThread = new Thread(view);
        updateThread.start();

        networkThread = new Thread(network);
        networkThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (view.isPlaying) {
            try {
                updateThread.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class TetramasterView extends SurfaceView implements Runnable {
        boolean isPlaying;
        Socket socket;
        ObjectOutputStream out;
        ObjectInputStream in;

        Drawable[][] grid = new Drawable[4][4];
        List<CardCell> hand;

        CardCell draggedCard;
        int draggedX, draggedY;

        GridEntity gridEntity = new GridEntity(124, 124);
        HandEntity handEntity = new HandEntity(1080 / 2, 1080);

        State state = new StateWaitTurn();

        Pos chosenPos = null;
        int chosenCard = -1;


        public TetramasterView(Context context) {
            super(context);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    grid[i][j] = new EmptyCell(124 + i * (16 + 196), 124 + j * (16 + 196));
                }
            }
        }

        private void draw() {
            if (getHolder().getSurface().isValid()) {
                Canvas c = getHolder().lockCanvas();

                c.drawColor(Color.argb(255, 128, 0, 128));

                gridEntity.draw(c);
                handEntity.draw(c);

                if(draggedCard!=null){
                    draggedCard.draw(c,draggedX,draggedY);
                }

                getHolder().unlockCanvasAndPost(c);
            }
        }

        @Override
        public void run() {
            while (isPlaying) {
                if (!network.receiveQueue.isEmpty()) {
                    Msg msg = network.receiveQueue.poll();

                    if (msg instanceof SetBlocks) {
                        SetBlocks m = (SetBlocks)msg;
                        List<Pos> l = scala.collection.JavaConversions.seqAsJavaList(m.blocks());
                        gridEntity.addBlocks(l);
                        for (Pos p : l) {
                            grid[p.x()][p.y()] = new BlockCell(124 + p.x() * (16 + 196), 124 + p.y() * (16 + 196));
                        }
                    }
                    else if (msg instanceof GiveHand) {
                        GiveHand m = (GiveHand)msg;
                        hand = Collections.synchronizedList(new ArrayList<CardCell>(5));
                        for (Card c : scala.collection.JavaConversions.seqAsJavaList(m.hand())) {
                            hand.add(new CardCell(0, 0, c, 0));
                            handEntity.add(c);
                        }
                    }
                    else if (msg instanceof StartTurn) {
                        state = new StatePlayCard();
                    }
                    else if (msg instanceof CmdFightCard) {
                        state = new StateFightCard();
                    }
                }

                draw();
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (true) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        int h = handEntity.intersect((int)event.getX(), (int)event.getY());
                        if (h != -1) {
                            handEntity.select(h);
                        }

                    case MotionEvent.ACTION_MOVE:
                        if (handEntity.hasSelected()) {
                            handEntity.setSelectedPosition((int)event.getX(), (int)event.getY());
                        }
                        return true;

                    case MotionEvent.ACTION_UP:
                        if (handEntity.hasSelected()) {
                            Pos p = gridEntity.intersect((int)event.getX(), (int)event.getY());
                            if (p != null) {
                                gridEntity.addCardCell(handEntity.removeSelect(), p);
                            }
                            else {
                                handEntity.cancelSelect();
                            }
                        }
                        return true;
                }
            }
            return super.onTouchEvent(event);
        }
    }

    private class Network implements Runnable {
        Socket socket;
        ObjectOutputStream out;
        ObjectInputStream in;

        public Queue<Msg> receiveQueue = new ConcurrentLinkedQueue<>();
        public Queue<Msg> sendQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            try {
                socket = new Socket("130.79.206.217", 1024);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            while (socket.isConnected()) {
                try {
                    Object obj = in.readObject();

                    if (obj instanceof Msg) {
                        Msg m = (Msg)obj;
                        receiveQueue.add(m);
                    }
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
