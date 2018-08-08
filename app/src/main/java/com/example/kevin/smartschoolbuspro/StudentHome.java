package com.example.kevin.smartschoolbuspro;

import android.arch.persistence.room.Database;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class StudentHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        retrieveFromDB();
        process_header();
    }

    private void retrieveFromDB() {
        // retrieve first element in the database
        if (UserDatabaseInitializer.executeCount(UserAppDatabase.getAppDatabase(this)) != 1) {
            int temp = 1/0;
        }
        user = UserDatabaseInitializer.executeAll(UserAppDatabase.getAppDatabase(this)).get(0);
    }

    private void process_header() {
        TextView txt_welcome = (TextView) findViewById(R.id.txt_welcome);
        txt_welcome.setText("Welcome, " + user.first_name + "!");

        TextView txt_datetime = (TextView) findViewById(R.id.txt_datetime);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getDefault());
        int day = cal.get(Calendar.DAY_OF_WEEK);
        String str_day = "", str_date = "", str_route = "";
        switch (day) {
            case Calendar.SUNDAY:
                str_day = "Sunday";
                break;
            case Calendar.MONDAY:
                str_day = "Monday";
                break;
            case Calendar.TUESDAY:
                str_day = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                str_day = "Wednesday";
                break;
            case Calendar.THURSDAY:
                str_day = "Thursday";
                break;
            case Calendar.FRIDAY:
                str_day = "Friday";
                break;
            case Calendar.SATURDAY:
                str_day = "Saturday";
                break;
        }
        String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December" };
        String month = monthName[cal.get(Calendar.MONTH)];
        str_date = str_day + ", " + month + " " + cal.get(Calendar.DATE) + ", " + cal.get(Calendar.YEAR);
        txt_datetime.setText(str_date);

        TextView txt_route = (TextView) findViewById(R.id.txt_route);
        txt_route.setText("Bus Route: " + Integer.toString(user.route));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            UserDatabaseInitializer.executeClear(UserAppDatabase.getAppDatabase(this));
            startActivity(new Intent(StudentHome.this, InitScreen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
