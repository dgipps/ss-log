package com.danielgipps.sslog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	Button addLift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DatabaseHelper db= new DatabaseHelper(this.getApplicationContext());
        
        List<LiftClass> lifts = db.getNewestLifts();
        
        ListView yourListView = (ListView) findViewById(R.id.recentWorkout);

     // get data from the table by the ListAdapter
        LiftsAdapter customAdapter = new LiftsAdapter(this, R.layout.recent_lift_elem, lifts);

        yourListView.setAdapter(customAdapter);
     
        addLift = (Button) findViewById(R.id.addSession);
        addLift.setOnClickListener(liftHandler);   
        
        
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    View.OnClickListener liftHandler = new View.OnClickListener() {
        public void onClick(View v) {
        	showLiftDialog();
          
        }
      };
      
      private void showLiftDialog() {
          FragmentManager fm = getFragmentManager();
          AddWorkoutDialog addWorkoutDialog = new AddWorkoutDialog();
          addWorkoutDialog.show(fm, "add_lift_frag");
      }
    
}
