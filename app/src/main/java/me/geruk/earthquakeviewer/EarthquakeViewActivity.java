package me.geruk.earthquakeviewer;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.time.format.DateTimeFormatter;

import me.geruk.earthquakeviewer.controller.EarthquakeListController;
import me.geruk.earthquakeviewer.model.Earthquake;
import me.geruk.earthquakeviewer.model.EarthquakeViewModel;
import me.geruk.earthquakeviewer.util.TimeFormatterUtil;

public class EarthquakeViewActivity extends AppCompatActivity implements OnMapReadyCallback {

    private EarthquakeListController controller;
    private SupportMapFragment mapFragment;
    private Earthquake eq;

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

    public void onOpenEarthquakeDetail(Earthquake earthquake) {
        eq = earthquake;
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
        }
        mapFragment.getMapAsync(this);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, mapFragment, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(eq.getLatitude(), eq.getLongitude());
        MarkerOptions marker = new MarkerOptions()
                .position(latLng)
                .title(eq.getTime().format(DateTimeFormatter.ofPattern(TimeFormatterUtil.TIME_FORMAT)))
                .snippet(getString(R.string.magnitude, eq.getMagnitude()));
        googleMap.addMarker(marker).showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 4.0f));
    }
}
