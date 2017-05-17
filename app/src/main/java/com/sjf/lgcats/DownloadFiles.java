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

public class DownloadFiles extends AsyncTask<Void, Void, Void> {

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
    protected Void doInBackground(Void ... v) {
        StringUtil.downloadLinks();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */
    }
}
