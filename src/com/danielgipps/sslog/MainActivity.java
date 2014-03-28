package com.danielgipps.sslog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	private Button addLift;
	
	private ListView yourListView;
	LiftsAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final DatabaseHelper db= new DatabaseHelper(this.getApplicationContext());
        
        List<LiftClass> lifts = db.getNewestLifts();
        
        yourListView = (ListView) findViewById(R.id.recentWorkout);

     // get data from the table by the ListAdapter
        customAdapter = new LiftsAdapter(this, R.layout.recent_lift_elem, lifts);

        yourListView.setAdapter(customAdapter);
     
        addLift = (Button) findViewById(R.id.addSession);
        addLift.setOnClickListener(liftHandler);   
        
        yourListView.setOnItemLongClickListener(new OnItemLongClickListener() {
        	public boolean onItemLongClick(AdapterView<?> p, View view, int pos, long id) {
        		ActionMode mActionMode = startActionMode(mActionModeCallback);
        		LiftClass aLift = (LiftClass) p.getAdapter().getItem(pos);
        		mActionMode.setTag(String.valueOf(aLift.getSqlId()));
        		view.setSelected(true);
        		
        		return true;
        	}
        });
      }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	refreshList();
    }
    
    public void refreshList() {
    	DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
    	customAdapter.clear();
    	customAdapter.addAll(db.getNewestLifts());
    	customAdapter.notifyDataSetChanged();
    }
    
    View.OnClickListener liftHandler = new View.OnClickListener() {
        public void onClick(View v) {
        	showLiftDialog();
          
        }
      };
      
      private void showLiftDialog() {
          FragmentManager fm = getFragmentManager();
          AddWorkoutDialog addWorkoutDialog = new AddWorkoutDialog(this);
          addWorkoutDialog.show(fm, "add_lift_frag");
          
      }
      
      private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

    	    // Called when the action mode is created; startActionMode() was called
    	    @Override
    	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    	        // Inflate a menu resource providing context menu items
    	        MenuInflater inflater = mode.getMenuInflater();
    	        inflater.inflate(R.menu.context_menu, menu);
    	        return true;
    	    }

    	    // Called each time the action mode is shown. Always called after onCreateActionMode, but
    	    // may be called multiple times if the mode is invalidated.
    	    @Override
    	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    	        return false; // Return false if nothing is done
    	    }

    	    // Called when the user selects a contextual menu item
    	    @Override
    	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
    	        String id = (String) mode.getTag();
    	        DatabaseHelper db = new DatabaseHelper(getApplication());
    	    	
    	    	switch (item.getItemId()) {
    	            case R.id.context_delete:
    	                db.deleteLift(Integer.parseInt(id));
    	                
    	                
    	                mode.finish(); // Action picked, so close the CAB
    	                return true;
    	            default:
    	                return false;
    	        }
    	    }

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				MainActivity.this.refreshList();
				
			}

    	};
    	
    	
    
}
