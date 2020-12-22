package com.sample.page.demo;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.sample.test.demo.constants.PizzaToppings;
import org.testng.asserts.SoftAssert;

import support.CommonInteract;

public class PizzaOrderFormPage extends BasePage {

	final static Logger logger = Logger.getLogger(PizzaOrderFormPage.class);
	String isOnlyAlpa = "^[a-zA-Z]*$";
	String isdigitreg = "^\\d+$";
	String emailaddressvalidation = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	String textMessageOnMissingPhoneNumber="Missing phone number";
	String textMessageOnMissingName="Missing name";
	String textMessageOnPlacingOrder="Thank you for your order! TOTAL";
	String textMessageOnPhoneNumberAndName= "Missing name Missing phone number";

	public PizzaOrderFormPage(WebDriver driver) {
		super(driver);
	}
	CommonInteract obj =new CommonInteract(driver);

	public void pizzaOrder(int index, String name, String email, String phoneNumber) throws InterruptedException
	{
		SoftAssert softAssert = new SoftAssert();
		HashMap<String,String> map = getInputData();
		System.out.print(map.get("Pizzatype"));

		obj.selectElementByIndex(map.get("Pizzatype"),index,"Pizzatype");

		String firstSelectedToppingText =obj.selectElementByIndex(map.get("PizzaTopping1"),index,"PizzaTopping1");
		softAssert.assertEquals(PizzaToppings.values()[index-1].getDisplayName(), firstSelectedToppingText);

		String secondSelectedToppingText=obj.selectElementByIndex(map.get("PizzaTopping2"),index,"PizzaTopping2");
		softAssert.assertEquals(PizzaToppings.values()[index-1].getDisplayName(), secondSelectedToppingText);

		obj.clickElement(map.get("PizzaCost"),"PizzaCost"); 
		obj.sendValuetoTextBox(map.get("PizzaQuantity"),"1","PizzaQuantity");	

		if(name.matches(isOnlyAlpa) || !name.isEmpty())
		{
			obj.sendValuetoTextBox(map.get("UserName"),name,"UserName"); 
		}
		else
		{
			logger.info("name should be Aplhabets");
			softAssert.fail("name should be Aplhabets");
		}		
		Thread.sleep(2000);

		if(email.matches(emailaddressvalidation))
		{
			obj.sendValuetoTextBox(map.get("EmailAddress"),email,"EmailAddress"); 
		}
		else
		{
			logger.info("email format is wrong");
			softAssert.fail("email format is wrong");
		}
		if(phoneNumber.matches(isdigitreg))
		{
			obj.sendValuetoTextBox(map.get("PhoneNumber"),phoneNumber,"PhoneNumber");
		}
		else
		{
			logger.info("phone should be numeric");
			softAssert.fail("phone should be numeric");
		}
		Thread.sleep(4000);
		obj.validateRadioButtonAndCheckbox(map.get("CreditCard"),"CreditCard");
		obj.validateElementDisplayed(map.get("Cash"));

		obj.validateElementDisplayed(map.get("ResetButton"));
		obj.clickElement(map.get("PlaceOrderButton"),"PlaceOrderButton");

		Thread.sleep(5000);
		if(name.isEmpty() && phoneNumber.isEmpty())
		{
			softAssert.assertEquals(obj.validateElementText(map.get("DialogText")), textMessageOnPhoneNumberAndName);
		}
		if(name.isEmpty())
		{
			softAssert.assertEquals(obj.validateElementText(map.get("DialogText")), textMessageOnMissingName);
		}
		if(phoneNumber.isEmpty())
		{
			softAssert.assertEquals(obj.validateElementText(map.get("DialogText")), textMessageOnMissingPhoneNumber);
		}
		obj.validateElementDisplayed(map.get("Dialog"));
		obj.validateElementDisplayed(map.get("DialogText"));
		if(textMessageOnPlacingOrder.contains(obj.validateElementText(map.get("DialogText"))))
		{
			logger.info("placing order text is displayed and same");
		}
		obj.clickElement(map.get("CloseDialog"),"CloseDialog");
		obj.clickElement(map.get("ResetButton"),"ResetButton");
		softAssert.assertAll();
	}


}
