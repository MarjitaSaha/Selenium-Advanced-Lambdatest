package testNG;

//import java.lang.reflect.Method;
//import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;

import org.openqa.selenium.*;

public class Testng1 {

	 public RemoteWebDriver driver = null;
	  @Test(priority=0)
	  @Parameters({ "browser", "version", "platform" })
	  public void scenarioOne(String browser, String version, String platform) throws Exception {
			//1 is in before test method  
		  //2
				  WebDriverWait wait = new WebDriverWait(driver, 20);
				  SoftAssert sa = new SoftAssert();
		  		   	//WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
			         String title = driver.getTitle();
			         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='uppercase font-bold text-black text-size-16 tracking-widest inline-block hover:underline']")));
			         String parent = driver.getWindowHandle();
			         
			   //3      
			         WebElement integration = driver.findElement(By.cssSelector("a[class='uppercase font-bold text-black text-size-16 tracking-widest inline-block hover:underline']"));
			         JavascriptExecutor js = (JavascriptExecutor) driver; 
			         js.executeScript("arguments[0].scrollIntoView();" , integration);
			         Thread.sleep(2000);
			         
			         //4
			         String clk = Keys.chord(Keys.CONTROL, Keys.ENTER);
			         driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[7]/div/div/div/div/a")).sendKeys(clk);
			         Thread.sleep(10000);
			         
			         //5
			         Set<String>s=driver.getWindowHandles();
			         Iterator<String> I1= s.iterator();

			         while(I1.hasNext())
			         {

			         String child_window=I1.next();


			         if(!parent.equals(child_window))
			         {
			        	 
			         driver.switchTo().window(child_window);
			         driver.manage().window().maximize();
			         Thread.sleep(3000);
			         
			         //6
			         driver.getCurrentUrl();
			         String url1 = driver.getCurrentUrl();
			         if(!url1.equals("https://www.lambdatest.com/integrations")) {
			        	 sa.equals("https://www.lambdatest.com/integrations"==url1);
			        	 System.out.print("Url 1 not verified");
			         }
			         else {
			        	 System.out.println("Url 1 verified successfully");
			         }
			         System.out.println(s);
			         //7
				      driver.findElement(By.xpath("//a[normalize-space()='Codeless Automation']"));
				      Thread.sleep(2000);
				      
				      //8
				      driver.findElement(By.xpath("//a[@href='https://www.lambdatest.com/support/docs/testingwhiz-integration/']")).click();
				      Thread.sleep(2000);
				      
				      //9
				      String title2 = driver.getTitle();
				      System.out.println(title2);
				      
				      Thread.sleep(3000);
				      if(title2 != "TestingWhiz Integration | LambdaTest") {
				    	  System.out.println("Title verificatioon status:"+sa.equals( "TestingWhiz Integration | LambdaTest" ==title2));
				      }
				      else {
				    	  System.out.println("Title verified successfully");
				      }
				      Thread.sleep(2000);
				      
				      //10
				      driver.close();
				      s.remove(child_window);
			         }
			         }
			         //switch to the parent window
			         driver.switchTo().window(parent);
			         
			         //11
			         System.out.println("Number of windows is: "+s.size());
			         Thread.sleep(2000);
			         
			         //12
			         driver.get("https://www.lambdatest.com/blog");
			         
			         //13
			         String url = driver.findElement(By.xpath("//a[@href='https://community.lambdatest.com/'][normalize-space()='Community']")).getAttribute("href");
			         System.out.println(url);
			         if(url.equals("https://community.lambdatest.com/")){
			        	 System.out.println("Url2 verified");
			         }
			         else {
			        	 System.out.println("Url2 not verified");
			         }
			         Thread.sleep(3000);
			         
			         //14
			         close();
				
			
	  }
	  @BeforeMethod
	  @Parameters({ "browser", "version", "platform" })
	  public void setUpClass(String browser, String version, String platform) throws Exception {

	  	String username = "marjitasaha"; 
			String accesskey = "raAN6ZWVRgGO0jSeVTVKVFuldWIkT76pt7tFcShivGoY9p8cgl"; 

	  		DesiredCapabilities capability = new DesiredCapabilities();    	
	        
	  		capability.setCapability(CapabilityType.BROWSER_NAME, browser);
	  		capability.setCapability(CapabilityType.VERSION,version);
	  		capability.setCapability(CapabilityType.PLATFORM, platform);		
	  		
	        capability.setCapability("build", "New TestNG");
	  		capability.setCapability("network", true);
	  		capability.setCapability("video", true);
	  		capability.setCapability("console", true);
	  		capability.setCapability("visual", true);

	  		String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
	  		System.out.println(gridURL);
	  		driver = new RemoteWebDriver(new URL(gridURL), capability);
	  		System.out.println(capability);
	  		System.out.println(driver.getSessionId());
	  		
	  		//1
	          driver.get("https://www.lambdatest.com/");
	          driver.manage().deleteAllCookies();
		 
	  }
	  @AfterMethod
	  public void close()
	  {
		  driver.quit();
	  }

}