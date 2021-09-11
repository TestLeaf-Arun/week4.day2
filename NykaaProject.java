package week4.day2;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
public class NykaaProject {
	public static void main(String[] args) throws Throwable {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	//	Mouse hover on Brands and Popular
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		WebElement popular = driver.findElement(By.xpath("//a[text()='Popular']"));
		Actions builder=new Actions(driver);
		builder.moveToElement(brand).perform();
		builder.moveToElement(popular).perform();
		
	//	Click on L'Oreal Paris
		driver.findElement(By.xpath("//ul[@class='l-vertical-list']//following::li[4]")).click();
		
	//	Go to the newly opened Window
		Set<String> windowhandleset = driver.getWindowHandles();
		List<String> windowhandlelist = new ArrayList<String>(windowhandleset);
		driver.switchTo().window(windowhandlelist.get(1));
		
	//	Check the Title of the page
		String title = driver.getTitle();
		if(title.contains("L'Oreal Paris")) {
			System.out.println("In L'Oreal Paris page");
		} else {
			System.out.println("Not in L'Oreal Paris page");
		}
		
	//	Scroll down the page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(2000);
	
	//	Click Sort By
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
		
	//	Select Customer Top rated
		driver.findElement(By.xpath("(//div[@class='control__indicator radio'])[4]")).click();
		Thread.sleep(2000);
		
	//	Scroll down the page
		js.executeScript("window.scrollBy(0,600)");
		
	//	Click Category and Click Shampoo
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']//following::span[1]")).click();
		driver.findElement(By.xpath("(//label[@class='control control--checkbox'])[1]")).click();
		
	//	Check the filter is applied with Shampoo
		String str = driver.findElement(By.xpath("//li[text()='Shampoo']")).getText();
		if (str.contains("Shampoo")) {
			System.out.println("Shampoo is selected");
		} else {
			System.out.println("Shampoo is not selected");
		}
		
	//  Click on L'Oreal Paris Color Protect Shampoo
		driver.findElement(By.xpath("(//h2[@title='L'Oreal Paris Colour Protect Shampoo'])[1]")).click();
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		
	//	Go to the newly opened Window
		Set<String> windowhandleset1 = driver.getWindowHandles();
		List<String> windowhandlelist1=new ArrayList<String>(windowhandleset1);
		driver.switchTo().window(windowhandlelist1.get(2));
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		
	//	Select Size as 175ml
		String size = driver.findElement(By.xpath("//span[@class='shade-txt']")).getText();
		System.out.println(size);
		
	//	Print the MRP of the Product [Replace the non-integer value with space]
		String original = driver.findElement(By.xpath("(//span[@class='post-card__content-price-offer'])[1]")).getText();
		String org = original.replaceAll("[^0-9]", "");
		List<Integer> value = new ArrayList<Integer>();
		value.add(Integer.parseInt(org));
		System.out.println("Product price:" + value.get(0));
		Thread.sleep(2000);
		
	//	Click on Add to Bag
		driver.findElement(By.xpath("(//button[text()='ADD TO BAG'])[1]")).click();
		Thread.sleep(2000);

	//  Go to Shopping Bag	
		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
		Thread.sleep(2000);
		
	//  Print the Grand Total Amount [Replace the non-integer value with space]
		String total = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		String tot = total.replaceAll("[^0-9]", "");
		List<Integer> value1 = new ArrayList<Integer>();		
		value1.add(Integer.parseInt(tot));
		System.out.println("Grand Total:" + value1.get(1));
		Thread.sleep(2000);
	
	//	Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();

	//  Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();		
		
	//  Get the Grand Total displayed in this page [Replace the non-integer value with space]	
		String finalamt = driver.findElement(By.xpath("(//div[@class='value']/span)[2]")).getText();
		String finamt = finalamt.replaceAll("[^0-9]", "");
		List<Integer> value2 = new ArrayList<Integer>();
		value2.add(Integer.parseInt(finamt));
		System.out.println("Grand total:" + value2.get(2));		
		
	//  Check if the Grand Total displayed in this page is same as the Grand Total displayed earlier
		if(tot.equals(finamt)) {
			System.out.println("Grand total and total are same");	
		} else {
			System.out.println("Grand total and total are different");
		}
		
	//  Close all the Windows	
		driver.close();
	}
}