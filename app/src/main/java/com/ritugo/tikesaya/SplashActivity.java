 package com.ritugo.tikesaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

 public class SplashActivity extends AppCompatActivity {

    Animation app_splash,btt;

    ImageView app_logo;

    TextView app_subtitle;

     String USERNAME_KEY = "usernamekey";
     String username_key = "";
     String usernama_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        // load animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        app_logo = findViewById(R.id.app_Logo);

        app_subtitle = findViewById(R.id.app_subtitle);

        app_subtitle.startAnimation(btt);

        app_logo.startAnimation(app_splash);

        getUsernameLocal();

    }

     public void getUsernameLocal(){
         SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
         usernama_key_new = sharedPreferences.getString(username_key, "");

         if (usernama_key_new.isEmpty()){
             // Setting timer untuk 2 detik
             Handler handler = new Handler();
             handler.postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     // Merubah activity ke activity lain
                     Intent gogetstarted = new Intent(SplashActivity.this,GetStartedAct.class);
                     startActivity(gogetstarted);
                     finish();
                 }
             }, 2000);
         }else {
             // Setting timer untuk 2 detik
             Handler handler = new Handler();
             handler.postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     // Merubah activity ke activity lain
                     Intent gogethome = new Intent(SplashActivity.this,HomeAct.class);
                     startActivity(gogethome);
                     finish();
                 }
             }, 2000);
         }
     }

     @Override
     public void onBackPressed() {
         super.onBackPressed();
     }
 }
