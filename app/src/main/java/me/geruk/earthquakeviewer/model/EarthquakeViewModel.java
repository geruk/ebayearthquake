package me.geruk.earthquakeviewer.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class EarthquakeViewModel extends ViewModel {
    private MutableLiveData<List<Earthquake>> earthquakes;

    public LiveData<List<Earthquake>> getEarthquakes() {
        if (earthquakes == null) {
            earthquakes = new MutableLiveData<>();
        }
        return earthquakes;
    }

    // TODO: Performance improvement: diff between two lists
    public void setEarthquakes(List<Earthquake> eq) {
        if (earthquakes == null) {
            earthquakes = new MutableLiveData<>();
        }
        earthquakes.setValue(eq);
    }
}
