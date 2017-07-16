package myproject.automation.hometask5;


import myproject.automation.hometask5.utils.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;
    public static String customProduct;
    String priceProduct;
    String qtyProduct;
    String urlProduct;
    int numberProduct;


    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void openSite(){
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/");
        Assert.assertEquals(driver.getTitle(), "prestashop-automation", "There isn't such site");
    }

    public void openRandomProduct() { //Определяем список товаров
        CustomReporter.log("TEST user ");
        WebElement allProductsButton = driver.findElement(By.xpath("//a[@class='all-product-link pull-xs-left pull-md-right h4']"));
        allProductsButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Главная")));
        List<WebElement> allProducts = driver.findElements(By.xpath("//h1[@class='h3 product-title']"));
        Random random = new Random(); //определяем номер случайного товара
        int productNumber = random.nextInt(allProducts.size());
        String [] nameProducts = new String[allProducts.size()]; //создание массива названий товаров
        int i = 0;
        for(WebElement name1: allProducts) {
            nameProducts[i] = name1.getText();
            i++;
        }
        customProduct = nameProducts[productNumber]; //название случайно отобранного товара

        WebElement buyProduct = driver.findElement(By.linkText(customProduct));
        buyProduct.click(); //кликаем купить товар



    }

  public void getOpenedProductInfo() {
        CustomReporter.log("TEST user ");
        WebElement priceProductBlock = driver.findElement(By.xpath("//span[@itemprop='price']"));
        priceProduct = priceProductBlock.getText();   //Получаем цену товара
        WebElement detailProduct = driver.findElement(By.linkText("Подробнее о товаре"));
        detailProduct.click();  // Входим в "Подробнее о товаре"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".label")));
        WebElement qtyProductBlock = driver.findElement(By.xpath("//div[@class='product-quantities']/span"));
        qtyProduct = qtyProductBlock.getText();   //Получаем строку с Количеством

        char[] mas = qtyProduct.toCharArray();  //Преобраз строку в массив
        char[] mass = new char[3];              //Получаем Строк массив Количества Товаров
        for(int i=0; i<3; i++){
            mass[i] = mas[i];
        }
        String temp = new String(mass);         //Преобраз массив в Строку
        numberProduct = Integer.parseInt(temp);  //получаем интовое значение количества Товара
        urlProduct = driver.getCurrentUrl();
       //CustomReporter.logAction("Get information about currently opened product");
    }

    public void buyRandomProduct() {
        CustomReporter.log("TEST user ");
        WebElement submitProduct = driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']"));
        submitProduct.click();   //Кн "В Корзину"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn btn-primary']")));
        WebElement submitProduct2 = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        submitProduct2.click(); //кн "Перейти к оформлению
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn btn-primary']")));
        // Проверка Цены
        WebElement priceBlock = driver.findElement(By.xpath("//span[@class='product-price']/strong"));
        Assert.assertEquals(priceBlock.getText(), priceProduct, "There isn't such PRICE");
        System.out.println("Price TRUE!");
        // Проверка Наименования
        WebElement nameBlock = driver.findElement(By.xpath("(//a[@class='label'])[1]"));
        Assert.assertEquals(nameBlock.getText(), nameBlock.getText(), "There isn't such name");
        System.out.println("Name TRUE!");
        // Проверка количества
        WebElement qtyBlock = driver.findElement(By.xpath("//span[@class='label js-subtotal']"));
        Assert.assertEquals(qtyBlock.getText(), "1 шт.", "There isn't such QTY");

        WebElement getOrder = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        getOrder.click(); // кн Оформить

    }
