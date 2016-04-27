package home;
import org.testng.Assert;
import org.apache.commons.lang3.time.StopWatch;
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
	
	public StopWatch RMSHome = new StopWatch();
	public StopWatch DriverTest = new StopWatch();
	public StopWatch PracticeDKT = new StopWatch();
	public StopWatch LaunchDKT = new StopWatch();
	
	
	@BeforeTest
	public void OpenDKT()
	{
		driver= new FirefoxDriver();
		RMSHome.start();
		driver.get("http://www.rms.nsw.gov.au/index.html");
	}
	
	@Test
	public void RMSHome()
	{
		Assert.assertEquals(driver.findElement(By.linkText("Customer Charter")).getText(), expStr1);
		RMSHome.stop();
		double res_time_sec = (double) RMSHome.getTime();
		res_time_sec = res_time_sec/1000;
		System.out.println(res_time_sec);
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
	public void HomeCheck3() throws InterruptedException
	{
		System.out.println("inside homecheck 3");
		Thread.sleep(2000);
		String str3 = driver.findElement(By.linkText("Practice driver knowledge test")).getText();
		System.out.println(str3);
		Assert.assertEquals(str3, expStr3);
	}
	@Test 
	public void PracticeDKTClick1()
	{
		DriverTest.start();
		driver.findElement(By.linkText("Practice driver knowledge test")).click();
		//String str4 = driver.findElement(By.xpath("//h1[@class='heading-middle']")).getText();
		//System.out.println(str4);
		Assert.assertEquals( driver.findElement(By.xpath("//h1[@class='heading-middle']")).getText(), expStr4);
		DriverTest.stop();
	}
	@Test(dependsOnMethods="PracticeDKTClick1")
	public void PracticeDKTClick2()
	{
		PracticeDKT.start();
		driver.findElement(By.linkText("Practice DKT")).click();
		PracticeDKT.stop();
		
	}
	@Test(dependsOnMethods="PracticeDKTClick2")
	public void LaunchDKT()
	{
		LaunchDKT.start();
		driver.findElement(By.linkText("Launch the free online practice test")).click();
		//String str5=driver.findElement(By.xpath("html/body/form/table/tbody/tr/td/table[1]/tbody/tr[3]/td/font/b")).getText();
		//System.out.println(str5);
		Assert.assertEquals(driver.findElement(By.xpath("html/body/form/table/tbody/tr/td/table[1]/tbody/tr[3]/td/font/b")).getText(), expStr5);
		LaunchDKT.stop();
	}
	@AfterTest
	public void CloseDKT() throws InterruptedException{
		Thread.sleep(4000);
		driver.close();
	}
	
}
