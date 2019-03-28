package com.ui.cases1.rvip_check;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.PropertiesUtil;
import com.ui.util.ZTestReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ZTestReport.class})
public class chargeOrangeDriver extends Base {
    String url = PropertiesUtil.getRootUrl();
    String caseFile = PropertiesUtil.getCaseFilePath();
    String chargemoney="1";

    @BeforeClass(enabled = false)
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页", "手机号"), "14566667777");
        sendKeys(getElement("登陆页", "密码"), "an1234");
        click(getElement("登陆页", "登陆按钮"));
    }

    @Test(
            description = "机构司机正确充值",
            priority = 1,
            enabled = true
    )
    public void chargeSuccess() throws InterruptedException {
        Thread.sleep(1000);
        click(getElement("公用", "首页"));
        Thread.sleep(2000);

        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double qyqmoney =doubleNum(getText(getElement("公用", "企业券")));
        double kymoney =doubleNum(getText(getElement("公用", "可用余额")));

        click(getElement("充值", "司机会员按钮"));
        click(getElement("充值", "账户管理按钮"));
        Thread.sleep(1000);
        double wMoney = doubleNum(getText(getElement("出纳", "机构司机待审核")));
        click(getElement("机构司机", "机构充值"));
        Thread.sleep(1000);
        sendKeys(getElement("充值", "充值金额"), chargemoney);
        Thread.sleep(1000);
        click(getElement("充值", "保存按钮"));
        Thread.sleep(1000);
        click(getElement("充值", "确定按钮"));
        Thread.sleep(2000);
        click(getElement("充值", "关闭按钮"));
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        //实际待领取金额
        double alwMoney = doubleNum(getText(getElement("出纳", "机构司机待审核")));
        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);


        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double alqyqmoney =doubleNum(getText(getElement("公用", "企业券")));
        double alkymoney =doubleNum(getText(getElement("公用", "可用余额")));



        double extotalMoney = totalMoney;
        double exqyqmoney = qyqmoney;
        double exkymoney = kymoney;
        double exwMoney = wMoney + Double.valueOf(chargemoney);



        AssertUtil.assertNumEquals(altotalMoney, extotalMoney);
        AssertUtil.assertNumEquals(alqyqmoney, exqyqmoney);
        AssertUtil.assertNumEquals(alkymoney, exkymoney);
        AssertUtil.assertNumEquals(alwMoney, exwMoney);

        Thread.sleep(1000);
    }


    @Test(
            description = "机构司机超额充值",
            priority = 3,
            enabled = true
    )
    public void overchargeFail() throws InterruptedException {
        click(getElement("公用", "首页"));
        Thread.sleep(2000);
        double allMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));

        click(getElement("充值", "司机会员按钮"));
        click(getElement("充值", "账户管理按钮"));
        Thread.sleep(1000);
        String money1 = getText(getElement("出纳", "机构司机待审核"));
        double money = doubleNum(money1);
        click(getElement("机构司机", "机构充值"));
        //String chargeMoney = String.valueOf(allMoney + 1);
        Thread.sleep(1000);
        double jyMoney = doubleNum(getText(getElement("机构司机","机构余额")));
        sendKeys(getElement("充值", "充值金额"), String.valueOf(jyMoney+1));
        Thread.sleep(1000);
        click(getElement("充值", "保存按钮"));
        Thread.sleep(2000);
        String alerrorMsg = getText(getElement("充值", "余额不足提示"));
        Thread.sleep(1000);
        driver.navigate().back();
        driver.navigate().refresh();
        Thread.sleep(1000);
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);
        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double alqyqmoney =doubleNum(getText(getElement("公用", "企业券")));
        double alkymoney =doubleNum(getText(getElement("公用", "可用余额")));
        AssertUtil.assertNumEquals(altotalMoney, allMoney);

        AssertUtil.assertTextEquals(alerrorMsg, "充值金额不可大于余额");
        Thread.sleep(1000);
    }





}