public void issueProduct(){
        CustomReporter.log("TEST user ");
        WebElement gender = driver.findElement(By.name("id_gender"));
        gender.click(); // указать Пол
        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("FirstName");  // указать Имя
        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("Lastname"); // указать фамилию
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("4webinar.test@gmail.com"); // указать Эмаил
        WebElement goOn = driver.findElement(By.xpath("//button[@data-link-action='register-new-customer']"));
        goOn.click(); //Продолжить
        WebElement adress1 = driver.findElement(By.name("address1"));
        adress1.sendKeys("Adress adress 33"); // ВВести адресс
        WebElement postCode = driver.findElement(By.name("postcode"));
        postCode.sendKeys("12121");  // Почт Индекс
        WebElement cityName = driver.findElement(By.name("city"));
        cityName.sendKeys("Cityname"); // город
        WebElement goOn1 = driver.findElement(By.name("confirm-addresses"));
        goOn1.click(); // Продолжить
        WebElement deliveryName = driver.findElement(By.id("delivery_option_2"));
        deliveryName.click();  //Доставка
        WebElement goOn2 = driver.findElement(By.name("confirmDeliveryOption"));
        goOn2.click(); //Продолжить
        WebElement payOption = driver.findElement(By.name("payment-option"));
        payOption.click(); // Оплата
        WebElement agreeMent = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
        agreeMent.click(); // Ознакомлен
        WebElement order = driver.findElement(By.xpath("//button[@class='btn btn-primary center-block']"));
        order.click(); //Заказать
  }

  public void checkOrder(){              // Проверка Ваш заказ подтвержден
      CustomReporter.log("TEST user ");
      WebElement orderSubmit = driver.findElement(By.cssSelector(".h1.card-title"));

      Assert.assertEquals(orderSubmit.getText(), "\uE876" +
            "ВАШ ЗАКАЗ ПОДТВЕРЖДЁН", "There isnot such Order!");
      System.out.println("Order TRUE!!!");
      WebElement qtyOrder = driver.findElement(By.cssSelector(".col-xs-2")); //Проверка Количества
      Assert.assertEquals(qtyOrder.getText(), "1", "There isnot such QTY!");
      System.out.println("QTY TRUE!!!");
                                                // Проверка Наименования
      WebElement nameOrder = driver.findElement(By.xpath("//div[@class=\"col-sm-4 col-xs-9 details\"]/span"));
      String textOrder = nameOrder.getText();  // Получаем строку
      char[] arr = customProduct.toCharArray();  // Определяем длину Наименования
      int lengthName = arr.length;

      char[] mas01 = textOrder.toCharArray();  //Преобраз строку в массив
      char[] mass01 = new char[lengthName];    //Получаем массив Наименования
      for(int i=0; i<lengthName; i++){
          mass01[i] = mas01[i];
      }
      String textOrdername = new String(mass01); //Преобраз массив в Строку - Название Товара
      Assert.assertEquals(textOrdername, customProduct, "There isnot such Name!");
      System.out.println("Name TRUE!!!");
                                            // Получаем Цену заказа
      WebElement priceOrder = driver.findElement(By.cssSelector(".col-xs-5.text-xs-right.bold"));
      Assert.assertEquals(priceOrder.getText(), priceProduct, "There isnot such PRICE!");
      System.out.println("PRICE TRUE!!!");
  }
            // Проверка изменения количества ТОвара
  public void checkQtyProduct(){
      driver.navigate().to(urlProduct);
      WebElement detailProduct = driver.findElement(By.linkText("Подробнее о товаре"));
      detailProduct.click();  // Входим в "Подробнее о товаре"
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".label")));
      WebElement qtyProductBlock = driver.findElement(By.xpath("//div[@class='product-quantities']/span"));
      String qtyProduct1 = qtyProductBlock.getText();   //Получаем строку с Количеством

      char[] mas = qtyProduct1.toCharArray();  //Преобраз строку в массив
      char[] mass = new char[3];              //Получаем Строк массив Количества Товаров
      for(int i=0; i<3; i++){
          mass[i] = mas[i];
      }
      String temp1 = new String(mass);         //Преобраз массив в Строку
      int numberProduct1 = Integer.parseInt(temp1);  //получаем интовое значение количества Товара
      Assert.assertEquals(numberProduct1, (numberProduct - 1), "QTY was not chenged!!!");
  }


}
