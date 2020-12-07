package me.geruk.earthquakeviewer.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.geruk.earthquakeviewer.R;
import me.geruk.earthquakeviewer.model.Earthquake;
import me.geruk.earthquakeviewer.model.EarthquakeViewModel;
import me.geruk.earthquakeviewer.network.GetEarthquakeListAsyncTask;

public class EarthquakeListController {

    private final EarthquakeViewModel earthquakeData;

    public EarthquakeListController(EarthquakeViewModel data) {
        earthquakeData = data;
    }

    public void startDefaultRequest() {
        new GetEarthquakeListAsyncTask(new GetEarthquakeListAsyncTask.Callback() {
            @Override
            public void onSuccess(List<Earthquake> earthquakes) {
                earthquakeData.setEarthquakes(earthquakes);
            }

            @Override
            public void onFailure() {

            }
        }).execute();
    }

    // TODO: delete
    private ArrayList<Earthquake> createTempList() {
        ArrayList<Earthquake> eqList = new ArrayList<>();
        eqList.add(
                new Earthquake.EarthquakeBuilder()
                        .setId("c0001xgp")
                        .setDepth(24.4)
                        .setLatitude(38.322)
                        .setLongitude(142.369)
                        .setMagnitude(8.8)
                        .setTime("2011-03-11 04:46:23")
                        .createEarthquake());
        eqList.add(
                new Earthquake.EarthquakeBuilder()
                        .setId("c0001xgb")
                        .setDepth(24.4)
                        .setLatitude(38.322)
                        .setLongitude(142.369)
                        .setMagnitude(8.8)
                        .setTime("2011-03-11 04:46:24")
                        .createEarthquake());
        eqList.add(
                new Earthquake.EarthquakeBuilder()
                        .setId("c0001xga")
                        .setDepth(24.4)
                        .setLatitude(38.322)
                        .setLongitude(142.369)
                        .setMagnitude(8.8)
                        .setTime("2011-03-11 04:46:25")
                        .createEarthquake());
        return eqList;
    }

}
