package com.tmb.challenges;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Challenge1 {

	
	@Test
	public static void captureInfo() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.worldometers.info/world-population/");
		
		WebElement mainFrame=driver.findElement(By.xpath("//div[@class='maincounter-number']/span[@class='rts-counter']"));
		
		System.out.println(driver.findElement(By.xpath("//div[@id='maincounter-wrap']/h1")).getText());
		for(int i=1;i<=10;i++)
		{
			System.out.println(mainFrame.getText());
			
			List<WebElement> otherData= driver.findElements(By.xpath("//div[@class='sec-counter']/span"));
			List<WebElement> otherDataHeading= driver.findElements(By.xpath("//div[@class='sec-text']"));
						
			for(int j=0;j<otherData.size();j++)
			{
				System.out.println(otherDataHeading.get(j).getText());
				System.out.println(otherData.get(j).getText());
				
			}
			System.out.println("***********************");
			Thread.sleep(1000);
		}
	}
}
	
