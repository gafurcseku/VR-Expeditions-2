package com.robotlab.expeditions2.activity.main;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.activity.MainFragment.MainFragment;
import com.robotlab.expeditions2.activity.expedition.ItemClick;
import com.robotlab.expeditions2.activity.expeditionDetails.BackClick;
import com.robotlab.expeditions2.activity.expeditionDetails.ExpeditionDetailsFragment;
import com.robotlab.expeditions2.base.BaseActivity;
import com.robotlab.expeditions2.databinding.ActivityMainBinding;
import com.robotlab.expeditions2.model.Expedition;
import com.robotlab.expeditions2.utility.DummyData;

public class MainActivity extends BaseActivity implements View.OnClickListener , ItemClick , BackClick {
    private ActivityMainBinding binding;
    private  MainViewModel viewModel;
    private MainFragment mainFragment;
    private Boolean isDetails = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.browseLinearLayout.setOnClickListener(this);
        binding.expeditionLinearLayout.setOnClickListener(this);
        binding.studentLinearLayout.setOnClickListener(this);
        binding.settingLinearLayout.setOnClickListener(this);
        setContentView(binding.getRoot());
        setVersionAndBuild();
        getSupportFragmentManager().beginTransaction().add(binding.detailsFragment.getId(), MainFragment.newInstance(false),MainFragment.class.getSimpleName()).addToBackStack(MainFragment.class.getSimpleName()).commit();


        /// For Dummy data , please remove it
        DummyData.saveDummyLesson(database);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.browseLinearLayout:
                removeOtherBack();
                viewModel.setBrowserClick(binding);
                mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.class.getSimpleName());
                mainFragment.showExpedition();
                isDetails = true;
                break;
            case R.id.expeditionLinearLayout:
                removeOtherBack();
                viewModel.setExpeditionClick(binding);
                mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.class.getSimpleName());
                mainFragment.showMyExpedition();
                isDetails = true;
                break;
            case R.id.studentLinearLayout:
                removeOtherBack();
                viewModel.setStudentClick(binding);
                mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.class.getSimpleName());
                mainFragment.showStudent();
                isDetails = true;
                break;
            case R.id.settingLinearLayout:
                removeOtherBack();
                viewModel.setSettingClick(binding);
                mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.class.getSimpleName());
                mainFragment.showSetting();
                isDetails = true;
                break;
        }
    }

    @Override
    public void OnClick(Expedition expedition,Boolean isMyExpedition) {
        if(isDetails){
            getSupportFragmentManager().beginTransaction().add(binding.detailsFragment.getId(), ExpeditionDetailsFragment.newInstance(expedition,isMyExpedition),ExpeditionDetailsFragment.class.getSimpleName()).addToBackStack(ExpeditionDetailsFragment.class.getSimpleName()).commit();
            isDetails = false;
        }
    }

    private void removeOtherBack(){
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    public void setDone(Boolean isComplete) {
        isDetails = true;
        if(isComplete){
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            }
        }
    }

    private void setVersionAndBuild(){
        int versionCode = 1;
        String versionName = "1.0";
        binding.versionTextView.setText("RobotLAB VR v"+versionCode+"(Build "+versionCode+")");

    }
}