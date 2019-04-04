package com.automation.model;

import java.util.List;

public class ReportSummary {
	
	String casenum = "";
	String passnum = "";
	String failnum = "";
	String jumpnum = "";
	
	long startTime=0;
	long stopTime=0;
	long timeSpan = 0;
	
	//List<Testcase> tc = null;
	
	public String getCasenum() {
		return casenum;
	}
	public void setCasenum(String casenum) {
		this.casenum = casenum;
	}
	public String getPassnum() {
		return passnum;
	}
	public void setPassnum(String passnum) {
		this.passnum = passnum;
	}
	public String getFailnum() {
		return failnum;
	}
	public void setFailnum(String failnum) {
		this.failnum = failnum;
	}
	public String getJumpnum() {
		return jumpnum;
	}
	public void setJumpnum(String jumpnum) {
		this.jumpnum = jumpnum;
	}
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
	public long getTimeSpan() {
		return timeSpan;
	}
	public void setTimeSpan(long timeSpan) {
		this.timeSpan = timeSpan;
	}
	
	
	
}