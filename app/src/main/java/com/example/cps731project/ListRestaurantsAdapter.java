package com.example.cps731project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListRestaurantsAdapter extends RecyclerView.Adapter<ListRestaurantsAdapter.ListRestaurantViewHolder> {
    private final LayoutInflater mInflater;
    private List<Restaurant> mRestaurants;

    ListRestaurantsAdapter(Context context) { mInflater = LayoutInflater.from(context); }
    @Override
    public ListRestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.listresturants_item, parent, false);
        return new ListRestaurantViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ListRestaurantViewHolder holder, int position) {
        if (mRestaurants != null) {
            Restaurant current = mRestaurants.get(position);
            holder.restaurantItemView.setText(current.getName() + mRestaurants.size());
        } else {
            // Covers the case of data not being ready yet.
            holder.restaurantItemView.setText("No Word");
        }
    }

    void setRestaurants(List<Restaurant> restaurants){
        mRestaurants = restaurants;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (mRestaurants != null)
            return mRestaurants.size();
        else return 0;
    }
    public Restaurant getRestaurantAtPosition(int position) {
        return mRestaurants.get(position);
    }
    class ListRestaurantViewHolder extends RecyclerView.ViewHolder {
        private final TextView restaurantItemView;

        private ListRestaurantViewHolder(View itemView) {
            super(itemView);
            restaurantItemView = itemView.findViewById(R.id.txtRestaurantName);
        }
    }


}
