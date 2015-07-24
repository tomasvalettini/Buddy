package com.durdlelabs.buddy.models.logic;

import com.durdlelabs.buddy.models.data.Contact;

import java.util.List;

public interface IJSONWriter {
    public void writeJSON(List<Contact> list);
    public String getJson();
}