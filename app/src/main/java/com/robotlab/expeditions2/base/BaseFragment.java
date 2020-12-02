package com.robotlab.expeditions2.base;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.database.DatabaseClient;

public class BaseFragment extends Fragment {
    protected Context context;
    protected ViewModelFactory viewModelFactory;
    protected AppDatabase database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        database=  DatabaseClient.getInstance(context).getAppDatabase();
        viewModelFactory = new ViewModelFactory(context,database);
    }

    protected void showShortToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
