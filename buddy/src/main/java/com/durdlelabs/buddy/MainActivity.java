package com.durdlelabs.buddy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.durdlelabs.buddy.model.data.CustomMenuItem;
import com.durdlelabs.buddy.presenters.MainActivityPresenter;
import com.durdlelabs.buddy.views.IMainActivityView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

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

        final List<CustomMenuItem> list = createMenuItems();
    }

    private List<CustomMenuItem> createMenuItems() {
        List<CustomMenuItem> list = new ArrayList<>();
        list.add(new CustomMenuItem(R.mipmap.ic_backup, "Backup Contacts"));
        list.add(new CustomMenuItem(R.mipmap.ic_restore, "Restore Contacts"));
        list.add(new CustomMenuItem(R.mipmap.ic_delete, "Delete Contacts"));
        list.add(new CustomMenuItem(R.mipmap.ic_share, "Share Backup"));
        return list;
    }

    @NonNull
    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void showToastMsg(String msg) {
        Toast.makeText(getApplicationContext(), "No backup found... Backup first!!", Toast.LENGTH_LONG).show();
    }

    public void shareContacts() {
        Intent sendIntent = new Intent();

        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(presenter.getAppFile()));
        sendIntent.setType("text/x-vcard");

        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }
}