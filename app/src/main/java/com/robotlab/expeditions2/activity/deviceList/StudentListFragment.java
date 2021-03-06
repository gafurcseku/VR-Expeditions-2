package com.robotlab.expeditions2.activity.deviceList;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

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
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDeviceListBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(StudentFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel.getStudent();
        viewModel.getStudentLiveData().observe(getViewLifecycleOwner(), item -> {
            adapter = new StudentAdapter(context,item);
            binding.studentRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
            binding.studentRecyclerView.setAdapter(adapter);
            binding.fastScroller.attachRecyclerView(binding.studentRecyclerView);
        } );
        return binding.getRoot();
    }
}