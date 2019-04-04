package com.automation.model;

import java.util.List;

public class Step {
	
	String stepid = "";
	String stepName = "";
	String stepkeyword = "";
	List<String> argslist = null;
	String stepstatus = "";
	
	public String getStepid() {
		return stepid;
	}
	public void setStepid(String stepid) {
		this.stepid = stepid;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getStepkeyword() {
		return stepkeyword;
	}
	public void setStepkeyword(String stepkeyword) {
		this.stepkeyword = stepkeyword;
	}
	public List<String> getArgslist() {
		return argslist;
	}
	public void setArgslist(List<String> argslist) {
		this.argslist = argslist;
	}
	public String getStepstatus() {
		return stepstatus;
	}
	public void setStepstatus(String stepstatus) {
		this.stepstatus = stepstatus;
	}
	
}
