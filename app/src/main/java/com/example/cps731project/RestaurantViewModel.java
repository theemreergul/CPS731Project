package com.example.cps731project;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private RestaurantRepository mRepository;
    private LiveData<List<Restaurant>> mAllRestaurants;
    public  RestaurantViewModel(Application application){
        super(application);
        mRepository = new RestaurantRepository(application);
        mAllRestaurants = mRepository.getAllWords();
    }
    LiveData<List<Restaurant>> getmAllRestaurants() { return mAllRestaurants; }
    public void insert(Restaurant restaurant) { mRepository.insert(restaurant); }

}
