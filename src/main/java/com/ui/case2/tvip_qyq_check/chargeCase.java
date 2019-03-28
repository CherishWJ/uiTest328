package com.ui.case2.tvip_qyq_check;

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
    String chargemoney="1";

    @Test(
            description = "正确充值",
            priority = 0,
            enabled = true
    )
    public void chargeSuccess() throws InterruptedException {
        Thread.sleep(1000);
        click(getElement("公用", "首页"));
        Thread.sleep(2000);

        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double waitCheck = doubleNum(getText(getElement("公用", "待审核金额")));
        double qyqmoney =doubleNum(getText(getElement("公用", "企业券")));
        double kymoney =doubleNum(getText(getElement("公用", "可用余额")));

        click(getElement("充值", "司机会员按钮"));
        click(getElement("充值", "账户管理按钮"));
        Thread.sleep(1000);
        double wMoney = doubleNum(getText(getElement("出纳", "总部司机待审核")));
        click(getElement("充值", "充值按钮"));
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
        double alwMoney = doubleNum(getText(getElement("出纳", "总部司机待审核")));
        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);


        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double alwaitCheck = doubleNum(getText(getElement("公用", "待审核金额")));
        double alqyq = doubleNum(getText(getElement("公用", "企业券")));
        double alky = doubleNum(getText(getElement("公用", "可用余额")));

        double exqyqMoney = qyqmoney - doubleNum(chargemoney);
        double extotalMoney = totalMoney - Double.valueOf(chargemoney);
        double exwMoney = wMoney + Double.valueOf(chargemoney);
        double exkyMoney = kymoney;
        double exwaitCheck = waitCheck+doubleNum(chargemoney);

        AssertUtil.assertNumEquals(altotalMoney, extotalMoney);
        AssertUtil.assertNumEquals(alwMoney, exwMoney);
        AssertUtil.assertNumEquals(alqyq, exqyqMoney);
        AssertUtil.assertNumEquals(alky, kymoney);
        AssertUtil.assertNumEquals(alwaitCheck,exwaitCheck);
        Thread.sleep(1000);
    }




    @Test(
            description = "超额充值",
            priority = 1,
            enabled = true
    )
    public void overchargeFail() throws InterruptedException {
        click(getElement("公用", "首页"));
        Thread.sleep(2000);
        double allMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double waitCheck = doubleNum(getText(getElement("公用", "待审核金额")));
        double qyqmoney = doubleNum(getText(getElement("公用", "企业券")));
        double kymoney = doubleNum(getText(getElement("公用", "可用余额")));
        click(getElement("充值", "司机会员按钮"));
        click(getElement("充值", "账户管理按钮"));
        Thread.sleep(1000);
        String money1 = getText(getElement("出纳", "总部司机待审核"));
        double money = doubleNum(money1);
        click(getElement("充值", "充值按钮"));
        String chargeMoney = String.valueOf(qyqmoney + 1);
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
        double alwaitCheck = doubleNum(getText(getElement("公用", "待审核金额")));
        double alqyq = doubleNum(getText(getElement("公用", "企业券")));
        double alky = doubleNum(getText(getElement("公用", "可用余额")));
        AssertUtil.assertNumEquals(altotalMoney, allMoney);
        AssertUtil.assertNumEquals(alqyq, qyqmoney);
        AssertUtil.assertNumEquals(alky, kymoney);
        AssertUtil.assertNumEquals(alwaitCheck, waitCheck);
        AssertUtil.assertTextEquals(alerrorMsg, "充值金额不可大于余额");

        Thread.sleep(1000);
    }


}
