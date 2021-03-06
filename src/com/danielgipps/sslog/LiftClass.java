/**
 * 
 */
package com.danielgipps.sslog;

import java.util.Date;

/**
 * @author Daniel Gipps
 *
 */
public class LiftClass {
	
	private String liftType;
	
	private Date liftDate;
	
	private int liftWeight;
	
	private int liftReps;
	
	private int liftSets;
	
	private String liftStatus;
	
	private int sqlId;
	
	public int getSqlId() {
		return sqlId;
	}

	public LiftClass(String liftType, String liftStatus, Date liftDate, int liftWeight, int id, int sets, int reps) {
		this.liftType = liftType;
		this.liftStatus = liftStatus;
		this.liftDate = liftDate;
		this.liftWeight = liftWeight;
		this.sqlId = id;
		this.setReps(reps);
		this.setSets(sets);
	}

	public String getLiftType() {
		return liftType;
	}

	public void setLiftType(String liftType) {
		this.liftType = liftType;
	}

	public Date getLiftDate() {
		return liftDate;
	}

	public void setLiftDate(Date liftDate) {
		this.liftDate = liftDate;
	}

	public Integer getLiftWeight() {
		return liftWeight;
	}

	public void setLiftWeight(Integer liftWeight) {
		this.liftWeight = liftWeight;
	}

	public String getLiftStatus() {
		return liftStatus;
	}

	public void setLiftStatus(String liftStatus) {
		this.liftStatus = liftStatus;
	}

	public int getReps() {
		return liftReps;
	}

	public void setReps(int liftReps) {
		this.liftReps = liftReps;
	}

	public int getSets() {
		return liftSets;
	}

	public void setSets(int liftSets) {
		this.liftSets = liftSets;
	}
	
	

}
