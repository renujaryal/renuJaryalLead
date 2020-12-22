package com.sample.test.demo.tests;

import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.page.demo.PizzaOrderFormPage;
import com.sample.test.demo.TestBase;

public class DemoTest extends TestBase {

	//This DataProvider will validate happy path , boundary, negative and other use case 
	@DataProvider(name = "pizzaForTestData")
	public static Object[][] dataProvFunc(){

		return new Object[][]{
			{"usernameTest","test@gmail.com", "5555555555"},
			{"LTest","tes1t@gmail.com", "3467hu76767"},
			{"LTest","tes1t@gmail...com", "76767hu76767"},
			{"testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest","testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest@gmail.com", "4567676767456767676745676767674567676767456767676745676767674567676767456767676745676767674567676767"},
			{"","tes1t@gmail.com", ""},
			{"","tes1t@gmail.com", "55555555"},
			{"usernameTest","tes1t@gmail.com", ""}
		};
	}

	@Test(description="validate positive, negative and other scenerios",groups = { "functionalTest", "smokeTest" },dataProvider="pizzaForTestData")
	public void demoTest(String name,String email,String phonenumber) throws InterruptedException {

		Random ran = new Random();
		int index = ran.nextInt(2) + 3;

		PizzaOrderFormPage pg = new PizzaOrderFormPage(getDriver());
		pg.pizzaOrder(index,name,email,phonenumber);
	}

	@Test(description="validate order should not placed without dropdown selections",groups = { "smokeTest" },dataProvider="pizzaForTestData")
	public void validateDropDownSelections(String name,String email,String phonenumber) throws InterruptedException {

		int index =0;

		PizzaOrderFormPage pg = new PizzaOrderFormPage(getDriver());
		pg.pizzaOrder(index, name,email,phonenumber);
	}
}
