package TestOnePack;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class testCase1 {
@Test
public void test()
	{
		String browserPath = "C:/Program Files/Mozilla Firefox/firefox.exe";
		System.setProperty("webdriver.firefox.bin", browserPath);
        WebDriver driver = new FirefoxDriver();
        String url = "http://www.baidu.com";
        driver.get(url);
        String pageSource = driver.getPageSource();
        WebElement findElement = driver.findElement(By.cssSelector("#kw"));
        findElement.sendKeys("124");
        driver.findElement(By.cssSelector("#su")).click();
        List<WebElement> findElements = driver.findElements(By.cssSelector("#s_tab>a"));
        for(WebElement element:findElements)
        {
        	System.out.print(element.getText());
        	
        }
        
//        System.out.println(pageSource);
	}
}
