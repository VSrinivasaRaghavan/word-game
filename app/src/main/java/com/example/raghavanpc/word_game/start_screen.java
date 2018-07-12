package com.example.raghavanpc.word_game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class start_screen extends AppCompatActivity {
    ImageView im;
    Intent i;
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        Window window = getWindow();
//        window.setFormat(PixelFormat.RGBA_8888);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        im=(ImageView)findViewById(R.id.im);
        Animation anim=AnimationUtils.loadAnimation(this,R.anim.scale);
        anim.reset();
        im.clearAnimation();
        anim.setDuration(2000);
        im.startAnimation(anim);
        new Timer().schedule(new TimerTask(){
            public void run() {
                i=new Intent(start_screen.this,start_menu.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        }, 2000);
    }
    public void startAnim()
    {
//        Animation anim= AnimationUtils.loadAnimation(this,R.anim.fade_in);
//        LinearLayout ly=(LinearLayout)findViewById(R.id.l);
//        ly.clearAnimation();
//        anim.setDuration(1000);
//        ly.startAnimation(anim);
//        Animation anim=AnimationUtils.loadAnimation(this,R.anim.scale);
//        anim.reset();
//        im.clearAnimation();
//        anim.setDuration(2000);
//        im.startAnimation(anim);
////        anim.reset();

        Toast.makeText(this, "asdasas", Toast.LENGTH_SHORT).show();
    }
}
