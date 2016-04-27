package home;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class DKT {
	public WebDriver driver;
	public String expStr1 = "Customer Charter";
	public String expStr2 = "Do it online";
	public String expStr3 = "Practice driver knowledge test";
	public String expStr4 = "Driver Knowledge Test (DKT)";
	public String expStr5 = "          Welcome to RTA's Online Demonstration Driver Knowledge Test";
	@BeforeTest
	public void OpenDKT()
	{
		driver= new FirefoxDriver();
		driver.get("http://www.rms.nsw.gov.au/index.html");
	}
	
	@Test
	public void HomeCheck1()
	{
		System.out.println("inside homecheck 1");
		String str1 = driver.findElement(By.linkText("Customer Charter")).getText();
		System.out.println(str1);
		Assert.assertEquals(str1, expStr1);
	}
	
	@Test
	public void HomeCheck2()
	{
		System.out.println("inside homecheck 2");
		String str2 = driver.findElement(By.xpath("html/body/main/div/section/div/div[3]/div[1]/h2/span")).getText();
		System.out.println(str2);
		Assert.assertEquals(str2, expStr2 );
	}
	@Test
	public void HomeCheck3()
	{
		System.out.println("inside homecheck 3");
		String str3 = driver.findElement(By.linkText("Practice driver knowledge test")).getText();
		System.out.println(str3);
		Assert.assertEquals(str3, expStr3);
	}
	@Test 
	public void PracticeDKTClick1()
	{
		driver.findElement(By.linkText("Practice driver knowledge test")).click();
	}
	@Test(dependsOnMethods="PracticeDKTClick1")
	public void PracticeDKTCheck1()
	{
		String str4 = driver.findElement(By.xpath("//h1[@class='heading-middle']")).getText();
		System.out.println(str4);
		Assert.assertEquals(str4, expStr4);
		driver.findElement(By.linkText("Practice DKT")).click();
	}
	@Test(dependsOnMethods="PracticeDKTCheck1")
	public void PracticeDKTCheck2()
	{
		driver.findElement(By.linkText("Launch the free online practice test")).click();
		String str5=driver.findElement(By.xpath("html/body/form/table/tbody/tr/td/table[1]/tbody/tr[3]/td/font/b")).getText();
		System.out.println(str5);
		Assert.assertEquals(str5, expStr5);
	}
	@AfterTest
	public void CloseDKT() throws InterruptedException{
		Thread.sleep(4000);
		driver.close();
	}
	
}
