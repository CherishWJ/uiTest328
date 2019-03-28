package com.ui.cases1.driverCase;

import com.ui.base.Base;
import com.ui.util.PropertiesUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;

public class importCharge extends Base {
    public static String url = PropertiesUtil.getRootUrl();
    public static String importcaseFile = PropertiesUtil.getImportCaseUrl();
    @BeforeClass()
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页", "手机号"), "18311447530");
        sendKeys(getElement("登陆页", "密码"), "an1234");
        click(getElement("登陆页", "登陆按钮"));

    }


    @Test(description = "导入充值",priority = 1,enabled = true)
    public void chargeSucess() throws InterruptedException, AWTException {
        Thread.sleep(1000);
        //String caseFile="C:\\test\\import.xlsx";

        click(getElement("公用", "首页"));
        Thread.sleep(2000);
        String allMoney1 = getText(getElement("公用", "万金油系统余额"));
        //企业券
        double  qyqmoney = doubleNum(getText(getElement("公用", "企业券")));
        //可用余额
        double kymoney =  doubleNum(getText(getElement("公用","可用余额")));
        //String allMoney =  allMoney1.replaceAll(",","");
        double allMoney = doubleNum(allMoney1);

        click(getElement("导入充值", "司机会员按钮"));
        click(getElement("导入充值", "账户管理按钮"));
        Thread.sleep(1000);
        upfile(getElement("导入充值","批量导入按钮"),importcaseFile);
        Thread.sleep(2000);



















/*

        click(getElement("充值", "首页"));
        click(getElement("充值", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);
        String actualallMoney1 = getText(getElement("充值", "万金油系统余额"));
        double  actualallMoney= doubleNum(actualallMoney1);
        double alqyq = doubleNum(getText(getElement("充值", "企业券")));
        double exalqyq =qyqmoney-doubleNum(chargemoney);
        double alky = doubleNum(getText(getElement("充值", "可用余额")));
        double exky= kymoney;

        //String b = Double.parseDouble(allMoney)-Double.parseDouble(chargemoney);
        double extotalMoney = allMoney - Double.valueOf(chargemoney);
        double a = Double.valueOf(money) + Double.valueOf(chargemoney);

        //断言万金油系统余额
        AssertUtil.assertNumEquals(actualallMoney,extotalMoney);
        //断言待领取金额
        AssertUtil.assertNumEquals(actualMoney,a);
        //断言企业券
        AssertUtil.assertNumEquals(alqyq,exalqyq);

        //断言可用余额
        AssertUtil.assertNumEquals(alky,exky);



        Thread.sleep(1000);
    }
    */





}}
