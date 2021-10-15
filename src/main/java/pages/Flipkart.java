package pages;

import base.ChooseBrowser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Flipkart extends ChooseBrowser implements base.locators {
    WebDriver driver;
    public Flipkart() {
        try {
            this.driver=getDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void login() throws IOException {

        String appurl=getAppURL();
        driver.get(appurl);
        String IDPass[] = getIDPass();
        System.out.println(driver.getTitle());
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(USERNAME_INPUT_LOC))).sendKeys(IDPass[0]);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_INPUT_LOC))).sendKeys(IDPass[1]);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SIGN_IN_INPUT_LOC))).click();
        Reporter.log("Logged in to:"+ appurl);


    }
    public void logout(){
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement user=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(USER_LOC, "Prafull"))));
        if(user.isDisplayed()){
            actions.moveToElement(user).clickAndHold().build().perform();
        }

        WebElement user_logout=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGOUT_LOC)));
        System.out.println(user_logout.isDisplayed());
        user_logout.click();

        ((Locatable) user).getCoordinates().inViewPort();
        try {
            user.click();
        } catch (Exception e) {
            new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();
            user.click();
        }
    }
    public void search(String str) throws IOException, InterruptedException {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ESCAPE).build().perform();
        driver.findElement(By.name(SEARCH_BOX)).sendKeys(str);
        driver.findElement(By.className(SEARCH_BOX_BUTTON)).click();
        Thread.sleep(3000);
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.xpath(LOW_TO_HIGH))).click();

        Thread.sleep(3000);
        List<WebElement> list_of_products = driver.findElements(By.xpath(LIST_OF_PRODUCTS));
        List<WebElement> list_of_products_price = driver.findElements(By.xpath(LIST_OF_PRODUCT_PRIZES));
        List<WebElement> list_of_ratings = driver.findElements(By.xpath(LIST_OF_RATINGS));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(3000);

        int n=list_of_products.size();

        String product_name;
        String product_price;
        String product_ratings;
        int int_product_price;
        int int_ratings;

        HashMap<String, Integer> map_final_products = new HashMap<String, Integer>();
        HashMap<String, Integer> map_final_Ratings = new HashMap<String, Integer>();
        if(list_of_products_price.size() != 0)
        {
            for(int i=1;i<n;i++) {
                product_price = list_of_products_price.get(i).getText();
                product_price = product_price.replaceAll("[^0-9]", "");
                int_product_price = Integer.parseInt(product_price);
                if(int_product_price<40000){
                    product_name = list_of_products.get(i).getText();//Iterate and fetch product name
                    product_ratings=list_of_ratings.get(i).getText();
                    product_name=product_name.replaceAll("[,]", "");

                    product_ratings=product_ratings.replaceAll("[,a-zA-Z ]", "");
                    int_ratings=Integer.parseInt(product_ratings);

                    map_final_products.put(product_name,int_product_price);
                    map_final_Ratings.put(product_name, int_ratings);
                }
            }

        }

        addIntoCsv(map_final_products,map_final_Ratings);


    }
    public void addIntoCsv(HashMap<String, Integer> h1, HashMap<String, Integer> h2) throws IOException {
        Map<String, Integer> sortedMap = sortHashMapByValues(h1);

        System.out.println("Sorted Map   : " + sortedMap);

        Map<String, Integer> unsortMap2 = h2;

        Map<String, Integer> sortedMap2 = new TreeMap<String, Integer>(unsortMap2);

        System.out.println("Sorted Map   : " + sortedMap2);

        String outputFile = "test.csv";

        FileWriter writer = new FileWriter("E:/test.csv",false);
        writer.append("Device Details");
        writer.append(',');
        writer.append("Prize in Rs");
        writer.append(',');
        writer.append("Ratings");
        writer.append('\n');
        for (Map.Entry<String, Integer> entry1 : sortedMap.entrySet()) {
            System.out.println("Key = " + entry1.getKey() + ", Value = " + entry1.getValue());
            String one=entry1.getKey();
            Integer two=entry1.getValue();

            writer.append(""+ one);
            writer.append(',');
            writer.append(""+two);
            writer.append(',');

            for (Map.Entry<String, Integer> entry2 : sortedMap2.entrySet())
                if(entry1.getKey()==entry2.getKey()) {
                    writer.append(""+entry2.getValue());
                }
            writer.append('\n');
        }

        writer.flush();
        writer.close();
    }
    public LinkedHashMap<String, Integer> sortHashMapByValues(
            HashMap<String, Integer> map) {
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        for (int num : list) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        return (sortedMap);
    }

    public void quite() {
        if(driver!=null)
            driver.quit();
    }
}
