package com.example.kevin.smartschoolbuspro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class InitScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_screen);
        setTitle("Login");

        if (UserDatabaseInitializer.executeCount(UserAppDatabase.getAppDatabase(getApplicationContext())) == 1) {
            User temp = UserDatabaseInitializer.executeAll(UserAppDatabase.getAppDatabase(this)).get(0);
            if (temp.user_type == 's') startActivity(new Intent(InitScreen.this, StudentHome.class));
            else if (temp.user_type == 'p') startActivity(new Intent(InitScreen.this, StudentHome.class));
            else if (temp.user_type == 'd') startActivity(new Intent(InitScreen.this, StudentHome.class));
        }

        // Define buttons on screen
        final ImageButton btn_student = findViewById(R.id.btn_student);
        final ImageButton btn_parent = findViewById(R.id.btn_parent);
        final ImageButton btn_driver = findViewById(R.id.btn_driver);
        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InitScreen.this, StudentLogin.class));
            }
        });
        btn_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InitScreen.this, ParentLogin.class));
            }
        });
        btn_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InitScreen.this, DriverLogin.class));
            }
        });

        final Button btn_clearDB = findViewById(R.id.btn_clearDB);
        btn_clearDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabaseInitializer.executeClear(UserAppDatabase.getAppDatabase(getApplicationContext()));
                System.out.println("done");
            }
        });

    }

}