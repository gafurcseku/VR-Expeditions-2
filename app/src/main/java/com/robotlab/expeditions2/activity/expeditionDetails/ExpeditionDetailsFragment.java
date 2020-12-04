package com.robotlab.expeditions2.activity.expeditionDetails;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;
import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentExpeditionDetailsBinding;
import com.robotlab.expeditions2.model.Expedition;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.utility.FileStore;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;


public class ExpeditionDetailsFragment extends BaseFragment implements View.OnClickListener {

    private static String ARG_PARAM = "ARG_PARAM";

    private FragmentExpeditionDetailsBinding binding;
    private ExpeditionDetailAdapter adapter;
    private BackClick backClick;
    private Expedition expedition;

    public static ExpeditionDetailsFragment newInstance(Expedition expedition) {
        ExpeditionDetailsFragment fragment = new ExpeditionDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, expedition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentExpeditionDetailsBinding.inflate(getLayoutInflater());
        onAttachToParentFragment(requireActivity());
        if (getArguments() != null) {
            expedition = (Expedition) getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new ExpeditionDetailAdapter(context,getDummy());
        binding.lessonRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        binding.lessonRecyclerView.setAdapter(adapter);
        binding.backImageView.setOnClickListener(this);
        showInformation();
        binding.MyExpeditionTextView.setOnClickListener(view -> {
            database.expeditionDao().insert(expedition);
            showLongToast("Add to My Expeditions complete");
        });
        binding.downloadImageView.setOnClickListener(view -> {
            FileDownload();
        });
        return binding.getRoot();
    }

    private List<Lesson> getDummy(){
        List<Lesson> lessonList = new ArrayList<>();
        for (int i= 0 ; i<50;i++){
            lessonList.add(new Lesson("Etymology","Ready to broadcast","https://gisgeography.com/wp-content/uploads/2015/09/map-of-the-world-429784_1280-678x322.jpg"));
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

    private void showInformation(){
        if(expedition!=null) {
            Glide.with(context).load(expedition.getImage_url()).centerCrop().placeholder(R.drawable.ic_application_icon).into(binding.topLogoImageView);
            binding.titleTextView.setText(expedition.getTitle());
            binding.subtitleTextView.setText(expedition.getDescription());
            binding.lessonNumberTextView.setText(expedition.getLesson());
            binding.gradeTextView.setText(expedition.getGrade());
            binding.typeTextView.setText(expedition.getType());
            binding.fileNameTextView.setText(expedition.getPdfName());
            binding.fileInformationTextView.setText(expedition.getPdfTitle());
        }
    }

    private void FileDownload(){
        Log.i("path",FileStore.getCacheFolder(context).getPath());
        PRDownloader.download("https://cdn.slashgear.com/wp-content/uploads/2020/05/deadly-wallpaper-1280x720.jpg", FileStore.getCacheFolder(context).getPath(),"sample.png").build().start(new OnDownloadListener() {
            @Override
            public void onDownloadComplete() {

            }

            @Override
            public void onError(Error error) {
                Log.i("Error",error.getServerErrorMessage());
            }
        });            ;
    }
}