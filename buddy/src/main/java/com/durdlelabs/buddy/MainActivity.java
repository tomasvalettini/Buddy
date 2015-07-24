package com.durdlelabs.buddy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.durdlelabs.buddy.adapters.MenuItemAdapter;
import com.durdlelabs.buddy.models.data.CustomMenuItem;
import com.durdlelabs.buddy.presenters.MainActivityPresenter;
import com.durdlelabs.buddy.views.IMainActivityView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * MainActivity is the only class not present in a "sub-package" since it is the entry point for
 * this android app. This is my personal taste, not an architecture decision. Criticism welcomed.
 */
public class MainActivity extends MvpActivity<IMainActivityView, MainActivityPresenter>
        implements IMainActivityView {
    @Bind(R.id.menu_items)
    ListView menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final List<CustomMenuItem> list = createMenuItems();
        setMenu(list);
    }

    @NonNull
    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void showToastMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private List<CustomMenuItem> createMenuItems() {
        List<CustomMenuItem> list = new ArrayList<>();
        list.add(new CustomMenuItem(R.mipmap.ic_backup, "Backup Contacts"));
        list.add(new CustomMenuItem(R.mipmap.ic_restore, "Restore Contacts"));
        list.add(new CustomMenuItem(R.mipmap.ic_delete, "Delete Contacts"));
        list.add(new CustomMenuItem(R.mipmap.ic_share, "Share Backup"));
        return list;
    }

    private void setMenu(final List<CustomMenuItem> list) {
        MenuItemAdapter mia = new MenuItemAdapter(this, android.R.layout.simple_list_item_1, list);

        menuItems.setAdapter(mia);
        menuItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        shareContacts();
                        break;
                    default:
                        showToastMsg("oupsies!! big issue :(");
                        return;
                }
            }
        });
    }

    public void shareContacts() {
        if (presenter.getAppFile() != null) {
            Intent sendIntent = new Intent();

            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(presenter.getAppFile()));
            sendIntent.setType("text/x-vcard");

            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
        } else {
            showToastMsg("No backup file found. Backup first please :)");
        }
    }
}