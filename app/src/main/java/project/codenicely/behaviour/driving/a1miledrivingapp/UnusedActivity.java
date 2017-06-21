package project.codenicely.behaviour.driving.a1miledrivingapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class UnusedActivity extends Activity implements SensorEventListener
{

    private float[] acceleration = new float[3];

    float time = System.nanoTime();
    float timeOld = System.nanoTime();
    int count = 0;

    private SensorManager sensorManager;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        textview=(TextView)findViewById(R.id.acceleration);
        sensorManager = (SensorManager) this
                .getSystemService(Context.SENSOR_SERVICE);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);

    }

    public void onResume()
    {
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);

        super.onResume();
    }

    public void onPause()
    {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            // Get a local copy of the acceleration measurements
            System.arraycopy(event.values, 0, acceleration, 0,
                    event.values.length);

            time = System.nanoTime();

            // The event timestamps are irregular so we average to determine the
            // update frequency instead of measuring deltas.
            double frequency = count++ / ((time - timeOld) / 1000000000.0);
            textview.setText("Frequency "+String.valueOf(frequency)+"\n\n\n Time"+String.valueOf(time));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        // TODO Auto-generated method stub

    }
}