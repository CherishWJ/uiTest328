package com.ui.cases1.oragnCase;

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
public class addOragn extends Base{
    private Logger logger = Logger.getLogger(addOragn.class);
    public static String url = "https://rvip.nucarf.cn";

    @BeforeClass
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页","手机号"),"18311447530");
        sendKeys(getElement("登陆页","密码"),"an1234");
        click(getElement("登陆页","登陆按钮"));
        Thread.sleep(2000);

    }

    @Test(dataProvider = "CaseDatas",description = "创建机构",priority = 1)
    public void addJG(String JGname,String mobilephone,String name,String expectedMsg) throws InterruptedException {
        click(getElement("新建账户","首页"));
        //万金油系统余额
        click(getElement("创建机构", "下属机构"));
        click(getElement("创建机构", "创建机构"));
        sendKeys(getElement("创建机构", "机构名称"),JGname);
        click(getElement("创建机构", "创建新管理员"));
        sendKeys(getElement("创建机构", "管理员手机号"), mobilephone);
        sendKeys(getElement("创建机构", "管理员姓名"),name);
        click(getElement("创建机构", "提交按钮"));
        String actual =getText(getElement("创建机构","机构名验证"));
        AssertUtil.assertTextEquals(actual,expectedMsg);
        //driver.navigate().back();
        Thread.sleep(1000);
    }
    @Test(dataProvider = "fail1CaseDatas",description = "重复创建机构",enabled = true,priority = 2)
    public void fail1addJG(String JGname,String mobilephone,String name,String expectedMsg) throws InterruptedException {
        click(getElement("新建账户","首页"));
        //万金油系统余额
        click(getElement("创建机构", "下属机构"));
        click(getElement("创建机构", "创建机构"));
        sendKeys(getElement("创建机构", "机构名称"),JGname);
        click(getElement("创建机构", "创建新管理员"));
        sendKeys(getElement("创建机构", "管理员手机号"), mobilephone);
        sendKeys(getElement("创建机构", "管理员姓名"),name);
        String actual =getText(getElement("创建机构","机构已存在提示"));
        click(getElement("创建机构", "提交按钮"));
        AssertUtil.assertTextEquals(actual,expectedMsg);

        //driver.navigate().back();
        Thread.sleep(1000);
    }
    @Test(dataProvider = "fail2CaseDatas",description = "创建机构管理员重复",enabled = true,priority = 3)
    public void fail2addJG(String JGname,String mobilephone,String name,String expectedMsg) throws InterruptedException {
        click(getElement("新建账户","首页"));
        //万金油系统余额
        click(getElement("创建机构", "下属机构"));
        click(getElement("创建机构", "创建机构"));
        sendKeys(getElement("创建机构", "机构名称"),JGname);
        click(getElement("创建机构", "创建新管理员"));
        sendKeys(getElement("创建机构", "管理员手机号"), mobilephone);
        sendKeys(getElement("创建机构", "管理员姓名"),name);
        String actual =getText(getElement("创建机构","管理员重复提示"));
        click(getElement("创建机构", "提交按钮"));
        AssertUtil.assertTextEquals(actual,expectedMsg);

        driver.navigate().back();
        Thread.sleep(1000);
    }


    @DataProvider
    public Object[][] CaseDatas() {
        String[] cellNames= {"机构名称", "管理员手机号","管理员姓名","期望结果"};
        Object[][] datas = ExcelUtil.read2("src/main/resources/data.xlsx","创建机构",cellNames);
        return datas;
    }
    @DataProvider
    public Object[][] fail1CaseDatas() {
        String[] cellNames= {"机构名称", "管理员手机号","管理员姓名","期望结果"};
        Object[][] datas = ExcelUtil.read2("src/main/resources/data.xlsx","重复创建机构",cellNames);
        return datas;
    }
    @DataProvider
    public Object[][] fail2CaseDatas() {
        String[] cellNames= {"机构名称", "管理员手机号","管理员姓名","期望结果"};
        Object[][] datas = ExcelUtil.read2("src/main/resources/data.xlsx","创建机构管理员重复",cellNames);
        return datas;
    }





    }



