package com.durdlelabs.buddy.asynctasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.durdlelabs.buddy.MainActivity;

public class PreparingRestoringAsyncTask extends AsyncTask<Void, Void, Void> {
    private MainActivity mainActivity;
    private String title;
    private String message;
    private ProgressDialog progressDialog;

    public PreparingRestoringAsyncTask(MainActivity ma, String title, String message) {
        this.mainActivity = ma;
        this.title = title;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mainActivity, title, message, false);
    }

    //this method updates shit in the background. If you want to update the "View", do it in onPostExecute :)
    @Override
    protected Void doInBackground(Void... params) {
        mainActivity.getPresenter().prepareRestoration();
        return null;
    }

    @Override
    protected void onPostExecute(Void a) {
        progressDialog.dismiss();
    }
}