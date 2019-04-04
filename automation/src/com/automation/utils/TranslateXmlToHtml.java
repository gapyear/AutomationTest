package com.automation.utils;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.PrintStream;  
  
import javax.xml.transform.Result;  
import javax.xml.transform.Source;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.stream.StreamResult;  
import javax.xml.transform.stream.StreamSource;  
  
public class TranslateXmlToHtml {  
    /** 
     * ��XMLת����HTML 
     * @throws Exception 
     */  
    public static void translate() throws Exception{  
        //����XML���ļ�������  
        FileInputStream fis=new FileInputStream("src/report.xml");  
        Source source=new StreamSource(fis);  
          
        //����XSL�ļ���������  
        FileInputStream fis1=new FileInputStream("src/report.xsl");  
        Source template=new StreamSource(fis1);  
          
        PrintStream stm=new PrintStream(new File("src/report.html"));  
        //��ת����Ľ������� stm �м� F:\123.html  
        Result result=new StreamResult(stm);  
        //����XSL�ļ�����׼��ת������  
        Transformer transformer=TransformerFactory.newInstance().newTransformer(template);  
        //����xml���н���  
        transformer.transform(source, result);   
        //�ر��ļ���  
        fis1.close();  
        fis.close();  
    }  
      
    public static void runTranslate(){  
        try {  
            translate();  
        } catch (Exception e) {  
            System.out.println("XMLת����HTMLʧ�ܣ�"+e.getMessage());  
        }  
    }  
}  