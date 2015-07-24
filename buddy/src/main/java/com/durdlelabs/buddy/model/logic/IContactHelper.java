package com.durdlelabs.buddy.model.logic;

import com.durdlelabs.buddy.model.data.Contact;

import java.util.List;

public interface IContactHelper {
    public List<Contact> readContacts();
    public List<Contact> getContacts();
    public void deleteContacts(List<Contact> c);
    public void insertContacts(List<Contact> c);
    public String getAppDir();
}