package week4.day2;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
public class SnapdealProject {
	public static void main(String[] args) throws Throwable {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	//  Go to Men's Fashion
		WebElement webMen = driver.findElement(By.xpath("(//span[@class='catText'])[6]"));		
		Actions builder = new Actions(driver);
		builder.moveToElement(webMen).perform();
		
	//	Go to Sports shoes				
		driver.findElement(By.xpath("(//span[@class='linkTest' and text()='Sports Shoes'])[1]")).click();
		
	//  Get the Count of the Sports shoes [Replace the non-integer value with space]
		String count = driver.findElement(By.xpath("//span[@class='category-count']	")).getText();
		String count1 = count.replaceAll("[^0-9]", "");
		int count2 = Integer.parseInt(count1);
		System.out.println("Count of the Sports shoes:" + count2);		
				 
	//  Click Training shoes	
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(2000);
				 
	//  Sort by Price Low to High	
		driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).click();
		driver.findElement(By.xpath("//div[@class='sort-selected']//following::li[2]")).click();
		Thread.sleep(2000);
		
	//  Scroll down the page	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");	
		
	//  Check if the items displayed are sorted correctly
		List<WebElement> prlist = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));				 
		List<Integer> val = new ArrayList<Integer>();
		for(int i=0; i<prlist.size(); i++)  {
			String pr = prlist.get(i).getText().replaceAll("[^0-9]", "");
			System.out.println(pr);
			val.add(Integer.parseInt(pr));
		}
	
		List<Integer> sortlist = new ArrayList<Integer>(val);
		Collections.sort(sortlist);
		for(int i=0; i<val.size(); i++) {
			if(val.get(i) == sortlist.get(i)) {
			System.out.println("Items are sorted correctly");
			} else {
			System.out.println("Items are not sorted");
			}
		} 
	
	//	Select any Shoe image
		WebElement VSS = driver.findElement(By.xpath("//img[@title='VSS Navy Training Shoes']"));
		builder.moveToElement(VSS).perform();
		
	//  Click Quick view
		driver.findElement(By.xpath("//div[contains(text(),'Quick View') and @pogid='641646837518']")).click();	
		
	//  Print the Cost	
		String price = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		price.replaceAll("[^0-9]", "");
		System.out.println("Product price:" + price);
		
	//  Print the Discount percentage	
		String disc = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Discount percentage:" + disc);
		
	//  Take a Snapshot	
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/shoe.png");
		FileUtils.copyFile(src, dst);
		
	//  Close the current Window [Quick view]	
		driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
		
	//  Select different brand		
		driver.findElement(By.xpath("(//button[contains(text(),'View More')])[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()=' Sparx ']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='APPLY']")).click();
			
		driver.close();
	}
}