package com.robotlab.expeditions2.activity.categorie;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentCategoriesBinding;
import com.robotlab.expeditions2.model.Category;
import com.robotlab.expeditions2.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

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
                    Paint paint = new Paint();
                    paint.setStrokeWidth(0.5f);
                    paint.setColor(Color.parseColor("#802D3236"));
                    paint.setAntiAlias(true);
                    paint.setPathEffect(new DashPathEffect(new float[]{8.0f, 8.0f}, 0));
                 //   DividerItemDecoration itemDecoration = new DividerItemDecoration(binding.categoriesRecyclerView.getContext(),DividerItemDecoration.VERTICAL);
                    binding.categoriesRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context).paint(paint).build());
                    binding.categoriesRecyclerView.setAdapter(categoryAdapter);
                    categoryAdapter.setOnItemListenerListener(position -> {
                        onItemListener.OnItemClickListener(position);
                    });

                    LinearLayoutManager layoutManager = ((LinearLayoutManager) binding.categoriesRecyclerView.getLayoutManager());
                    int visibleItemCount = layoutManager.findLastVisibleItemPosition();

                    if(visibleItemCount < categories.size()){
                        binding.loadMoreImageView.setVisibility(View.INVISIBLE);
                    }

                }
            }
        });
    }

}