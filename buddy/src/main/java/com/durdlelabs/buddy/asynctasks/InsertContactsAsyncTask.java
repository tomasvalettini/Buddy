package com.durdlelabs.buddy.asynctasks;
/*

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.durdlelabs.buddy.presenter.MainMenuPresenter;

public class InsertContactsAsyncTask extends AsyncTask<Void, Void, Void> {
    private MainMenuPresenter mainMenuPresenter;
    private String title;
    private String message;
    private ProgressDialog progressDialog;

    public InsertContactsAsyncTask(MainMenuPresenter mp, String title, String message) {
        this.mainMenuPresenter = mp;
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
        mainMenuPresenter.insertContacts();
        return null;
    }

    @Override
    protected void onPostExecute(Void a) {
        Toast.makeText(mainMenuPresenter.getActivity(), "Restoration Complete!", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }
}*/
