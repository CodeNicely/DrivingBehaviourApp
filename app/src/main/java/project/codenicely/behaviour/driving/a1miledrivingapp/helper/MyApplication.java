package project.codenicely.behaviour.driving.a1miledrivingapp.helper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import io.predict.PIOTripSegment;
import io.predict.PredictIO;
import io.predict.PredictIOListener;
import project.codenicely.behaviour.driving.a1miledrivingapp.example.PredictIOManager;
import project.codenicely.behaviour.driving.a1miledrivingapp.location.NewLocationActivity;

/**
 * Created by meghalagrawal on 05/06/17.
 */

public class MyApplication extends MultiDexApplication implements PredictIOListener,
        PredictIO.PIOActivationListener {
    private PredictIOManager mPredictIOManager;

    private Context context;
    @Override
    public void onCreate() {
        context=getApplicationContext();

        super.onCreate();

        mPredictIOManager = new PredictIOManager(this);
        mPredictIOManager.onApplicationCreate();

        try {
            startPredictIO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startPredictIO() throws Exception {
        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Start Predict IO"));

        // Instantiate predict.io SDK
        PredictIO predictIO = PredictIO.getInstance(getApplicationContext());
        predictIO.setAppOnCreate(this);
        // Set events listener
        predictIO.setListener(this);
        // Start tracking
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("No permission"));

            return;
        }
        predictIO.start(this);

    }

    @Override
    public void onTerminate() {
        EventBus.getDefault().unregister(context);
        super.onTerminate();
    }

    void showMessage(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivated() {

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Activated"));

            }
        }, 1000);


    }

    @Override
    public void onActivationFailed(int i) {
        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Activatation Failed"));

    }

    @Override
    public void departed(PIOTripSegment pioTripSegment) {
        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Departed"));


    }

    @Override
    public void searchingInPerimeter(Location location) {
        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Searching in perimeter"));


    }

    @Override
    public void suspectedArrival(PIOTripSegment pioTripSegment) {
        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Suspected Arrival"));


    }

    @Override
    public void arrived(PIOTripSegment pioTripSegment) {
        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Arrived"));


    }

    @Override
    public void beingStationaryAfterArrival(PIOTripSegment pioTripSegment) {

        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Begin stationary after arrival"));


    }

    @Override
    public void canceledDeparture(PIOTripSegment pioTripSegment) {

        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Cancel Departure"));


    }

    @Override
    public void didUpdateLocation(Location location) {

        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Did location update"));


    }

    @Override
    public void detectedTransportationMode(PIOTripSegment pioTripSegment) {

        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Detect transportation mode"));


    }

    @Override
    public void traveledByAirplane(PIOTripSegment pioTripSegment) {

        EventBus.getDefault().post(new NewLocationActivity.PredictIoEvent("Travel by airplane"));


    }


}