package com.ui.case2.tvip_organ_check;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.PropertiesUtil;
import com.ui.util.ZTestReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ZTestReport.class})
public class frozenMoney extends Base{
    String url = PropertiesUtil.getRootUrl();



    @BeforeClass(enabled =false)
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页", "手机号"), "18311447530");
        sendKeys(getElement("登陆页", "密码"), "an1234");
        click(getElement("登陆页", "登陆按钮"));
        selectList("UI自动化测试企业");
    }

    @Test(
            description = "冻结资金",
            priority = 1,
            enabled = true
    )
    public void freeze() throws InterruptedException {
        Thread.sleep(1000);
        click(getElement("公用", "首页"));
        Thread.sleep(2000);

        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));


        click(getElement("冻结", "司机会员按钮"));
        click(getElement("冻结", "账户管理按钮"));
        click(getElement("公用","机构更多"));
        Thread.sleep(1000);
        click(getElement("冻结","冻结资金"));
        Thread.sleep(1000);
        click(getElement("冻结","确定"));
        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);


        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));

        double extotalMoney = totalMoney;

        AssertUtil.assertNumEquals(altotalMoney, extotalMoney);
        Thread.sleep(1000);
    }

    @Test(
            description = "解冻资金",
            priority = 2,
            enabled = true
    )

    public void unfreeze() throws InterruptedException {
        Thread.sleep(1000);
        click(getElement("公用", "首页"));
        Thread.sleep(2000);

        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));


        click(getElement("冻结", "司机会员按钮"));
        click(getElement("冻结", "账户管理按钮"));
        click(getElement("公用","机构更多"));
        Thread.sleep(1000);
        click(getElement("冻结","解冻资金"));
        Thread.sleep(1000);
        click(getElement("冻结","确定"));
        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);

        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double extotalMoney = totalMoney;

        AssertUtil.assertNumEquals(altotalMoney, extotalMoney);
        Thread.sleep(1000);
    }




}
