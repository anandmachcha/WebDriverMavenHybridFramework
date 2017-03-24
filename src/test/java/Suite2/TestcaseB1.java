package Suite2;

import java.util.Hashtable;

import org.testng.annotations.Test;

import utils.Constants;
import utils.DataProviders;
import utils.ExcelReader;
import utils.ReadTestData;

public class TestcaseB1 {

	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataSuite2")
	public void testCaseB1(Hashtable<String,String> table)
	{
		ExcelReader excel = new ExcelReader(Constants.SUITE2);
		ReadTestData.checkExecution("Suite2", "TestcaseB1", table.get("RunMode"), excel);
		
		
	}
	
}
