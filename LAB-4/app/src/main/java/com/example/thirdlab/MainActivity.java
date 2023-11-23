package com.example.thirdlab;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer = findViewById(R.id.fragment_container);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new InventoryFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    // Method to add an inventory item to the list in InventoryListFragment
    public void addInventoryItem(Inventory inventory) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragment instanceof InventoryListFragment) {
            ((InventoryListFragment) fragment).addInventoryItem(inventory);
        }
    }

    // Method to show the InventoryListFragment
    public void showInventoryListFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new InventoryListFragment())
                .addToBackStack(null)
                .commit();
    }

    // Method to go back to the InventoryFragment
    public void goBackToInventoryFragment() {
        getSupportFragmentManager().popBackStack();
    }
}
