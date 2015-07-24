package com.durdlelabs.buddy.model.logic;

import com.durdlelabs.buddy.model.data.Contact;

import java.util.List;

public interface IJSONWriter {
    public void writeJSON(List<Contact> list);
    public String getJson();
}