package com.example.thirdlab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class InventoryListFragment extends Fragment {
    private ArrayList<Inventory> inventories;
    private ArrayAdapter<String> inventoryAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inventories = new ArrayList<>();
        inventoryAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);
        ListView listView = view.findViewById(R.id.listInventory);
        listView.setAdapter(inventoryAdapter);

        // Handle the button click to go back to InventoryFragment
        Button goBackButton = view.findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).goBackToInventoryFragment();
            }
        });

        return view;
    }

    // Method to add an inventory item to the list
    public void addInventoryItem(Inventory inventory) {
        inventories.add(inventory);
        inventoryAdapter.add(inventory.getmTitle());
        inventoryAdapter.notifyDataSetChanged();
    }
}
