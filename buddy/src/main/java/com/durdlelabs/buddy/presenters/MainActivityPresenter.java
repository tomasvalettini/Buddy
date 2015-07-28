package com.durdlelabs.buddy.presenters;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;

import com.durdlelabs.buddy.models.data.Contact;
import com.durdlelabs.buddy.models.logic.ContactHelper;
import com.durdlelabs.buddy.models.logic.ContactHelperVcf;
import com.durdlelabs.buddy.models.logic.FilePersistance;
import com.durdlelabs.buddy.models.logic.IContactHelper;
import com.durdlelabs.buddy.views.IMainActivityView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter extends MvpBasePresenter<IMainActivityView> {
    private IContactHelper contactHelper;
    private IContactHelper ch;
    private FilePersistance fp;
    private File appDir;

    public MainActivityPresenter(Resources res, ContentResolver cr, Context con) {
        contactHelper = new ContactHelperVcf(res, cr);
        ch = new ContactHelper(res, cr);
        fp = new FilePersistance(con);
        appDir = fp.getAppFile();
    }

    public void backupContacts(Context con) {
        contactHelper.readContacts();
        fp.writeToFile(getVcf().trim());
        appDir = fp.getAppFile();
    }

    public String getVcf() {
        return ((ContactHelperVcf) contactHelper).getVcfString();
    }

    public File getAppFile() {
        return appDir;
    }

    /**
     * for testing purposes.
     */
    public void insertContacts() {
        List<Contact> contactsToInsert = new ArrayList<>();

        //contactsToInsert.add(new Contact("1200", "A", "+1 123-456-7890"));
        contactsToInsert.add(new Contact("1201", "Ab", "234-567-8901"));
        contactsToInsert.add(new Contact("1202", "Abc", "345-678-9012"));
        contactsToInsert.add(new Contact("1203", "Abcd", "456-789-0123"));
        contactsToInsert.add(new Contact("1204", "Abcde", "567-890-1234"));
        contactsToInsert.add(new Contact("1205", "Abcdef", "678-901-2345"));
        contactsToInsert.add(new Contact("1206", "Abcdefg", "789-012-3456"));
        contactsToInsert.add(new Contact("1207", "Abcdefgh", "890-123-4567"));
        contactsToInsert.add(new Contact("1208", "Abcdefghi", "012-345-6789"));

        contactHelper.insertContacts(contactsToInsert);
    }

    public void prepareRestoration() {
        List<Contact> contacts = ch.readContacts();

        for (Contact c : contacts) {
            c.setSelected(true);
        }

        ch.deleteContacts(contacts);
    }
}