package com.ui.case2.tvip_organ_check;

import com.ui.base.Base;
import com.ui.util.PropertiesUtil;
import org.testng.annotations.Test;

public class organCheckLogin extends Base {

    String url = PropertiesUtil.getRootUrl();
    String caseFile = PropertiesUtil.getCaseFilePath();
    String chargemoney="2";

   @Test(description = "进行登录")
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页", "手机号"), "10044441234");
        sendKeys(getElement("登陆页", "密码"), "an1234");
        click(getElement("登陆页", "登陆按钮"));
    }

}
