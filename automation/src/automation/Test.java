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
    	
    	//����driver
        //System.setProperty("webdriver.chrome.driver","D:\\automation\\chromedriver.exe");//chromedriver�����ַ
        WebDriver driver =new ChromeDriver(); //�½�һ��WebDriver �Ķ��󣬵���new ����chromedriver������
        
        //����վ
        driver.get("http://115.159.25.34:8090/login");
        
        //������������
        driver.manage().window().maximize();
        
        //�����û��������¼
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(new  String[] {"WSC110"});
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(new String[] {"111111"});
        driver.findElement(By.xpath("//button[.='��¼']")).click();
        
       	Thread.sleep(2000);
       	
       	//���Autotest�˵�
       	driver.findElement(By.xpath("//span[.='Autotest']/..")).click();
       	Thread.sleep(1000);
       	//����û�����˵�
       	driver.findElement(By.xpath("//a[.='�û�����']")).click();
       	
       	//��Ӱ�ť��iframe�£���Ҫ���л�
       	WebElement iframe_add = driver.findElement(By.xpath("//iframe[@name='iframe2']"));
       	driver.switchTo().frame(iframe_add);
       	
       	//�ȴ�ҳ���ϡ���ӡ���ť����
       	//WebDriverWait dw = new WebDriverWait(driver,20);
       	//dw.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn  btn-primary']")));
       	
       	//�������ӡ���ť
    	driver.findElement(By.xpath("//button[@class='btn  btn-primary']")).click();
    	
       	
    	//�����û�����iframe_add��
    	//WebElement iframe_info = driver.findElement(By.xpath("//div[.='�����û�']/following-sibling::div/iframe"));
    	driver.switchTo().frame(driver.findElement(By.xpath("//div[.='�����û�']/following-sibling::div/iframe")));
    	
    	
    	driver.findElement(By.id("name")).sendKeys("qq");
    	driver.findElement(By.id("username")).sendKeys("auto6");
    	driver.findElement(By.id("password")).sendKeys("000000");
    	driver.findElement(By.id("mobile")).sendKeys("15900001111");
    	driver.findElement(By.id("email")).sendKeys("sha.luo@capgemini.com");
    	List<WebElement> radio = driver.findElements(By.xpath("//label[contains(text(),'״̬')]/following-sibling::div//input"));
    	//ѡ�е�һ����������
    	radio.get(0).click();
    	//ѡ��testls�����Ŀ
    	List<WebElement> proj_checkbox = driver.findElements(By.xpath("//label[contains(text(),'Team')]/following-sibling::div//input"));
    	for(int i=0; i<proj_checkbox.size(); i++){
    		//System.out.println(proj_checkbox.get(i).getAttribute("value"));
    		if (proj_checkbox.get(i).getAttribute("value").equals("79")){
    			proj_checkbox.get(i).click();
    		}
    		
    	}
    	
    	List<WebElement> member_checkbox = driver.findElements(By.xpath("//label[contains(text(),'��ɫ')]/following-sibling::div//input"));
    	//ѡ�е�һ����project member��		
    	member_checkbox.get(0).click();
    	
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//button[.='�ύ']")).click();
    	
    	Thread.sleep(2000);
    	//iframe�л�����iframe
    	driver.switchTo().parentFrame();
    	
    	//��ѯ�մ������û�
    	driver.findElement(By.id("searchName")).sendKeys("qq");
    	driver.findElement(By.xpath("//button[.='��ѯ']")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//a[@title='�༭']")).click();
    	
    	//�޸�����
    	//WebElement iframe_info = driver.findElement(By.xpath("//iframe[@id='layui-layer-iframe1']"));
    	driver.switchTo().frame(driver.findElement(By.xpath("//div[.='�û��޸�']/following-sibling::div/iframe")));
    	WebElement em_inp = driver.findElement(By.id("email"));
    	em_inp.clear();
    	em_inp.sendKeys("sha.luo.change@capgemini.com");
    	driver.findElement(By.xpath("//button[.='�ύ']")).click();
    	
    	Thread.sleep(2000);
    	//iframe�л�����ҳ��
    	driver.switchTo().defaultContent();
    	driver.findElement(By.xpath("//a[contains(text(),'�˳�')]")).click();
    	
    	Thread.sleep(2000);
    	
    	//���������û����µ�¼
    	driver.findElement(By.xpath("//input[@name='username']")).sendKeys(new  String[] {"auto6"});
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(new String[] {"000000"});
        driver.findElement(By.xpath("//button[.='��¼']")).click();
    	
    	Thread.sleep(3000);
        try {
            /**
             * WebDriver�Դ���һ�����ܵȴ��ķ�����
            dr.manage().timeouts().implicitlyWait(arg0, arg1����
            Arg0���ȴ���ʱ�䳤�ȣ�int ���� ��
            Arg1���ȴ�ʱ��ĵ�λ TimeUnit.SECONDS һ��������Ϊ��λ��
             */
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);        
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * dr.quit()��dr.close()�������˳������,�򵥵�˵һ�����ߵ����𣺵�һ��close��
         * ������˶��ҳ���ǹز��ɾ��ģ���ֻ�رյ�ǰ��һ��ҳ�档�ڶ���quit��
         * ���˳�������Webdriver���еĴ��ڣ��˵ķǳ��ɾ��������Ƽ�ʹ��quit��Ϊһ��case�˳��ķ�����
         */
        driver.quit();//�˳������
    }
    
}
