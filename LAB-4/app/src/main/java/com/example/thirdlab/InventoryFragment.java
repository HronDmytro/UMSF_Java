package com.example.thirdlab;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

public class InventoryFragment extends Fragment {
    private Inventory mInventory;
    private EditText mTitleField;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInventory = new Inventory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        mTitleField = view.findViewById(R.id.inventory_title);
        mSolvedCheckBox = view.findViewById(R.id.inventory_solved);

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mInventory.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mInventory.setmSolved(isChecked);
            }
        });

        // Handle the button click to save the entered data in InventoryListFragment
        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the current inventory item to the activity and add to the list
                ((MainActivity) requireActivity()).addInventoryItem(mInventory);

                // Clear the fields after saving
                mTitleField.getText().clear();
                mSolvedCheckBox.setChecked(false);
            }
        });

        // Handle the button click to show the Inventory List Fragment
        Button showListButton = view.findViewById(R.id.showInventoryListButton);
        showListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the InventoryListFragment
                ((MainActivity) requireActivity()).showInventoryListFragment();
            }
        });

        return view;
    }

    public Inventory getmInventory() {
        return mInventory;
    }
}
