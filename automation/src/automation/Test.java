package automation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.jni.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Sleeper;

public class Test {
    public static void main(String[] args) throws InterruptedException {
    	
    	//创建driver
        //System.setProperty("webdriver.chrome.driver","D:\\automation\\chromedriver.exe");//chromedriver服务地址
        WebDriver driver =new ChromeDriver(); //新建一个WebDriver 的对象，但是new 的是chromedriver的驱动
        
        //打开网站
        driver.get("http://115.159.25.34:8090/login");
        
        //最大化浏览器窗口
        driver.manage().window().maximize();
        
        //输入用户名密码登录
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(new  String[] {"WSC110"});
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(new String[] {"111111"});
        driver.findElement(By.xpath("//button[.='登录']")).click();
        
       	Thread.sleep(2000);
       	
       	//点击Autotest菜单
       	driver.findElement(By.xpath("//span[.='Autotest']/..")).click();
       	Thread.sleep(1000);
       	//点击用户管理菜单
       	driver.findElement(By.xpath("//a[.='用户管理']")).click();
       	
       	//添加按钮在iframe下，需要先切换
       	WebElement iframe_add = driver.findElement(By.xpath("//iframe[@name='iframe2']"));
       	driver.switchTo().frame(iframe_add);
       	
       	//等待页面上【添加】按钮存在
       	//WebDriverWait dw = new WebDriverWait(driver,20);
       	//dw.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn  btn-primary']")));
       	
       	//点击【添加】按钮
    	driver.findElement(By.xpath("//button[@class='btn  btn-primary']")).click();
    	
       	
    	//增加用户表单在iframe_add下
    	//WebElement iframe_info = driver.findElement(By.xpath("//div[.='增加用户']/following-sibling::div/iframe"));
    	driver.switchTo().frame(driver.findElement(By.xpath("//div[.='增加用户']/following-sibling::div/iframe")));
    	
    	
    	driver.findElement(By.id("name")).sendKeys("qq");
    	driver.findElement(By.id("username")).sendKeys("auto6");
    	driver.findElement(By.id("password")).sendKeys("000000");
    	driver.findElement(By.id("mobile")).sendKeys("15900001111");
    	driver.findElement(By.id("email")).sendKeys("sha.luo@capgemini.com");
    	List<WebElement> radio = driver.findElements(By.xpath("//label[contains(text(),'状态')]/following-sibling::div//input"));
    	//选中第一个“正常”
    	radio.get(0).click();
    	//选中testls这个项目
    	List<WebElement> proj_checkbox = driver.findElements(By.xpath("//label[contains(text(),'Team')]/following-sibling::div//input"));
    	for(int i=0; i<proj_checkbox.size(); i++){
    		//System.out.println(proj_checkbox.get(i).getAttribute("value"));
    		if (proj_checkbox.get(i).getAttribute("value").equals("79")){
    			proj_checkbox.get(i).click();
    		}
    		
    	}
    	
    	List<WebElement> member_checkbox = driver.findElements(By.xpath("//label[contains(text(),'角色')]/following-sibling::div//input"));
    	//选中第一个“project member”		
    	member_checkbox.get(0).click();
    	
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//button[.='提交']")).click();
    	
    	Thread.sleep(2000);
    	//iframe切换到父iframe
    	driver.switchTo().parentFrame();
    	
    	//查询刚创建的用户
    	driver.findElement(By.id("searchName")).sendKeys("qq");
    	driver.findElement(By.xpath("//button[.='查询']")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//a[@title='编辑']")).click();
    	
    	//修改邮箱
    	//WebElement iframe_info = driver.findElement(By.xpath("//iframe[@id='layui-layer-iframe1']"));
    	driver.switchTo().frame(driver.findElement(By.xpath("//div[.='用户修改']/following-sibling::div/iframe")));
    	WebElement em_inp = driver.findElement(By.id("email"));
    	em_inp.clear();
    	em_inp.sendKeys("sha.luo.change@capgemini.com");
    	driver.findElement(By.xpath("//button[.='提交']")).click();
    	
    	Thread.sleep(2000);
    	//iframe切换到主页面
    	driver.switchTo().defaultContent();
    	driver.findElement(By.xpath("//a[contains(text(),'退出')]")).click();
    	
    	Thread.sleep(2000);
    	
    	//用新增的用户重新登录
    	driver.findElement(By.xpath("//input[@name='username']")).sendKeys(new  String[] {"auto6"});
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(new String[] {"000000"});
        driver.findElement(By.xpath("//button[.='登录']")).click();
    	
    	Thread.sleep(3000);
        try {
            /**
             * WebDriver自带了一个智能等待的方法。
            dr.manage().timeouts().implicitlyWait(arg0, arg1）；
            Arg0：等待的时间长度，int 类型 ；
            Arg1：等待时间的单位 TimeUnit.SECONDS 一般用秒作为单位。
             */
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);        
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * dr.quit()和dr.close()都可以退出浏览器,简单的说一下两者的区别：第一个close，
         * 如果打开了多个页面是关不干净的，它只关闭当前的一个页面。第二个quit，
         * 是退出了所有Webdriver所有的窗口，退的非常干净，所以推荐使用quit最为一个case退出的方法。
         */
        driver.quit();//退出浏览器
    }
    
}
