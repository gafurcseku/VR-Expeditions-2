package com.robotlab.expeditions2.activity.deviceList;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentDeviceListBinding;


public class StudentListFragment extends BaseFragment {
    private FragmentDeviceListBinding binding;
    private StudentFragmentViewModel viewModel;
    private StudentAdapter adapter;

    public static StudentListFragment newInstance() {
        StudentListFragment fragment = new StudentListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDeviceListBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(StudentFragmentViewModel.class);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding.studentRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
        viewModel.getStudent();
        viewModel.getStudentLiveData().observe(getViewLifecycleOwner(), item -> {
            adapter = new StudentAdapter(context,item);
            binding.studentRecyclerView.setAdapter(adapter);
        } );
        return binding.getRoot();
    }
}