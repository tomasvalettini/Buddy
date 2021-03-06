package com.durdlelabs.buddy.asynctasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.durdlelabs.buddy.DeleteActivity;

public class DeleteContactsAsyncTask extends AsyncTask<Void, Void, Void> {
    private DeleteActivity deleteActivity;
    private String title;
    private String message;
    private ProgressDialog progressDialog;

    public DeleteContactsAsyncTask(DeleteActivity da, String title, String message) {
        this.deleteActivity = da;
        this.title = title;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(deleteActivity, title, message, false);
    }

    //this method updates shit in the background. If you want to update the "View", do it in onPostExecute :)
    @Override
    protected Void doInBackground(Void... params) {
        deleteActivity.getPresenter().deleteContacts(deleteActivity.getPresenter().getSelectedContacts());
        deleteActivity.getPresenter().refreshContacts();
        return null;
    }

    @Override
    protected void onPostExecute(Void a) {
        deleteActivity.getPresenter().updateContactList();
        progressDialog.dismiss();
    }
}