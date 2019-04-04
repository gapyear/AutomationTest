package automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

public class Testa {
	WebDriver driver;
	
	//打开浏览器
	public void open(String browser){
		
		if(browser.trim().equalsIgnoreCase("chrome")){
			 this.driver =new ChromeDriver();
		}
		else if(browser.trim().equalsIgnoreCase("firefox")){
			this.driver =new FirefoxDriver();
		}
		
		//return this.driver;
	}
	
	public void maxWindow(){
		this.driver.manage().window().maximize();
	}
	
	//打开网页
	public void gotourl(String url){
		this.driver.get(url);
	}
	
	private WebElement getElement(String type,String locator){
		
		WebElement element = null;
		
		if(type.trim().equalsIgnoreCase("id")){
			element = this.driver.findElement(By.id(locator));
		}
		else if(type.trim().equalsIgnoreCase("xpath")){
			element = this.driver.findElement(By.xpath(locator));
		}
		
		return element;
	}
	
	//点击元素
	public void clickElement(String type,String locator){
		
			this.getElement(type, locator).click();
	}

	//输入信息 
	public void inputText(String type,String locator,String text){
		
		this.getElement(type, locator).sendKeys(text);
	}
	
	//切换iframedriver
	public void switchto(String type,String locator){
		
		WebElement iframe = this.getElement(type, locator);
       	this.driver.switchTo().frame(iframe);
		
		//return this.driver;
	}
	
	//切换到父页面
	public void switchtoparent(){
		this.driver.switchTo().parentFrame();
	}
	
	//切换iframedriver主页面
	public void switchreturn(){
		this.driver.switchTo().defaultContent();
	}
	
	//根据下标选取一组元素中某一个
	public void selectElementByIndex(String type,String locator,Object index){
		List<WebElement> elements = null;
		
		if(type.trim().equalsIgnoreCase("id")){
			elements = this.driver.findElements(By.id(locator));
		}
		else if(type.trim().equalsIgnoreCase("xpath")){
			elements = this.driver.findElements(By.xpath(locator));
		}
		
		elements.get(Integer.parseInt(String.valueOf(index))).click();
		//return elements.get(index); 
	}
	
	//根据文本选取元素中的某一个
	public void selectElementByValue(String type,String locator,String value){
		List<WebElement> elements = null;
		WebElement element = null;
		if(type.trim().equalsIgnoreCase("id")){
			elements = this.driver.findElements(By.id(locator));
		}
		else if(type.trim().equalsIgnoreCase("xpath")){
			elements = this.driver.findElements(By.xpath(locator));
		}
		
		for(int i=0; i<elements.size(); i++){
    		//System.out.println(proj_checkbox.get(i).getAttribute("value"));
    		if (elements.get(i).getAttribute("value").equals(value)){
    			element =  elements.get(i);
    		}
    		
    	}
		
		element.click();
		//return element;

	}
	
	//sleep
	public void sleep(Object time) throws InterruptedException{
		
		Thread.sleep(Long.parseLong(String.valueOf(time)));
	}
	
}
