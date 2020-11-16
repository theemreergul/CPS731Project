package com.example.cps731project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import java.util.List;

public class ListRestaurantActivity extends AppCompatActivity {
    private RestaurantViewModel mRestaurantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurant);
        RecyclerView recyclerView = findViewById(R.id.rvListRestaurants);
        final ListRestaurantsAdapter adapter = new ListRestaurantsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRestaurantViewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);

        mRestaurantViewModel.getmAllRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(@Nullable final List<Restaurant> restaurants) {
                // Update the cached copy of the words in the adapter.
                adapter.setRestaurants(restaurants);
            }
        });



    }
}