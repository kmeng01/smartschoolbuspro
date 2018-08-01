package com.example.kevin.smartschoolbuspro;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    List<User> getAll();

    @Query("SELECT * FROM user_table where first_name LIKE  :firstName AND last_name LIKE :lastName")
    User findByName(String firstName, String lastName);

    @Query("SELECT * FROM user_table WHERE uid = :input")
    User findByID(int input);

    @Query("SELECT COUNT(*) from user_table")
    int countUsers();

    @Insert
    void insert(User u);

    @Query("DELETE FROM user_table")
    void clearAll();

}