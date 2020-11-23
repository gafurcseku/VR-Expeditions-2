package com.robotlab.expeditions2.activity.expedition;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentExpeditionBinding;
import com.robotlab.expeditions2.model.Expedition;

import java.util.List;


public class ExpeditionFragment extends BaseFragment {

    private FragmentExpeditionBinding binding;
    private ExpeditionViewModel viewModel;
    private ExpeditionAdapter expeditionAdapter;

    public static ExpeditionFragment newInstance() {
        ExpeditionFragment fragment = new ExpeditionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentExpeditionBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(ExpeditionViewModel.class);
        setLiveData();
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    private void setLiveData(){
        viewModel.getExpeditions();
        viewModel.expeditionLiveData.observe(this, new Observer<List<Expedition>>() {
            @Override
            public void onChanged(List<Expedition> expeditions) {
                if(!expeditions.isEmpty()){
                    expeditionAdapter = new ExpeditionAdapter(context,expeditions);
                    binding.expeditionRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
                    binding.expeditionRecyclerView.setAdapter(expeditionAdapter);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return binding.getRoot();
    }
}