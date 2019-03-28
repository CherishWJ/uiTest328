package com.ui.test;

import com.ui.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class testcharge extends Base {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        //初始化一个chrome浏览器实例，实例名称叫driver
        WebDriver driver = new ChromeDriver();
        //最大化窗口
        driver.manage().window().maximize();
        //设置隐性等待时间
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        // get()打开一个站点
        driver.get("https://rvip.nucarf.cn/");
        //getTitle()获取当前页面title的值
        System.out.println("当前打开页面的标题是： " + driver.getTitle());
        driver.findElement(By.id("username")).sendKeys("18311447530");
        driver.findElement(By.name("password")).sendKeys("an1234");
        driver.findElement(By.id("login_button")).click();
        //点击司机会员

        driver.findElement(By.linkText("首页")).click();
        driver.findElement(By.linkText("运营数据")).click();
        //String  money = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div[1]/a/div[2]/div[1]/span")).getText();
        ///html/body/div[3]/div[2]/div/div[2]/div[2]/a/div[2]/div[1]/span
        //String  money = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div[2]/a/div[2]/div[1]/span")).getText();
        //System.out.println(money);
        driver.findElement(By.linkText("司机会员")).click();
        System.out.println("点击司机会员");
        driver.findElement(By.linkText("账户管理")).click();
        System.out.println("点击账户管理");

        driver.findElement(By.xpath(".//*[@id='yidian_table']/tbody/tr[1]/td[13]/a")).click();
        System.out.println("点击充值");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"recharge_cart\"]/div/div/div[2]/div[3]/div[2]/div/input")).sendKeys("3");
        //点击保存按钮
        driver.findElement(By.xpath("//*[@id=\"recharge_cart\"]/div/div/div[3]/button[2]")).click();
        //点击确定
        Thread.sleep(1000);
        //点击关闭
        driver.findElement(By.xpath("/html/body/div[9]/div/div/div/div[2]/button[2]")).click();
        //点击关闭
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"batchCreateUser\"]/div[1]/div/div/div[2]/button[2]")).click();
        driver.navigate().back();
        Thread.sleep(1000);
        driver.findElement(By.linkText("首页")).click();
        driver.findElement(By.linkText("运营数据")).click();
        Thread.sleep(3000);
        String  almoney1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div[1]/a/div[2]/div[1]/span")).getText();
        String  almoney2 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div[2]/a/div[2]/div[1]/span")).getText();
        String  almoney3 = driver.findElement(By.xpath("html/body/div[3]/div[2]/div/div[2]/div[3]/a/div[2]/div[1]/span")).getText();

        System.out.println(almoney1);
        System.out.println(almoney2);
        System.out.println(almoney3);

        String str1 = almoney1.trim().replaceAll(",", "");
        double num1 = Double.valueOf(str1);
        System.out.println(num1);


        double num2 = Double.valueOf(almoney2);
        System.out.println(num2);


        double num3 = Double.valueOf(almoney3);
        System.out.println(num3);







        //double a = Double.valueOf(almoney)-8;

        }

   //




    }

