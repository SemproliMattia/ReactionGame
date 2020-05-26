package com.example.reflexgames;

import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;


public class MainActivity extends AppCompatActivity {

    public static final int BG_COLOR = Color.parseColor("#FFFF00");
    public static final long MIN_DELAY = 0;
    public static final long MAX_DELAY = 0;
    public int currentTry = 0;

    public CountDownTimer cTimer;
    public int maxMilliSecond=1000;

    public int width;
    public int height;
    public AlertDialog.Builder dlgAlert;
    public  MediaPlayer audio;
    public  MediaPlayer audio2;


    @BindView(R.id.sfondo)
    View sfond;
    @BindView(R.id.titolo)
    View title;
    @BindView(R.id.root)
    View root;
    @BindView(R.id.TouchArea)
    View TouchArea;
    @BindView(R.id.WrongArea)
    View wrongArea;
    @BindView(R.id.WrongArea1)
    View wrongArea1;
    @BindView(R.id.WrongArea2)
    View wrongArea2;
    @BindView(R.id.WrongArea3)
    View wrongArea3;
    @BindView(R.id.targetIu)
    View targetIu;
    @BindView(R.id.wrongTarget)
    View wT;
    @BindView(R.id.wrongTarget1)
    View wT1;
    @BindView(R.id.wrongTarget2)
    View wT2;
    @BindView(R.id.wrongTarget3)
    View wT3;
    @BindView(R.id.msgTv)
    TextView msgTv;
    @BindView(R.id.StartArea)
    View StartArea;

    private boolean gameStarted, displayed;
    private static Handler HANDLER;
    public Random rnd=new Random();
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ButterKnife.bind(this);
        HANDLER = new Handler();

        audio =  MediaPlayer.create(this, R.raw.rightsound);
        audio2 =  MediaPlayer.create(this, R.raw.wrong);

        Display screensize = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        screensize.getSize(size);
        width = size.x;
        height = size.y;

        ll= (LinearLayout) findViewById(R.id.TouchArea);

        dlgAlert  = new AlertDialog.Builder(this);

        cTimer=new CountDownTimer(maxMilliSecond-(currentTry*100),100) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                audio2.start();
                gameStarted=false;

                dlgAlert.setMessage("POINTS: " + currentTry + "\n\nTouch the target to restart the game.");
                dlgAlert.setTitle("YOU LOST, TIME OUT!!!");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                currentTry = 0;

