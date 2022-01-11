package search;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;
public class Meeshosearch {
    public static WebDriver driver;
    static String url = "https://meesho.com/";
    static String s_search_string = "Shirts";

    @BeforeMethod
    public void startDriver() throws Exception {

        System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(url);
        WebElement searchbx = driver.findElement(By.xpath("//body/div[@id='__next']/div[2]/div[1]/div[1]/div[2]/div[1]/input[1]"));
        Assert.assertEquals(searchbx.isDisplayed(), true);
        System.out.println("Search Box is displayed." + true);
        searchbx.sendKeys("Shirts" + Keys.ENTER);
    }

    public void validation() {
        WebElement txtbx_search_result_text = driver.findElement(By.xpath("//body/div[@id='__next']/div[3]/div[1]/div[2]/div[1]/div[1]/span[1]"));
        Assert.assertEquals(txtbx_search_result_text.isDisplayed(), true);

    }

    public void lowcost() {
        List<WebElement> list_of_products = driver.findElements(By.xpath("//div[@class='NewProductCard__ProductImage-sc-j0e7tu-14 hXRxk']/following::p[1]"));
        List<WebElement> list_of_products_price = driver.findElements(By.xpath("//*[@id='__next']/div[3]/div/div[3]/div//a/div/div[2]/div[1]/h5"));
        for (WebElement element : list_of_products) {
            System.out.println(element.getText());
        }
        for (WebElement element1 : list_of_products_price) {
            System.out.println(element1.getText());
        }
        String product_name;
        String product_price;
        int int_product_price;
        HashMap<Integer, String> map_final_products = new HashMap<Integer, String>();
        for (int count = 0; count < list_of_products.size(); count++) {
            product_name = list_of_products.get(count).getText();
            product_price = list_of_products_price.get(count).getText();
            product_price = product_price.replaceAll("[^0-9]", "");
            int_product_price = Integer.parseInt(product_price);
            map_final_products.put(int_product_price, product_name);
        }
        System.out.println("Product Name and price fetched from UI and saved in HashMap as:" + map_final_products.toString());
        Set<Integer> allkeys = map_final_products.keySet();
        ArrayList<Integer> array_list_values_product_prices = new ArrayList<Integer>(allkeys);
        Collections.sort(array_list_values_product_prices);
        int low_price = array_list_values_product_prices.get(0);
        System.out.println("Low Product Price is: " + low_price + " Product name is: " + map_final_products.get(low_price));
    }


    @AfterMethod
    public void close() {
        driver.quit();
    }


@Test
    public void run()
    {
        Meeshosearch flipkartsearch=new Meeshosearch();
        flipkartsearch.validation();
        flipkartsearch.lowcost();

    }
}

