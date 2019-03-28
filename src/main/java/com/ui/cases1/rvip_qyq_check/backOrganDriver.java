package com.ui.cases1.rvip_qyq_check;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.PropertiesUtil;
import com.ui.util.ZTestReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({ZTestReport.class})
public class backOrganDriver extends Base {
    public static String url = PropertiesUtil.getRootUrl();

    @BeforeClass(enabled = false)
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页", "手机号"), "18311447530");
        sendKeys(getElement("登陆页", "密码"), "an1234");
        click(getElement("登陆页", "登陆按钮"));
        Thread.sleep(1000);
        selectList("UI自动化测试企业");
        Thread.sleep(1000);
    }

    @Test(description = "正常回收机构司机资金",priority = 0)
    public void backMoney() throws InterruptedException {
        String money= "1";
        click(getElement("公用", "首页"));
        driver.navigate().refresh();
        Thread.sleep(2000);
        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        //企业券
        double  qyqmoney = doubleNum(getText(getElement("公用", "企业券")));
        //可用余额
        double kymoney =  doubleNum(getText(getElement("公用","可用余额")));




        Thread.sleep(1000);
        click(getElement("回收司机资金", "司机会员按钮"));
        click(getElement("回收司机资金", "账户管理按钮"));
        Thread.sleep(2000);
        double yMoney = doubleNum(getText(getElement("机构司机", "机构司机余额")));
        Thread.sleep(1000);
        click(getElement("公用","机构更多"));
        click(getElement("回收司机资金","回收资金"));
        Thread.sleep(1000);
        sendKeys(getElement("回收司机资金","回收金额"),money);
        click(getElement("回收司机资金","保存"));
        Thread.sleep(1000);
        click(getElement("回收司机资金","确定"));
        Thread.sleep(1000);
        driver.navigate().refresh();
        double alyMoney = doubleNum(getText(getElement("机构司机", "机构司机余额")));

        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));

        driver.navigate().refresh();
        Thread.sleep(3000);
        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        //企业券
        double  alqyqmoney = doubleNum(getText(getElement("公用", "企业券")));
        //可用余额
        double alkymoney =  doubleNum(getText(getElement("公用","可用余额")));



        //期望剩余资金
        double exyMoney=yMoney-doubleNum(money);
        //期望万金油余额
        double extotalMoney = totalMoney;
        double exqyqmoney = qyqmoney;
        double exkymoney = kymoney;



        AssertUtil.assertNumEquals(alyMoney,exyMoney);
        AssertUtil.assertNumEquals(altotalMoney,extotalMoney);
        AssertUtil.assertNumEquals(alqyqmoney,exqyqmoney);
        AssertUtil.assertNumEquals(alkymoney,exkymoney);


    }

    @Test(description = "超额回收机构司机资金",priority = 1)
    public void overbackMoney() throws InterruptedException {
        click(getElement("公用", "首页"));
        driver.navigate().refresh();
        Thread.sleep(2000);
        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        //企业券
        double  qyqmoney = doubleNum(getText(getElement("公用", "企业券")));
        //可用余额
        double kymoney =  doubleNum(getText(getElement("公用","可用余额")));



        Thread.sleep(1000);
        click(getElement("回收司机资金", "司机会员按钮"));
        click(getElement("回收司机资金", "账户管理按钮"));
        Thread.sleep(2000);
        double yMoney = doubleNum(getText(getElement("机构司机", "机构司机余额")));
        Thread.sleep(1000);
        click(getElement("公用","机构更多"));
        click(getElement("回收司机资金","回收资金"));
        Thread.sleep(1000);
        //回收司机余额超额
        sendKeys(getElement("回收司机资金","回收金额"),String.valueOf(yMoney+1));
        Thread.sleep(1000);
        click(getElement("回收司机资金","保存"));
        Thread.sleep(2000);
        String errorMsg = getText(getElement("回收司机资金","超额提示"));
        Thread.sleep(1000);
        click(getElement("回收司机资金","关闭按钮"));
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);
        double alyMoney = doubleNum(getText(getElement("机构司机", "机构司机余额")));
        driver.navigate().back();
        Thread.sleep(1000);
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));

        driver.navigate().refresh();
        Thread.sleep(3000);
        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        //企业券
        double  alqyqmoney = doubleNum(getText(getElement("公用", "企业券")));
        //可用余额
        double alkymoney =  doubleNum(getText(getElement("公用","可用余额")));




        //期望剩余资金
        double exyMoney=yMoney;
        //期望万金油余额
        double extotalMoney=totalMoney;
        double exqyqmoney = qyqmoney;
        double exkymoney = kymoney;

        AssertUtil.assertNumEquals(alyMoney,exyMoney);
        AssertUtil.assertTextEquals(errorMsg,"万金油回收资金超过限制!");
        AssertUtil.assertNumEquals(altotalMoney,extotalMoney);
        AssertUtil.assertNumEquals(alqyqmoney,exqyqmoney);
        AssertUtil.assertNumEquals(alkymoney,exkymoney);


    }

}
