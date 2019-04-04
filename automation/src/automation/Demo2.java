package automation;

import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.MapContext;

import java.util.HashMap;
import java.util.Map;

/**
 * A Camel Application
 */
public class Demo2 {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static Object convertToCode(String jexlExp, Map<String, Object> map) {
        //�������������
    	JexlEngine jexl=new JexlBuilder().create();
        //����һ�����ʽ
    	JexlExpression e = jexl.createExpression(jexlExp);
        //���������Ĳ��������
        JexlContext jc = new MapContext();
        for (String key : map.keySet()) {
            jc.set(key, map.get(key));
        }
        //�����������ʽ���õ����
        for (String key : map.keySet()) {
        	System.out.println(jc.get(key));
        }
        
        
        Object code = e.evaluate(jc);
        
        System.out.println(code);
        
        if (null == code) {
            return "";
        }
       
        return code;
    }

    public static void main(String... args) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>(16);
            Testa t = new Testa();
            map.put("obj", t);
            map.put("browser", "chrome");
            String expression = "obj.open(browser)";
            Object code = convertToCode(expression, map);
            
            //System.out.println((Boolean) code);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
