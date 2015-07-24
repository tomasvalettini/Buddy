package com.durdlelabs.buddy.model.logic;

import com.durdlelabs.buddy.model.data.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JSONWriter implements IJSONWriter {
    String json;

    @Override
    public String getJson() {
        return json;
    }

    @Override
    public void writeJSON(List<Contact> list) {
        JSONArray jList = new JSONArray();

        for (Contact c : list) {
            JSONObject obj = new JSONObject();

            try {
                obj.put("id", c.getId());
                obj.put("name", c.getName());
                obj.put("phone", c.getPhone());

                jList.put(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        json = jList.toString();
    }
}