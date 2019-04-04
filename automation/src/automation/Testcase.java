package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Testcase {
	Testa obj = new Testa();
	
	//������ҳ
	/*public WebDriver openUrl(){
		WebDriver driver = this.obj.open("chrome");
		this.obj.gotourl("http://115.159.25.34:8090/login");
		
		return driver;
	}*/
	public void openUrl(){
		this.obj.open("chrome");
		this.obj.gotourl("http://115.159.25.34:8090/login");
		this.obj.maxWindow();
		//return driver;
	}
	
	//��¼
	public void login(String username,String password){
		
		this.obj.inputText("xpath","//input[@name='username']",username);
		this.obj.inputText("xpath","//input[@name='password']",password);
		this.obj.clickElement("xpath", "//button[.='��¼']");
	}
	
	//�������û�
	public void addNewUser(Map<String,String> map) throws InterruptedException{
		
		this.obj.clickElement("xpath","//span[.='Autotest']/..");
       	this.obj.sleep(1000);
       	this.obj.clickElement("xpath","//a[.='�û�����']");
       	
       	this.obj.switchto("xpath", "//iframe[@name='iframe2']");
       	
       	this.obj.clickElement("xpath","//button[@class='btn  btn-primary']");
       	
    	this.obj.switchto("xpath", "//div[.='�����û�']/following-sibling::div/iframe");
       	
		this.obj.inputText("id", "name", map.get("name"));
		this.obj.inputText("id", "username", map.get("username"));
		this.obj.inputText("id", "password", map.get("password"));
		this.obj.inputText("id", "mobile", map.get("mobile"));
		this.obj.inputText("id", "email", map.get("email"));
		
		this.obj.selectElementByIndex("xpath", "//label[contains(text(),'״̬')]/following-sibling::div//input", 0);
    	this.obj.selectElementByValue("xpath", "//label[contains(text(),'Team')]/following-sibling::div//input", "79");
    	this.obj.selectElementByIndex("xpath", "//label[contains(text(),'��ɫ')]/following-sibling::div//input", 0);
    	
    	this.obj.sleep(2000);
    	this.obj.clickElement("xpath", "//button[.='�ύ']");
    	this.obj.sleep(2000);
    	
    	this.obj.switchtoparent();
	}
	
	
	//��ѯ�û�
	public void searchByName(String name) throws InterruptedException{
		this.obj.inputText("id", "searchName", name);
		this.obj.clickElement("xpath", "//button[.='��ѯ']");
		this.obj.sleep(2000);
    	this.obj.clickElement("xpath", "//a[@title='�༭']");
	}
	
	//�˳���¼
	public void logout(){
		this.obj.switchreturn();
		this.obj.clickElement("xpath", "//a[contains(text(),'�˳�')]");
	}
	
}
