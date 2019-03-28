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
public class updateDriver extends Base {

    String url = PropertiesUtil.getRootUrl();

    @BeforeClass(enabled = false)

    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页","手机号"),"18311447530");
        sendKeys(getElement("登陆页","密码"),"an1234");
        click(getElement("登陆页","登陆按钮"));
        Thread.sleep(1000);
    }

    @Test(dataProvider = "successCaseDatas",description = "修改司机账户信息",priority = 0,enabled = true)
    public void successUpdate(String name,String usercard,String carno,String expectename,String expecteusercard,String expectecarno ) throws InterruptedException {
        click(getElement("修改司机信息","司机会员按钮"));
        click(getElement("修改司机信息","账户管理按钮"));
        click(getElement("公用","更多按钮"));
        Thread.sleep(1000);
        click(getElement("修改司机信息","修改按钮"));
        sendKeys(getElement("修改司机信息","姓名"),name);
        //sendKeys(getElement("修改司机信息","手机号"),mobilephone);
        sendKeys(getElement("修改司机信息","身份证号"),usercard);
        sendKeys(getElement("修改司机信息","车牌号"),carno);
        click(getElement("修改司机信息","提交按钮"));
        String actual_name = getText(getElement("修改司机信息","实际姓名"));
        //String actual_phone = getText(getElement("修改司机信息","实际手机号"));
        String actual_usercard = getText(getElement("修改司机信息","实际身份证号"));
        String actual_carno = getText(getElement("修改司机信息","实际车牌号"));
        AssertUtil.assertTextEquals(actual_name,expectename);
        //AssertUtil.assertTextEquals(actual_phone,expectephone);
        AssertUtil.assertTextEquals(actual_usercard,expecteusercard);
        AssertUtil.assertTextEquals(actual_carno,expectecarno);
        driver.navigate().back();
        Thread.sleep(1000);

    }
    @Test(dataProvider = "failCaseDatas",description = "修改司机账户信息重复",priority = 1)
    public void failUpdate(String name,String mobilephone,String usercard,String carno,String expectename,String expectephone,String expecteusercard,String expectecarno ,String exerrorMsg) throws InterruptedException {
        click(getElement("修改司机信息","司机会员按钮"));
        click(getElement("修改司机信息","账户管理按钮"));
        click(getElement("公用","更多按钮"));
        click(getElement("修改司机信息","修改按钮"));
        sendKeys(getElement("修改司机信息","姓名"),name);
        //sendKeys(getElement("修改司机信息","手机号"),mobilephone);
        sendKeys(getElement("修改司机信息","身份证号"),usercard);
        sendKeys(getElement("修改司机信息","车牌号"),carno);
        click(getElement("修改司机信息","提交按钮"));
        Thread.sleep(1000);
        String errorMsg = getText(getElement("修改司机信息","重复提示"));
        AssertUtil.assertTextEquals(errorMsg,exerrorMsg);
        driver.navigate().back();
        Thread.sleep(1000);
    }

    @DataProvider
    public Object[][] successCaseDatas() {
        String[] cellNames= {"姓名","身份证号","车牌号","预期姓名","预期身份证号","预期车牌号"};
        Object[][] datas = ExcelUtil.read2("src/main/resources/data.xlsx","修改司机信息",cellNames);
        return datas;
    }
    @DataProvider
    public Object[][] failCaseDatas() {
        String[] cellNames= {"姓名","身份证号","车牌号","预期错误提示"};
        Object[][] datas = ExcelUtil.read2("src/main/resources/data.xlsx","修改司机信息重复",cellNames);
        return datas;
    }

}
