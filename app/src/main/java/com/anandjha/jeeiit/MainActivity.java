package com.anandjha.jeeiit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.StatusBarManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imgLogo;
    private TextView textLogo, textSlogan;

    private static int SPLASH_SCREEN = 1000; //set time


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        imgLogo=findViewById(R.id.imgLogo);
        textLogo=findViewById(R.id.textLogo);
        textSlogan=findViewById(R.id.textSlogan);
        Animation top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        Animation bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        imgLogo.setAnimation(top_animation);
        textLogo.setAnimation(bottom_animation);
        textSlogan.setAnimation(bottom_animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,UserLogin.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}
