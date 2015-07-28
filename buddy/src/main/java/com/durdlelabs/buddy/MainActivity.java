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
import com.durdlelabs.buddy.asynctasks.BackupContactsAsyncTask;
import com.durdlelabs.buddy.models.data.CustomMenuItem;
import com.durdlelabs.buddy.presenters.MainActivityPresenter;
import com.durdlelabs.buddy.views.IMainActivityView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

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
        return new MainActivityPresenter(getResources(), getContentResolver(), getApplicationContext());
    }

    @Override
    public void showToastMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private List<CustomMenuItem> createMenuItems() {
        List<CustomMenuItem> list = new ArrayList<>();
        list.add(new CustomMenuItem(R.mipmap.ic_backup, getResources().getString(R.string.menu_backup)));
        list.add(new CustomMenuItem(R.mipmap.ic_restore, getResources().getString(R.string.menu_restore)));
        list.add(new CustomMenuItem(R.mipmap.ic_delete, getResources().getString(R.string.menu_delete)));
        list.add(new CustomMenuItem(R.mipmap.ic_share, getResources().getString(R.string.menu_share)));
        return list;
    }

    private void setMenu(final List<CustomMenuItem> list) {
        MenuItemAdapter mia = new MenuItemAdapter(this, android.R.layout.simple_list_item_1, list);

        final MainActivity ma = this; // this is needed to use the activity into the AdapterView.OnItemClickListener
        menuItems.setAdapter(mia);
        menuItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        BackupContactsAsyncTask bcat = new BackupContactsAsyncTask(ma, getResources().getString(R.string.please_wait), getResources().getString(R.string.backup_contacts));
                        bcat.execute();
                        break;
                    case 1:
                        File file = ma.getPresenter().getAppFile();

                        if (file != null) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.fromFile(file), "text/x-vcard"); //storage path is path of your vcf file and vFile is name of that file.
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_backup), Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2:
                        Intent nextScreen = new Intent(getApplicationContext(), DeleteActivity.class);
                        startActivity(nextScreen);
                        break;
                    case 3:
                        shareContacts();
                        break;
                    default:
                        showToastMsg(getResources().getString(R.string.uh_oh));
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
            showToastMsg(getResources().getString(R.string.uh_oh));
        }
    }
}