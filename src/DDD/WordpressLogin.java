package DDD;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class WordpressLogin {
	WebDriver driver;
	@Test(dataProvider="WordpressData")
	public void LoginToWordpress(String username,String password)
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://demosite.center/wordpress/wp-login.php");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='wp-submit']")).click();
		/*WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wp-submit']")));*/
		Assert.assertTrue(driver.getTitle().contains("Dashboard"),"User is not able to login-inavlid Credentials");
		System.out.println("Page Title verified-user is login Successfully");
	}
	@DataProvider(name="WordpressData")
	public Object[][] passData()
	{
		Object[][] data=new Object[3][2];
		data[0][0]="admin1";
		data[0][1]="demo1";
		data[1][0]="admin";
		data[1][1]="demo123";
		data[2][0]="admin";
		data[2][1]="demo1234";
		return data;		
	}
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
}