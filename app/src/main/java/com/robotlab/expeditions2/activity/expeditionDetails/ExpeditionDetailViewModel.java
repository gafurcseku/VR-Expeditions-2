package com.robotlab.expeditions2.activity.expeditionDetails;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModel;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Status;
import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.download.DownloadListener;
import com.robotlab.expeditions2.utility.FileStore;

public class ExpeditionDetailViewModel extends ViewModel {
    private Context context;
    private AppDatabase database;
    private DownloadListener downloadListener;
    private int currentDownloadId;


    public ExpeditionDetailViewModel(Context context, AppDatabase database) {
        this.context = context;
        this.database = database;
    }

    /**
     * This function use to monitor file or Image download
     */

    public void FileDownload(int downloadId, int position, int total, String filePath, String fileName, AppCompatTextView percentageTextView, DownloadListener downloadListener){

        if(Status.RUNNING ==PRDownloader.getStatus(downloadId)){
            return;
        }

        if(Status.PAUSED == PRDownloader.getStatus(downloadId)){
            PRDownloader.resume(downloadId);
            return;
        }

        currentDownloadId = PRDownloader.download(filePath, FileStore.getCacheFolder(context).getPath(),fileName).build().setOnStartOrResumeListener(new OnStartOrResumeListener() {
          @Override
          public void onStartOrResume() {
              downloadListener.onDownloadComplete(0,currentDownloadId);
          }
      }).setOnPauseListener(() -> {

      }).setOnCancelListener(() -> {

      }).setOnProgressListener(progress -> {
          if(percentageTextView!=null){
              float totalPercentage =  ((float)progress.currentBytes / (float)progress.totalBytes)*100;
              percentageTextView.setText((int) Math.ceil(totalPercentage) +"%");
          }
      }).start(new OnDownloadListener() {
            @Override
            public void onDownloadComplete() {
                //totalCalculate = totalCalculate +totalProgress;
                downloadListener.onDownloadComplete(1,downloadId);
            }

            @Override
            public void onError(Error error) {
                Log.i("Error",error.toString());
                downloadListener.onDownloadComplete(0,0);
            }
        });
    }
}


