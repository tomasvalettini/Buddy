package com.durdlelabs.buddy.models.logic;

import com.durdlelabs.buddy.models.data.Contact;

import java.util.List;

public interface IContactHelper {
    List<Contact> readContacts();
    List<Contact> getContacts();
    void deleteContacts(List<Contact> c);
    void insertContacts(List<Contact> c);
    void prepareRestoration();
    String getAppDir();
}