package rough;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataParameterization extends ReadTestData{

	
@Test(dataProvider="getData")	
public void testData(String i,String td, String b, String rm, String un)
{
	System.out.println(i+" | "+td+" | "+b+" | "+rm+" | "+un);
	
}
	
	
	
	
@DataProvider
public Object[][] getData()
{
	String tcName = "Login Test";
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
		
		testData = new Object[totalDataRows][cols];
		
		for(int j=dataStartRowNum;j<dataEndRowNum;j++)
		{
			for(int k=0;k<cols;k++)
			{
				testData[j-dataStartRowNum][k] = er.getCellData("testdata", k, j);
			}
		}
		
	
	return testData;

}
	
}
