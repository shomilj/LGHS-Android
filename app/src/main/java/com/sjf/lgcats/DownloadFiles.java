package com.sjf.lgcats;

import android.os.AsyncTask;

import java.net.URL;

/**
 * Created by HarryWang on 5/16/17.
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

interface DownloadAsyncTaskInterface {
    void saveDownload(String result);
}

public class DownloadFiles extends AsyncTask<Void, Void, String> {

    private DownloadAsyncTaskInterface downloadAsyncTaskInterface;

    private String url;

    public DownloadFiles (String u, DownloadAsyncTaskInterface d) {
        url = u;
        downloadAsyncTaskInterface = d;
    }

    @Override
    protected void onPreExecute() {
        /*
         *    do things before doInBackground() code runs
         *    such as preparing and showing a Dialog or ProgressBar
        */
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        /*
         *    updating data
         *    such a Dialog or ProgressBar
        */
    }

    @Override
    protected String doInBackground(Void ... v) {
        return StringUtil.downloadLinks(url);
    }

    @Override
    protected void onPostExecute(String result) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */
        downloadAsyncTaskInterface.saveDownload(result);
    }
}
