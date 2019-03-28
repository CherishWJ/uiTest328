package com.ui.listeners;


import com.ui.util.ScreenUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.util.Date;


//通过继承来制定一个监听器
public class ReportListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr){
        //当某一个用例执行失败就进入此方法
         String baseDir ="target/surefire-reports/screenshot" ;
         //获取当前正在执行的测试class
         String testname=tr.getTestContext().getCurrentXmlTest().getName();
         baseDir+=(File.separator+testname);
         Date date = new Date();
         String dateString = DateFormatUtils.format(date,"yyyy-MM-dd");
         baseDir+=(File.separator+dateString);
         //在baseDir目录下保存截图
          File destFile = ScreenUtil.takeScreenshot(baseDir);
        //获取图片绝对路径
        String absolutePath = destFile.getAbsolutePath();
        String toBeReplaced = absolutePath.substring(0,absolutePath.indexOf("screenshot"));
        String accessPath = absolutePath.replace(toBeReplaced,"http://localhost/");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Reporter.log("<img src='"+accessPath+"' width='400'  height='400'><a href='"+accessPath+"' target='_blank'>点击查看大图</a></img>");



    }


}

