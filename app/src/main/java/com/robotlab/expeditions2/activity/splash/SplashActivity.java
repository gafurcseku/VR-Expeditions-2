package com.robotlab.expeditions2.activity.splash;

import android.os.Bundle;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.activity.main.MainActivity;
import com.robotlab.expeditions2.base.BaseActivity;

/*
  * This is Application starting point.
  * Splash screen wait 5 second and go to the main screen.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setActivityLunch(MainActivity.class,null,false);
    }
}