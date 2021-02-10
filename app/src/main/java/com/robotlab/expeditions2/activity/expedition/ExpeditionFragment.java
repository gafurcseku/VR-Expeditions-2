package com.robotlab.expeditions2.activity.expedition;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
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


public class ExpeditionFragment extends BaseFragment implements ItemClick {

    private FragmentExpeditionBinding binding;
    private ExpeditionViewModel viewModel;
    private ExpeditionAdapter expeditionAdapter;
    private ItemClick itemClick;
    private int CategoryId;

    public static ExpeditionFragment newInstance(int CategoryId) {
        ExpeditionFragment fragment = new ExpeditionFragment();
                Bundle args = new Bundle();
        args.putInt("CATEGORY_ID", CategoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentExpeditionBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(ExpeditionViewModel.class);
        if (getArguments() != null) {
            CategoryId = getArguments().getInt("CATEGORY_ID");
            binding.categoriesNameTextView.setText(viewModel.getCategoryName(CategoryId));
        }
        onAttachToParentFragment(getParentFragment());
        setLiveData();
    }

    /**
     * Init Live data View Model here
     */

    private void setLiveData() {
        viewModel.getExpeditions(CategoryId);
        viewModel.expeditionLiveData.observe(this, expeditions -> {
            if (!expeditions.isEmpty()) {
                expeditionAdapter = new ExpeditionAdapter(context, expeditions, ExpeditionFragment.this);
                binding.expeditionRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                binding.expeditionRecyclerView.setNestedScrollingEnabled(false);
                binding.expeditionRecyclerView.setItemViewCacheSize(20);
                binding.expeditionRecyclerView.setDrawingCacheEnabled(true);
                binding.expeditionRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                binding.expeditionRecyclerView.setAdapter(expeditionAdapter);
                binding.fastScroller.attachRecyclerView(binding.expeditionRecyclerView);
            }
        });
    }

    /**
     * This function use category id to get Name of the category and display at top
     */

    public void setCategory(int CategoryId){
        viewModel.getExpeditions(CategoryId);
        binding.categoriesNameTextView.setText(viewModel.getCategoryName(CategoryId));
    }

    /**
     * This function use to perform search base on use text write
     */

    public void searchText(String text){
        if(expeditionAdapter!=null){
            expeditionAdapter.searchText(text);
        }
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