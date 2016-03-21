package android.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import fr.u_strasbg.tetramaster.tetramasterclientandroid.R;
import game.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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


        public TetramasterView(Context context) {
            super(context);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    grid[i][j] = new EmptyCell();
                }
            }
        }

        private void draw() {
            if (getHolder().getSurface().isValid()) {
                Canvas c = getHolder().lockCanvas();

                c.drawColor(Color.argb(255, 128, 0, 128));

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        grid[i][j].draw(c, 124 + i * (16 + 196), 124 + j * (16 + 196));
                    }
                }

                if (hand != null) {
                    int x = (1080 - hand.size() * 196 - (hand.size() - 1) * 16) / 2;
                    for (CardCell cell : hand) {
                        cell.draw(c, x, 1080);
                        x += (16 + 196);
                    }
                }

                getHolder().unlockCanvasAndPost(c);
            }
        }

        @Override
        public void run() {
            while (isPlaying) {
                if (!network.msgQueue.isEmpty()) {
                    Msg msg = network.msgQueue.poll();

                    if (msg instanceof SetBlocks) {
                        SetBlocks m = (SetBlocks)msg;
                        List<Pos> l = scala.collection.JavaConversions.seqAsJavaList(m.blocks());
                        for (Pos p : l) {
                            grid[p.x()][p.y()] = new BlockCell();
                        }
                    }
                    else if (msg instanceof GiveHand) {
                        GiveHand m = (GiveHand)msg;
                        hand = new ArrayList<>(5);
                        for (Card c : scala.collection.JavaConversions.seqAsJavaList(m.hand())) {
                            hand.add(new CardCell(c));
                        }
                    }
                }

                draw();
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    draggedCard = hand.remove(0);
                case MotionEvent.ACTION_MOVE:
                    event.getX();
                    event.getY();
                    break;

                case MotionEvent.ACTION_UP:
                    break;
            }
            return super.onTouchEvent(event);
        }
    }

    private class Network implements Runnable {
        Socket socket;
        ObjectOutputStream out;
        ObjectInputStream in;

        public Queue<Msg> msgQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            try {
                socket = new Socket("130.79.206.217", 1024);
                // Il faut respecter l'ordre d'abord OutputStream puis InputStream sinon on se fait Narbouter
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
                        msgQueue.add(m);
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
