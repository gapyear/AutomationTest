package com.automation.utils;
import com.automation.keywords.KeyWords;

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
import org.w3c.dom.Element;

import net.sf.json.JSONObject;

public class ExeString2 {
	
	/*public static JSONObject runExcel(String filePath,Map<String,Object> caseinfo) throws Exception{
		
		//ExcelUtils eu = new ExcelUtils();
		
		List<JSONObject> data = ExcelUtils.getData(filePath);
		
		Map<String, Object> map = new HashMap<>();
		String expression = "";
		//Testa a = new Testa();
		//KeyWords kw = new KeyWords();
		String stepid = "";
		String stepName = "";
		String stepkeyword = "";
		List<String> argslist = null;
		String stepstatus = "";
		List<String> stepstatusList = new ArrayList<String>();
		String casestatus = "";
		Map<String,Object> info = null;
		List<Element> stepList = new ArrayList<Element>();
		String casenumber="";
		String casename="";
		String casetime="";
		JSONObject summary = new JSONObject();//返回的json，存每个case运行信息
		
		File file = WriteToXML.init();
		Document doc = WriteToXML.createDoc(file);
		long startTime=0;
		long stopTime=0;
		long timeSpan = 0;
		
		int itemindex = 0;//步骤编号
		for(JSONObject item : data){
			itemindex++;
			map.put("kw", KeyWords.class);
			String keyword = (String) item.get("keyword");
			map.put(keyword, keyword);
			argslist = (List<String>) item.get("args");
		
			String argstring = item.getString("args").toString();
			argstring = argstring.substring(1, argstring.length()-1);
			//System.out.println(argstring);
			map.put(argstring, argstring);
			expression = "kw."+keyword+"("+argstring+")";
			
			if(itemindex<10){
				stepid = "00"+(itemindex);
			}else{
				stepid = "0"+(itemindex);
			}
			
			stepName = item.getString("description");
			stepkeyword = keyword;
			
			//argslist.clear();
			//argslist.add(argstring);
			startTime =fromDateStringToLong(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
			
			stepstatus = (String) invokeMethod(expression, map);
			
			stopTime = fromDateStringToLong(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
			
			
			timeSpan += (stopTime - startTime);
			
			
			
			stepstatusList.add(stepstatus);
			//System.out.println(stepstatus);
			info = WriteToXML.createStep(file,doc,stepid,stepName,stepkeyword,argslist,stepstatus);
			stepList.add((Element)info.get("step"));
			
			
			
			if(stepstatus.equals("failed")){
				continue;
			}
		}
		

	
		//System.out.println(stepList.size());
		if(stepstatusList.contains("failed")){
			casestatus = "failed";
		}else {
			casestatus = "passed";
		}
		
		if(Integer.parseInt(caseinfo.get("casenum").toString())<10){
			casenumber="00"+caseinfo.get("casenum").toString();
		}else{
			casenumber = "0"+caseinfo.get("casenum").toString();
		}
		casename = caseinfo.get("casename").toString();
		casetime = Long.toString(timeSpan)+"ms";
		//System.out.println(timeSpan);
		WriteToXML.createTestCase(file,doc,stepList,casestatus,casenumber,casename,casetime);
		
		
		summary.put("status",casestatus);
		summary.put("starttime",startTime);
		summary.put("runtime",timeSpan);
		summary.put("stoptime",stopTime);
		
		return summary;
		
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

		  //System.out.println(c);
		  return c;
	}
	*/
	
}
