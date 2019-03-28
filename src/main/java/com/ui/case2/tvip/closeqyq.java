package com.ui.case2.tvip;

import com.ui.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.awt.*;

public class closeqyq extends Base {
    @Test(description = "关闭企业钱包")
    public void closeQyq() throws InterruptedException, AWTException {
        driver.navigate().to("http://rconsole.nucarf.cn");
        sendKeys(getElement("企业钱包", "手机号"), "18311447530");
        sendKeys(getElement("企业钱包", "密码"), "111111");
        Thread.sleep(1000);
        click(getElement("企业钱包", "登录"));
        click(getElement("企业钱包","企业会员"));
        click(getElement("企业钱包","企业账户管理"));
        Thread.sleep(1000);
        sendKeys(getElement("企业钱包","企业名称"),"UI自动化测试企业:1253");
        Thread.sleep(2000);


//        Robot robot = new Robot();
//        Thread.sleep(1000);
//        // 释放回车
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);

        click(getElement("企业钱包","查询"));
        Thread.sleep(2000);
        click(getElement("企业钱包","查询"));
        //移动到更多
        WebElement element = driver.findElement(By.id("tr_1253"));
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();

        Thread.sleep(2000);
        click(getElement("测试公用","更多配置"));
        click(getElement("企业钱包","修改企业配置"));

        WebElement job=driver.findElement(By.id("company_wallet_status"));
        // /创建selenium自带的类进行下拉框定位
        Select downList=new Select(job);
        // 职位选择第二个元素
        downList.selectByIndex(0);
        //selectList("不开启");
        Thread.sleep(2000);

       //移动到提交
        WebElement element1 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div/div/div/div[1]/div/form/div/div[2]/div[20]/div/div/button[2]"));
        Actions action1 = new Actions(driver);
        action1.moveToElement(element1).perform();
        Thread.sleep(1000);
        click(getElement("企业钱包","提交"));
        // 设置
        WebElement settings = driver.findElement(By.id("system_user_info_head_img"));

        Actions action3 = new Actions(driver);
        action3.moveToElement(settings).perform();

        driver.findElement(By.linkText("退出登录")).click();


        //*[@id="yidian_search_form"]/div[1]/div[1]/div[1]/div/div/span/input[2]

    }
}


