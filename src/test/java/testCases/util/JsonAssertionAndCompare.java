package testCases.util;

import com.qa.helper.HelperReadFile;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class JsonAssertionAndCompare {

    @Test(groups = {"jsoncompare1"})
    public void testJsonCompare1() {
        String json1 = "{ }";
        String json2 = "{ }";
        String source = HelperReadFile.parseFileToJson("TestData/source.json").toString();
        String destination = HelperReadFile.parseFileToJson("TestData/destination.json").toString();

        //Compares actual json is present in expected
        JSONAssert.assertEquals(source, destination, JSONCompareMode.LENIENT);

        //Compares array objects

    }

    @Test(groups = {"jsoncompare2"})
    public void testJsonCompare2() {
        //Compare with custom message
        String actual = "{id:123,name:\"John\"}";
        String failureMessage = "Only one field is expected: name";
        try {
            JSONAssert.assertEquals(failureMessage,
                    "{name:\"John\"}", actual, JSONCompareMode.STRICT);
        } catch (AssertionError ae) {
            System.out.println(ae);
        }
    }

}
