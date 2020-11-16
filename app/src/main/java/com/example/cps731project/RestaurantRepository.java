package com.example.cps731project;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RestaurantRepository {
    private RestaurantDao mRestaurantDao;
    private LiveData<List<Restaurant>> mAllRestaurants;
    RestaurantRepository(Application application) {
        WaiteraterDatabase db = WaiteraterDatabase.getDatabase(application);
        mRestaurantDao = db.restaurantDao();
        mAllRestaurants = mRestaurantDao.getAllRestaurants();
    }
    LiveData<List<Restaurant>> getAllWords() {
        return mAllRestaurants;
    }
    public void insert(Restaurant restaurant) {
        new insertAsyncTask(mRestaurantDao).execute(restaurant);
    }
    /**
     * Insert a word into the database.
     */
    private static class insertAsyncTask extends AsyncTask<Restaurant, Void, Void> {

        private RestaurantDao mAsyncTaskDao;

        insertAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Restaurant... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * Delete all words from the database (does not delete the table)
     */
    private static class deleteAllRestaurantsAsyncTask extends AsyncTask<Void, Void, Void> {
        private RestaurantDao mAsyncTaskDao;

        deleteAllRestaurantsAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     *  Delete a single word from the database.
     */
    private static class deleteRestaurantAsyncTask extends AsyncTask<Restaurant, Void, Void> {
        private RestaurantDao mAsyncTaskDao;

        deleteRestaurantAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Restaurant... params) {
            mAsyncTaskDao.deleteRestaurant(params[0]);
            return null;
        }
    }
}
