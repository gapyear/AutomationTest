package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.HashMap;
import java.util.Map;

public class Testrun {
	public static void main(String[] args) throws InterruptedException {
		
		Testcase tc1 = new Testcase();
		
		//WebDriver driver = tc1.openUrl();
		tc1.openUrl();
		
		//driver.manage().window().maximize();
		
		tc1.login("WSC110", "111111");
		
		
		Map <String,String> map=new HashMap<>();
		map.put("name", "ww");
		map.put("username", "auto7");
		map.put("password", "000000");
		map.put("mobile", "11111111111");
		map.put("email", "xx@qq.com");
		map.put("status", "Õý³£");
		map.put("team", "testls");
		map.put("role", "Project Member");
		
		tc1.addNewUser(map);
		
		tc1.searchByName(map.get("name"));
		
		tc1.logout();
	}
}
