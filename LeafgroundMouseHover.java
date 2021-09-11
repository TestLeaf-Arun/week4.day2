package week4.day2;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
public class LeafgroundMouseHover {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/mouseOver.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement course = driver.findElement(By.xpath("//a[contains(text(),'TestLeaf Courses')]"));          
		Actions builder = new Actions(driver);
		builder.moveToElement(course).perform();
		
		driver.findElementByXPath("//a[contains(text(),'Selenium')]").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		builder.moveToElement(course).perform();
		
		List<WebElement> list = driver.findElements(By.xpath("//a[@class='listener']"));		
		for (WebElement eachData : list) {
			String text = eachData.getText();
			System.out.println(text);	
		}	
	}
}