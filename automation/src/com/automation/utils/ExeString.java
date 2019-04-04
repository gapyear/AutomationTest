package com.automation.utils;

import com.automation.keywords.KeyWords;
import com.automation.model.Step;
import com.automation.model.Steps;
import com.automation.model.Testcase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.w3c.dom.Document;

import net.sf.json.JSONObject;

public class ExeString {
	
	public static Testcase runExcel(String filePath,Map<String,Object> caseinfo) throws Exception{
		
		List<JSONObject> data = ExcelUtils.getData(filePath);
		
		Map<String, Object> map = new HashMap<>();
		String expression = "";
		
		List<String> stepstatusList = new ArrayList<String>();
		Steps steps = new Steps();
		Testcase tc = new Testcase();		

		List<Step> stepList = new ArrayList<Step>();
		
		File file = WriteToXML.init();
		Document doc = WriteToXML.createDoc(file);
		
		int itemindex = 0;//步骤编号
		long timeSpan = 0;//步骤执行时间
		long startTime = 0;
		long stopTime = 0;
		
		for(JSONObject item : data){
			
			map.put("kw", KeyWords.class);
			String keyword = (String) item.get("keyword");
			map.put(keyword, keyword);
			String argstring = item.getString("args").toString();
			argstring = argstring.substring(1, argstring.length()-1);
			//System.out.println(argstring);
			map.put(argstring, argstring);
			expression = "kw."+keyword+"("+argstring+")";
			
			Step step = new Step();
			
			//设置每个步骤的id
			itemindex++;
			if(itemindex<10){
				step.setStepid("00"+(itemindex));
			}else{
				step.setStepid("0"+(itemindex));
			}
			
			//设置每个步骤的名称
			step.setStepName(item.getString("description"));
			//设置每个步骤的keyword
			step.setStepkeyword(keyword);
			//设置每个步骤的参数
			step.setArgslist((List<String>) item.get("args"));
			
			//开始执行步骤时间
			startTime =fromDateStringToLong(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
			//执行步骤，返回该步骤执行的状态
			String stepstatus = (String) invokeMethod(expression, map);
			//结束执行步骤时间
			stopTime = fromDateStringToLong(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
			//执行步骤的总时间
			
			timeSpan += (stopTime - startTime);
			//设置每个步骤执行后的状态
			step.setStepstatus(stepstatus);
			//将每个步骤执行后的状态添加到List里
			stepstatusList.add(step.getStepstatus());
			
			//执行完后每个步骤将这个step对象存到一个对象list里，当for循环完后统一写入xml
			stepList.add(step);
			
		}
		
		
		//执行完后steps添加step
		steps.setStep(stepList);
		
		tc.setCasename(caseinfo.get("casename").toString());
		
		if(Integer.parseInt(caseinfo.get("casenum").toString())<10){
			String casenumber="00"+caseinfo.get("casenum").toString();
			tc.setCasenumber(casenumber);
		}else{
			String casenumber = "0"+caseinfo.get("casenum").toString();
			tc.setCasenumber(casenumber);
		}
		tc.setSteps(steps);
		tc.setCasetime(Long.toString(timeSpan)+"ms");
		
		if(stepstatusList.contains("failed")){
			tc.setCasestatus("failed");
		}else {
			tc.setCasestatus("passed");
		}
		
		tc.setStartTime(startTime);
		tc.setStopTime(stopTime);
		
		WriteToXML.createTestCase(file,doc,tc);
		
		return tc;
		
	}
	
	private static long fromDateStringToLong(String inVal) {

		Date date = null;
	    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
	    try {
	        date = inputFormat.parse(inVal);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return date.getTime();

	}

	public static Object invokeMethod(String jexlExp,Map<String,Object> map){
		  JexlEngine jexl=new JexlBuilder().create();
		  
		  JexlExpression e = jexl.createExpression(jexlExp);
		  
		  JexlContext jc = new MapContext();
		  
		  for(String key:map.keySet()){
		   jc.set(key, map.get(key));
		  }

		  Object c = e.evaluate(jc);
				  
		  if(null==c){
			
			  return "";
		  }

		  return c;
	}
	
	
}
