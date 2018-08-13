//
// ResourcesAdapter.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// ResourcesAdapter - adapter for resource table view
//

package com.avinalabs.losgatoshighschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResourcesAdapter extends BaseAdapter {
    private ArrayList<Object> cellArray;
    private LayoutInflater inflater;
    private static final int TYPE_LINK_CELL = 0;
    private static final int TYPE_STACK_CELL = 1;
    private static final int TYPE_DIVIDER_CELL = 2;

    public ResourcesAdapter(Context context, ArrayList<Object> cellArray) {
        this.cellArray = cellArray;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cellArray.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return cellArray.get(position);
    }

    @Override
    public int getViewTypeCount() {
        // TYPE_PERSON and TYPE_DIVIDER
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof ResourceStackCellObject) {
            return TYPE_STACK_CELL;
        } else if (getItem(position) instanceof ResourceCellObject) {
            return TYPE_LINK_CELL;
        }

        return TYPE_DIVIDER_CELL;
    }

    @Override
    public boolean isEnabled(int position) {
        return (getItemViewType(position) == TYPE_LINK_CELL || getItemViewType(position) == TYPE_STACK_CELL);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_LINK_CELL:
                    convertView = inflater.inflate(R.layout.row_item, parent, false);
                    break;
                case TYPE_STACK_CELL:
                    convertView = inflater.inflate(R.layout.row_item, parent, false);
                    break;
                case TYPE_DIVIDER_CELL:
                    convertView = inflater.inflate(R.layout.row_header, parent, false);
                    break;
            }
        }

        switch (type) {
            case TYPE_LINK_CELL:
                ResourceCellObject cell = (ResourceCellObject) getItem(position);
                TextView textView = (TextView) convertView.findViewById(R.id.row_item_textview);
                textView.setText(cell.getLabel());
                break;
            case TYPE_STACK_CELL:
                ResourceStackCellObject cell1 = (ResourceStackCellObject) getItem(position);
                TextView textView1 = (TextView) convertView.findViewById(R.id.row_item_textview);
                textView1.setText(cell1.getLabel());
                break;
            case TYPE_DIVIDER_CELL:
                TextView title = (TextView) convertView.findViewById(R.id.row_item_header);
                String titleString = (String) getItem(position);
                title.setText(titleString);
                break;
        }

        return convertView;
    }
}