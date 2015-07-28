package com.durdlelabs.buddy.models.logic;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;

import com.durdlelabs.buddy.models.data.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactHelper implements IContactHelper {
    private ContentResolver cr;
    private Resources res;
    private List<Contact> contacts;

    public ContactHelper(Resources res, ContentResolver cr) {
        this.res = res;
        this.cr = cr;
    }

    @Override
    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public List<Contact> readContacts() {
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        contacts = new ArrayList<>();
        int num = 0;

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                Contact c = new Contact();
                c.setId(cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID)));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                c.setName(name);

                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    // get the phone number
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{c.getId()}, null);

                    while (pCur.moveToNext()) {
                        String number = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        c.setPhone(number);
                    }

                    pCur.close();
                    contacts.add(c);
                    num++;
                }
            }
        }

        Log.v("count", "total contacts: " + num);

        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                if (c1.getName().compareTo(c2.getName()) < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        return contacts;
    }

    @Override
    public void deleteContacts(List<Contact> c) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        for (Contact co : c) {
            if (co.isSelected()) {
                String[] args = new String[]{co.getId()};

                ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI).withSelection(ContactsContract.RawContacts.CONTACT_ID + "=?", args).build());
            }
        }

        try {
            cr.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertContacts(List<Contact> c) {
        for (Contact co : c) {
            ArrayList ops = new ArrayList<ContentProviderOperation>();

            ops.add(ContentProviderOperation
                    .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .build());
            ops.add(ContentProviderOperation
                    .newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(
                            ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(
                            ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,
                            co.getName()).build());
            ops.add(ContentProviderOperation
                    .newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(
                            ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
                            co.getPhone())
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());

            try {
                cr.applyBatch(ContactsContract.AUTHORITY, ops);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (OperationApplicationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void prepareRestoration() { }

    @Override
    public String getAppDir() {
        return null;
    }

    private long getContactID(String number) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        String[] projection = {ContactsContract.PhoneLookup._ID};
        Cursor cursor = null;

        try {
            cursor = cr.query(contactUri, projection, null, null, null);

            if (cursor.moveToFirst()) {
                int personID = cursor.getColumnIndex(ContactsContract.PhoneLookup._ID);
                return cursor.getLong(personID);
            }

            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

        return -1;
    }

    public ContentResolver getCr() {
        return cr;
    }
}