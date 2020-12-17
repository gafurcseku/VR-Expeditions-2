package com.robotlab.expeditions2.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.robotlab.expeditions2.activity.main.MainActivity;
import com.robotlab.expeditions2.base.ViewModelFactory;
import com.robotlab.expeditions2.customDialog.CustomAlertDialog;
import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.database.DatabaseClient;

/*
    This is Application base Activity
    Where you define all common action need in activity
 */

public class BaseActivity extends AppCompatActivity {
    protected Context context;
    protected Intent intent;
    protected ViewModelFactory viewModelFactory;
    protected AppDatabase database;
    protected CustomAlertDialog customAlertDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        database=  DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
        viewModelFactory = new ViewModelFactory(context,database);
        customAlertDialog = new CustomAlertDialog(context);
    }

    /*
        This function use to go one activity to another activity.
     */

    protected void setActivityLunch(Class activity, Bundle bundle, Boolean isFinish){
        intent = new Intent(context, activity);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if(isFinish)
            finish();
    }

    protected void showShortToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
