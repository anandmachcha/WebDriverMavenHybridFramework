package Suite1;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.Constants;
import utils.DataProviders;
import utils.ExcelReader;
import utils.ReadTestData;

public class TestcaseA2 {

	
	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataSuite1")
	public void testCaseA2(Hashtable<String,String> table)
	{
		ExcelReader excel = new ExcelReader(Constants.SUITE1);
		ReadTestData.checkExecution("Suite1", "TestcaseA2", table.get("RunMode"), excel);
	}
	
}
