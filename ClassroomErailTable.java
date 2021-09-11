package week4.day2;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class ClassroomErailTable {
	public static void main(String[] args) throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	//  From Station	
		WebElement fromStation = driver.findElement(By.id("txtStationFrom"));
		fromStation.clear();
		fromStation.sendKeys("MS");
		fromStation.sendKeys(Keys.ENTER);

	//  To Station	
		WebElement toStation = driver.findElement(By.id("txtStationTo"));
		toStation.clear();
		toStation.sendKeys("MDU");
		toStation.sendKeys(Keys.ENTER);

	//  Select Date	
		driver.findElement(By.id("chkSelectDateOnly")).click();
		Thread.sleep(1000);
		
		WebElement webtable = driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']"));
		List<WebElement> trainList = webtable.findElements(By.tagName("tr"));
		System.out.println(trainList.size()); // number of Trains available
		
		List<String> nameList = new ArrayList<String>(); // empty List
		
		for (int i = 0; i < trainList.size(); i++) {
			WebElement eachRow = trainList.get(i);
			List<WebElement> eachData = eachRow.findElements(By.tagName("td"));
			String text = eachData.get(1).getText();
			System.out.println(i + " " + text);			
			nameList.add(text);  // add to the empty List
		}
		
		Collections.sort(nameList);
		System.out.println("Sorted");
		System.out.println(nameList);
	}
}