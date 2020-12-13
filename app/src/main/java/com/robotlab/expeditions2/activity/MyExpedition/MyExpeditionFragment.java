package com.robotlab.expeditions2.activity.MyExpedition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.robotlab.expeditions2.activity.expedition.ItemClick;
import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentMyExpeditionBinding;
import com.robotlab.expeditions2.model.Expedition;

import java.util.List;


public class MyExpeditionFragment extends BaseFragment implements ItemClick {

    private FragmentMyExpeditionBinding binding;
    private MyExpeditionViewModel viewModel;
    private MyExpeditionAdapter expeditionAdapter;
    private ItemClick itemClick;

    public static MyExpeditionFragment newInstance() {
        MyExpeditionFragment fragment = new MyExpeditionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMyExpeditionBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MyExpeditionViewModel.class);
        setLiveData();
        onAttachToParentFragment(getParentFragment());
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
                    expeditionAdapter = new MyExpeditionAdapter(context,expeditions,MyExpeditionFragment.this);
                    binding.myExpeditionRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    binding.myExpeditionRecyclerView.setAdapter(expeditionAdapter);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void OnClick(Expedition expedition,Boolean isMyExpedition) {
        itemClick.OnClick(expedition,isMyExpedition);
    }

    public void onAttachToParentFragment(Fragment fragment) {
        try {
            itemClick = (ItemClick) fragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragment.toString() + " must implement OnPlayerSelectionSetListener");
        }
    }
}