package com.example.cps731project;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;
    UserRepository(Application application) {
        WaiteraterDatabase db = WaiteraterDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAllUsers();
    }
    LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }
    public void insert(User usr) {
        new UserRepository.insertAsyncTask(mUserDao).execute(usr);
    }
    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao mAsyncTaskDao;

        deleteAllUsersAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
        private static class deleteUserAsyncTask extends AsyncTask<User, Void, Void> {
            private UserDao mAsyncTaskDao;

            deleteUserAsyncTask(UserDao dao) {
                mAsyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final User... params) {
                mAsyncTaskDao.deleteUser(params[0]);
                return null;
            }
        }
    }
}
