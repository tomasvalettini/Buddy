package com.durdlelabs.buddy.asynctasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.durdlelabs.buddy.MainActivity;

public class BackupContactsAsyncTask extends AsyncTask<Void, Void, Void> {
    private MainActivity mainActivity;
    private String title;
    private String message;
    private ProgressDialog progressDialog;

    public BackupContactsAsyncTask(MainActivity ma, String title, String message) {
        this.mainActivity = ma;
        this.title = title;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mainActivity, title, message, true);
    }

    //this method updates shit in the background. If you want to update the "View", do it in onPostExecute :)
    @Override
    protected Void doInBackground(Void... params) {
        mainActivity.getPresenter().backupContacts(mainActivity.getApplicationContext());
        return null;
    }

    @Override
    protected void onPostExecute(Void a) {
        progressDialog.dismiss();

        mainActivity.shareContacts();
    }
}
