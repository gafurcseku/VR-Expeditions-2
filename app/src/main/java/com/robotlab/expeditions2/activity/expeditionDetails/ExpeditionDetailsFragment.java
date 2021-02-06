package com.robotlab.expeditions2.activity.expeditionDetails;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.robotlab.expeditions2.R;
import com.robotlab.expeditions2.base.BaseFragment;
import com.robotlab.expeditions2.databinding.FragmentExpeditionDetailsBinding;
import com.robotlab.expeditions2.download.DownloadListener;
import com.robotlab.expeditions2.model.Expedition;
import com.robotlab.expeditions2.model.Lesson;
import com.robotlab.expeditions2.model.PdfFile;
import com.robotlab.expeditions2.utility.DummyData;
import com.robotlab.expeditions2.utility.FileStore;
import com.robotlab.expeditions2.utility.NetworkUtil;

import java.io.File;
import java.util.List;
import java.util.Optional;




public class ExpeditionDetailsFragment extends BaseFragment implements View.OnClickListener {

    private static String ARG_PARAM = "ARG_PARAM";
    private static String ARG_PARAM1= "ARG_PARAM1";

    private ExpeditionDetailViewModel viewModel;
    private FragmentExpeditionDetailsBinding binding;
    private ExpeditionDetailAdapter adapter;
    private BackClick backClick;
    private Expedition expedition;
    private Optional<PdfFile> downloadPdf;
    private List<Lesson> lessonList;
    private Boolean isMyExpedition;


    public static ExpeditionDetailsFragment newInstance(Expedition expedition,Boolean isMyExpedition) {
        ExpeditionDetailsFragment fragment = new ExpeditionDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, expedition);
        args.putBoolean(ARG_PARAM1, isMyExpedition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentExpeditionDetailsBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this,viewModelFactory).get(ExpeditionDetailViewModel.class);
        onAttachToParentFragment(requireActivity());
        if (getArguments() != null) {
            expedition = (Expedition) getArguments().getSerializable(ARG_PARAM);
            isMyExpedition = getArguments().getBoolean(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding.backImageView.setOnClickListener(this);
        binding.backTextView.setOnClickListener(this);
        showInformation();

        if(isMyExpedition)
            binding.MyExpeditionTextView.setVisibility(View.GONE);

        if(database.expeditionDao().isExists(expedition.get_id())){
            binding.MyExpeditionTextView.setVisibility(View.GONE);
        }


        binding.MyExpeditionTextView.setOnClickListener(view -> {
            if(NetworkUtil.isConnected(context)){
                binding.MyExpeditionTextView.setVisibility(View.GONE);
                database.expeditionDao().insert(expedition);
                database.pdfFileDao().insert(downloadPdf.get());
                database.lessonDao().insert(lessonList);
//                for (Lesson lesson : lessonList){
//                    List<LessonImage> lessonImageList = DummyData.getLessonImages(lesson.getId());
//                    database.lessonImageDao().insert(lessonImageList);
//                }
                StartDownload();
                if(adapter!=null){
                    adapter.setPreDownload(0);
                }
            }else{
                customAlertDialog.showDialog("Please check your internet connection");
            }

        });
        binding.downloadImageView.setOnClickListener(view -> {
            if(isMyExpedition){
                PdfFile pdfFile = database.pdfFileDao().getPdfFile(expedition.get_id());
                if(pdfFile != null){
                    OpenPdfFile(""+pdfFile.getPdfId()+".pdf");
                }
            }else{
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadPdf.get().getPdfFileUrl()));
                startActivity(browserIntent);

            }
        });
        return binding.getRoot();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backImageView:
            case R.id.backTextView:
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
            if(isMyExpedition){
                File file = new File(FileStore.getCacheFolder(context).getPath()+"/"+expedition.getImage_url());
                Glide.with(context)
                        .load(file)
                        .centerCrop()
                        .placeholder(R.drawable.ic_application_icon)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.topLogoImageView);
            }else{
                Glide.with(context)
                        .load(expedition.getImage_url())
                        .centerCrop()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_application_icon)
                        .into(binding.topLogoImageView);
            }


            binding.titleTextView.setText(expedition.getTitle());
            binding.subtitleTextView.setText(expedition.getDescription());
            binding.lessonNumberTextView.setText(expedition.getLesson());
            binding.gradeTextView.setText(expedition.getGrade());
            binding.typeTextView.setText(expedition.getType());

            if(isMyExpedition){
                PdfFile pdfFile = database.pdfFileDao().getPdfFileByExpeditionId(expedition.get_id());
                if(pdfFile != null){
                   // binding.fileNameTextView.setText(pdfFile.getPdfName());
                    binding.fileInformationTextView.setText(pdfFile.getPdfTitle());
                }
                lessonList = database.lessonDao().getLessonByExpeditionId(expedition.get_id());
            }else{
                downloadPdf = DummyData.getPdfList().stream().filter(pdf -> pdf.getExpeditionId() == expedition.get_id()).findFirst();
                if(downloadPdf.isPresent()){
                    binding.fileInformationTextView.setText(downloadPdf.get().getPdfTitle());
                }

                lessonList = DummyData.getLesson(expedition.get_id(),database);
            }

            adapter = new ExpeditionDetailAdapter(context,database, lessonList,viewModel , isMyExpedition);

            binding.lessonRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            binding.lessonRecyclerView.setNestedScrollingEnabled(false);
            binding.lessonRecyclerView.setItemViewCacheSize(200);
            binding.lessonRecyclerView.setDrawingCacheEnabled(true);
            binding.lessonRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            binding.lessonRecyclerView.setAdapter(adapter);
            binding.fastScroller.attachRecyclerView(binding.lessonRecyclerView);

        }
    }

    private void StartDownload(){
        if(expedition!=null){
            PdfFile pdfFile = database.pdfFileDao().getPdfFile(expedition.get_id());
            if (pdfFile.getStatus() == 0){
                viewModel.FileDownload(pdfFile.getDownloadId(),0,0, pdfFile.getPdfFileUrl(), ""+pdfFile.getPdfId()+".pdf", null, new DownloadListener() {
                    @Override
                    public void onDownloadComplete(int status, int DownloadId) {
                        database.pdfFileDao().downloadStatus(DownloadId,status,pdfFile.getPdfId());
                    }
                });
            }

            viewModel.FileDownload(expedition.get_id(), 0, 0, expedition.getImage_url(), expedition.getImage_url(), null, new DownloadListener() {
                @Override
                public void onDownloadComplete(int status, int DownloadId) {
                }
            });
        }
    }


    private void OpenPdfFile(String fileName){
        File file = new File(FileStore.getCacheFolder(context).getPath()+"/"+fileName);
        Log.i("file Path",file.getAbsolutePath());
        if(file.exists()){
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri apkURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
                intent.setDataAndType(apkURI, "application/pdf");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent);
            }catch (ActivityNotFoundException e) {
                Toast.makeText(context, " You don't have any App to open PDF", Toast.LENGTH_LONG).show();
            }

        }else{
            customAlertDialog.showDialog("Please Download file first.");
        }

    }

}