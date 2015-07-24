package com.durdlelabs.buddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.durdlelabs.buddy.R;
import com.durdlelabs.buddy.models.data.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends ArrayAdapter<Contact> {
    List<Contact> contacts = new ArrayList<>();
    private Context context;

    public ContactsAdapter(Context context, int textViewResourceId, List<Contact> objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        contacts = objects;

        for (int i = 0; i < contacts.size(); i++) {
            contacts.get(i).setSelected(false);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.contact_row, parent, false);
        TextView nameTextView = (TextView) rowView.findViewById(R.id.contact_name);
        TextView phoneTextView = (TextView) rowView.findViewById(R.id.contact_phone);
        TextView contactCircleTextView = (TextView) rowView.findViewById(R.id.contact_circle);

        String[] parts = contacts.get(position).getName().trim().split(" ");

        if (parts.length > 1) {
            contactCircleTextView.setText(parts[0].charAt(0) + "" + parts[1].charAt(0) + "");
        } else {
            contactCircleTextView.setText(contacts.get(position).getName().charAt(0) + "");
        }

        nameTextView.setText(contacts.get(position).getName());
        phoneTextView.setText(contacts.get(position).getPhone());

        if (contacts.get(position).isSelected()) {
            rowView.setBackgroundColor(parent.getResources().getColor(R.color.item_list_selected));
        }

        return rowView;
    }

    public List<Contact> getContactList() {
        return contacts;
    }

    public void setSelected(int position) {
        contacts.get(position).setSelected(true);
    }

    public void removeSelected(int position) {
        contacts.get(position).setSelected(false);
    }
}