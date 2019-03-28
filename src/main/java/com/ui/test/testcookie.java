package com.ui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class testcookie {
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

        System.out.println("登陆前的cookie是："+driver.manage().getCookies());
//        driver.findElement(By.id("username")).sendKeys("18311447530");
//        driver.findElement(By.name("password")).sendKeys("an1234");
//        driver.findElement(By.id("login_button")).click();
        //点击司机会员
        Thread.sleep(2000);
        System.out.println("登陆后的cookie是："+driver.manage().getCookies());

        // 通过fiddler抓包工具，找到get为“http://www.woniuxy.com/login/isLogined”的URL，在右侧窗口查看该请求的Cookie，
        // 找到重要的三个参数“JSESSIONID”和“token”和 “userId

//        Cookie c1 = new Cookie("d04e76e2deaa4b85b976322bbf3b3c2f", "1");
//        Cookie c2 = new Cookie("a05f37ac453831a1fdb38290230d45c6", "1");
//        Cookie c3 = new Cookie("c41254338e1c4eb4903d1237f26a4e0d", "1");
//        Cookie c4 = new Cookie("3108a54d14e177103b94133261399431", "1");
//        Cookie c5 = new Cookie("5235cf2a99e4c180f170ddaaec46251b", "1");
//        Cookie c6 = new Cookie("c02b1c157c7c55372985c09290ade2f6", "1");
        Cookie c7= new Cookie("exhaustionusername", "18311447530");
        Cookie c8= new Cookie("PHPSESSID", "eksodujda5r2ub997f7ckvqsb1");
        Cookie c9 = new Cookie("pgv_pvi", "3696660480");
        //Cookie c10 = new Cookie("pgv_si", "s783361024");

        /*
        d04e76e2deaa4b85b976322bbf3b3c2f=1
        a05f37ac453831a1fdb38290230d45c6=1
        c41254338e1c4eb4903d1237f26a4e0d=1
        3108a54d14e177103b94133261399431=1
        5235cf2a99e4c180f170ddaaec46251b=1
        c02b1c157c7c55372985c09290ade2f6=1
        exhaustionusername=18311447530
        PHPSESSID=ltespkuobdnaj5b1cluh5bsse0
        pgv_pvi=3696660480
        pgv_si=s783361024

  */
       // PHPSESSID=ltespkuobdnaj5b1cluh5bsse0


//        driver.manage().addCookie(c1);
//        driver.manage().addCookie(c2);
//        driver.manage().addCookie(c3);
//        driver.manage().addCookie(c4);
//        driver.manage().addCookie(c5);
//        driver.manage().addCookie(c6);
        driver.manage().addCookie(c7);
        driver.manage().addCookie(c8);
        driver.manage().addCookie(c9);
        //driver.manage().addCookie(c10);



        driver.navigate().refresh();
        Thread.sleep(2000);

        //设置鼠标悬停




}
}
