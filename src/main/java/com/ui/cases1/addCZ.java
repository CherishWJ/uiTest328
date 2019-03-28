package com.ui.cases1;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.ExcelUtil;
import com.ui.util.ZTestReport;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ZTestReport.class})
public class addCZ extends Base {
    private Logger logger = Logger.getLogger(addCZ.class);
    public static String url = "https://rvip.nucarf.cn";

    @BeforeClass
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页","手机号"),"18311447530");
        sendKeys(getElement("登陆页","密码"),"an1234");
        click(getElement("登陆页","登陆按钮"));
        Thread.sleep(2000);
    }

    @Test(dataProvider = "CaseDatas",description = "创建机构")
    public void addCZ(String name, String mobilephone, String juese, String expectedMsg) throws InterruptedException {
        click(getElement("添加操作员","操作员管理"));
        click(getElement("添加操作员","添加操作员"));
        sendKeys(getElement("添加操作员","姓名"),name);
        sendKeys(getElement("添加操作员","手机号"),mobilephone);
        click(getElement("添加",juese));
        click(getElement("添加操作员","提交按钮"));
        String actual =getText(getElement("添加操作员","操作员验证"));
        AssertUtil.assertTextEquals(actual,expectedMsg);
        driver.navigate().back();
        Thread.sleep(1000);

    }
    @DataProvider
    public Object[][] CaseDatas() {
        String[] cellNames= {"机构名称", "管理员手机号","管理员姓名","角色","期望结果"};
        Object[][] datas = ExcelUtil.read2("src/main/resources/data.xlsx","创建机构",cellNames);
        return datas;
    }

}
