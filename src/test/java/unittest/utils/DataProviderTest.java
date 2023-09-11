package unittest.utils;


import org.junit.jupiter.api.Test;

public class DataProviderTest {

//    @DataProvider(name = "createUser")
//    public Object[][] getLoginData() {
//        String filePath = "./src/test/resources/TestData/a.csv";
//        Object obj[][] = FileUtils.readCsv(filePath);
//        return obj;
//    }

    @Test
    public void testDataProvider(String a) {
        System.out.println(a);
    }

}
