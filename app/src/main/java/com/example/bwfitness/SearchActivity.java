package com.example.bwfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.bwfitness.adapter.ListViewAdapter;
import com.example.bwfitness.databinding.ActivitySearchBinding;
import com.example.bwfitness.model.Model;

import java.util.ArrayList;

public class SearchActivity extends NavDrawerBaseActivity{

    ActivitySearchBinding activitySearchBinding;
    ListView list;
    ListViewAdapter adapter;
    String [] title;
    String [] desc;
    int [] image;
    ArrayList<Model> featuresList = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySearchBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(activitySearchBinding.getRoot());
        allocateActivityTitle("Search");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Search");


        title = new String[]{"Overall Booking","Home Page", "PT Booking", "Gym Slot Booking", "Gym Membership Booking",
                "Overall Tracking", "Calorie Tracking", "Workout Tracking", "Progress",
                "Settings", "Payment", "Workout Advice", "Nutrition Advice",
                "Bench Press Progress","Deadlift Progress","Squat Progress","Body Weight Progress"};

        desc = new String[]{"General Booking Page","Access the home page here", "Book your PT here", "Book your Gym Slot here", "Book your Gym Membership here"
                , "General Tracking Page", "Track your Calories here", "Track your Workouts here", "Track your Body Weight and Lifting Progress here"
                , "Settings page", "Payment page", "Check out Workout Advice for all experience levels", "Check out nutrition advice for all weight levels",
        "Check your Bench Press Progress","Check your Deadlift Progress","Check your Squat Progress","Check your Body Weight Progress"};

        image = new int[]{R.drawable.gym_pay,R.drawable.hhhh, R.drawable.gym_pay, R.drawable.gym_pay, R.drawable.gym_pay, R.drawable.gym_track,
                R.drawable.gym_track, R.drawable.gym_track, R.drawable.pt,
                R.drawable.gym_settings, R.drawable.gym_pay, R.drawable.pt, R.drawable.pt,
                R.drawable.bench,R.drawable.dl,R.drawable.squat,R.drawable.bmi};

        list = findViewById(R.id.listView);

        for (int i = 0; i < title.length; i++) {
            Model model = new Model(title[i], desc[i], image[i]);
            featuresList.add(model);
        }

        adapter = new ListViewAdapter(this, featuresList);

        list.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("");
                    list.clearTextFilter();
                } else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.nav_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}