package project.codenicely.behaviour.driving.a1miledrivingapp.trip.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Float2;

import java.util.ArrayList;
import java.util.List;

import project.codenicely.behaviour.driving.a1miledrivingapp.trip.models.LocationData;

/**
 * Created by meghal on 21/5/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tripsManager";

    // Contacts table name
    private static final String TABLE_TRIPS = "trips";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_SPEED = "speed";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRIPS_TABLE = "CREATE TABLE " + TABLE_TRIPS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_LATITUDE + " TEXT,"
                + KEY_LONGITUDE + " TEXT,"
                + KEY_SPEED + " TEXT" + ")";
        db.execSQL(CREATE_TRIPS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);

        // Create tables again
        onCreate(db);
    }

    // Adding new location
    public void addLocation(LocationData locationData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LATITUDE, locationData.getLatitude());
        values.put(KEY_LONGITUDE, locationData.getLongitude());
        values.put(KEY_SPEED, locationData.getSpeed());

        // Inserting Row
        db.insert(TABLE_TRIPS, null, values);
        db.close(); // Closing database connection
    }

    public LocationData getLocationPoint(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TRIPS, new String[] { KEY_ID,
                        KEY_LATITUDE, KEY_LONGITUDE,KEY_SPEED }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        // return contact
        return new LocationData(
                Integer.parseInt(cursor.getString(0)),
                Double.parseDouble(cursor.getString(1)),
                Double.parseDouble(cursor.getString(2)),
                Float.parseFloat(cursor.getString(2)));
    }

    public List<LocationData> getAllLocationPoints() {

        List<LocationData> locationDataList = new ArrayList<LocationData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRIPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LocationData locationData = new LocationData();
                locationData.setTrip_id(Integer.parseInt(cursor.getString(0)));
                locationData.setLatitude(Double.parseDouble(cursor.getString(1)));
                locationData.setLongitude(Double.parseDouble(cursor.getString(2)));
                locationData.setSpeed(Float.parseFloat(cursor.getString(3)));

                // Adding contact to list
                locationDataList.add(locationData);
            } while (cursor.moveToNext());
        }

        // return contact list
        return locationDataList;
    }


    public float getSpeed(double latitude, double longitude) {

        // Select All Query
        float speed=100;
        String selectQuery = "SELECT  * FROM " + TABLE_TRIPS + " WHERE "+
                                     KEY_LATITUDE+"="+String.valueOf(latitude)+" AND " +
                                     KEY_LONGITUDE+"="+String.valueOf(longitude);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                speed=Float.parseFloat(cursor.getString(3));
//                locationData.setSpeed(Float.parseFloat(cursor.getString(3)));

                // Adding contact to list
            } while (cursor.moveToNext());
        }

        // return contact list
        return speed;
    }

}
