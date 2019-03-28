package com.ui.cases1.driverCase;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.ExcelUtil;
import com.ui.util.PropertiesUtil;
import com.ui.util.ZTestReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ZTestReport.class})
public class addDriverCase extends Base {
    public static String url = PropertiesUtil.getRootUrl();
    public static String caseFile = PropertiesUtil.getCaseFilePath();

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

    /**
     * 正常创建账户
     *
     * @param name
     * @param mobilephone
     * @param usercard
     * @param carno
     * @param money
     * @param expectedMsg
     * @throws InterruptedException
     */
    @Test(dataProvider = "successCaseDatas", description = "成功创建司机账户", priority = 1, enabled = true)
    public void addAcount(String name, String mobilephone, String usercard, String carno, String money, String expectedMsg) throws InterruptedException {
        click(getElement("公用", "首页"));
        //万金油系统余额
        Thread.sleep(2000);
        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        //企业券
        double qyqmoney = doubleNum(getText(getElement("公用", "企业券")));
        //可用余额
        double kymoney = doubleNum(getText(getElement("公用", "可用余额")));
        click(getElement("新建账户", "司机会员按钮"));
        click(getElement("新建账户", "新建账户按钮"));
        sendKeys(getElement("新建账户", "司机姓名"), name);
        sendKeys(getElement("新建账户", "手机号"), mobilephone);
        sendKeys(getElement("新建账户", "身份证号"), usercard);
        sendKeys(getElement("新建账户", "车牌号"), carno);
        sendKeys(getElement("新建账户", "充值金额"), money);
        click(getElement("新建账户", "提交按钮"));
        Thread.sleep(1000);
        //待领取金额
        double wMoney = doubleNum(getText(getElement("新建账户", "待领取")));
        //待验证手机
        String actualphone = getText(getElement("新建账户", "验证手机"));
        Thread.sleep(2000);
        //driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        //万金油系统余额
        driver.navigate().refresh();
        Thread.sleep(3000);

        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double extotaldmoney = totalMoney - doubleNum(money);
        double alqyq = doubleNum(getText(getElement("公用", "企业券")));
        double exalqyq = qyqmoney - doubleNum(money);
        double alky = doubleNum(getText(getElement("公用", "可用余额")));
        double exky = kymoney;


        //手机号码断言账户创建成功
        AssertUtil.assertTextEquals(actualphone, expectedMsg);
        //断言待领取金额是否为充值金额
        AssertUtil.assertNumEquals(wMoney, doubleNum(money));
        //断言万金油系统余额
        AssertUtil.assertNumEquals(extotaldmoney, altotalMoney);
        //断言显示的余额是否和首页相同
        //AssertUtil.assertNumEquals(doubleNum(ymoney),totalMoney);
        //断言企业券
        AssertUtil.assertNumEquals(alqyq, exalqyq);
        //断言可用余额
        AssertUtil.assertNumEquals(alky, exky);
        Thread.sleep(2000);

    }

