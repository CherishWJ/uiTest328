package com.ui.case2.tvip;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.PropertiesUtil;
import com.ui.util.ZTestReport;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ZTestReport.class})
public class backOragnMoney extends Base {
    private Logger logger = Logger.getLogger(chargeOrgan.class);
    public static String url = PropertiesUtil.getRootUrl();
    @BeforeClass(enabled = false)
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页", "手机号"), "18311447530");
        sendKeys(getElement("登陆页", "密码"), "an1234");
        click(getElement("登陆页", "登陆按钮"));
        Thread.sleep(2000);
    }

    @Test(description = "正常回收机构金额",priority = 0)
    public void backOrgan() throws InterruptedException {
        String money = "1";
        click(getElement("公用", "首页"));
        Thread.sleep(2000);
        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));


        Thread.sleep(1000);
        click(getElement("回收机构金额", "下属机构"));
        click(getElement("回收机构金额", "机构列表"));
        Thread.sleep(2000);
        double ymoney = doubleNum(getText(getElement("回收机构金额", "余额")));
        double lsmoney = doubleNum(getText(getElement("回收机构金额" ,"历史储值")));

        Thread.sleep(1000);
        click(getElement("测试公用","回收机构"));
        Thread.sleep(1000);
        click(getElement("回收机构金额","回收"));
        Thread.sleep(1000);
        sendKeys(getElement("回收机构金额","回收金额"),money);
        click(getElement("回收机构金额","保存"));
        Thread.sleep(1000);
        click(getElement("回收机构金额","确定"));
        Thread.sleep(1000);
        driver.navigate().refresh();


        double alymoney = doubleNum(getText(getElement("回收机构金额", "余额")));
        double allsmoney = doubleNum(getText(getElement("回收机构金额", "历史储值")));

        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);


        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));


        double expectedymoney = ymoney-doubleNum(money);
        double expectedlsmoney = lsmoney-doubleNum(money);
        double expectedtotalMoney = totalMoney+doubleNum(money);

        AssertUtil.assertNumEquals(alymoney,expectedymoney);
        AssertUtil.assertNumEquals(allsmoney,expectedlsmoney);
        AssertUtil.assertNumEquals(altotalMoney,expectedtotalMoney);

    }

    @Test(description = "超额回收机构金额",enabled = true,priority = 1)
    public void overbackOrgan() throws InterruptedException {
        click(getElement("公用", "首页"));
        Thread.sleep(2000);

        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));


        Thread.sleep(1000);
        click(getElement("回收机构金额", "下属机构"));
        click(getElement("回收机构金额", "机构列表"));
        Thread.sleep(2000);
        double ymoney = doubleNum(getText(getElement("回收机构金额", "余额")));
        double lsmoney = doubleNum(getText(getElement("回收机构金额" ,"历史储值")));

        Thread.sleep(1000);
        click(getElement("测试公用","回收机构"));
        click(getElement("回收机构金额","回收"));
        Thread.sleep(1000);
        sendKeys(getElement("回收机构金额","回收金额"),String.valueOf(ymoney+1));
        click(getElement("回收机构金额","保存"));
        String errorMsg = getText(getElement("回收机构金额","超额提示"));
        Thread.sleep(1000);
        click(getElement("回收机构金额","关闭"));
        Thread.sleep(1000);
        driver.navigate().refresh();

        double alymoney = doubleNum(getText(getElement("回收机构金额", "余额")));
        double allsmoney = doubleNum(getText(getElement("回收机构金额", "历史储值")));

        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);


        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));


        double expectedymoney = ymoney;
        double expectedlsmoney = lsmoney;
        double expectedtotalMoney = totalMoney;


        AssertUtil.assertNumEquals(alymoney,expectedymoney);
        AssertUtil.assertNumEquals(allsmoney,expectedlsmoney);
        AssertUtil.assertNumEquals(altotalMoney,expectedtotalMoney);


    }




}
