package com.robotlab.expeditions2.activity.main;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.activity.expedition.ExpeditionFragment;
import com.robotlab.expeditions2.base.BaseActivity;
import com.robotlab.expeditions2.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private  MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.browseLinearLayout.setOnClickListener(this);
        binding.expeditionLinearLayout.setOnClickListener(this);
        binding.studentLinearLayout.setOnClickListener(this);
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().add(binding.rightFragmentViw.getId(), ExpeditionFragment.newInstance()).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.browseLinearLayout:
                viewModel.setBrowserClick(binding);
                break;
            case R.id.expeditionLinearLayout:
                viewModel.setExpeditionClick(binding);
                break;
            case R.id.studentLinearLayout:
                viewModel.setStudentClick(binding);
                break;
            case R.id.settingLinearLayout:
                break;
        }
    }
}