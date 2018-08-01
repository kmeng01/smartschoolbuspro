package com.example.kevin.smartschoolbuspro;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String first_name;

    @ColumnInfo(name = "last_name")
    public String last_name;

    @ColumnInfo(name = "route")
    public int route;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "user_type")
    public char user_type;

//    public User(int u, int r, String f, String l, String e) {
//        this.uid=u;
//        this.route=r;
//        this.first_name=f;
//        this.last_name=l;
//        this.email=e;
//    }

}