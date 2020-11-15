package com.example.findmydoc.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findmydoc.CustomAlertDialog;
import com.example.findmydoc.R;
import com.google.android.material.navigation.NavigationView;

public class DoctorDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    ProgressDialog progressDialog;
    DrawerLayout drawer;
    ImageView menu, menu2;
    ActionBarDrawerToggle toggle;
    TextView doctor_name;
    ActionBar actionBar;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        fragmentManager=getSupportFragmentManager();

        actionBar=getSupportActionBar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        title=findViewById(R.id.title);
        setSupportActionBar(toolbar);
        progressDialog=new ProgressDialog(DoctorDashboard.this);
        progressDialog.setMessage("Please Wait...");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        menu=findViewById(R.id.menu_icon);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(drawer.isDrawerOpen(GravityCompat.START)){
                    menu.setImageDrawable(getResources().getDrawable(R.drawable.back));
                    drawer.closeDrawer(GravityCompat.START);
                }
                else {
                    drawer.openDrawer(GravityCompat.START);
                    menu.setImageDrawable(getResources().getDrawable(R.drawable.ic_nav));
                }
            }
        });
        drawer.openDrawer(GravityCompat.START);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(DoctorDashboard.this);
        View navheader=navigationView.getHeaderView(0);
        doctor_name=navheader.findViewById(R.id.doctor_name);
        changeFragmentView(new DoctorHomePanel());
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        Fragment fragment = null;
        if (id == R.id.home) {
            title.setText("Home");
            fragment=new DoctorHomePanel();
            changeFragmentView(fragment);

        }
        else if (id == R.id.prof_item) {

            title.setText("Profile");
            fragment=new DoctorProfile();
            changeFragmentView(fragment);

        }
        else if (id == R.id.patient_list) {

            title.setText("My Patient");
            fragment=new PatientList();
            changeFragmentView(fragment);

        }


        return true;
    }

    @Override
    public void onBackPressed() {

        int count = fragmentManager.getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(count==1){

            CustomAlertDialog.getInstance().show_exit_dialog(DoctorDashboard.this);
        }
        else {

            super.onBackPressed();
        }


    }

    public void changeFragmentView(Fragment fragment){

        fragmentManager =getSupportFragmentManager();
        fragmentManager.popBackStack();
        int count = fragmentManager.getBackStackEntryCount();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment)
                .addToBackStack(null).commit();
        drawer.closeDrawer(GravityCompat.START);
    }

}