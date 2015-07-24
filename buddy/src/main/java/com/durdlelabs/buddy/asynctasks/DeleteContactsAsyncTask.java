package com.durdlelabs.buddy.asynctasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.durdlelabs.buddy.presenter.MainPresenter;

public class DeleteContactsAsyncTask extends AsyncTask<Void, Void, Void> {
    private MainPresenter mainPresenter;
    private String title;
    private String message;
    private ProgressDialog progressDialog;

    public DeleteContactsAsyncTask(MainPresenter mp, String title, String message) {
        this.mainPresenter = mp;
        this.title = title;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mainPresenter.getActivity(), title, message, false);
    }

    //this method updates shit in the background. If you want to update the "View", do it in onPostExecute :)
    @Override
    protected Void doInBackground(Void... params) {
        mainPresenter.deleteContacts(mainPresenter.getSelectedContacts());
        mainPresenter.refreshContacts();
        return null;
    }

    @Override
    protected void onPostExecute(Void a) {
        mainPresenter.updateContactList();
        progressDialog.dismiss();
    }
}
