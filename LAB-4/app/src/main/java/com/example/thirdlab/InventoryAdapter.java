package com.example.thirdlab;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class InventoryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Inventory> inventories;

    public InventoryAdapter(Context context, ArrayList<Inventory> inventories) {
        this.context = context;
        this.inventories = inventories;
    }

    @Override
    public int getCount() {
        return inventories.size();
    }

    @Override
    public Object getItem(int position) {
        return inventories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }
}
