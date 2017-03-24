package utils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.SkipException;


public class ReadTestData {
	
	
	public static boolean isTestSuiteExecutable(String testSuiteName)
	{
		
		ExcelReader excel = new ExcelReader(Constants.TESTSUITE_PATH);
		
		int rowCount = excel.getRowCount(Constants.TESTSUITE_SHEET_NAME);
		
		for(int rNum=2; rNum<=rowCount;rNum++)
		{
			
			String suiteName = excel.getCellData(Constants.TESTSUITE_SHEET_NAME, 0, rNum);
					if(suiteName.equalsIgnoreCase(Constants.TESTSUITE1_NAME))
					{
						String runMode = excel.getCellData(Constants.TESTSUITE_SHEET_NAME, 1, rNum);
						if(runMode.equalsIgnoreCase("Y"))
							return true;
						else
							return false;
						
						
					}
			
		}
		return false;
	}
	
	
	public static boolean isTestExecutable(String testSuiteName, String testCaseName , ExcelReader excel)
	{
			
		
		int rowCount = excel.getRowCount(Constants.TESTCASE_SHEET_NAME);
		
		for(int rNum=2; rNum<=rowCount;rNum++)
		{
			String tcName = excel.getCellData(Constants.TESTCASE_SHEET_NAME, 0, rNum);
			
					if(tcName.equalsIgnoreCase(testCaseName))
					{
						String runMode = excel.getCellData(Constants.TESTCASE_SHEET_NAME, 1, rNum);
						
						if(runMode.equalsIgnoreCase("Y"))
							return true;
						else
							return false;
							
					}
		}
		
		return false;
	}
	
	
	
	public static void checkExecution(String testSuiteName, String tcName, String dataRunMode, ExcelReader excel)
	{
		
		if(!isTestSuiteExecutable(testSuiteName))
			throw new SkipException("Skipped : "+testSuiteName+" as the RunMode is set to No");
		
		if(!isTestExecutable(testSuiteName, tcName, excel))
			throw new SkipException("Skipped : "+tcName+" as the RunMode is set to No");
		
		if(dataRunMode.equalsIgnoreCase("N"))
			throw new SkipException("Skipped this data row as the RunMode is set to No");
		
		
	}
	
	public static Object[][] getData(String sheetName, String testCaseName, ExcelReader er)
	{
	
		int cols=0;
		int testCaseRowNum=1; // to track the row number where testcase starts
		int colStartRowNum = testCaseRowNum+1 ; // start of columns for a testcase
		int dataStartRowNum = colStartRowNum+1; // Start of the data for a test
		int dataEndRowNum=0; // End of the data for each test
		int totalDataRows=0; // Total rows of data for each test
		
		// Find the starting row of a Testcase
		
		while(!er.getCellData(sheetName, 0, testCaseRowNum).equalsIgnoreCase(testCaseName))
		{
			
			testCaseRowNum++;
			
		}
		
		System.out.println("Start of Testcase ("+testCaseName+") at Row : "+testCaseRowNum);		
		
		colStartRowNum = testCaseRowNum +1;
		//Find the total no.of columns for a Testcase 
		while(!er.getCellData(sheetName, cols, colStartRowNum).equalsIgnoreCase("")){
			
			cols++;	
		}
		
		System.out.println("No. of columns : "+cols); 
		dataStartRowNum = colStartRowNum +1;
		
		while(!er.getCellData(sheetName, 0, testCaseRowNum).equalsIgnoreCase(testCaseName))
		{
			
			testCaseRowNum++;
			
		}
		
		while(!er.getCellData(sheetName, 0, colStartRowNum+1).equalsIgnoreCase("")){
			
			colStartRowNum++;
			totalDataRows++	;
			
		}
		
		dataEndRowNum = dataStartRowNum+totalDataRows;
		
		Object[][] testData = new Object[totalDataRows][1];
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
