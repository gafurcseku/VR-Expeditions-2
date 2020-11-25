package com.robotlab.expeditions2.activity.expeditionDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentExpeditionDetailsBinding;
import com.robotlab.expeditions2.model.Lesson;

import java.util.ArrayList;
import java.util.List;


public class ExpeditionDetailsFragment extends BaseFragment {

    private FragmentExpeditionDetailsBinding binding;
    private ExpeditionDetailAdapter adapter;

    public static ExpeditionDetailsFragment newInstance() {
        ExpeditionDetailsFragment fragment = new ExpeditionDetailsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentExpeditionDetailsBinding.inflate(getLayoutInflater());
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new ExpeditionDetailAdapter(context,getDummy());
        binding.lessonRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        binding.lessonRecyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

    private List<Lesson> getDummy(){
        List<Lesson> lessonList = new ArrayList<>();
        for (int i= 0 ; i<50;i++){
            lessonList.add(new Lesson("Etymology","Ready to broadcast",null));
        }
        return  lessonList;
    }
}