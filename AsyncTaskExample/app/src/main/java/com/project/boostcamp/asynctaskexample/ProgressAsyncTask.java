package com.project.boostcamp.asynctaskexample;

import android.os.AsyncTask;

/**
 * Created by Hong Tae Joon on 2017-07-20.
 */

public class ProgressAsyncTask extends AsyncTask<Integer, Integer, Void> {
    private OnUpdateListener updateListener;

    public ProgressAsyncTask(OnUpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try {
            for(int i=0; i<100; i++) {
                Thread.currentThread().sleep(100);
                publishProgress(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        updateListener.onUpdate(values[0]);
        super.onProgressUpdate(values);
    }
}
