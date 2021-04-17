package com.tmb.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Challenge2 {


	static WebDriver driver;
	static List<String> list = null;
	static JavascriptExecutor js;

	
	@Test
	public void carouselCode() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
		driver = new ChromeDriver();
		browseurl("https://noon.com");

		/*
		 * Choose from the following sections: 
		 * 1. Recommended For You 
		 * 2. Save big on mobiles & tablets 
		 * 3. Top picks in electronics
		 */

		String requiredSectionXpath = sectionName("Recommended For You");
		scrollToObject(requiredSectionXpath);
		
		String requiredSectionItemsXpath = requiredSectionXpath	+ " //div[@class='e3js0d-6 iKEZJh']/div[@class='e3js0d-7 jULUCI']";
		String requiredSectionNextArrow = requiredSectionXpath + " //div[starts-with(@class,'swiper-button')]";
		
		WebElement nextArrow=driver.findElement(By.xpath(requiredSectionNextArrow));
		
		list = new ArrayList<>();
		List<WebElement> allItems = driver.findElements(By.xpath(requiredSectionItemsXpath));
		
		for (int i = 0, j = 0; i < allItems.size(); i++) 
		{
			// Item count on every carousel is 7 before clicking next button
			if (j == 7) 
			{
				if (nextArrow.isDisplayed()) 
				{
					nextArrow.click();
					j = 0;
					Thread.sleep(500);
				}
			}
			j++;
			String itemName = allItems.get(i).getText();
			list.add(itemName);
		}

		System.out.println("******************SORTED LIST*******************");
		sortList(list);
		
		driver.close();

	}

	/*
	 *sectionName method gets section as user input and replaces the text in xpath, then traverses back
	 * to parent element and continues to browse that particular carousel
	 */
	public static String sectionName(String RequiredSectionName) {

		String dynamicXpath = "//h3[contains(text(),'%replaceable%')]/parent::div/parent::div/parent::div";
		String newXpath = dynamicXpath.replace("%replaceable%", RequiredSectionName);
		return newXpath;
	}

	public static void sortList(List<String> list) {
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}

	public static void browseurl(String urlTo) throws InterruptedException {
		driver.get(urlTo);
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3000)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,-3000)");
	}

	public static void scrollToObject(String objectXpath) throws InterruptedException
	{
		WebElement scrollObject = driver.findElement(By.xpath(objectXpath));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true)", scrollObject);
		Thread.sleep(1000);
	}
	
	
}
