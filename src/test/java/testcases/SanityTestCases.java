package testcases;

import base.ChooseBrowser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.Flipkart;
import java.io.IOException;


public class SanityTestCases extends ChooseBrowser{
    Flipkart flipkart = new Flipkart();
  @Test
  @Parameters("text_to_search")
    public void tc_001(String text) throws IOException, InterruptedException {
        flipkart.login();
        flipkart.search(text);

    }
    @AfterClass
    public void tearDown() {
        flipkart.quite();
    }
}
