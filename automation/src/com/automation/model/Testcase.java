package com.automation.model;

import java.util.List;

public class Testcase {
	
	String casenumber="";
	String casename="";
	Steps steps = null;
	
	long startTime = 0;
	long stopTime = 0;
	String casetime="";
	String casestatus = "";
	
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getStopTime() {
		return stopTime;
	}
	public void setStopTime(long stopTime) {
		this.stopTime = stopTime;
	}
	
	public String getCasenumber() {
		return casenumber;
	}
	public void setCasenumber(String casenumber) {
		this.casenumber = casenumber;
	}
	public String getCasename() {
		return casename;
	}
	public void setCasename(String casename) {
		this.casename = casename;
	}
	
	public Steps getSteps() {
		return steps;
	}
	public void setSteps(Steps steps) {
		this.steps = steps;
	}
	public String getCasetime() {
		return casetime;
	}
	public void setCasetime(String casetime) {
		this.casetime = casetime;
	}
	public String getCasestatus() {
		return casestatus;
	}
	public void setCasestatus(String casestatus) {
		this.casestatus = casestatus;
	}
	
}
