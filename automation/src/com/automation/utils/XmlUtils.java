package com.automation.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlUtils {
	
	public static Document getDoc() throws Exception{
		
		File file = new File("src/report.xml");
		 
		 DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		 
		 Document doc = newDocumentBuilder.parse(file);
		 
		 return doc;
		
	}
	public static Element addCase() throws Exception{
		
		 Document doc = getDoc();
		 
		 Element testcase = doc.createElement("testcase");
		 
		 return testcase;
	}
	
	public static Element addStep(int num,String kw,String[] args,String status) throws Exception{
		
		Document doc = getDoc();
		
		Element step = doc.createElement("step");
		Element id = doc.createElement("id");
		Element keyword = doc.createElement("keyword");
		Element argum = doc.createElement("args");
		Element stepstatus = doc.createElement("stepstatus");
		
		id.setTextContent(Integer.toString(num));
		keyword.setTextContent(kw);
		
		for(int i=0;i<args.length;i++){
			Element value = doc.createElement("value");
			value.setTextContent(args[i]);
			argum.appendChild(value);
		}
		stepstatus.setTextContent(status);
		
		step.appendChild(id);
		step.appendChild(keyword);
		step.appendChild(argum);
		step.appendChild(stepstatus);
		
		return step;
		
	}
	
	public static void addCaseContent(String casename,String status) throws Exception{
		
			Document doc = getDoc();

			Element testcase = addCase();
			
			//Element step = addStep();
		
			//创建person的几个子节点
			Element name = doc.createElement("name");
			Element steps = doc.createElement("steps");
			Element casestatus = doc.createElement("casestatus");
			
			
			testcase.appendChild(name);
			testcase.appendChild(steps);
			testcase.appendChild(casestatus);
			
			name.setTextContent(casename);
			casestatus.setTextContent(status);
					
			
			//给person的id设置值
			//person.setAttribute("id", "3");
			//将person追加到根节点
			//root.appendChild(person);
			
			//注意：XML文件是被加载到内存中 修改也是在内存中 ==》因此需要将内存中的数据同步到磁盘中
			
			 /* static TransformerFactory newInstance():获取 TransformerFactory 的新实例。 
			 * abstract  Transformer newTransformer():创建执行从 Source 到 Result 的复制的新 Transformer。
			 * abstract  void transform(Source xmlSource, Result outputTarget):将 XML Source 转换为 Result。*/
			 
			//Transformer transformer = TransformerFactory.newInstance().newTransformer();
			//DOMSource source = new DOMSource(doc);
			//Source source = new DOMSource(doc);
			//StreamResult result = new StreamResult();
			//Result result = new StreamResult(file);
			//transformer.transform(source, result);
		
	}

}
