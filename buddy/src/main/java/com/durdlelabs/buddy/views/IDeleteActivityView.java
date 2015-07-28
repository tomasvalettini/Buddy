package com.durdlelabs.buddy.views;

import com.durdlelabs.buddy.models.data.Contact;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

public interface IDeleteActivityView extends MvpView {
    void updateContactList();
    List<Contact> getSelectedContacts();
}