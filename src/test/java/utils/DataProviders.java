package utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	
	@DataProvider(name="getDataSuite1")
	public static Object[][] getDataSuite1(Method m)
	{
		ExcelReader er = new ExcelReader(Constants.SUITE1);
		
		return ReadTestData.getData("TestData", m.getName(), er);
		
	}
	
	
	@DataProvider(name="getDataSuite2")
	public static Object[][] getDataSuite2(Method m)
	{
		ExcelReader er = new ExcelReader(Constants.SUITE2);
		
		return ReadTestData.getData("TestData", m.getName(), er);
	}
	
}
