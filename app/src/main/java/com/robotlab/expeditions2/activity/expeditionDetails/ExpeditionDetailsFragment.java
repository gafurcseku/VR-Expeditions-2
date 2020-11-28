package com.robotlab.expeditions2.activity.expeditionDetails;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentExpeditionDetailsBinding;
import com.robotlab.expeditions2.model.Lesson;

import java.util.ArrayList;
import java.util.List;


public class ExpeditionDetailsFragment extends BaseFragment implements View.OnClickListener {

    private FragmentExpeditionDetailsBinding binding;
    private ExpeditionDetailAdapter adapter;
    private BackClick backClick;

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
        onAttachToParentFragment(requireActivity());
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
        binding.backImageView.setOnClickListener(this);
        return binding.getRoot();
    }

    private List<Lesson> getDummy(){
        List<Lesson> lessonList = new ArrayList<>();
        for (int i= 0 ; i<50;i++){
            lessonList.add(new Lesson("Etymology","Ready to broadcast",null));
        }
        return  lessonList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backImageView:
                showLongToast("Click");
                backClick.setDone(true);
                break;
            default:
                break;
        }
    }

    public void onAttachToParentFragment(Activity activity) {
        try {
            backClick = (BackClick) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnPlayerSelectionSetListener");
        }
    }
}