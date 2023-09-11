package unittest.utils;

import com.saucelab.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JsonAssertionAndCompare {

    @Test
    public void testJsonCompare1() {
        String json1 = "{ }";
        String json2 = "{ }";
        String source = FileUtils.parseFileToJson("TestData/source.json").toString();
        String destination = FileUtils.parseFileToJson("TestData/destination.json").toString();

        //Compares actual json is present in expected
        JSONAssert.assertEquals(source, destination, JSONCompareMode.LENIENT);

        //Compares array objects

    }

    @Test
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