    /**
     * 重复创建司机账户
     *
     * @param name
     * @param mobilephone
     * @param usercard
     * @param carno
     * @param money
     * @param expectedMsg
     * @throws InterruptedException
     */
    @Test(dataProvider = "refailCaseDatas", description = "重复创建司机账户", priority = 2, enabled = true)
    public void failaddAcount(String name, String mobilephone, String usercard, String carno, String money, String expectedMsg) throws InterruptedException {
        click(getElement("公用", "首页"));
        // click(getElement("新建账户","运营数据"));
        //万金油系统余额
        Thread.sleep(2000);
        String totalMoney1 = getText(getElement("公用", "万金油系统余额"));
        double totalMoney = doubleNum(totalMoney1);
        //企业券
        double qyqmoney = doubleNum(getText(getElement("公用", "企业券")));
        //可用余额
        double kymoney = doubleNum(getText(getElement("公用", "可用余额")));

        click(getElement("新建账户", "司机会员按钮"));
        click(getElement("新建账户", "新建账户按钮"));
        sendKeys(getElement("新建账户", "司机姓名"), name);
        sendKeys(getElement("新建账户", "手机号"), mobilephone);
        sendKeys(getElement("新建账户", "身份证号"), usercard);
        sendKeys(getElement("新建账户", "车牌号"), carno);
        sendKeys(getElement("新建账户", "充值金额"), money);
        click(getElement("新建账户", "提交按钮"));
        //待验证手机
        String alerrorMsg = getText(getElement("新建账户", "已存在提示"));

        Thread.sleep(2000);
        driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));


        //万金油系统余额
        driver.navigate().refresh();
        Thread.sleep(3000);
        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double extotaldmoney = totalMoney;
        double exalqyq = doubleNum(getText(getElement("公用", "企业券")));
        double exalky = doubleNum(getText(getElement("公用", "可用余额")));


        //断言万金油系统余额
        AssertUtil.assertNumEquals(extotaldmoney, altotalMoney);
        //断言企业券
        AssertUtil.assertNumEquals(qyqmoney, exalqyq);
        //断言可用余额
        AssertUtil.assertNumEquals(kymoney, exalky);
        Thread.sleep(2000);
        //手机号码断言用户已存在
        AssertUtil.assertTextEquals(alerrorMsg, expectedMsg);


        Thread.sleep(2000);

    }

    @Test(dataProvider = "overfailCaseDatas", description = "创建司机账户时金额超出", enabled = true,priority = 3)
    public void overaddAcount(String name, String mobilephone, String usercard, String carno, String expectedMsg) throws InterruptedException {
        click(getElement("公用", "首页"));
        // click(getElement("新建账户","运营数据"));
        //万金油系统余额
        driver.navigate().refresh();
        Thread.sleep(2000);
        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        //企业券
        double qyqmoney = doubleNum(getText(getElement("公用", "企业券")));
        //可用余额
        double kymoney = doubleNum(getText(getElement("公用", "可用余额")));

        //可用余额

        click(getElement("新建账户", "司机会员按钮"));
        click(getElement("新建账户", "新建账户按钮"));
        sendKeys(getElement("新建账户", "司机姓名"), name);
        sendKeys(getElement("新建账户", "手机号"), mobilephone);
        sendKeys(getElement("新建账户", "身份证号"), usercard);
        sendKeys(getElement("新建账户", "车牌号"), carno);
        sendKeys(getElement("新建账户", "充值金额"), String.valueOf(qyqmoney + 1));
        click(getElement("新建账户", "提交按钮"));
        Thread.sleep(1000);
        String alerrorMsg = getText(getElement("新建账户", "余额不足提示"));

        Thread.sleep(2000);
        //driver.navigate().back();
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        //万金油系统余额
        driver.navigate().refresh();
        Thread.sleep(3000);
        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double alqyqMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double alkyMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));

        double extotalmoney = totalMoney;
        double exqyqmoney = qyqmoney;
        double exkymoney = kymoney;

        //断言万金油系统余额
        AssertUtil.assertNumEquals(extotalmoney, altotalMoney);
        //断言是否出现错误提示
        AssertUtil.assertTextEquals(alerrorMsg, expectedMsg);
        //断言企业券
        AssertUtil.assertNumEquals(qyqmoney, exqyqmoney);
        //断言可用余额
        AssertUtil.assertNumEquals(kymoney, exkymoney);
        Thread.sleep(2000);

    }

    @DataProvider
    public Object[][] successCaseDatas() {
        String[] cellNames = {"司机姓名", "手机号", "身份证号", "车牌号", "充值金额", "期望结果"};
        Object[][] datas = ExcelUtil.read2(caseFile, "创建账户", cellNames);
        return datas;
    }

    @DataProvider
    public Object[][] refailCaseDatas() {
        String[] cellNames = {"司机姓名", "手机号", "身份证号", "车牌号", "充值金额", "期望结果"};
        Object[][] datas = ExcelUtil.read2(caseFile, "重复创建账户", cellNames);
        return datas;
    }

    @DataProvider
    public Object[][] overfailCaseDatas() {
        String[] cellNames = {"司机姓名", "手机号", "身份证号", "车牌号", "期望结果"};
        Object[][] datas = ExcelUtil.read2(caseFile, "创建账户充值超额", cellNames);
        return datas;
    }


}