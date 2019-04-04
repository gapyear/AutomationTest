/**
 * @author shaluo
 * ����selenium��װ���õ�keywords
 * */

package com.automation.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class KeyWords {
	
		static WebDriver driver = null;
		/**
		 * ��ȡdriver
		 * ��������getDriver
		 * ������String browser-Ҫʹ�õ������
		 * */
		public static void getDriver(String browser){
			
			try{
				if(browser.equalsIgnoreCase("chrome")){
					driver = new ChromeDriver();
				}else if(browser.equalsIgnoreCase("firefox")){
					driver = new FirefoxDriver();
				}
				
				//return "passed";
				
			}catch(Exception e){
				
				//return "failed";
			}
		}
		
		/**
		 * ��ĳ��ҳ
		 * ��������goUrl
		 * ������String url-Ҫ�򿪵���ַ
		 * */
		public static String goUrl(String url){
			
			try{
				driver.get(url);
				
				return "passed";
				
			}catch(Exception e){
				return "failed";
			}
		}
		
		/**
		 * ֱ�Ӵ�ĳ�����������ҳ
		 * ��������open
		 * ������String browser,String url-Ҫ�򿪵��������Ҫ�򿪵���ַ
		 * */

		public static String open(String browser,String url) {
			
			try{
				getDriver(browser);
				goUrl(url);
				
				return "passed";
			}catch(Exception e){
				return "failed";
			}
			
		}
		
		/**
		 * �ر������
		 * ��������close
		 * ��������
		 * */
		public static String close(){
			
			try{
				driver.quit();
				return "passed";
			}catch(Exception e){
				return "failed";
			}
		}
		
		/**
		 * ������������
		 * ��������maxWindow
		 * ��������
		 * */
		public static String maxWindow(){
			try{
				driver.manage().window().maximize();
				return "passed";
			}catch(Exception e){
				return "failed";
			}
		}
		
		/**
		 * ��ȡĳ��Ԫ��
		 * ��������getElement
		 * ������String type,String locator-ѡȡԪ�صķ�ʽ���÷�ʽ��ָʾֵ
		 * */
		public static WebElement getElement(String type,String locator){
			
			WebElement element = null;
			
			if(type.equalsIgnoreCase("xpath")){
				
				element = driver.findElement(By.xpath(locator));
				
			}else if(type.equalsIgnoreCase("id")){
				
				element = driver.findElement(By.id(locator));
				
			}else if(type.equalsIgnoreCase("name")){
				
				element = driver.findElement(By.name(locator));
				
			}else if(type.equalsIgnoreCase("className")){
				
				element = driver.findElement(By.className(locator));
				
			}else if(type.equalsIgnoreCase("cssSelector")){
				
				element = driver.findElement(By.cssSelector(locator));
				
			}else if(type.equalsIgnoreCase("tagName")){
				
				element = driver.findElement(By.tagName(locator));
			}
			
			return element;
		}
			
		/**
		 * ���ĳԪ��
		 * ��������clickElement
		 * ������String type,String locator-ѡȡԪ�صķ�ʽ���÷�ʽ��ָʾֵ
		 * */
		public static String clickElement(String type,String locator){
			
			try{
				getElement(type,locator).click();
				return "passed";
			}catch(Exception e){
				return "failed";
			}
		}
		
		/**
		 * ���������������
		 * ��������inputText
		 * ������String type,String locator,String text-ѡȡԪ�صķ�ʽ���÷�ʽ��ָʾֵ��������ı�
		 * */
		public static String inputText(String type,String locator,String text){
			
			try{
				getElement(type,locator).sendKeys(text);
				return "passed";
			}
			catch(Exception e){
				return "failed";
			}
		}
		
		public static String sleep(Object time) throws InterruptedException{
			try{
				Thread.sleep(Long.parseLong(String.valueOf(time)));
				return "passed";
			}catch(Exception e){
				return "failed";
			}
		}
		
		public static String checkText(String text){
			Boolean isExist = null;
			try{
				isExist =  getElement("tagName","body").getText().contains(text);// ����body�ڵ���
				
				if(isExist == true){
					return "passed";
				}else{
					return "failed";
				}
				
			}catch(Exception e){
				return "failed";
			}
		}
}
