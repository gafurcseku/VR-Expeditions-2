package com.robotlab.expeditions2.activity.main;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.activity.MainFragment.MainFragment;
import com.robotlab.expeditions2.activity.MyExpedition.MyExpeditionFragment;
import com.robotlab.expeditions2.activity.deviceList.StudentListFragment;
import com.robotlab.expeditions2.activity.expedition.ExpeditionFragment;
import com.robotlab.expeditions2.activity.expedition.ItemClick;
import com.robotlab.expeditions2.activity.expeditionDetails.BackClick;
import com.robotlab.expeditions2.activity.expeditionDetails.ExpeditionDetailsFragment;
import com.robotlab.expeditions2.base.BaseActivity;
import com.robotlab.expeditions2.databinding.ActivityMainBinding;
import com.robotlab.expeditions2.model.Expedition;

public class MainActivity extends BaseActivity implements View.OnClickListener , ItemClick , BackClick {
    private ActivityMainBinding binding;
    private  MainViewModel viewModel;
    private MainFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.browseLinearLayout.setOnClickListener(this);
        binding.expeditionLinearLayout.setOnClickListener(this);
        binding.studentLinearLayout.setOnClickListener(this);
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().add(binding.detailsFragment.getId(), MainFragment.newInstance(false),MainFragment.class.getSimpleName()).addToBackStack(MainFragment.class.getSimpleName()).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.browseLinearLayout:
                viewModel.setBrowserClick(binding);
                //getSupportFragmentManager().beginTransaction().replace(binding.detailsFragment.getId(), MainFragment.newInstance(false)).commit();
                mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.class.getSimpleName());
                mainFragment.showExpedition();
                break;
            case R.id.expeditionLinearLayout:
                viewModel.setExpeditionClick(binding);
//                getSupportFragmentManager().beginTransaction().replace(binding.detailsFragment.getId(), MainFragment.newInstance(true),"YES").commit();
                mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.class.getSimpleName());
                mainFragment.showMyExpedition();
                break;
            case R.id.studentLinearLayout:
                viewModel.setStudentClick(binding);

                //getSupportFragmentManager().beginTransaction().replace(binding.detailsFragment.getId(), StudentListFragment.newInstance()).commit();
                mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.class.getSimpleName());
                mainFragment.showStudent();
                break;
            case R.id.settingLinearLayout:
                break;
        }
    }

    @Override
    public void OnClick(Expedition expedition) {
        showShortToast("YE");
        getSupportFragmentManager().beginTransaction().add(binding.detailsFragment.getId(), ExpeditionDetailsFragment.newInstance(),ExpeditionDetailsFragment.class.getSimpleName()).addToBackStack(ExpeditionDetailsFragment.class.getSimpleName()).commit();
    }

    @Override
    public void setDone(Boolean isComplete) {
        if(isComplete){
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            }
        }

    }
}