package project.codenicely.behaviour.driving.a1miledrivingapp.trip.models;

/**
 * Created by meghal on 21/5/17.
 */

public class LocationData {

    private int trip_id;
    private long timestamp;
    private double latitude;
    private double longitude;
    private float speed;


    public LocationData(int trip_id, long timestamp, double latitude, double longitude, float speed) {
        this.trip_id = trip_id;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
    }


    public LocationData() {

    }

    public int getTrip_id() {
        return trip_id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
