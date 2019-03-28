package com.ui.cases1;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.ExcelUtil;
import com.ui.util.ZTestReport;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;

@Listeners({ZTestReport.class})
public class RegisterCase3 extends Base {
    private Logger logger = Logger.getLogger(RegisterCase3.class);
    /*失败测试用例，手机号为空*/
    //下方没有提供时，默认使用方法名称
    @Test(dataProvider = "failCaseDatas")
    public void failCase1(String mobilepone,String pwd,String confirmPwd,String expectedMsg){
        String url = "http://39.108.136.60:8085/lmcanon_web_auto/mng/register.html";
        to(url);
        sendKeys(getElement("登陆页","用户名"),mobilepone);
        sendKeys(getElement("登陆页","密码"),pwd);
        sendKeys(getElement("登陆页","重复密码"),confirmPwd);
        click(getElement("登陆页","登陆按钮"));
        String actual = getText(getElement("注册页","错误提示"));
        AssertUtil.assertTextEquals(actual,expectedMsg);
    }

    @Test(dataProvider = "successCaseDatas")
    public void successCase(String mobilepone,String pwd,String confirmPwd){
        driver.navigate().to("http://39.108.136.60:8085/lmcanon_web_auto/mng/register.html");
        getElement("注册页","用户名").sendKeys(mobilepone);
        getElement("注册页","密码").sendKeys(pwd);
        getElement("注册页","重复密码").sendKeys(confirmPwd);
        getElement("注册页","注册按钮").click();
        //显示等待，拿到登录界面的url
        boolean flag = urlPresenceContent("login.html");
        //String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(flag);
    }

    @DataProvider
    public Object[][] failCaseDatas() {
        String[] cellNames= {"手机号", "密码", "重复密码", "期望值"};
        //cellNames说明需要取哪些列
        Object[][] datas = ExcelUtil.read2("src/test/resources/register.xlsx","DL-1",cellNames);
        //进行输出测试
        for(Object [] objects:datas){
            System.out.print(objects);

        }
        return datas;
    }


    @DataProvider
    public Object[][] successCaseDatas() {
        Object[][] datas = ExcelUtil.read("src/test/resources/register.xlsx","DL-2",2,2,1,3);
        return datas;
    }
    
    
    public static void main(String[] args){
        String[] cellNames= {"司机姓名", "手机号", "车牌号", "身份证号","充值金额"};
        //cellNames说明需要取哪些列
        Object[][] datas = ExcelUtil.read2("src/main/resources/login.xlsx","测试",cellNames);
        //进行输出测试
        for(Object [] objects:datas){
            System.out.println(Arrays.toString(objects));

        }
        
    }
    }







