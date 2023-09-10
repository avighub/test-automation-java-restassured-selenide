package DataProvider;

import com.saucelab.helper.HelperReadFile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class CreateUserDataProvider {
    @DataProvider(name = "createUser")
    public Object[][] getLoginData() throws IOException, InvalidFormatException {
        String filePath = "./src/test/resources/TestData/CreateUserTestData.csv";
        Object obj[][] = HelperReadFile.readCsv(filePath);
        return obj;
    }

}
