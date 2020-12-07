package me.geruk.earthquakeviewer.util;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.geruk.earthquakeviewer.model.Earthquake;

public class EarthquakeJSONHelper {

    public static @Nullable
    List<Earthquake> parseJSON(String jsonString) {
        ArrayList<Earthquake> eqs = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonString);
            JSONArray earthquakes = data.getJSONArray("earthquakes");
            for (int i = 0; i < earthquakes.length(); i++) {
                JSONObject earthquake = earthquakes.getJSONObject(i);
                eqs.add(new Earthquake.EarthquakeBuilder()
                        .setMagnitude(earthquake.getDouble("magnitude"))
                        .setLongitude(earthquake.getDouble("lng"))
                        .setLatitude(earthquake.getDouble("lat"))
                        .setDepth(earthquake.getDouble("depth"))
                        .setId(earthquake.getString("eqid"))
                        .setTime(earthquake.getString("datetime"))
                        .createEarthquake());
            }
            return eqs;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
