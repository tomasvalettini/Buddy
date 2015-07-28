package com.durdlelabs.buddy.models.logic;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.durdlelabs.buddy.models.data.Contact;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactHelperVcf extends ContactHelper {
    private String vcfString;

    public ContactHelperVcf(Resources res, ContentResolver cr) {
        super(res, cr);
    }

    @Override
    public List<Contact> readContacts() {
        Cursor cur = getCr().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        cur.moveToFirst();
        StringBuilder sb = new StringBuilder();
        int num = 0;

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, id);
                AssetFileDescriptor fd;

                try {
                    fd = getCr().openAssetFileDescriptor(uri, "r");
                    FileInputStream fis = fd.createInputStream();
                    byte[] buf = new byte[(int) fd.getDeclaredLength()];
                    fis.read(buf);

                    if (hasPhoneNumber(buf)) {
                        num++;
                        sb.append(new String(buf));
                    }
                } /*catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/ catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        cur.close();
        vcfString = sb.toString();
        return new ArrayList<>();
    }

    private boolean hasPhoneNumber(byte[] buf) {
        String contact = new String(buf);
        String[] lines = contact.split("\n");
        boolean found = false;

        for (String line : lines) {
            found = line.startsWith("TEL");

            if (found) break;
        }

        return found;
    }

    public String getVcfString() {
        return vcfString;
    }

    @Override
    public void prepareRestoration() {
        Cursor cur = getCr().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        while (cur.moveToNext()) {
            String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
            Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
            getCr().delete(uri, null, null);
        }
    }
}
