package com.robotlab.expeditions2.utility;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileStore {

    /**
     * Use this function create  and return Image and file store folder
     *
     * @param context A context
     * @return cacheDir A File
     */
    public static File getCacheFolder(Context context) {
        File cacheDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(context.getExternalCacheDir(), "cache_folder");
            if(!cacheDir.isDirectory()) {
                cacheDir.mkdirs();
            }
        }

        if(!cacheDir.isDirectory()) {
            cacheDir = context.getCacheDir(); //get system cache folder
        }

        return cacheDir;
    }

}
