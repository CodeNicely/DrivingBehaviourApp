package project.codenicely.behaviour.driving.a1miledrivingapp.trip.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import project.codenicely.behaviour.driving.a1miledrivingapp.MapsActivity;
import project.codenicely.behaviour.driving.a1miledrivingapp.R;
import project.codenicely.behaviour.driving.a1miledrivingapp.helper.Keys;
import project.codenicely.behaviour.driving.a1miledrivingapp.helper.sqlite.DatabaseHandler;
import project.codenicely.behaviour.driving.a1miledrivingapp.location.models.JourneyData;

public class JourneysActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JourneyAdapter journeyAdapter;

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journeys);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        db = new DatabaseHandler(this);

        List<JourneyData> journeyDataList = db.getAllJourneyPoints();

        Toast.makeText(this, "Size of journey table is " + String.valueOf(journeyDataList.size()), Toast.LENGTH_SHORT).show();

        journeyAdapter = new JourneyAdapter(this, journeyDataList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(journeyAdapter);

        journeyAdapter.notifyDataSetChanged();
    }

    public void openMapsActivity(long journey_id) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(Keys.KEY_JOURNEY_ID, journey_id);
        startActivity(intent);
    }

}