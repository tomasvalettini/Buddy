package com.durdlelabs.buddy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.durdlelabs.buddy.presenters.MainActivityPresenter;
import com.durdlelabs.buddy.views.IMainActivityView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;

/**
 * MainActivity is the only class not present in a "sub-package" since it is the entry point for
 * this android app. This is my personal taste, not an architecture decision. Criticism welcomed.
 */
public class MainActivity extends MvpActivity<IMainActivityView, MainActivityPresenter>
        implements IMainActivityView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @NonNull
    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void backupContacts() {
        Toast.makeText(getApplicationContext(), "Backup Contacts", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void restoreContacts() {
        Toast.makeText(getApplicationContext(), "Restore Contacts", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteContacts() {
        Toast.makeText(getApplicationContext(), "Delete Contacts", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shareContacts() {
        Toast.makeText(getApplicationContext(), "Share Contacts", Toast.LENGTH_SHORT).show();
    }
}