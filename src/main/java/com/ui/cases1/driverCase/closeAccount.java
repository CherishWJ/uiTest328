package com.ui.cases1.driverCase;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.PropertiesUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class closeAccount extends Base {
    public static String url = PropertiesUtil.getRootUrl();

    @BeforeClass(enabled = false)
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页", "手机号"), "18311447530");
        sendKeys(getElement("登陆页", "密码"), "an1234");
        click(getElement("登陆页", "登陆按钮"));
        Thread.sleep(2000);
    }

    @Test(description = "销户",priority = 1)
    public void close() throws InterruptedException {

        click(getElement("公用", "首页"));
        driver.navigate().refresh();
        Thread.sleep(2000);
        double totolMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        //企业券
        double  qyqmoney = doubleNum(getText(getElement("公用", "企业券")));
        //可用余额
        double kymoney =  doubleNum(getText(getElement("公用","可用余额")));

        Thread.sleep(1000);
        click(getElement("销户", "司机会员按钮"));
        click(getElement("销户", "账户管理按钮"));
        Thread.sleep(2000);
        double yMoney = doubleNum(getText(getElement("销户", "余额")));
        double wMoney = doubleNum(getText(getElement("销户", "待领取金额")));

        Thread.sleep(1000);
        click(getElement("公用","更多按钮"));
        Thread.sleep(1000);
        click(getElement("销户","销户"));
        Thread.sleep(1000);
        click(getElement("销户","确定"));
        Thread.sleep(1000);

        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));

        driver.navigate().refresh();
        Thread.sleep(3000);
        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        //企业券
        double  alqyqMoney = doubleNum(getText(getElement("公用", "企业券")));
        //可用余额
        double alkyMoney =  doubleNum(getText(getElement("公用","可用余额")));

        //期望万金油余额
        double extotalMoney=totolMoney+yMoney+wMoney;
        double exqyqMoney=qyqmoney+yMoney+wMoney;
        double exkyMoney = kymoney;

        AssertUtil.assertNumEquals(altotalMoney,extotalMoney);
        AssertUtil.assertNumEquals(alqyqMoney,exqyqMoney);
        AssertUtil.assertNumEquals(alkyMoney,exkyMoney);



    }




    /*
    'DB_HOST'  => 'rm-2ze21vx4c30t61hl2zo.mysql.rds.aliyuncs.com',
            'DB_NAME'  => 'yidian_ceshi',
            'DB_USER'  => 'testdb',
            'DB_PWD'   => 'IlCniXRXTeWOBg5+rDik',
            */
}


