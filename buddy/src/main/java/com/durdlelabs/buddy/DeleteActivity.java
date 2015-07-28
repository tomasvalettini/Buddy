package com.durdlelabs.buddy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.durdlelabs.buddy.adapters.ContactsAdapter;
import com.durdlelabs.buddy.asynctasks.DeleteContactsAsyncTask;
import com.durdlelabs.buddy.models.data.Contact;
import com.durdlelabs.buddy.presenters.DeleteActivityPresenter;
import com.durdlelabs.buddy.views.IDeleteActivityView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class DeleteActivity extends MvpActivity<IDeleteActivityView, DeleteActivityPresenter>
        implements IDeleteActivityView {
    @Bind(R.id.main_activity_listview)
    ListView listView;
    private ContactsAdapter cAdapter = null;
    private boolean selectAll = true;
    private MenuItem mi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_listview);

        displayContacts(presenter.readContacts());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                toggleSelected(arg1, arg2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mi = menu.findItem(R.id.action_delete);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_select_all:
                selectAllContacts();
                return true;
            case R.id.action_delete:
                final DeleteActivity da = this;
                new AlertDialog.Builder(this)
                        .setTitle("Watch out!")
                        .setMessage("Are you sure you want to delete the selected people?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DeleteContactsAsyncTask dcat = new DeleteContactsAsyncTask(da, "Please wait...", "Deleting seletcted contacts...");
                                dcat.execute();
                                showDelete(false);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @NonNull
    @Override
    public DeleteActivityPresenter createPresenter() {
        return new DeleteActivityPresenter(getResources(), getContentResolver());
    }

    private void displayContacts(List<Contact> contacts) {
        cAdapter = new ContactsAdapter(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(cAdapter);
    }

    private void toggleSelected(View arg1, int arg2) {
        if (!cAdapter.getContactList().get(arg2).isSelected()) {
            arg1.setBackgroundColor(getResources().getColor(R.color.item_list_selected));
            setSelected(arg2);
        } else {
            arg1.setBackgroundColor(getResources().getColor(R.color.item_list_unselected));
            removeSelected(arg2);
        }

        showDelete(true);
    }

    private void setSelected(int position) {
        cAdapter.setSelected(position);
    }

    private void removeSelected(int position) {
        cAdapter.removeSelected(position);
    }

    private void showDelete(boolean b) {
        if (b) {
            if (getSelectedContacts().size() <= 0) {
                showDeleteMenuItem(false);
            } else {
                showDeleteMenuItem(true);
            }
        }
        else {
            showDeleteMenuItem(false);
        }
    }

    private void showDeleteMenuItem(boolean b) {
        mi.setVisible(b);
    }

    private List<Contact> getContactList() {
        return cAdapter.getContactList();
    }

    @Override
    public void updateContactList() {
        displayContacts(presenter.getContacts());
        listView.invalidate();
    }

    @Override
    public List<Contact> getSelectedContacts() {
        List<Contact> selected = new ArrayList<>();

        for (Contact c : getContactList()) {
            if (c.isSelected()) {
                selected.add(c);
            }
        }

        return selected;
    }

    private void selectAllContacts() {
        showSelectedAll();
    }

    private void showSelectedAll() {
        if (getSelectedContacts().size() <= 0) {
            selectAll = true;
        }

        if (selectAll) {
            for (int i = 0; i < getContactList().size(); i++) {
                cAdapter.setSelected(i);
            }
        } else {
            for (int i = 0; i < getContactList().size(); i++) {
                cAdapter.removeSelected(i);
            }
        }

        showDelete(true);

        selectAll = !selectAll;
        listView.invalidateViews();
    }
}