package home;
import java.sql.SQLException;

import net.perf.database.PerfDB;

import org.testng.Assert;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class DKTNew {
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
	
	public PerfDB myPerfDB = new PerfDB();
	
	@Test
	public void DKT() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			driver= new FirefoxDriver();
			System.out.println(myPerfDB.connectDB("jdbc:mysql://atnsw-bench006:3306/monitoringdb", "perfmon", "123"));
			System.out.println(myPerfDB.initializeScriptExec("Driver Knowledge Test", "Roads and Maritime Services"));
			
			//start stop watch - home
			RMSHome.start();
			driver.get("http://www.rms.nsw.gov.au/index.html");
			Assert.assertEquals(driver.findElement(By.linkText("Customer Charter")).getText(), expStr1);
			RMSHome.stop();
			double res_time_sec = (double) RMSHome.getTime();
			res_time_sec = res_time_sec/1000;
			System.out.println(res_time_sec);
			if(driver.findElement(By.linkText("Customer Charter")).getText().equals(expStr1))
				myPerfDB.recordStepResponseTime ("Roads and Maritime Services", "Driver Knowledge Test", "RMS Home Page", "RMS Home Page", "Pass", "Successfully executed", res_time_sec);
			else{
				myPerfDB.recordStepExecFailure("Roads and Maritime Services", "Driver Knowledge Test", "RMS Home Page", "RMS Home Page", "Fail", "Page not found");
				myPerfDB.updateScriptExecutionFailure("Roads and Maritime Services", "Driver Knowledge Test", "RMS Home Page");
				myPerfDB.closeConnections();
				driver.close();
			}
		/*
			String str2 = driver.findElement(By.xpath("html/body/main/div/section/div/div[3]/div[1]/h2/span")).getText();
			System.out.println(str2);
			Assert.assertEquals(str2, expStr2 );
		
			System.out.println("inside homecheck 3");
			Thread.sleep(2000);
			String str3 = driver.findElement(By.linkText("Practice driver knowledge test")).getText();
			System.out.println(str3);
			Assert.assertEquals(str3, expStr3);*/
			
			//start stop watch for practise DKT
		
			DriverTest.start();
			driver.findElement(By.linkText("Practice driver knowledge test")).click();
			//String str4 = driver.findElement(By.xpath("//h1[@class='heading-middle']")).getText();
			//System.out.println(str4);
			Assert.assertEquals( driver.findElement(By.xpath("//h1[@class='heading-middle']")).getText(), expStr4);
			DriverTest.stop();
			
			res_time_sec = (double) DriverTest.getTime();
			res_time_sec = res_time_sec/1000;
			if( driver.findElement(By.xpath("//h1[@class='heading-middle']")).getText().equals(expStr4))				
				myPerfDB.recordStepResponseTime ("Roads and Maritime Services", "Driver Knowledge Test", "Driver Knowledge Test", "Driver Knowledge Test", "Pass", "Successfully executed", res_time_sec);
			else{
				myPerfDB.recordStepExecFailure("Roads and Maritime Services", "Driver Knowledge Test", "Driver Knowledge Test", "Driver Knowledge Test", "Fail", "Page not found");
				myPerfDB.updateScriptExecutionFailure("Roads and Maritime Services", "Driver Knowledge Test", "Driver Knowledge Test");
				myPerfDB.closeConnections();
				driver.close();
			}
			PracticeDKT.start();
			driver.findElement(By.linkText("Practice DKT")).click();
			PracticeDKT.stop();
			
			res_time_sec = (double) PracticeDKT.getTime();
			res_time_sec = res_time_sec/1000;
			
			myPerfDB.recordStepResponseTime ("Roads and Maritime Services", "Driver Knowledge Test", "Practice DKT", "Practice DKT", "Pass", "Successfully executed", res_time_sec);
		
			LaunchDKT.start();
			driver.findElement(By.linkText("Launch the free online practice test")).click();
			String str5=driver.findElement(By.xpath("html/body/form/table/tbody/tr/td/table[1]/tbody/tr[3]/td/font/b")).getText();
			//System.out.println(str5);
			Assert.assertEquals(str5, expStr5);
			
			LaunchDKT.stop();
			
			res_time_sec = (double) LaunchDKT.getTime();
			res_time_sec = res_time_sec/1000;
			//if(str5 == expStr5)
			if(str5.equals(expStr5))
				myPerfDB.recordStepResponseTime ("Roads and Maritime Services", "Driver Knowledge Test", "Launch online practise test", "Launch online practise test", "Pass", "Successfully executed", res_time_sec);
			else{
				myPerfDB.recordStepExecFailure("Roads and Maritime Services", "Driver Knowledge Test", "Launch online practise test", "Launch online practise test",  "Fail", "Page not found");
				myPerfDB.updateScriptExecutionFailure("Roads and Maritime Services", "Driver Knowledge Test", "Launch online practise test");
				myPerfDB.closeConnections();
				driver.close();
			}
			myPerfDB.updateScriptExecSuccess("Driver Knowledge Test", "Roads and Maritime Services");
			myPerfDB.closeConnections();
		}
		@AfterTest
		public void CloseDKT() throws InterruptedException{
			Thread.sleep(4000);
			driver.close();
	}

}
