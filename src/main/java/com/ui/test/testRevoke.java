package com.ui.test;

import com.ui.base.Base;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class testRevoke extends Base {
    public static void main(String[] args) throws InterruptedException {
         Logger logger = Logger.getLogger(testRevoke.class);

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
        logger.info("当前打开页面的标题是： " + driver.getTitle());
        driver.findElement(By.id("username")).sendKeys("18311447530");
        driver.findElement(By.name("password")).sendKeys("an1234");
        driver.findElement(By.id("login_button")).click();

        driver.findElement(By.linkText("首页")).click();
        logger.info("点击首页");
        driver.findElement(By.linkText("运营数据")).click();
        logger.info("运营数据");
        String totalMoney = driver.findElement(By.xpath("//*[@class='number']/span")).getText();
        logger.info("查看首页余额");
        driver.findElement(By.linkText("司机会员")).click();
        logger.info("点击司机会员");
        driver.findElement(By.linkText("账户管理")).click();
        logger.info("点击账户管理");
        String money = driver.findElement(By.xpath(".//*[@id='yidian_table']/tbody/tr[1]/td[9]")).getText();
        logger.info("查看司机待领取金额");
        driver.findElement(By.xpath(".//*[@id='tr_11829']/button")).click();
        logger.info("点击更多");
        driver.findElement(By.linkText("撤销待领取")).click();
        logger.info("点击撤销待领取");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='modal-scrollable']/div/div/div/div[2]/button[2]")).click();
        logger.info("点击确认");
        Thread.sleep(1000);
        String almoney = driver.findElement(By.xpath(".//*[@id='yidian_table']/tbody/tr[1]/td[9]")).getText();
        logger.info("查看司机待领取金额");
        driver.findElement(By.linkText("首页")).click();
        logger.info("点击首页");
        driver.findElement(By.linkText("运营数据")).click();
        logger.info("点击运营数据");
        String altotalMoney = driver.findElement(By.xpath("//*[@class='number']/span")).getText();
        logger.info("查看账户余额");





}}
