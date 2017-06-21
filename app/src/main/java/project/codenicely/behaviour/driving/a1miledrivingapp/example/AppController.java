package project.codenicely.behaviour.driving.a1miledrivingapp.example;

import android.app.Application;


public class AppController extends Application {

    private PredictIOManager mPredictIOManager;

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        //PredictIO SDK code
        mPredictIOManager = new PredictIOManager(this);
        mPredictIOManager.onApplicationCreate();
    }
}