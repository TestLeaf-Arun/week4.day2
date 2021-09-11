package week4.day2;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
public class jQueryUISelectable {
	public static void main(String[] args) throws Throwable {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/selectable");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(frame1);		
		
		WebElement item2 = driver.findElement(By.xpath("//li[text()='Item 2']"));
		WebElement item6 = driver.findElement(By.xpath("//li[text()='Item 6']"));
		
		Actions builder = new Actions(driver);
		builder.clickAndHold(item2).moveToElement(item6).release().perform();		
	}
}