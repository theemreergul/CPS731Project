package com.example.cps731project;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Restaurant restaurant);

    @Query("DELETE FROM restaurant_table")
    void deleteAll();

    @Delete
    void deleteRestaurant(Restaurant word);

    @Query("SELECT * from restaurant_table ORDER BY name ASC")
    LiveData<List<Restaurant>> getAllRestaurants();

}
