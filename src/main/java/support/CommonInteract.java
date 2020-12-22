package support;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import com.sample.page.demo.BasePage;

public class CommonInteract extends BasePage {

	final static Logger logger = Logger.getLogger(CommonInteract.class);
	SoftAssert softAssert = new SoftAssert();

	public CommonInteract(WebDriver driver) {
		super(driver);
	}

	public void clickElement(String locator, String element)
	{
		WebElement ele=driver.findElement(By.xpath(locator));
		ele.click();
		logger.info("element is clicked" + element);
	}

	public String selectElementByIndex(String locator, int selectByIndex,String DropDownType)
	{
		String strCurrentValue = "";
		if(selectByIndex!=0)
		{
			Select dropDown = new Select(driver.findElement(By.xpath(locator)));

			List<WebElement> options=dropDown.getOptions();
			if(!options.isEmpty())
			{
				dropDown.selectByIndex(selectByIndex);	
				strCurrentValue = dropDown.getFirstSelectedOption().getText();
				logger.info("element is selected from dropdown"+ DropDownType);
			}else {
				softAssert.fail("drop down options are missing");
			}
		}
		else
		{
			softAssert.fail("please choose value from dropdown");
		}
		softAssert.assertAll();
		return strCurrentValue;
	}

	public void selectElementByVisibleText(String locator,String selectByVisibleText)
	{
		Select byVisibleText = new Select(driver.findElement(By.xpath(locator)));
		List<WebElement> options=byVisibleText.getOptions();
		if(!options.isEmpty())
		{	
			byVisibleText.deselectByVisibleText(selectByVisibleText);
			logger.info("element is selected from dropdown");
		}else {
			softAssert.fail("drop down options are missing");
		}
		softAssert.assertAll();
	}
	public void selectElementByValue(String locator, String selectByValue)
	{
		Select byValue = new Select(driver.findElement(By.xpath(locator)));
		List<WebElement> options=byValue.getOptions();
		if(!options.isEmpty())
		{	
			byValue.selectByValue(selectByValue);
			logger.info("element is selected from dropdown");
		}else {
			softAssert.fail("drop down options are missing");
		}
		softAssert.assertAll();
	}
	public void sendValuetoTextBox(String locator, String text, String inputboxText) throws InterruptedException
	{
		WebElement sendText = driver.findElement(By.xpath(locator));
		sendText.clear();
		sendText.sendKeys(text);
		logger.info("text is sent in text box" + inputboxText);
	}

	public void validateElementDisplayed(String locator) 
	{
		boolean eleDisplay = driver.findElement(By.xpath(locator)).isDisplayed();
		logger.info("element is displayed" + eleDisplay);
	}

	public void validateRadioButtonAndCheckbox(String locator, String radioButtonele)
	{
		WebElement ele=driver.findElement(By.xpath(locator));
		if(ele.isSelected())
		{
			logger.info("radio button is selected by default" + radioButtonele);
		}else {
			ele.click();
			logger.info("radio button is selected" + radioButtonele);
		}
	}

	public String validateElementText(String locator) 
	{
		String eletext = driver.findElement(By.xpath(locator)).getText();
		logger.info("element text is displayed" + eletext);
		return eletext;
	}
}
