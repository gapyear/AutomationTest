package com.automation.testsuite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.automation.model.ReportSummary;
import com.automation.model.Testcase;
import com.automation.utils.*;

import net.sf.json.JSONObject;

public class RunCases {

	public static void main(String[] args) throws Exception {
		
		//清空xml
		WriteToXML.clearReportTestcase();
		
		ReportSummary rs = new ReportSummary();
		List<Testcase> testcases = new ArrayList<Testcase>();
		
		List<String> casepath = new ArrayList<String>();
		casepath.add("case1_login");
		casepath.add("case2_addUser");
		casepath.add("case3_editUser");
		
		int casenum = 0;
		int passnum = 0;
		int failnum = 0;
		int jumpnum = 0;
		long timeSpan = 0;
		long startTime = 0;
		long stopTime = 0;
		for(String item : casepath){
			
			Map<String,Object> caseinfo = new HashMap<>();
			
			casenum++;
			caseinfo.put("casenum", casenum);
			caseinfo.put("casename", item);
			
			Testcase tc = new Testcase();
			tc = ExeString.runExcel("D://automation//"+item+".xlsx",caseinfo);
			testcases.add(tc);
		}
		
		rs.setCasenum(Integer.toString(casepath.size()));
		
		for(Testcase item:testcases){
			if(item.getCasestatus().equals("passed")){
				passnum++;
			}else if(item.getCasestatus().equals("failed")){
				failnum++;
			}else if(item.getCasestatus().equals("jumped")){
				jumpnum++;
			}
			
		}
		
		startTime = testcases.get(0).getStartTime();
		stopTime =  testcases.get(testcases.size()-1).getStopTime();
		timeSpan = stopTime - startTime;
		
		rs.setPassnum(Integer.toString(passnum));
		rs.setFailnum(Integer.toString(failnum));
		rs.setJumpnum(Integer.toString(jumpnum));
		
		rs.setStartTime(startTime);
		rs.setStopTime(stopTime);
		rs.setTimeSpan(timeSpan);	
			
		//创建报告汇总xml
		WriteToXML.createReportSummary(rs);
		//xml转换成html
		TranslateXmlToHtml.runTranslate();
		//发送报告
		//SendReport.sendReport();
	}

}
