package com.automation.testsuite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.automation.utils.ExeString;
import com.automation.utils.TranslateXmlToHtml;
import com.automation.utils.WriteToXML;

import net.sf.json.JSONObject;

public class ThreadRunCases implements Runnable{
	
	private Thread t;
	private String casepath;
	
	ThreadRunCases(String casepaths){
		casepath = casepaths;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			/*List<JSONObject> reportSummary = new ArrayList<>();
			String totalcase = "";
			int passnumber = 0;
			int failnumber = 0;
			int jumpnumber = 0;
			String starting = "";
			long running = 0;
			String ending = "";
			

			
			
			int casenum = 0;
		
				
				Map<String,Object> caseinfo = new HashMap<>();
				
				JSONObject casesummary = new JSONObject();
				
				casenum++;
				
				caseinfo.put("casenum", casenum);
				caseinfo.put("casename", casepath);
				*/
				//casesummary = ExeString.runExcel("D://automation//"+casepath+".xlsx",caseinfo);
				Map<String,Object> caseinfo = new HashMap<>();
				caseinfo.put("casenum", 0);
				caseinfo.put("casename", this.casepath);
				ExeString.runExcel("D://automation//"+this.casepath+".xlsx",caseinfo);
				//WriterQueue.getQueue().put
				/*reportSummary.add(casesummary);
			
			
			totalcase = String.valueOf(reportSummary.size());
			for(JSONObject item : reportSummary){
				if(item.getString("status").equals("passed")){
					passnumber++;
				}else if(item.getString("status").equals("failed")){
					failnumber++;
				}else if(item.getString("status").equals("jumped")){
					jumpnumber++;
				}
				
				running+=item.getLong("runtime");
			}
			starting = new Date(reportSummary.get(0).getLong("starttime")).toString();
			ending = new Date(reportSummary.get(reportSummary.size()-1).getLong("starttime")).toString();
			
			//创建报告汇总xml
			WriteToXML.createReportSummary(totalcase,String.valueOf(passnumber),String.valueOf(failnumber),String.valueOf(jumpnumber),starting,(running/1000)+"s",ending);
			
			//xml转换成html
			TranslateXmlToHtml.runTranslate();*/
			
			//发送报告
			//SendReport.sendReport();
	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void start(){
		System.out.println("启动线程");
		
		if(t==null){
			Thread t = new Thread(this);
			t.start();
		}
		
	}

}
