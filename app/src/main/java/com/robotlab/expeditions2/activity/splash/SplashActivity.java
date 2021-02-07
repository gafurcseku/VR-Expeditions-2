package com.robotlab.expeditions2.activity.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.activity.main.MainActivity;
import com.robotlab.expeditions2.base.BaseActivity;
import com.robotlab.expeditions2.databinding.ActivitySplashBinding;
import com.robotlab.expeditions2.utility.DummyData;

/*
  * This is Application starting point.
  * Splash screen wait 5 second and go to the main screen.
 */

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /// For Dummy data , please remove it
        DummyData.saveDummyLesson(database);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                setActivityLunch(MainActivity.class,null,true);
            }
        },3000);

    }
}