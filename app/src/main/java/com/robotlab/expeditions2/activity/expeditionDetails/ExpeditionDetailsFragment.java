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
import com.robotlab.expeditions2.model.PdfFile;
import com.robotlab.expeditions2.utility.DummyData;
import com.robotlab.expeditions2.utility.FileStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TooManyListenersException;
import java.util.stream.Collectors;


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
        adapter = new ExpeditionDetailAdapter(context, DummyData.getLesson());
        binding.lessonRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        binding.lessonRecyclerView.setAdapter(adapter);
        binding.backImageView.setOnClickListener(this);
        showInformation();
        binding.MyExpeditionTextView.setOnClickListener(view -> {
            database.expeditionDao().insert(expedition);
            showLongToast("Add to My Expeditions complete");
        });
        binding.downloadImageView.setOnClickListener(view -> {

        });
        return binding.getRoot();
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

            Optional<PdfFile> downloadPdf = DummyData.getPdfList().stream().filter(pdf -> pdf.getExpeditionId() == expedition.get_id()).findFirst();
            binding.fileNameTextView.setText(downloadPdf.get().getPdfName());
            binding.fileInformationTextView.setText(downloadPdf.get().getPdfTitle());
        }
    }

}