                sfond.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                msgTv.setVisibility(View.VISIBLE);
                root.setBackgroundColor(Color.WHITE);
                TouchArea.setVisibility(View.GONE);
                targetIu.setVisibility(View.GONE);
                wT.setVisibility(View.GONE);
                wrongArea.setVisibility(View.GONE);
                wT1.setVisibility(View.GONE);
                wrongArea1.setVisibility(View.GONE);
                wT2.setVisibility(View.GONE);
                wrongArea2.setVisibility(View.GONE);
                wT3.setVisibility(View.GONE);
                wrongArea3.setVisibility(View.GONE);
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (HANDLER != null)
        {
            HANDLER.removeCallbacksAndMessages(null);
        }
    }

    @OnTouch(R.id.root)
    public boolean manageStart(){
        if(gameStarted)
        {
        }
        else
        {
            gameStarted = true;
            displayed = false;
            HANDLER.postDelayed(bgColorTask, randomLongBetween(MIN_DELAY, MAX_DELAY));
        }
        return false;
    }

    @OnTouch(R.id.TouchArea)
            public boolean manageGame(){
            if(gameStarted)
            {

            if (displayed)
            {
                if(currentTry < 20)
                {
                    audio.start();

                    cTimer.cancel();
                    cTimer.start();

                    int width2 = width / targetIu.getWidth() ;
                    int heigth2 = height / targetIu.getHeight() ;

                    int a = rnd.nextInt(width2);
                    int b = rnd.nextInt(heigth2);

                    int q = rnd.nextInt();

                    if ((q%4) == 0 ) {
                        wT.setBackgroundResource(R.drawable.wrong);
                    } else if ((q%4) == 1 ) {
                        wT.setBackgroundResource(R.drawable.wrong1);
                    } else if ((q%4) == 2 ) {
                        wT.setBackgroundResource(R.drawable.wrong2);
                    } else {
                        wT.setBackgroundResource(R.drawable.wrong3);
                    }

                    ll.setX(a * targetIu.getWidth());
                    ll.setY(b * targetIu.getHeight());

                    int c;
                    int d;

                    do {

                        c = rnd.nextInt(width2);
                        d = rnd.nextInt(heigth2);

                    }while(a == c && b == d);

                    wrongArea.setX(c * wT.getWidth());
                    wrongArea.setY(d * wT.getHeight());

                    wrongArea.setVisibility(View.VISIBLE);
                    wT.setVisibility(View.VISIBLE);
                }
                else if(currentTry < 40)
                {
                    audio.start();

                    cTimer.cancel();
                    cTimer.start();

                    int width2 = width / targetIu.getWidth();
                    int heigth2 = height / targetIu.getHeight();

                    int a = rnd.nextInt(width2);
                    int b = rnd.nextInt(heigth2);

                    int q = rnd.nextInt();

                    if ((q%4) == 0 ) {
                        wT.setBackgroundResource(R.drawable.wrong);
                        wT1.setBackgroundResource(R.drawable.wrong3);
                    } else if ((q%4) == 1 ) {
                        wT.setBackgroundResource(R.drawable.wrong1);
                        wT1.setBackgroundResource(R.drawable.wrong2);
                    } else if ((q%4) == 2 ) {
                        wT.setBackgroundResource(R.drawable.wrong2);
                        wT1.setBackgroundResource(R.drawable.wrong1);
                    } else {
                        wT.setBackgroundResource(R.drawable.wrong3);
                        wT1.setBackgroundResource(R.drawable.wrong);
                    }

                    ll.setX(a * targetIu.getWidth());
                    ll.setY(b * targetIu.getHeight());

                    int c;
                    int d;
                    int c1;
                    int d1;

                    do {

                        c = rnd.nextInt(width2);
                        d = rnd.nextInt(heigth2);
                        c1 = rnd.nextInt(width2);
                        d1 = rnd.nextInt(width2);

                    }while((a == c && b == d) || (a == c1 && b == d1));

                    wrongArea.setX(c * wT.getWidth());
                    wrongArea.setY(d * wT.getHeight());
                    wrongArea1.setX(c1 * wT1.getWidth());
                    wrongArea1.setY(d1 * wT1.getHeight());

                    wrongArea.setVisibility(View.VISIBLE);
                    wT.setVisibility(View.VISIBLE);
                    wrongArea1.setVisibility(View.VISIBLE);
                    wT1.setVisibility(View.VISIBLE);
                }
                else if(currentTry < 60)
                {
                    audio.start();

                    cTimer.cancel();
                    cTimer.start();

                    int width2 = width / targetIu.getWidth();
                    int heigth2 = height / targetIu.getHeight();

                    int a = rnd.nextInt(width2);
                    int b = rnd.nextInt(heigth2);

                    int q = rnd.nextInt();

                    if ((q%4) == 0 ) {
                        wT.setBackgroundResource(R.drawable.wrong);
                        wT1.setBackgroundResource(R.drawable.wrong3);
                        wT2.setBackgroundResource(R.drawable.wrong1);
                    } else if ((q%4) == 1 ) {
                        wT.setBackgroundResource(R.drawable.wrong1);
                        wT1.setBackgroundResource(R.drawable.wrong2);
                        wT2.setBackgroundResource(R.drawable.wrong3);
                    } else if ((q%4) == 2 ) {
                        wT.setBackgroundResource(R.drawable.wrong2);
                        wT1.setBackgroundResource(R.drawable.wrong1);
                        wT2.setBackgroundResource(R.drawable.wrong);
                    } else {
                        wT.setBackgroundResource(R.drawable.wrong3);
                        wT1.setBackgroundResource(R.drawable.wrong);
                        wT2.setBackgroundResource(R.drawable.wrong2);
                    }

                    ll.setX(a * targetIu.getWidth());
                    ll.setY(b * targetIu.getHeight());

                    int c;
                    int d;
                    int c1;
                    int d1;
                    int c2;
                    int d2;

                    do {

                        c = rnd.nextInt(width2);
                        d = rnd.nextInt(heigth2);
                        c1 = rnd.nextInt(width2);
                        d1 = rnd.nextInt(width2);
                        c2 = rnd.nextInt(width2);
                        d2 = rnd.nextInt(width2);

                    }while((a == c && b == d) || (a == c1 && b == d1) || (a == c2 && b == d2));

                    wrongArea.setX(c * wT.getWidth());
                    wrongArea.setY(d * wT.getHeight());
                    wrongArea1.setX(c1 * wT1.getWidth());
                    wrongArea1.setY(d1 * wT1.getHeight());
                    wrongArea2.setX(c2 * wT2.getWidth());
                    wrongArea2.setY(d2 * wT2.getHeight());

                    wrongArea.setVisibility(View.VISIBLE);
                    wT.setVisibility(View.VISIBLE);
                    wrongArea1.setVisibility(View.VISIBLE);
                    wT1.setVisibility(View.VISIBLE);
                    wrongArea2.setVisibility(View.VISIBLE);
                    wT2.setVisibility(View.VISIBLE);
                }
                else if(currentTry > 60)
                {
                    audio.start();

                    cTimer.cancel();
                    cTimer.start();

                    int width2 = width / targetIu.getWidth();
                    int heigth2 = height / targetIu.getHeight();

                    int a = rnd.nextInt(width2);
                    int b = rnd.nextInt(heigth2);

                    int q = rnd.nextInt();

                    if ((q%4) == 0 ) {
                        wT.setBackgroundResource(R.drawable.wrong);
                        wT1.setBackgroundResource(R.drawable.wrong3);
                        wT2.setBackgroundResource(R.drawable.wrong1);
                        wT3.setBackgroundResource(R.drawable.wrong2);
                    } else if ((q%4) == 1 ) {
                        wT.setBackgroundResource(R.drawable.wrong1);
                        wT1.setBackgroundResource(R.drawable.wrong2);
                        wT2.setBackgroundResource(R.drawable.wrong3);
                        wT3.setBackgroundResource(R.drawable.wrong);
                    } else if ((q%4) == 2 ) {
                        wT.setBackgroundResource(R.drawable.wrong2);
                        wT1.setBackgroundResource(R.drawable.wrong1);
                        wT2.setBackgroundResource(R.drawable.wrong);
                        wT3.setBackgroundResource(R.drawable.wrong3);
                    } else {
                        wT.setBackgroundResource(R.drawable.wrong3);
                        wT1.setBackgroundResource(R.drawable.wrong);
                        wT2.setBackgroundResource(R.drawable.wrong2);
                        wT3.setBackgroundResource(R.drawable.wrong1);
                    }

                    ll.setX(a * targetIu.getWidth());
                    ll.setY(b * targetIu.getHeight());

                    int c;
                    int d;
                    int c1;
                    int d1;
                    int c2;
                    int d2;
                    int c3;
                    int d3;

                    do {

                        c = rnd.nextInt(width2);
                        d = rnd.nextInt(heigth2);
                        c1 = rnd.nextInt(width2);
                        d1 = rnd.nextInt(width2);
                        c2 = rnd.nextInt(width2);
                        d2 = rnd.nextInt(width2);
                        c3 = rnd.nextInt(width2);
                        d3 = rnd.nextInt(width2);

                    }while((a == c && b == d) || (a == c1 && b == d1) || (a == c2 && b == d2) || (a == c3 && b == d3));

                    wrongArea.setX(c * wT.getWidth());
                    wrongArea.setY(d * wT.getHeight());
                    wrongArea1.setX(c1 * wT1.getWidth());
                    wrongArea1.setY(d1 * wT1.getHeight());
                    wrongArea2.setX(c2 * wT2.getWidth());
                    wrongArea2.setY(d2 * wT2.getHeight());
                    wrongArea3.setX(c3 * wT3.getWidth());
                    wrongArea3.setY(d3 * wT3.getHeight());

                    wrongArea.setVisibility(View.VISIBLE);
                    wT.setVisibility(View.VISIBLE);
                    wrongArea1.setVisibility(View.VISIBLE);
                    wT1.setVisibility(View.VISIBLE);
                    wrongArea2.setVisibility(View.VISIBLE);
                    wT2.setVisibility(View.VISIBLE);
                    wrongArea3.setVisibility(View.VISIBLE);
                    wT3.setVisibility(View.VISIBLE);
                }
                TouchArea.setVisibility(View.VISIBLE);
                targetIu.setVisibility(View.VISIBLE);

                currentTry++;
            }
            else
                {
                HANDLER.removeCallbacks(bgColorTask);
                gameStarted = false;
            }
        }
        return false;
    }

    @OnTouch(R.id.WrongArea)
    public boolean manageWrongGame(){
        if(gameStarted)
        {
            if (displayed)
            {
                audio2.start();

                gameStarted=false;

                dlgAlert.setMessage("POINTS: " + currentTry + "\n\nTouch the target to restart the game.");
                dlgAlert.setTitle("YOU LOST, YOU HIT WRONG TARGET!!!");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                cTimer.cancel();

                currentTry = 0;

                wT.setVisibility(View.GONE);
                wrongArea.setVisibility(View.GONE);
                wT1.setVisibility(View.GONE);
                wrongArea1.setVisibility(View.GONE);
                wT2.setVisibility(View.GONE);
                wrongArea2.setVisibility(View.GONE);
                wT3.setVisibility(View.GONE);
                wrongArea3.setVisibility(View.GONE);
            }
        }
        return false;
    }

    @OnTouch(R.id.WrongArea1)
    public boolean manageWrongGame1(){
        if(gameStarted)
        {
            if (displayed)
            {
                audio2.start();

                gameStarted=false;

                dlgAlert.setMessage("POINTS: " + currentTry + "\n\nTouch the target to restart the game.");
                dlgAlert.setTitle("YOU LOST, YOU HIT WRONG TARGET!!!");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                cTimer.cancel();

                currentTry = 0;

                wT.setVisibility(View.GONE);
                wrongArea.setVisibility(View.GONE);
                wT1.setVisibility(View.GONE);
                wrongArea1.setVisibility(View.GONE);
                wT2.setVisibility(View.GONE);
                wrongArea2.setVisibility(View.GONE);
                wT3.setVisibility(View.GONE);
                wrongArea3.setVisibility(View.GONE);
            }
        }
        return false;
    }

    @OnTouch(R.id.WrongArea2)
    public boolean manageWrongGame2(){
        if(gameStarted)
        {
            if (displayed)
            {
                audio2.start();

                gameStarted=false;

                dlgAlert.setMessage("POINTS: " + currentTry + "\n\nTouch the target to restart the game.");
                dlgAlert.setTitle("YOU LOST, YOU HIT WRONG TARGET!!!");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                cTimer.cancel();

                currentTry = 0;

                wT.setVisibility(View.GONE);
                wrongArea.setVisibility(View.GONE);
                wT1.setVisibility(View.GONE);
                wrongArea1.setVisibility(View.GONE);
                wT2.setVisibility(View.GONE);
                wrongArea2.setVisibility(View.GONE);
                wT3.setVisibility(View.GONE);
                wrongArea3.setVisibility(View.GONE);
            }
        }
        return false;
    }

    @OnTouch(R.id.WrongArea3)
    public boolean manageWrongGame3(){
        if(gameStarted)
        {
            if (displayed)
            {
                audio2.start();

                gameStarted=false;

                dlgAlert.setMessage("POINTS: " + currentTry + "\n\nTouch the target to restart the game.");
                dlgAlert.setTitle("YOU LOST, YOU HIT WRONG TARGET!!!");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                cTimer.cancel();

                currentTry = 0;

                wT.setVisibility(View.GONE);
                wrongArea.setVisibility(View.GONE);
                wT1.setVisibility(View.GONE);
                wrongArea1.setVisibility(View.GONE);
                wT2.setVisibility(View.GONE);
                wrongArea2.setVisibility(View.GONE);
                wT3.setVisibility(View.GONE);
                wrongArea3.setVisibility(View.GONE);
            }
        }
        return false;
    }

    private Runnable bgColorTask = new Runnable() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                        title.setVisibility(View.GONE);
                        sfond.setVisibility(View.GONE);
                        msgTv.setVisibility(View.GONE);
                        root.setBackgroundResource(R.drawable.sfondoingame);
                        TouchArea.setVisibility(View.VISIBLE);
                        targetIu.setVisibility(View.VISIBLE);
                        wT.setVisibility(View.VISIBLE);
                        displayed = true;
                }
            });
        }
    };

    public static long randomLongBetween(long origin, long bound){
        return origin + (long) (Math.random() * (bound - origin));
    }
}