package week4.day2;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
public class jQueryUIResizable {
	public static void main(String[] args) throws Throwable {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(frame1);		
		Thread.sleep(2000);
		
		WebElement resize = driver.findElement(By.xpath("(//div[@id='resizable']//div)[3]"));
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		builder.moveToElement(resize).perform();
		
		Thread.sleep(2000);		
		builder.clickAndHold().dragAndDropBy(resize, -100, -100).perform();
	}
}