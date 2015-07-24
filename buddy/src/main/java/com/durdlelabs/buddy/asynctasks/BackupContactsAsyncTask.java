package com.durdlelabs.buddy.asynctasks;
/*

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.durdlelabs.buddy.MainMenuActivity;
import com.durdlelabs.buddy.presenter.MainMenuPresenter;

public class BackupContactsAsyncTask extends AsyncTask<Void, Void, Void> {
    private MainMenuPresenter mainMenuPresenter;
    private String title;
    private String message;
    private ProgressDialog progressDialog;

    public BackupContactsAsyncTask(MainMenuPresenter mmp, String title, String message) {
        this.mainMenuPresenter = mmp;
        this.title = title;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mainMenuPresenter.getActivity(), title, message, true);
    }

    //this method updates shit in the background. If you want to update the "View", do it in onPostExecute :)
    @Override
    protected Void doInBackground(Void... params) {
        mainMenuPresenter.backupContacts(mainMenuPresenter.getActivity().getApplicationContext());
        return null;
    }

    @Override
    protected void onPostExecute(Void a) {
        progressDialog.dismiss();

        ((MainMenuActivity) mainMenuPresenter.getActivity()).shareContacts();
    }
}*/
