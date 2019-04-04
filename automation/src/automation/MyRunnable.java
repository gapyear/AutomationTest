package automation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String filepath = "D:\\automation\\data.xlsx";
		List<List<String>> list = ExcelUtils.getListFromFile(filepath);
		if (list != null) {
			Map<String,Object> map = new HashMap<String,Object>();
			Testa a = new Testa();
			//Demo1 demo = new Demo1();
			String expression = "";
			for(List<String> row : list) {
				map.clear();
				map.put("a", a);
				String keyword = row.get(0);
				map.put(keyword,keyword);
				List<String> argList=new ArrayList<String>();
				for(String s:row) {
					//System.out.println(s);
					
					if (!s.equals(row.get(0))){
						
						argList.add("\""+s+"\"");
						//argstring += s;
						//argstring += "\",\"";
					}
					
				}
					//System.out.println(argList);
				
				String argstring = "";
				for (int i=0;i<argList.size();i++){
					argstring+=argList.get(i)+",";
				}
				if(argstring.length()>0){
					argstring = argstring.substring(0, argstring.length()-1);
				}
				System.out.println(argstring);
				
				map.put(argstring,argstring);
				
				expression = "a."+keyword+"("+argstring+")";
				Demo1.invokeMethod(expression, map);
				
				
			}
			
			//Demo1.invokeMethod(expression, map);
			//String expression = "t."+row.get(0)+"("+argstring+")";
			//System.out.println(expression);
			//Object code = Demo1.invokeMethod(expression, map);
			//System.out.println(map);
			
		}else {
			System.out.println("Error");
		}
		
		
	}

}
