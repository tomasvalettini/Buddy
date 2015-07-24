package com.durdlelabs.buddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.durdlelabs.buddy.R;
import com.durdlelabs.buddy.models.data.CustomMenuItem;

import java.util.List;

public class MenuItemAdapter extends ArrayAdapter<CustomMenuItem> {
    private List<CustomMenuItem> menuItems;
    private Context context;

    public MenuItemAdapter(Context context, int resource, List<CustomMenuItem> list) {
        super(context, resource, list);

        this.context = context;
        menuItems = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.menu_item, parent, false);

        ImageView menuItemIcon = (ImageView) rowView.findViewById(R.id.menu_item_icon);
        TextView menuItemLabel = (TextView) rowView.findViewById(R.id.menu_item_label);

        menuItemIcon.setImageResource(menuItems.get(position).getResId());
        menuItemLabel.setText(menuItems.get(position).getLabel());

        return rowView;
    }
}