package com.robotlab.expeditions2.activity.expeditionDetails;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModel;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.downloader.Status;
import com.robotlab.expeditions2.database.AppDatabase;
import com.robotlab.expeditions2.utility.FileStore;

public class ExpeditionDetailViewModel extends ViewModel {
    private Context context;
    private AppDatabase database;
    private DownloadListener downloadListener;

    public ExpeditionDetailViewModel(Context context, AppDatabase database) {
        this.context = context;
        this.database = database;
    }


    private void FileDownload(int downloadId, String filePath, String fileName, AppCompatTextView percentageTextView,AppCompatTextView messageTextView,DownloadListener downloadListener){
        Log.i("path", FileStore.getCacheFolder(context).getPath());

        if(Status.RUNNING ==PRDownloader.getStatus(downloadId)){
            return;
        }

        if(Status.PAUSED == PRDownloader.getStatus(downloadId)){
            PRDownloader.resume(downloadId);
            return;
        }

      int currentDownloadId = PRDownloader.download(filePath, FileStore.getCacheFolder(context).getPath(),fileName).build().setOnStartOrResumeListener(new OnStartOrResumeListener() {
          @Override
          public void onStartOrResume() {

          }
      }).setOnPauseListener(() -> {

      }).setOnCancelListener(() -> {

      }).setOnProgressListener(progress -> {
          if(percentageTextView!=null)
              percentageTextView.setText(progress.toString());
      }).start(new OnDownloadListener() {
            @Override
            public void onDownloadComplete() {
                downloadListener.onDownloadComplete(true,1);
                if(messageTextView!=null)
                    messageTextView.setVisibility(View.GONE);
            }

            @Override
            public void onError(Error error) {
                Log.i("Error",error.getServerErrorMessage());
                downloadListener.onDownloadComplete(false,0);
            }
        });            ;
    }
}

