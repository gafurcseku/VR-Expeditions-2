package com.robotlab.expeditions2.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.robotlab.expeditions2.activity.main.MainActivity;
import com.robotlab.expeditions2.base.ViewModelFactory;

/*
    This is Application base Activity
    Where you define all common action need in activity
 */

public class BaseActivity extends AppCompatActivity {
    protected Context context;
    protected Intent intent;
    protected ViewModelFactory viewModelFactory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        viewModelFactory = new ViewModelFactory(context);
    }

    /*
        This function use to go one activity to another activity.
     */

    protected void setActivityLunch(Class activity, Bundle bundle, Boolean isFinish){
        intent = new Intent(context, activity);
        intent.putExtras(bundle);
        startActivity(intent);
        if(isFinish)
            finish();
    }
}
