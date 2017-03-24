package rough;

public class ReadTestData {

	
	public static ExcelReader er = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testdata.xlsx");
	
	public static String tcName[] = new String[3];
	
	public static int cols=0;
	public static int testCaseRowNum; // to track the row number where testcase starts
	public static int colStartRowNum ; // start of columns for a testcase
	public static int dataStartRowNum; // Start of the data for a test
	public static int dataEndRowNum; // End of the data for each test
	public static int totalDataRows; // Total rows of data for each test
	
	public ReadTestData(){
		tcName[0] = "Login Test";
		tcName[1] = "SignUp Test";
		tcName[2] = "UserReg Test" ;	
		testCaseRowNum = 1; // to track the row number where testcase starts
		colStartRowNum =testCaseRowNum+1; // start of columns for a testcase
		dataStartRowNum = colStartRowNum +1; // Start of the data for a test
		dataEndRowNum = 0; // End of the data for each test
		totalDataRows = 0; // Total rows of data for each test
	}
	
	
	// Find the Start of the Testcase
	public void findTestcaseStartRow(String sheetName, String testName){
		
		while(!er.getCellData(sheetName, 0, testCaseRowNum).equalsIgnoreCase(testName))
		{
			
			testCaseRowNum++;
			
		}
		
	}

	//Find the Total columns for each test
	public void findTotalColsForTest(String sheetName)
	{

		while(!er.getCellData("testdata", cols, colStartRowNum).equalsIgnoreCase("")){
			
				cols++;	
		}
		
	}
	
	//Find the starting row of each Test
	public void findTestcaseStartRow(String sheetName, int testInputIndex){
		
		while(!er.getCellData(sheetName, 0, testCaseRowNum).equalsIgnoreCase(tcName[testInputIndex]))
		{
			
			testCaseRowNum++;
			
		}
		
	}
	
	//Find the total number of data rows for each test
	public void findTotalRowsForTest(String sheetName)
	{
		while(!er.getCellData(sheetName, 0, colStartRowNum+1).equalsIgnoreCase("")){
			
			colStartRowNum++;
			totalDataRows++	;
			
		}
	}
	
	//Find end of the Test data for each test
	public void findEndOfDataForTest(String sheetName)
	{
		dataEndRowNum = dataStartRowNum+totalDataRows;
		
	}
	
	//Display the test data for each Testcase
	public void displayDataForTest(String sheetName)
	{
		for(int j=dataStartRowNum;j<dataEndRowNum;j++)
		{
			for(int k=0;k<cols;k++)
			{
				System.out.print(er.getCellData("testdata", k, j)+" | ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ReadTestData rtd = new ReadTestData();

		for(int i=0;i<rtd.tcName.length;i++)
		{
			
			rtd.findTestcaseStartRow("testData", i);
			
			System.out.print(tcName[i]+" \n - Starts at row :"+testCaseRowNum);
			
			colStartRowNum = testCaseRowNum+1;
			
			//get the no. of cols of the test
			rtd.findTotalColsForTest("testdata");
			dataStartRowNum = colStartRowNum +1; // start of the testdata for each test
			System.out.print (" \n-Has :"+cols+" columns.");
			
			
			//get the no. of rows of the testdata
			rtd.findTotalRowsForTest("testdata");
			System.out.print (" \n-Has :"+totalDataRows+" rows of test data.");
			System.out.println();
			
			//dataEndRowNum = dataStartRowNum + totalDataRows; // get the end row of the testdata for this test
			rtd.findEndOfDataForTest("testdata");
			
			//display the data for a testcase
			rtd.displayDataForTest("sheetName");
			
			totalDataRows = 0;
			cols=0;
			System.out.println();
			
		}
		
		
	}

}
