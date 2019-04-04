package automation;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.MapContext;
public class Demo1 {
	public static Object invokeMethod(String jexlExp,Map<String,Object> map){
		  JexlEngine jexl=new JexlBuilder().create();
		  
		  JexlExpression e = jexl.createExpression(jexlExp);
		  
		  JexlContext jc = new MapContext();
		  
		  for(String key:map.keySet()){
		   jc.set(key, map.get(key));
		  }
		  System.out.println(map);
		  if(null==e.evaluate(jc)){
			
			  return "";
		  }
		  
		  return e.evaluate(jc);
		 }
	
}
