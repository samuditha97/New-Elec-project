package com.example.klnresearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;

    //VARIABLES
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView slogan;

    SharedPreferences OnBoarding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.front_logo);
        slogan = findViewById(R.id.slogan);


        image.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                OnBoarding = getSharedPreferences("OnBoarding",MODE_PRIVATE);
                boolean isFirstTime = OnBoarding.getBoolean("firstTime",true);

                if (isFirstTime){
                    SharedPreferences.Editor editor = OnBoarding.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), bluetooth_search.class);
                    startActivity(intent);
                    finish();

                }



            }
        },SPLASH_TIMER);
    }
}