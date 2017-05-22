package project.codenicely.behaviour.driving.a1miledrivingapp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by meghal on 5/3/17.
 */

public class SharedPrefs {

    private static final String PREF_NAME = "SharedPreference";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USER_ID = "userId";

    private static String TAG = "Shared Preference";
    private static final String KEY_IS_TRIP_ONGOING = "on_going_trip" ;
    private static final String KEY_DISTRACTED_TIME = "time";


    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;


    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setTripOngoing(boolean isFirstTimeUser) {

        editor.putBoolean(KEY_IS_TRIP_ONGOING, isFirstTimeUser);
        editor.commit();
    }

    public boolean isTripOngoing() {
        return pref.getBoolean(KEY_IS_TRIP_ONGOING, false);
    }

    public int getDistractedTime(){
        return pref.getInt(KEY_DISTRACTED_TIME,0);
    }

    public void setKeyDistractedTime(int time){

        editor.putInt(KEY_DISTRACTED_TIME, time);
        editor.commit();


    }


    public String getUserId() {

        return pref.getString(KEY_USER_ID, "Not Available");

    }

    public void setUserId(String userId) {

        editor.putString(KEY_USER_ID, userId);
        editor.commit();

    }

}
