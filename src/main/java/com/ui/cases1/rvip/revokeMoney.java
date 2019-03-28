package com.ui.cases1.rvip;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.PropertiesUtil;
import com.ui.util.ZTestReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ZTestReport.class})
public class revokeMoney extends Base {
    public static String url = "https://rvip.nucarf.cn";
    public static String caseFile=PropertiesUtil.getCaseFilePath();
    @BeforeClass(enabled = false)
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页", "手机号"), "18311447530");
        sendKeys(getElement("登陆页", "密码"), "an1234");
        click(getElement("登陆页", "登陆按钮"));
        Thread.sleep(2000);
    }

    @Test(description = "撤销总部司机待领取")
    public void revoke() throws InterruptedException {
        click(getElement("公用", "首页"));
        Thread.sleep(2000);
        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));


        Thread.sleep(1000);
        click(getElement("撤销待领取", "司机会员按钮"));
        click(getElement("撤销待领取", "账户管理按钮"));
        Thread.sleep(1000);
        double money = doubleNum(getText(getElement("撤销待领取", "实际待领取金额")));
        Thread.sleep(1000);
        click(getElement("公用","总部更多"));
        click(getElement("撤销待领取","撤销待领取"));
        Thread.sleep(1000);
        click(getElement("撤销待领取","确认按钮"));
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);
        double alMoney = doubleNum(getText(getElement("撤销待领取", "实际待领取金额")));
        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(3000);

        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));

        double extotalMoney = totalMoney+money;



        //断言待领取
        AssertUtil.assertNumEquals(alMoney,0);
        //断言企业余额
        AssertUtil.assertNumEquals(altotalMoney,extotalMoney);


    }





}
