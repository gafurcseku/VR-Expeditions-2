package com.robotlab.expeditions2.activity.categorie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.activity.expedition.ItemClick;
import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentCategoriesBinding;
import com.robotlab.expeditions2.model.Category;

import java.util.List;


public class CategoriesFragment extends BaseFragment {

    private View rootView;
    private FragmentCategoriesBinding binding;
    private CategoriesViewModel viewModel;
    private CategoryAdapter categoryAdapter;
    private OnItemListener onItemListener;

    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this,viewModelFactory).get(CategoriesViewModel.class);
        binding = FragmentCategoriesBinding.inflate(getLayoutInflater());
        onAttachToParentFragment(getParentFragment());
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLiveData();
        return binding.getRoot();
    }

    public void onAttachToParentFragment(Fragment fragment) {
        try {
            onItemListener = (OnItemListener) fragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragment.toString() + " must implement OnPlayerSelectionSetListener");
        }
    }

    private void setLiveData(){
        viewModel.getCategory();
        viewModel.categoryLiveData.observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                if(!categories.isEmpty()){
                    categoryAdapter = new CategoryAdapter(context,categories);
                    binding.categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    binding.categoriesRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    DividerItemDecoration itemDecoration = new DividerItemDecoration(binding.categoriesRecyclerView.getContext(),DividerItemDecoration.VERTICAL);
                    binding.categoriesRecyclerView.addItemDecoration(itemDecoration);
                    binding.categoriesRecyclerView.setAdapter(categoryAdapter);
                    categoryAdapter.setOnItemListenerListener(position -> {
                        onItemListener.OnItemClickListener(position);
                    });
                }
            }
        });
    }

}