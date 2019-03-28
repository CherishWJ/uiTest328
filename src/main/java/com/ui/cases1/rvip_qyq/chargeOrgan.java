package com.ui.cases1.rvip_qyq;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.ZTestReport;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({ZTestReport.class})
public class chargeOrgan extends Base {
    private Logger logger = Logger.getLogger(chargeOrgan.class);
    public static String url = "https://rvip.nucarf.cn";
    @BeforeClass(enabled = false)
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页", "手机号"), "18311447530");
        sendKeys(getElement("登陆页", "密码"), "an1234");
        click(getElement("登陆页", "登陆按钮"));
        Thread.sleep(2000);
    }

    @Test(description = "给机构充值",priority = 0)
    public void chargeOrgan() throws InterruptedException {
        String chargeMoney = "1";
        click(getElement("公用", "首页"));
        Thread.sleep(2000);

        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double qyqmoney =doubleNum(getText(getElement("公用", "企业券")));
        double kymoney =doubleNum(getText(getElement("公用", "可用余额")));


        Thread.sleep(1000);
        click(getElement("机构充值", "下属机构"));
        click(getElement("机构充值", "机构列表"));
        Thread.sleep(2000);
        double ymoney = doubleNum(getText(getElement("机构充值", "余额")));
        double lsmoney=doubleNum(getText(getElement("机构充值", "历史储值")));

        Thread.sleep(1000);
        click(getElement("机构充值","充值"));
        Thread.sleep(1000);
        sendKeys(getElement("机构充值","充值金额"),chargeMoney);
        click(getElement("机构充值","保存"));
        Thread.sleep(1000);
        click(getElement("机构充值","确定"));
        Thread.sleep(1000);
        driver.navigate().refresh();
        double alymoney = doubleNum(getText(getElement("机构充值", "余额")));
        double allsmoney = doubleNum(getText(getElement("机构充值", "历史储值")));

        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);
        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double alqyq = doubleNum(getText(getElement("公用", "企业券")));
        double alky = doubleNum(getText(getElement("公用", "可用余额")));


        double extotalMoney = totalMoney-doubleNum(chargeMoney);
        double exymoney = ymoney+doubleNum(chargeMoney);
        double exlsmoney = lsmoney+doubleNum(chargeMoney);
        double exqyqMoney = qyqmoney -doubleNum(chargeMoney);
        double exkyMoney = kymoney;

        AssertUtil.assertNumEquals(alymoney,exymoney);
        AssertUtil.assertNumEquals(allsmoney,exlsmoney);
        AssertUtil.assertNumEquals(altotalMoney,extotalMoney);
        AssertUtil.assertNumEquals(alqyq,exqyqMoney);
        AssertUtil.assertNumEquals(alky,exkyMoney);

    }

    @Test(description = "给机构超额充值",priority = 1)
    public void overchargeOrgan() throws InterruptedException {

        click(getElement("公用", "首页"));
        Thread.sleep(2000);

        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double qyqMoney = doubleNum(getText(getElement("公用", "企业券")));
        double kyMoney = doubleNum(getText(getElement("公用", "可用余额")));

        Thread.sleep(1000);
        click(getElement("机构充值", "下属机构"));
        click(getElement("机构充值", "机构列表"));
        Thread.sleep(2000);
        double ymoney = doubleNum(getText(getElement("机构充值", "余额")));
        double lsmoney = doubleNum(getText(getElement("机构充值", "历史储值")));

        Thread.sleep(1000);
        click(getElement("机构充值","充值"));
        Thread.sleep(1000);
        sendKeys(getElement("机构充值","充值金额"),String.valueOf(qyqMoney+1));

        click(getElement("机构充值","保存"));
        Thread.sleep(1000);
        String errorMsg = getText(getElement("机构充值","超额提示"));

        click(getElement("机构充值","关闭"));
        Thread.sleep(1000);
        driver.navigate().refresh();
        double alymoney = doubleNum(getText(getElement("机构充值", "余额")));
        double allsmoney = doubleNum(getText(getElement("机构充值", "历史储值")));

        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);

        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double alqyq = doubleNum(getText(getElement("公用", "企业券")));
        double alky = doubleNum(getText(getElement("公用", "可用余额")));


        double extotalMoney = totalMoney;
        double exymoney = ymoney;
        double exlsmoney = lsmoney;
        double exqyqMoney = qyqMoney;
        double exkyMoney = kyMoney;

        AssertUtil.assertNumEquals(alymoney,exymoney);
        AssertUtil.assertNumEquals(allsmoney,exlsmoney);
        AssertUtil.assertNumEquals(altotalMoney,extotalMoney);
        AssertUtil.assertNumEquals(alqyq,exqyqMoney);
        AssertUtil.assertNumEquals(alky,exkyMoney);





    }
}


