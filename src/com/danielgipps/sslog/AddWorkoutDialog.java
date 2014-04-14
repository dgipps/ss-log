package com.danielgipps.sslog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddWorkoutDialog extends DialogFragment{

	private AutoCompleteTextView liftType;
	private Button pickDate;
	private Button addLift;
	private Spinner completed;
	private TextView dateDisplay;
	private TextView weightText;
	private EditText setText;
	private EditText repText;
	
	private int yearOf;
	private int month;
	private int day;
	static final int DATE_PICKER_ID = 1111;
	
	private MainActivity activity;

    public AddWorkoutDialog() {
        // Empty constructor required for DialogFragment
    }
    
    public AddWorkoutDialog(MainActivity act) {
    	activity = act;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_lift_frag, container);
        
        
        liftType = (AutoCompleteTextView) view.findViewById(R.id.workoutType);
        pickDate = (Button) view.findViewById(R.id.dateButton);
        dateDisplay = (TextView) view.findViewById(R.id.dateDisplay);
        addLift = (Button) view.findViewById(R.id.addbutton);
        completed = (Spinner) view.findViewById(R.id.completedSpinner);
        weightText = (EditText) view.findViewById(R.id.weight);
        setText = (EditText) view.findViewById(R.id.fieldSets);
        repText = (EditText) view.findViewById(R.id.fieldReps);
        
        
        final DatabaseHelper db= new DatabaseHelper(getActivity().getApplicationContext());
        
        final List<String> autoLift = db.getLiftTypes();
        
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_dropdown_item_1line, autoLift);
        
        liftType.setAdapter(typeAdapter);
        
        getDialog().setTitle(R.string.add_workout_title);
        final Calendar c = Calendar.getInstance();
        yearOf = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        
        final DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener, 
                yearOf, month,day);

        pickDate.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
                dialog.show();
            }
        });
        
        updateDisplay();
        
        addLift.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String lift = liftType.getText().toString();
				String liftStatus = completed.getSelectedItem().toString();
				Integer weight = Integer.parseInt(weightText.getText().toString());
				Integer sets = Integer.parseInt(setText.getText().toString());
				Integer reps = Integer.parseInt(repText.getText().toString());
				
				Calendar c = Calendar.getInstance();
				c.set(yearOf,month,day,0,0);
				
				Date date = c.getTime();
				
				LiftClass aLift = new LiftClass(lift, liftStatus, date, weight, 1, reps, sets);
				db.addLiftExt(aLift);
				
				activity.refreshList();
				dismiss();
				
			}
			
		});

        return view;
    }
    

    
    private DatePickerDialog.OnDateSetListener datePickerListener 
    = new DatePickerDialog.OnDateSetListener() {

    	public void onDateSet(DatePicker view, int selectedYear,
    			int selectedMonth, int selectedDay) {
    		yearOf = selectedYear;
    		month = selectedMonth;
    		day = selectedDay;

    		updateDisplay();
    	}
    };
    
    private void updateDisplay() {
    	dateDisplay.setText(
    			new StringBuilder()
    			.append(month + 1).append("-")
                .append(day).append("-")
                .append(yearOf).append(" "));
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    }

}
