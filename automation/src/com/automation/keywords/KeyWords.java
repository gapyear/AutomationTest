/**
 * @author shaluo
 * 基于selenium封装常用的keywords
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
		 * 获取driver
		 * 方法名：getDriver
		 * 参数：String browser-要使用的浏览器
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
		 * 打开某网页
		 * 方法名：goUrl
		 * 参数：String url-要打开的网址
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
		 * 直接打开某浏览器，打开网页
		 * 方法名：open
		 * 参数：String browser,String url-要打开的浏览器，要打开的网址
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
		 * 关闭浏览器
		 * 方法名：close
		 * 参数：无
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
		 * 最大化浏览器窗口
		 * 方法名：maxWindow
		 * 参数：无
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
		 * 获取某个元素
		 * 方法名：getElement
		 * 参数：String type,String locator-选取元素的方式，该方式的指示值
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
		 * 点击某元素
		 * 方法名：clickElement
		 * 参数：String type,String locator-选取元素的方式，该方式的指示值
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
		 * 输入框中输入内容
		 * 方法名：inputText
		 * 参数：String type,String locator,String text-选取元素的方式，该方式的指示值，输入的文本
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
				isExist =  getElement("tagName","body").getText().contains(text);// 遍历body节点下
				
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
