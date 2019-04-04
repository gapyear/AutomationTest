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
		
		int itemindex = 0;//������
		long timeSpan = 0;//����ִ��ʱ��
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
			
			//����ÿ�������id
			itemindex++;
			if(itemindex<10){
				step.setStepid("00"+(itemindex));
			}else{
				step.setStepid("0"+(itemindex));
			}
			
			//����ÿ�����������
			step.setStepName(item.getString("description"));
			//����ÿ�������keyword
			step.setStepkeyword(keyword);
			//����ÿ������Ĳ���
			step.setArgslist((List<String>) item.get("args"));
			
			//��ʼִ�в���ʱ��
			startTime =fromDateStringToLong(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
			//ִ�в��裬���ظò���ִ�е�״̬
			String stepstatus = (String) invokeMethod(expression, map);
			//����ִ�в���ʱ��
			stopTime = fromDateStringToLong(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
			//ִ�в������ʱ��
			
			timeSpan += (stopTime - startTime);
			//����ÿ������ִ�к��״̬
			step.setStepstatus(stepstatus);
			//��ÿ������ִ�к��״̬��ӵ�List��
			stepstatusList.add(step.getStepstatus());
			
			//ִ�����ÿ�����轫���step����浽һ������list���forѭ�����ͳһд��xml
			stepList.add(step);
			
		}
		
		
		//ִ�����steps���step
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
