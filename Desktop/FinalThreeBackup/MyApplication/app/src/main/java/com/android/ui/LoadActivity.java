package com.android.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class LoadActivity extends AppCompatActivity {
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        getSupportActionBar().hide();

        img=(ImageView)findViewById(R.id.img);

        AlphaAnimation   alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(3000);
        alpha.setFillAfter(true);
        img.setAnimation(alpha);

        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(LoadActivity.this,LogginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
