package com.danielgipps.sslog;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME="workouts.db";
	private static final int SCHEMA=1;
	 // Contacts table name
    private static final String TABLE_LIFTS = "lifts";
 
    // Contacts Table Columns names
    private static final String KEY_TYPE = "type";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_STATUS = "status";
    private static final String KEY_DATE = "date";
	

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		db.execSQL("CREATE TABLE " + TABLE_LIFTS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TYPE
				+ " TEXT, " + KEY_WEIGHT + " REAL, "
				+ KEY_STATUS + " TEXT, " + KEY_DATE + " INTEGER);");
		
		LiftClass test1Lift = new LiftClass("Benchpress", "Completed",new Date() , 135);
        LiftClass test2Lift = new LiftClass("OHP", "Failed",new Date() , 85);
        LiftClass test3Lift = new LiftClass("Squat", "Stalled",new Date() , 145);
        
        addLift(test1Lift, db);
        addLift(test2Lift, db);
        addLift(test3Lift, db);
        
 //       db.close();
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void addLiftExt(LiftClass aLift) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		addLift(aLift,db);
	}
	private void addLift(LiftClass aLift, SQLiteDatabase db) {

		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_TYPE, aLift.getLiftType()); 
	    values.put(KEY_WEIGHT, aLift.getLiftWeight());
	    values.put(KEY_STATUS, aLift.getLiftStatus());
	    values.put(KEY_DATE, aLift.getLiftDate().getTime());
	    // Inserting Row
	    db.insert(TABLE_LIFTS, null, values);
	    
	}
	
	public ArrayList<LiftClass> getNewestLifts() {
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_LIFTS + " ORDER BY " + KEY_DATE + " DESC LIMIT 8;", null);
		
		ArrayList<LiftClass> lifts = new ArrayList<LiftClass>();
		
		if (cursor.moveToFirst()) {
	        do {
	        	String type = cursor.getString(1);
	        	int weight = cursor.getInt(2);
	        	String status = cursor.getString(3);
	        	Date date = new Date();
	        	date.setTime(cursor.getInt(4));
	        	
	            LiftClass aLift = new LiftClass(type, status, date, weight);


	            lifts.add(aLift);
	        } while (cursor.moveToNext());
	    }
		
		db.close();
		
		return lifts;
		
	}

}
