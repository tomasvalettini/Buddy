package com.durdlelabs.buddy.presenters;

import android.content.ContentResolver;
import android.content.res.Resources;

import com.durdlelabs.buddy.models.data.Contact;
import com.durdlelabs.buddy.models.logic.ContactHelper;
import com.durdlelabs.buddy.models.logic.IContactHelper;
import com.durdlelabs.buddy.views.IDeleteActivityView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

public class DeleteActivityPresenter extends MvpBasePresenter<IDeleteActivityView> {
    private IContactHelper ch;

    public DeleteActivityPresenter(Resources res, ContentResolver cr) {
        ch = new ContactHelper(res, cr);
    }

    public List<Contact> readContacts() {
        return ch.readContacts();
    }

    public List<Contact> getContacts() {
        return ch.getContacts();
    }
}