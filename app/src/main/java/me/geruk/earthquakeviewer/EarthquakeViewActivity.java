package me.geruk.earthquakeviewer;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import me.geruk.earthquakeviewer.controller.EarthquakeListController;
import me.geruk.earthquakeviewer.model.EarthquakeViewModel;

public class EarthquakeViewActivity extends AppCompatActivity {

    EarthquakeListController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, EarthquakeListFragment.class, null)
                    .commit();
        }

        controller = new EarthquakeListController(new ViewModelProvider(this).get(EarthquakeViewModel.class));
        controller.startDefaultRequest();
    }

}
