package com.example.progga.anything;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class clubinfo extends AppCompatActivity {
    FloatingActionButton fab_nav,fav_event,fav_update;
    Animation fabopen,fabclose,clockwise,anticlockwise;
    boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clubinfo);
        fab_nav=(FloatingActionButton)findViewById(R.id.fab_nav);
        fav_event=(FloatingActionButton)findViewById(R.id.fab_event);
        fav_update=(FloatingActionButton)findViewById(R.id.fab_update);
        fabopen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fabclose= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        clockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        anticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anti_clockwise);
        fav_update.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fab2_color)));
        fav_event.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fab1_color)));
        fab_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen)
                {
                    fav_event.startAnimation(fabclose);
                    fav_update.startAnimation(fabclose);
                    fab_nav.startAnimation(anticlockwise);
                    fav_event.setClickable(false);
                    fav_update.setClickable(false);
                    isOpen=false;
                }
                else
                {
                    fav_event.startAnimation(fabopen);
                    fav_update.startAnimation(fabopen);
                    fav_update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(clubinfo.this,UpdateclubActivity.class);
                            startActivity(intent);
                        }
                    });
                    fav_event.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(clubinfo.this,eventpost.class);
                            startActivity(intent);
                        }
                    });
                    fab_nav.startAnimation(clockwise);
                    fav_event.setClickable(true);
                    fav_update.setClickable(true);
                    isOpen=true;
                }
            }
        });

    }
}
