package com.example.findmydoc.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.findmydoc.R;
import com.google.android.material.navigation.NavigationView;

public class DocDasboard2 extends AppCompatActivity {


    NavigationView nav;
    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);

        drawer = findViewById(R.id.drawer_layout);
        nav = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.tool_bar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar
                , R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if(item.getItemId() == R.id.prof_item) {
                    Toast.makeText(getApplicationContext(), " Profile selected", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Doctor_Profile.class);
                    startActivity(intent);

//                    case R.id.trans_item:
//                        Toast.makeText(getApplicationContext(), " Transaction History selected", Toast.LENGTH_LONG).show();
//                    case R.id.rides_item:
//                        Toast.zmakeText(getApplicationContext(), " Rides selected", Toast.LENGTH_LONG).show();

                }
                return true;
            }
        });

    }
}