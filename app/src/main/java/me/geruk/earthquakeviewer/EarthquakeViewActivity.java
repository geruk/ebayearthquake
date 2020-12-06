package me.geruk.earthquakeviewer;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EarthquakeViewActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        initializeData();
    }

    /**
     * set up default list of earthquakes
     */
    private void initializeData() {

    }

}
