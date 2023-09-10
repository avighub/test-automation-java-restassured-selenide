package testCases.util;

import com.saucelab.helper.HelperReadFile;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

    @DataProvider(name = "createUser")
    public Object[][] getLoginData() {
        String filePath = "./src/test/resources/TestData/a.csv";
        Object obj[][] = HelperReadFile.readCsv(filePath);
        return obj;
    }

    @Test(groups = {"dataprovider"}, dataProvider = "createUser")
    public void testDataProvider(String a) {
        System.out.println(a);
    }

}
