package me.geruk.earthquakeviewer.network;

import android.os.AsyncTask;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import me.geruk.earthquakeviewer.model.Earthquake;
import me.geruk.earthquakeviewer.util.EarthquakeJSONHelper;

public class GetEarthquakeListAsyncTask extends AsyncTask<EarthquakeParameter, Void, List<Earthquake>> {

    private static final String API_URL = "http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman";

    public static interface  Callback {
        void onSuccess(List<Earthquake> earthquakes);
        void onFailure();
    }

    public GetEarthquakeListAsyncTask(Callback callback) {
        this.callback = callback;
    }

    private final Callback callback;

    @Override
    protected @Nullable
    List<Earthquake> doInBackground(EarthquakeParameter... earthquakeParameters) {
        HttpURLConnection urlConnection = null;
        String result = "";
        try {
            URL url = new URL(API_URL);
            urlConnection = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            result = br.lines().collect(Collectors.joining());
        } catch (Exception e) {
            // TODO gracefully error
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return EarthquakeJSONHelper.parseJSON(result);
    }

    @Override
    protected void onPostExecute(List<Earthquake> result) {
        callback.onSuccess(result);
    }

}
