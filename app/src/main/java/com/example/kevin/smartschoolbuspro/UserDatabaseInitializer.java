package com.example.kevin.smartschoolbuspro;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

//import com.kevin.persistence.database.UserAppDatabase;
//import com.kevin.persistence.entity.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.Nonnull;

public class UserDatabaseInitializer {

    private static final String TAG = UserDatabaseInitializer.class.getName();

    public static void executeAdd(@NonNull final UserAppDatabase db, @Nonnull User u) {
        PopulateDbAsync task = new PopulateDbAsync(db, u);
        task.execute();
    }

    public static User executeGet(@NonNull final UserAppDatabase db, @Nonnull int uid) {
        try {
            GetDbAsync task = new GetDbAsync(db, uid);
            return task.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
        return new User();
    }

    public static void executeClear(@NonNull final UserAppDatabase db) {
        ClearDbAsync task = new ClearDbAsync(db);
        task.execute();
    }

    public static Integer executeCount(@NonNull final UserAppDatabase db) {
        try {
            CountDbAsync task = new CountDbAsync(db);
            return task.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
        return -1;
    }

    public static List<User> executeAll(@NonNull final UserAppDatabase db) {
        try {
            GetAllDbAsync task = new GetAllDbAsync(db);
            return task.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
        return null;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserAppDatabase mDb;
        private final User u;

        PopulateDbAsync(UserAppDatabase db, User uu) {
            mDb = db;
            u = uu;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDb.userDao().insert(u);
            return null;
        }

    }

    private static class GetDbAsync extends AsyncTask<User, User, User> {

        private final UserAppDatabase mDb;
        private final int uid;

        GetDbAsync(UserAppDatabase db, int uu) {
            mDb = db;
            uid = uu;
        }

        @Override
        protected User doInBackground(final User... params) {
            return mDb.userDao().findByID(uid);
        }

    }

    private static class ClearDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserAppDatabase mDb;

        ClearDbAsync(UserAppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDb.userDao().clearAll();
            return null;
        }

    }

    private static class CountDbAsync extends AsyncTask<Integer, Integer, Integer> {

        private final UserAppDatabase mDb;

        CountDbAsync(UserAppDatabase db) {
            mDb = db;
        }

        @Override
        protected Integer doInBackground(final Integer... params) {
            return mDb.userDao().countUsers();
        }

    }

    private static class GetAllDbAsync extends AsyncTask<List<User>, List<User>, List<User>> {

        private final UserAppDatabase mDb;

        GetAllDbAsync (UserAppDatabase db) {
            mDb = db;
        }

        @Override
        protected List<User> doInBackground(final List<User>... params) {
            return mDb.userDao().getAll();
        }

    }

}
