package com.robotlab.expeditions2.activity.MainFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.activity.MyExpedition.MyExpeditionFragment;
import com.robotlab.expeditions2.activity.expedition.ExpeditionFragment;
import com.robotlab.expeditions2.activity.main.MainViewModel;
import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentMainBinding;


public class MainFragment extends BaseFragment implements View.OnClickListener , TextView.OnEditorActionListener {
    private static String IS_FAVORITE;
    private Boolean isFavorite = false ;
    private FragmentMainBinding binding;
    private MainViewModel viewModel;

    public static MainFragment newInstance(Boolean isFavorite) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_FAVORITE, isFavorite);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        binding.searchEditText.setFocusable(false);
        if (getArguments() != null) {
            isFavorite = getArguments().getBoolean(IS_FAVORITE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel.class);
        binding.searchEditText.setOnClickListener(this);
        binding.searchEditText.setOnEditorActionListener(this);
        binding.searchButton.setOnClickListener(this);

        setUpLiveData();

        if(isFavorite)
            getChildFragmentManager().beginTransaction().replace(binding.rightFragmentViw.getId(), MyExpeditionFragment.newInstance()).commit();
        else
            getChildFragmentManager().beginTransaction().replace(binding.rightFragmentViw.getId(), ExpeditionFragment.newInstance()).commit();
        return binding.getRoot();
    }

    private void setUpLiveData(){
        viewModel.getSearchLiveData().observe(requireActivity(), item -> {
            if(item.getValid()){
                showLongToast("Call Search Rest Api");
            }else if(!item.getValid()){
                showLongToast(item.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchEditText:
                binding.searchEditText.setFocusableInTouchMode(true);
                break;
            case R.id.searchButton:
                viewModel.searchText(binding.searchEditText.getText().toString().trim());
                closeKeyboard();
                break;
            default:
                break;
        }
    }

    public void showKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
    }

    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            viewModel.searchText(binding.searchEditText.getText().toString().trim());
            closeKeyboard();
            return true;
        }
        return false;
    }
}