package com.robotlab.expeditions2.activity.deviceList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentDeviceListBinding;


public class DeviceListFragment extends BaseFragment {
    private FragmentDeviceListBinding binding;
    private DeviceFragmentViewModel viewModel;

    public static DeviceListFragment newInstance() {
        DeviceListFragment fragment = new DeviceListFragment();
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
        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(DeviceFragmentViewModel.class);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return binding.getRoot();
    }
}