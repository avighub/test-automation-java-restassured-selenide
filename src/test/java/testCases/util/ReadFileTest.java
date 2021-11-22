package testCases.util;

import com.codoid.products.exception.FilloException;
import com.qa.helper.HelperReadFile;
import org.testng.annotations.Test;

public class ReadFileTest {

    @Test(groups = "excel")
    public void testExcelReadByFillo() throws FilloException {
        HelperReadFile helperReadFile = new HelperReadFile();
        String query = "Select * from Sheet1";
        String filePath = "./src/test/resources/TestCases.xlsx";
        helperReadFile.getExcelDataByColumnName(filePath, "Sheet1", "Test case Name");
    }

    @Test(groups = "excel")
    public void testFilioGetFieldNames() throws FilloException {
        HelperReadFile helperReadFile = new HelperReadFile();
//        String query="Select * from Sheet1";
        String filePath = "./src/test/resources/TestCases.xlsx";
        System.out.println(helperReadFile.getExcelFieldNames(filePath, "Sheet1"));
    }

    @Test(groups = "excel")
    public void testFilioGetDataByColIndex() throws FilloException {
        HelperReadFile helperReadFile = new HelperReadFile();
//        String query="Select * from Sheet1";
        String filePath = "./src/test/resources/TestCases.xlsx";
        System.out.println(helperReadFile.getExcelDataByColumnIndex(filePath, "Sheet1", 2));
    }

}
