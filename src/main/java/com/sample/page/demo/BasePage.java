package com.sample.page.demo;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import support.jsonReader;

public class BasePage {

	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;    
	}

	protected HashMap<String,String>  getInputData() {
		HashMap<String,String> map=new HashMap<String,String>();
		org.json.simple.JSONObject jsonObject;
		try {
			jsonObject = jsonReader.ReadJsonFile("PizzaOrderForm.json");

			map.put("Pizzatype",(String)jsonObject.get("pizzaType").toString());
			map.put("PizzaTopping1",(String)jsonObject.get("pizzaToppingDropdown1").toString());
			map.put("PizzaTopping2",(String)jsonObject.get("pizzaToppingDropdown2").toString());
			map.put("PizzaCost",(String)jsonObject.get("pizza1Cost").toString());	
			map.put("PizzaQuantity",(String)jsonObject.get("pizza1Quantity").toString());
			map.put("EmailAddress",(String)jsonObject.get("email").toString());
			map.put("UserName",(String)jsonObject.get("name").toString());
			map.put("PhoneNumber",(String)jsonObject.get("phone").toString());
			map.put("CreditCard",(String)jsonObject.get("radioCreditCard").toString());
			map.put("Cash",(String)jsonObject.get("radioCash").toString());
			map.put("PlaceOrderButton",(String)jsonObject.get("placeOrderButton").toString());
			map.put("ResetButton",(String)jsonObject.get("resetButton").toString());
			map.put("DialogText",(String)jsonObject.get("dialogText").toString());
			map.put("Dialog",(String)jsonObject.get("dialog").toString());
			map.put("CloseDialog",(String)jsonObject.get("closeDialog").toString());

		} catch (Exception e) {

		}
		return map;
	}

}
