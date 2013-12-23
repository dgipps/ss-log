package com.danielgipps.sslog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LiftClass test1Lift = new LiftClass("Benchpress", "Completed",new Date() , 135);
        LiftClass test2Lift = new LiftClass("OHP", "Failed",new Date() , 85);
        LiftClass test3Lift = new LiftClass("Squat", "Stalled",new Date() , 145);
        
        List<LiftClass> lifts = new ArrayList<LiftClass>();
        lifts.add(test1Lift);
        lifts.add(test2Lift);
        lifts.add(test3Lift);
        
        ListView yourListView = (ListView) findViewById(R.id.recentWorkout);

     // get data from the table by the ListAdapter
     LiftsAdapter customAdapter = new LiftsAdapter(this, R.layout.recent_lift_elem, lifts);

     yourListView .setAdapter(customAdapter);
        
        
        
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
