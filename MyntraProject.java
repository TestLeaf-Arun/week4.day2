package week4.day2;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
public class MyntraProject {
	public static void main(String[] args) throws IOException, Throwable {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	//  Mouse hover on Men
		WebElement webMen = driver.findElement(By.xpath("//a[text()='Men'][1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(webMen).perform();
		
	//  Click Jackets in Men
		driver.findElement(By.xpath("//a[text()='Jackets'][1]")).click();
		
	//  Get the Jackets result count
		String jacketcount = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		System.out.println("Jackets count:" + jacketcount);
		
	//  Replace the non-integer value with space
		String repjacketcount = jacketcount.replaceAll("[^0-9]", "");
		int count1 = Integer.parseInt(repjacketcount);
		System.out.println("Total Jackets count:" + count1);
		
	//  Get the Jackets Category count
		String jacketscategcount = driver.findElement(By.xpath("//span[@class='categories-num'][1]")).getText();
		System.out.println("Jackets category count:" + jacketscategcount);
		
	//  Replace the non-integer value with space
		String repjacketscategcount = jacketscategcount.replaceAll("[^0-9]", "");
		int count2 = Integer.parseInt(repjacketscategcount);
		System.out.println("Category Jacket count:" + count2);
		
	//  Get the Rain Jackets Category count
		String rainjacketcount = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		System.out.println("Rain Jackets count:" + rainjacketcount);
		
	//  Replace the non-integer value with space
		String reprainjacketcount = rainjacketcount.replaceAll("[^0-9]", "");
		int count3 = Integer.parseInt(reprainjacketcount);
		System.out.println("Category Rain Jacket count:" + count3);
		
	//  Validating whether the sum of categories gives the Total count
		if (count2 + count3 == count1) {
			System.out.println("Total count is matched");
		} else {
			System.out.println("Total count is not matching");
		}
		
	//  Check the Jackets checkbox
		driver.findElement(By.xpath("//div[@class='common-checkboxIndicator']")).click();
		
	//  Click more in Brand
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		
	//  Enter the text Duke
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']")).sendKeys("Duke");
		
	//  Click the Duke checkbox
		driver.findElement(By.xpath("(//label[@class=' common-customCheckbox'])[1]")).click();
		
	//  Close the popup
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		
	//  Confirm that all Brands are Duke
		List<WebElement> prodBrand = driver.findElements(By.tagName("h3"));
		int listsize = prodBrand.size();
		System.out.println(listsize);
		for (int i=0; i<listsize; i++) {
			String prodBrandText = prodBrand.get(i).getText();
			if (prodBrandText.equalsIgnoreCase("Duke")) {
				System.out.println("Product is Duke");
			} else {
				System.out.println("Product is not Duke");
			}
		}
		Thread.sleep(2000);
		
	//  Mouse hover on Sort by option
		driver.executeScript("window.scrollBy(0,500)");
		WebElement sortoption = driver.findElement(By.xpath("//div[contains(text(),'Sort by : ')]"));
		builder.moveToElement(sortoption).perform();
		
	//  Selecting Better discount from Sort option
		driver.findElement(By.xpath("(//label[@class='sort-label '])[3]")).click();
		
	//  Fetching the price of 1st displayed item
		String text2 = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("Price of the 1st displayed item:" + text2);
		
	//  Clicking the 1st displayed item
		driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).click();
		
	//  1st item opens in new tab, so windowHandle should be used
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		String windowHandle = windowHandlesList.get(1);
		driver.switchTo().window(windowHandle);
		System.out.println(driver.getTitle());
		
	//  Take Screenshot
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/window1.png");
		FileUtils.copyFile(src, dst);
		
	//  Click Wishlist icon in the Child window
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		driver.close();
	}
}