/**
 * 
 */
package com.danielgipps.sslog;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Daniel Gipps
 *
 */
public class LiftsAdapter extends ArrayAdapter<LiftClass> {

	public LiftsAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}
	private List<LiftClass> items;

	public LiftsAdapter(Context context, int resource, List<LiftClass> items) {

	    super(context, resource, items);

	    this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    View v = convertView;

	    if (v == null) {

	        LayoutInflater vi;
	        vi = LayoutInflater.from(getContext());
	        v = vi.inflate(R.layout.recent_lift_elem, null);

	    }

	    LiftClass p = items.get(position);

	    if (p != null) {

	        TextView tt = (TextView) v.findViewById(R.id.listElemType);
	        TextView tt1 = (TextView) v.findViewById(R.id.secondLine);
	        ImageView imgCompleted = (ImageView) v.findViewById(R.id.icon);

	        String liftStatus = p.getLiftStatus();
	        
	        if (liftStatus.equals("Completed")) {
	        	imgCompleted.setImageDrawable(new ColorDrawable(Color.GREEN));
	        }
	        else if (liftStatus.equals("Stalled")) {
	        	imgCompleted.setImageDrawable(new ColorDrawable(Color.YELLOW));
	        }
	        else {
	        	imgCompleted.setImageDrawable(new ColorDrawable(Color.RED));
	        }
	        if (tt != null) {
	            tt.setText(p.getLiftType());
	        }
	        if (tt1 != null) {
	        	String weight = Integer.toString(p.getLiftWeight());
	        	weight += " lbs";

	            tt1.setText(weight);
	        }

	    }

	    return v;

	}
	
	public void refreshLifts(List<LiftClass> lifts) {
		this.items = lifts;
	}

}
