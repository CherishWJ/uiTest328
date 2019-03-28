package com.ui.cases1.tconsole;

import com.ui.base.Base;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;

public class createOrder {
    public class createOrdertest extends Base {
        @BeforeClass(enabled = true)
        public void Login() throws InterruptedException {
            driver.navigate().to("http://tconsole.nucarf.cn");
            sendKeys(getElement("企业钱包", "手机号"), "18311447530");
            sendKeys(getElement("企业钱包", "密码"), "889911");
            Thread.sleep(1000);
            click(getElement("企业钱包", "登录"));
            click(getElement("创建消费订单","个人会员"));
        }
        
        @Test(description = "创建消费订单",invocationCount=100000,threadPoolSize=1)
        public void createOrder() throws InterruptedException, AWTException {
            //Thread.sleep(1000);
            click(getElement("创建消费订单","创建订单"));
            //Thread.sleep(1000);
            sendKeys(getElement("创建消费订单","司机姓名"),"18311447530:王文静:王文静测试企业:京A23214:252626.2元:11621");
            //Thread.sleep(1000);
            sendKeys(getElement("创建消费订单","消费油站"),"石景山（服务区）中石油加油站（南）:944");
           // Thread.sleep(1000);
            sendKeys(getElement("创建消费订单","收款金额"),"1");
            //Thread.sleep(1000);
            click(getElement("创建消费订单","提交"));

}}}
