package org.test.Absolutepath;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flipkart {
	public static void main(String[] args) throws Exception {
		String newText1=null;
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\SureshG\\Downloads\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		try {
		driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();
		}
		catch(Exception e) {
			System.out.println("PopUp Closed");
		}
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("mobiles",Keys.ENTER);
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		File f= new File("C:\\Users\\SureshG\\subhaworkspace\\Absolutepath\\Xcel\\Flip.xlsx"); 
		FileOutputStream fs= new FileOutputStream(f);
		Workbook w= new XSSFWorkbook();
		Sheet s= w.createSheet("FlipKart");
		Thread.sleep(3000);
		List<WebElement> mobName = driver.findElements(By.xpath("//div[@id='container']//div//div//a//div[contains(text(),'GB')]"));
		for(int i=0;i<mobName.size();i++) {
			String text=mobName.get(i).getText();
			Row r=s.createRow(i);
			Cell c=r.createCell(0);
			c.setCellValue(text);
		}w.write(fs);
		mobName.get(5).click();
		String par=driver.getWindowHandle();
		Set<String>All=driver.getWindowHandles();
		System.out.println(par+"\n"+All);
		for(String X:All) {
			if(!X.equals(par)) {
				driver.switchTo().window(X);
				WebElement name=driver.findElement(By.xpath("//div[@id='container']//span[contains(text(),'GB')]"));
				newText1=name.getText();
				System.out.println("Mobile Name:"+newText1);
			}
		}
		FileInputStream ffs= new FileInputStream(f);
		Cell c1=s.getRow(5).getCell(0);
		System.out.println("Excel Value:"+c1);
		if(c1.equals(newText1)) {
			System.out.println("pass");
			
		}
		else {
			System.out.println("Fail");
		}
		
		
		
		

	}

}
