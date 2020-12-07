package me.geruk.earthquakeviewer.model;

import java.time.LocalDateTime;

import me.geruk.earthquakeviewer.util.TimeFormatterUtil;

public class Earthquake implements Comparable<Earthquake> {
    String id;
    LocalDateTime time;
    double depth;
    double latitude, longitude;
    double magnitude;

    public Earthquake(String id, LocalDateTime time, double depth, double lat, double lng, double magnitude) {
        this.id = id;
        this.time = time;
        this.depth = depth;
        this.latitude = lat;
        this.longitude = lng;
        this.magnitude = magnitude;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public double getDepth() {
        return depth;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getMagnitude() {
        return magnitude;
    }

    @Override
    public int compareTo(Earthquake other) {
        if (this.magnitude != other.magnitude) {
            return Double.compare(this.magnitude, this.magnitude);
        }
        return this.id.compareTo(other.id);
    }

    public static class EarthquakeBuilder {
        private String id;
        private LocalDateTime time;
        private double depth;
        private double lat;
        private double lng;
        private double magnitude;

        public Earthquake.EarthquakeBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public Earthquake.EarthquakeBuilder setTime(String time) {
            this.time = TimeFormatterUtil.convertTime(time);
            return this;
        }

        public Earthquake.EarthquakeBuilder setTime(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public Earthquake.EarthquakeBuilder setDepth(double depth) {
            this.depth = depth;
            return this;
        }

        public Earthquake.EarthquakeBuilder setLatitude(double lat) {
            this.lat = lat;
            return this;
        }

        public Earthquake.EarthquakeBuilder setLongitude(double lng) {
            this.lng = lng;
            return this;
        }

        public Earthquake.EarthquakeBuilder setMagnitude(double magnitude) {
            this.magnitude = magnitude;
            return this;
        }

        public Earthquake createEarthquake() {
            return new Earthquake(id, time, depth, lat, lng, magnitude);
        }
    }
}
