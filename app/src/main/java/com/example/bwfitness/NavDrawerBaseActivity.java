package com.example.bwfitness;

import android.content.Intent;

import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class NavDrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout)getLayoutInflater().inflate(R.layout.activity_nav_drawer_base,null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(this,HomePageActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_booking:
                startActivity(new Intent(this,BookingActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_tracking:
                startActivity(new Intent(this,TrackingActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_progress:
                startActivity(new Intent(this, GymProgressActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_advice:
                startActivity(new Intent(this,OverallActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_advicenutrition:
                startActivity(new Intent(this,OverallNutritionActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_settings:
                startActivity(new Intent(this,SettingsActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.action_search:
                startActivity(new Intent(this,SearchActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_logout:
                startActivity(new Intent(this,MainActivity.class));
                overridePendingTransition(0,0);
                break;
        }
        return false;
    }

    protected void allocateActivityTitle(String titleString) {
        if(getSupportActionBar() !=null){
            getSupportActionBar().setTitle(titleString);
        }
    }
}