package com.robotlab.expeditions2.application;

import android.app.Application;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.splunk.mint.Mint;

public class ExpeditionApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);

        Mint.initAndStartSession(this, "b82ccb9f");
    }
}
