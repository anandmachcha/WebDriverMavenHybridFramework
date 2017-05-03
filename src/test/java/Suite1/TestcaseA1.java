package Suite1;

import java.util.Hashtable;

import org.testng.annotations.Test;

import utils.Constants;
import utils.ExcelReader;
import utils.ReadTestData;
import utils.DataProviders;

public class TestcaseA1 {

	
	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataSuite1")
	public void testCaseA1(Hashtable<String,String> table)
	{
		ExcelReader excel = new ExcelReader(Constants.SUITE1);
		ReadTestData.checkExecution("Suite1", "TestcaseA1", table.get("RunMode"), excel);
		// THis is the change which needs to be committed.
		
	}
	
	
}
