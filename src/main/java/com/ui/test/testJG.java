package com.ui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class testJG {
    public static void main(String[] args) throws InterruptedException {
     System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

    //初始化一个chrome浏览器实例，实例名称叫driver
    WebDriver driver = new ChromeDriver();
    //最大化窗口
        driver.manage().window().maximize();
    //设置隐性等待时间
        driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);

    // get()打开一个站点
        driver.get("https://rvip.nucarf.cn/");
    //getTitle()获取当前页面title的值
        System.out.println("当前打开页面的标题是： " + driver.getTitle());
        driver.findElement(By.id("username")).sendKeys("18311447530");
        driver.findElement(By.name("password")).sendKeys("an1234");
        driver.findElement(By.id("login_button")).click();
        //创建机构
        driver.findElement(By.linkText("下属机构")).click();
        //创建机构
        driver.findElement(By.linkText("创建机构")).click();
        driver.findElement(By.id("subname")).sendKeys("机构了");

}
}
