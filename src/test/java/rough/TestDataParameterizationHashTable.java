package rough;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataParameterizationHashTable extends ReadTestData{

	
@Test(dataProvider="getData")	
public void testData(Hashtable<String,String> table)
{
	System.out.println(table.get("Iteration")+" | "+table.get("TestData")+" | "+table.get("Browser")+" | "+table.get("RunMode"));
	
}
	

@DataProvider
public Object[][] getData()
{
	
	String tcName = "SignUp Test";
	Object[][] testData=null;
	
	ReadTestData rtd = new ReadTestData();

		rtd.findTestcaseStartRow("testData", tcName);

		colStartRowNum = testCaseRowNum+1;
		
		//get the no. of cols of the test
		rtd.findTotalColsForTest("testdata");
		dataStartRowNum = colStartRowNum +1; // start of the testdata for each test
		
		//get the no. of rows of the testdata
		rtd.findTotalRowsForTest("testdata");
		
		// get the end row of the testdata for this test
		rtd.findEndOfDataForTest("testdata");
		
		testData = new Object[totalDataRows][1];
		int i=0;
		for(int j=dataStartRowNum;j<dataEndRowNum;j++)
		{
			Hashtable<String,String> table = new Hashtable<String,String>();
			for(int k=0;k<cols;k++)
			{
				String colName = er.getCellData("testdata", k, dataStartRowNum-1);
				String data = er.getCellData("testdata", k, j);
				table.put(colName, data);
			}
			testData[i][0] = table;
			i++;
		}
		

	return testData;

}
	
}
