package demoAssignment;

import java.util.Random;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment1 {
	WebDriver driver;
	
	@BeforeSuite
	public void setup()
	{
		WebDriverManager.chromedriver().setup(); 		
        driver = new ChromeDriver();
        System.out.println("Before Suite");
	}
	
	@Test
	public void First() throws InterruptedException
	{
		        
        driver.get("http://automationpractice.com/index.php");
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		Assert.assertEquals(driver.getTitle(), "My Store");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		Assert.assertEquals(driver.getTitle(), "Login - My Store");
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"email_create\"]")));
		
		WebElement Email= driver.findElement(By.xpath("//*[@id=\"email_create\"]"));
		String email;
		Random rand = new Random();
		int randNO=rand.nextInt(999999999);
		System.out.println("Number generated is "+randNO);
		
		email="Admin"+randNO+"@Gmail.com";
		Email.sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();
		Assert.assertEquals(driver.getTitle(), "Login - My Store");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("customer_firstname")));
		driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[1]/div[1]/label/div/span/input")).click();
		driver.findElement(By.name("customer_firstname")).sendKeys("FirstName");
		driver.findElement(By.name("customer_lastname")).sendKeys("LastName");
		driver.findElement(By.name("passwd")).sendKeys("Test123");
		driver.findElement(By.name("firstname")).sendKeys("FirstNAme");
		driver.findElement(By.name("lastname")).sendKeys("LastNAme");
		driver.findElement(By.name("address1")).sendKeys("40000");
		driver.findElement(By.name("city")).sendKeys("City");
		Select State=new Select(driver.findElement(By.id("id_state")));
		State.selectByVisibleText("Texas");
		driver.findElement(By.name("postcode")).sendKeys("40000");
		Select Country=new Select(driver.findElement(By.name("id_country")));
		Country.selectByVisibleText("United States");
		driver.findElement(By.name("phone")).sendKeys("65326532");
		driver.findElement(By.id("phone_mobile")).sendKeys("65326532");
		driver.findElement(By.name("alias")).clear();
		driver.findElement(By.name("alias")).sendKeys("CorpUSer01");
		driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span")).click();
		Assert.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.", driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText());
		System.out.println("account created");
		//String expectedUserName="FirstNAme"+"LastNAme";
		//Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText(), expectedUserName);
		System.out.println("First Name captured");
		Assert.assertEquals(driver.getTitle(), "My account - My Store");
		Thread.sleep(3000);
	}
	
	@AfterSuite
	public void AfterEnd()
	{
		driver.quit();
		System.out.println("AfterSuite");
	}

}
