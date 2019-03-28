package com.ui.case2.tvip_organ;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.PropertiesUtil;
import com.ui.util.ZTestReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ZTestReport.class})
public class chargeCase extends Base {
    String url = PropertiesUtil.getRootUrl();
    String caseFile = PropertiesUtil.getCaseFilePath();
    String chargemoney="2";


    @Test(
            description = "正确充值",
            priority = 1,
            enabled = true
    )
    public void chargeSuccess() throws InterruptedException {
        Thread.sleep(1000);
        click(getElement("公用", "首页"));
        Thread.sleep(2000);

        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));

        click(getElement("充值", "司机会员按钮"));
        click(getElement("充值", "账户管理按钮"));
        Thread.sleep(1000);
        double wMoney = doubleNum(getText(getElement("机构司机", "机构司机待领取")));
        click(getElement("测试公用", "机构司机充值"));
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
        double alwMoney = doubleNum(getText(getElement("机构司机", "机构司机待领取")));
        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);


        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));



        double extotalMoney = totalMoney - Double.valueOf(chargemoney);
        double exwMoney = wMoney + Double.valueOf(chargemoney);


        AssertUtil.assertNumEquals(altotalMoney, extotalMoney);
        AssertUtil.assertNumEquals(alwMoney, exwMoney);

        Thread.sleep(1000);
    }

    @Test(
            description = "3分钟内同一账户充值相同金额充值",
            priority = 2,
            enabled = true
    )
    public void rechargeFail() throws InterruptedException {
        String expectMsg="同一金额、同一个账号3分钟内不能重复";
        click(getElement("公用", "首页"));
        Thread.sleep(2000);
        String allMoney1 = getText(getElement("公用", "万金油系统余额"));
        double allMoney = doubleNum(allMoney1);

        click(getElement("充值", "司机会员按钮"));
        click(getElement("充值", "账户管理按钮"));
        Thread.sleep(1000);
        String money1 = getText(getElement("机构司机", "机构司机待领取"));
        double money = doubleNum(money1);
        click(getElement("测试公用", "机构司机充值"));
        Thread.sleep(1000);
        sendKeys(getElement("充值", "充值金额"), chargemoney);
        Thread.sleep(1000);
        click(getElement("充值", "保存按钮"));
        Thread.sleep(1000);
        click(getElement("充值", "确定按钮"));
        Thread.sleep(1000);
        String actualMsg = getText(getElement("充值", "错误提示"));
        Thread.sleep(1000);
        click(getElement("充值", "ok按钮"));
        Thread.sleep(1000);
        click(getElement("充值", "关闭"));
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        //实际待领取金额
        double alwMoney = doubleNum(getText(getElement("机构司机", "机构司机待领取")));
        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);
        String actualallMoney1 = getText(getElement("公用", "万金油系统余额"));
        double actualallMoney = doubleNum(actualallMoney1);

        AssertUtil.assertNumEquals(actualallMoney, allMoney);
        AssertUtil.assertNumEquals(alwMoney, money);
        AssertUtil.assertContains(actualMsg, expectMsg);
        Thread.sleep(1000);
    }

    @Test(
            description = "超额充值",
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
        String money1 = getText(getElement("机构司机", "机构司机待领取"));
        double money = doubleNum(money1);
        click(getElement("测试公用", "机构司机充值"));
        String chargeMoney = String.valueOf(allMoney + 1);
        Thread.sleep(1000);
        sendKeys(getElement("充值", "充值金额"), chargeMoney);
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
        AssertUtil.assertNumEquals(altotalMoney, allMoney);
        AssertUtil.assertTextEquals(alerrorMsg, "充值金额不可大于余额");
        Thread.sleep(1000);
    }


}
