package com.ui.cases1.driverCase;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.ExcelUtil;
import com.ui.util.PropertiesUtil;
import com.ui.util.ZTestReport;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ZTestReport.class})
public class LoginCase extends Base {

    String url = PropertiesUtil.getRootUrl();
    String caseFile = PropertiesUtil.getCaseFilePath();

    /*失败测试用例，手机号为空*/
    //下方没有提供时，默认使用方法名称
    @Test(dataProvider = "failCaseDatas",description = "登陆失败测试用例")
    public void failCase(String mobilepone,String pwd,String expectedMsg) throws InterruptedException {
        to(url);
        sendKeys(getElement("登陆页","手机号"),mobilepone);
        sendKeys(getElement("登陆页","密码"),pwd);
        click(getElement("登陆页","登陆按钮"));
        Thread.sleep(2000);
        String actual = getText(getElement("登陆页","错误提示"));
        AssertUtil.assertTextEquals(actual,expectedMsg);
        Thread.sleep(1000);
    }

    @Test(dataProvider = "successCaseDatas",description = "登陆成功测试用例")
    public void successCase(String mobilepone,String pwd,String expectedMsg) throws InterruptedException {
        to(url);
        sendKeys(getElement("登陆页","手机号"),mobilepone);
        sendKeys(getElement("登陆页","密码"),pwd);
        click(getElement("登陆页","登陆按钮"));
        Thread.sleep(1000);
        //显示等待，拿到登录界面的url
        String actual = getText(getElement("登陆页","登录名"));
        AssertUtil.assertTextEquals(actual,expectedMsg);
        Thread.sleep(2000);
    }

    @DataProvider
    public Object[][] failCaseDatas() {
        String[] cellNames= {"手机号", "密码","期望结果"};
        //cellNames说明需要取哪些列
        Object[][] datas = ExcelUtil.read2(caseFile,"登陆失败",cellNames);
        return datas;
    }


    @DataProvider
    public Object[][] successCaseDatas() {
        String[] cellNames= {"手机号", "密码","期望结果"};
        Object[][] datas = ExcelUtil.read2(caseFile,"登陆成功",cellNames);
        return datas;
    }





}
