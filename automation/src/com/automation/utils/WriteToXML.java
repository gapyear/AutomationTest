package com.automation.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.automation.model.ReportSummary;
import com.automation.model.Step;
import com.automation.model.Testcase;
import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference.Elements;

import net.sf.json.JSONObject;

public class WriteToXML {
	
	public static File init(){
		
		File file= new File("src/report.xml");
		
		return file;
	}
	
	 public static void addReport(File file,Document doc) throws Exception{

		//File file = new File("src/report.xml");
		
		//Document doc = createDoc(file);
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
			//DOMSource source = new DOMSource(doc);
		Source source = new DOMSource(doc);
			//StreamResult result = new StreamResult();
		Result result = new StreamResult(file);
		
		transformer.transform(source, result);

	}
	 
	 public static Document createDoc(File file) throws Exception{
		 
		 //File file = new File("src/report.xml");
		 
		 DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		 
		 Document doc = newDocumentBuilder.parse(file);
		 
		 return doc;
	 }
	 
	 
	 public static void createTestCase(File file,Document doc,Testcase tc) throws Exception{
		 
		// File file = new File("src/report.xml");
		 
		// Document doc = createDoc(file);
		 
		 Element testcase = doc.createElement("testcase");
		 
		 Element num = doc.createElement("num");
		 Element name = doc.createElement("name");
		 Element steps = doc.createElement("steps");
		 Element casetime = doc.createElement("casetime");
		 Element casestatus = doc.createElement("casestatus");
		 
		 num.setTextContent(tc.getCasenumber());
		 name.setTextContent(tc.getCasename());
		 casetime.setTextContent(tc.getCasetime());
		 casestatus.setTextContent(tc.getCasestatus());
		// System.out.println(steps);
		 testcase.appendChild(num);
		 testcase.appendChild(name);
		 
		 for(int i=0;i<tc.getSteps().getStep().size();i++){
			 Element stepelement = createStep(file,doc,tc.getSteps().getStep().get(i));
			 steps.appendChild(stepelement);
		 }
		 testcase.appendChild(steps);
		 testcase.appendChild(casetime);
		 testcase.appendChild(casestatus);
		 
		 NodeList reportList = doc.getElementsByTagName("report");
		 Element report = (Element)reportList.item(0);
		 report.appendChild(testcase);
		 
		 addReport(file,doc);
	 }
	
	 public static Element createStep(File file,Document doc,Step stepobj) throws Exception{
		 
		 //Map<String, Object> map = new HashMap<String,Object>();
		 
		 //File file = new File("src/report.xml");
		 
		 // System.out.println(file);
		 //Document doc = createDoc(file);
		 
		 //System.out.println(doc.getElementsByTagName("report"));
		 //System.out.println(args_value);
		 Element step = doc.createElement("step");
		 
		 Element id = doc.createElement("id");
		 Element name = doc.createElement("name");
		 Element keyword = doc.createElement("keyword");
		 Element args = doc.createElement("args");
		 Element stepstatus = doc.createElement("stepstatus");
		 
		 id.setTextContent(stepobj.getStepid());
		 name.setTextContent(stepobj.getStepName());
		 keyword.setTextContent(stepobj.getStepkeyword());
		 
		 if(stepobj.getArgslist().size()>0){
			 for(int i=0;i<stepobj.getArgslist().size();i++){
				 
				 Element value = doc.createElement("value");
				 value.setTextContent(stepobj.getArgslist().get(i));
				 //value.setTextContent("testvalue"+i);
				 
				 args.appendChild(value);
			 }
			 
		 }else {
			 Element value = doc.createElement("value");
			 args.appendChild(value);
		 }
		 
		 stepstatus.setTextContent(stepobj.getStepstatus());
		 //System.out.println(args.getNodeName());
		 
		
		 step.appendChild(id);
		 step.appendChild(name);
		 step.appendChild(keyword);
		 step.appendChild(args);
		 step.appendChild(stepstatus);
		 
		 //addReport(file,doc);
		// System.out.println(steps.getChildNodes().getLength());
		 //map.put("doc", doc);
		 //map.put("step", step);
		 
		 return step;
	 }
	 
	 public static void clearReportTestcase() throws Exception{
		 
		 File file = init();
		 Document doc = createDoc(file);
		 
		 NodeList reportList = doc.getElementsByTagName("report");
		 Element report = (Element)reportList.item(0);
		 
		 
		 
		 if(report.hasChildNodes()){
			 
			 NodeList testcaseList = doc.getElementsByTagName("testcase");
			 NodeList summaryList = doc.getElementsByTagName("summary");
			 
			 int len = testcaseList.getLength() + summaryList.getLength();
			 
			 for(int i=0;i<len;i++){
				 
				 /*System.out.println("-------1------");
				 System.out.println(testcaseList.item(i));
				 System.out.println("-------1------");
				 System.out.println("-------2------");
				 System.out.println(report.getFirstChild());
				 System.out.println("-------2------");*/
				 
				 report.removeChild(report.getFirstChild());
			 }
		 }
		 
		 addReport(file,doc);
	 }
	 
	 public static void createReportSummary(ReportSummary rs) throws Exception{
		 
		 
		 File file = init();
		 Document doc = createDoc(file);
		 
		 Element summary = doc.createElement("summary");
		 
		 Element totalcasenum = doc.createElement("totalcasenum");
		 Element passnum = doc.createElement("passnum");
		 Element failnum = doc.createElement("failnum");
		 Element jumpnum = doc.createElement("jumpnum");
		 Element starttime = doc.createElement("starttime");
		 Element runtime = doc.createElement("runtime");
		 Element endtime = doc.createElement("endtime");
		 
		 totalcasenum.setTextContent(rs.getCasenum());
		 passnum.setTextContent(rs.getPassnum());
		 failnum.setTextContent(rs.getFailnum());
		 jumpnum.setTextContent(rs.getJumpnum());
		 
		 //new Date(reportSummary.get(0).getLong("starttime")).toString();
		 //System.out.println(new Date(rs.getStartTime()).toString());
		 
		 starttime.setTextContent(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date(rs.getStartTime())).toString());
		 runtime.setTextContent(rs.getTimeSpan()/1000+"s");
		 endtime.setTextContent(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date(rs.getStopTime())).toString());
		 
		 summary.appendChild(totalcasenum);
		 summary.appendChild(passnum);
		 summary.appendChild(failnum);
		 summary.appendChild(jumpnum);
		 summary.appendChild(starttime);
		 summary.appendChild(runtime);
		 summary.appendChild(endtime);
		 
		 NodeList reportList = doc.getElementsByTagName("report");
		 Element report = (Element)reportList.item(0);
		 report.appendChild(summary);
		 
		 addReport(file,doc);
		 
		 
	 }
	

}
