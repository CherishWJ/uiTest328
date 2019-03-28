package com.ui.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class AssertUtil {
    private static Logger logger = Logger.getLogger(AssertUtil.class);
    //断言两者一致,静态方法只可以访问静态全局变量
    public static void assertTextEquals(String actual,String expected){
        logger.info("断言比较两者的值是否一致，实际值为：【"+actual+"】,期望值为：【"+expected+"】");
        Assert.assertEquals(actual, expected);
    }

    //断言为真的情况
    public static void assertTrue(boolean actual){
        logger.info("断言比较是否为真，实际值为：【"+actual+"】");
        Assert.assertTrue(actual);
    }

    //断言double数字型数据是否相等
    public static void assertNumEquals(double actual,double expected){
        logger.info("断言比较两者的值是否一致，实际值为：【"+actual+"】,期望值为：【"+expected+"】");
        Assert.assertEquals(actual,expected);
    }

    //包含断言
    public static void assertContains(String actual,String expected){
        logger.info("断言比较两者结果是否存在包含关系，实际值为：【"+actual+"】,期望值为：【"+expected+"】");
        Assert.assertTrue(actual.contains(expected),
                String.format("期待'%s'包含'%s'，实际为不包含.", actual, expected));

    }


    }

