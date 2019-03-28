package com.ui.util;


import com.ui.base.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenUtil {

    public static File takeScreenshot(String baseDir) {
        WebDriver driver = Base.driver;
        File screenImg=null;
        if(driver instanceof FirefoxDriver){
            FirefoxDriver firefoxDriver = (FirefoxDriver) driver;
            screenImg = firefoxDriver.getScreenshotAs(OutputType.FILE);
        }else if(driver instanceof ChromeDriver){
            ChromeDriver chromeDriver = (ChromeDriver) driver;
            screenImg = chromeDriver.getScreenshotAs(OutputType.FILE);
        }else if(driver instanceof InternetExplorerDriver){
            InternetExplorerDriver internetExplorerDriver = (InternetExplorerDriver) driver;
            screenImg = internetExplorerDriver.getScreenshotAs(OutputType.FILE);
        }
        Date date = new Date();
        long time = date.getTime();
        File destFile = new File(baseDir+File.separator+time+".jpg");
        try {
            FileUtils.copyFile(screenImg,destFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("截图拷贝出错");
        }

        return destFile;
    }
}
