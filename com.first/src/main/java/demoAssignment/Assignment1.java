package demoAssignment;

import java.util.Random;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment1 {
	WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		WebDriverManager.chromedriver().setup(); 		
        driver = new ChromeDriver();
        System.out.println("Before Suite");
        driver.get("http://automationpractice.com/index.php");
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
	}
	
	@Test
	public void Register() throws InterruptedException
	{
		        

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
		String expectedUserName="FirstName"+" LastName";
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText(), expectedUserName);
		System.out.println("First Name captured");
		Assert.assertEquals(driver.getTitle(), "My account - My Store");
		Thread.sleep(3000);
		System.out.println("registration successfull");
	}
	
	@Test 
	public void Login()
	{
		Assert.assertEquals(driver.getTitle(), "My Store");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		Assert.assertEquals(driver.getTitle(), "Login - My Store");
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("Admin1@gmail.com");
		driver.findElement(By.name("passwd")).sendKeys("Test123");
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")));
		String FullName=driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText();
		Assert.assertEquals(FullName,"FirstName LastName" );
		System.out.println("Login successfull");
		
	}
	
	@Test
	public void Purchase() throws InterruptedException
	{
		Assert.assertEquals(driver.getTitle(), "My Store");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		Assert.assertEquals(driver.getTitle(), "Login - My Store");
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("Admin1@gmail.com");
		driver.findElement(By.name("passwd")).sendKeys("Test123");
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span")).click();
		driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();
		
	}
	
	@AfterMethod
	public void AfterEnd()
	{
		driver.quit();
		System.out.println("AfterSuite");
	}

}
