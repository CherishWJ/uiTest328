package com.ui.base;

import com.ui.pojo.UIElement;
import com.ui.util.UILibraryUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;


public class Base {

    private Logger logger = Logger.getLogger(Base.class);
    //共享数据
    public static WebDriver driver;
   //测试路径
    static String rootUrl = "https://rvip.nucarf.cn/";

    /**
     * @param browserType  浏览器类型
     * @param driverPath  流量器驱动类型
     */
    @BeforeSuite
    @Parameters(value = {"browserType", "driverPath"})

    public void init(String browserType, String driverPath) {
        logger.info("配置信息：浏览器类型:【"+browserType+"】，驱动文件路径：【"+driverPath+"】");
        if ("ie".equalsIgnoreCase(browserType)) {
            System.setProperty("webdriver.ie.driver", driverPath);
            //创建DesirdCapabilitys对象，在对象中保存浏览器设置信息
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //忽略浏览器的安全设置
            //capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS);
            //忽略浏览器的缩放设置
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            //创建驱动对象，打开IE浏览器
            driver = new InternetExplorerDriver(capabilities);
            logger.info("***打开ie浏览器********");
        } else if ("firefox".equalsIgnoreCase(browserType)) {
            //设置系统变量，指定驱动文件路径，保证在创建驱动之前设置好
            System.setProperty("webdriver.gecko.driver", driverPath);
            //打开火狐浏览器
            driver = new FirefoxDriver();
            logger.info("****打开firefox浏览器****");
        } else if ("chrome".equalsIgnoreCase(browserType)) {
            //设置系统变量，指定驱动文件路径，保证在创建驱动之前设置好
            System.setProperty("webdriver.chrome.driver", driverPath);
            //打开火狐浏览器
            driver = new ChromeDriver();
            logger.info("*****打开chrome浏览器***");
        } else {
            System.out.println("输入有误，请重新输入");
        }

        driver.manage().window().maximize();
        //设置隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logger.info("窗口最大化");
    }

    /**  关闭流量器驱动
     * @throws InterruptedException
     */
    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        logger.info("*******测试完成，关闭驱动对象*********");
        driver.quit();
    }

    /**显示等待
     * @param locator   定位位置
     * @return
     */

    public WebElement waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("元素定位成功");
            return webElement;
        } catch (Exception e) {
            logger.error("元素定位失败");
        }
        return null;
    }

    /** 根据页面关键字+元素关键字来获取元素
     * @param pageKeyword  页面关键字
     * @param elementKeyword   元素关键字
     * @return
     */

    public WebElement getElement(String pageKeyword, String elementKeyword) {
        //根据页面关键字和元素关键字拿到ui库中的uiElement对象
        UIElement uiElement = UILibraryUtil.getUIElement(pageKeyword, elementKeyword);
        //通过拿到的UIElement对象，取出by和value属性值来判断通过什么方式来定位页面元素
        String by = uiElement.getBy();
        String value = uiElement.getValue();
        logger.info("根据{by:"+by+",value:"+value+"}来定位【"+pageKeyword+"】页面的【"+elementKeyword+"】元素");
        By locator = null;
        //通过什么选择器来定位元素，取决于配的by是什么
        if ("id".equals(by)) {
            locator = By.id(value);
        } else if ("name".equals(by)) {
            locator = By.name(value);
        } else if ("xpath".equals(by)) {
            locator = By.xpath(value);
        } else if ("className".equals(by)) {
            locator = By.className(value);
        } else if ("linkText".equals(by)) {
            locator = By.linkText(value);
        } else if ("tagName".equals(by)) {
            locator = By.tagName(value);
        } else if ("cssSelector".equals(by)) {
            locator = By.cssSelector(value);
        }
        return waitElement(locator);
    }


    /**url等待
     * @param part
     * @return
     */
    //封装显示等待，出现想要的url才会停止
    public boolean urlPresenceContent(String part) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            boolean flag = wait.until(ExpectedConditions.urlContains(part));
            return flag;
        } catch (Exception e) {
            System.out.println("超时了");
        }
        return false;

    }

    /**跳转页面
     * @param url
     */
    public void to(String url){
            logger.info("访问测试页面：【" + url + "】");
            driver.navigate().to(url);
        }



    /** 写入数据
     * @param element  页面元素
     * @param value  值
     */
    public void sendKeys(WebElement element,String value){
        logger.info("向【"+element+"】中写入数据：【"+value+"】");
        element.clear();
        element.sendKeys(value);



        /*
        try {
            if (element.isEnabled()) {
                element.clear();
                element.sendKeys(value);
                logger.info("元素赋值内容："+value+"，元素："+element.toString().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("元素赋值出现异常：\n"+e.getMessage());
        }
        */

    }

    /**点击元素
     * @param element  页面元素
     */
    //点击元素
    public void click(WebElement element){

        try {
            if (element.isEnabled()) {

                element.click();
                logger.info("点击页面元素：【"+element+"】");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("元素点击出现异常：\n"+e.getMessage());

    }

    }


        /**
         * 获取当前titie
         * @return
         */
        public String getCurrentTitle() {
            String pageTitle = driver.getTitle();
            logger.info("浏览器当前页面title："+pageTitle);
            return pageTitle;
        }

        /**
         * 获取当前url
         * @return
         */
        public String getCurrentUrl() {
            String pageUrl = driver.getCurrentUrl();
            logger.info("浏览器当前页面url是："+pageUrl);
            return pageUrl;
        }

    /** 获取文本值
     * @param element   页面元素
     * @return
     */
    //获取元素文本值
    public String getText(WebElement element){
        String value = element.getText();
        logger.info("获取【"+element+"】元素文本值:【"+value+"】");
        return value;

    }

    /**
     * 确认元素是否可见
     * @param element 元素
     * @return
     */
    public boolean checkVisable(WebElement element) {

        return element.isDisplayed();
    }
    /**
     * 移动
     * @param from
     * @param to
     */
    protected void moveMenu(WebElement from,WebElement to) {
        //使下菜单可见
        Actions action = new Actions(driver);
        action.clickAndHold(from).moveToElement(to)	.release();
        action.build().perform();
        logger.info("移动菜单");
    }

    //处理弹窗
    public void confrim(){
        Alert confirm = driver.switchTo().alert();
        //获取弹窗内容
        System.out.println(confirm.getText());
        logger.info("-----关闭弹窗----");
        confirm.accept(); //关闭confirm　
    }


    /**处理当前页不可见元素
     *
     */
   public void scrollMove(WebElement element){
       //WebElement element = driver.findElement(By.id("tr_1253"));
       Actions action = new Actions(driver);
       action.moveToElement(element).perform();
       logger.info("----移动滚动条到最右边---------");
   }

    /**
     * 处理下拉列表
     */

   public void  selectList(String text){
       //找到下拉框Select的id
       WebElement job=driver.findElement(By.id("company_wallet_status"));
       // /创建selenium自带的类进行下拉框定位
       Select downList=new Select(job);
       // 职位选择第二个元素
       downList.selectByValue(text);

        // 一个节点下所有的option选项
       //Select dropList = new Select(getElement("企业钱包","是否开启"));
       //使用选项文字选择选项
       //dropList.selectByValue(text);
   }


   //处理数字类型转换为doubkle类型

public  double doubleNum(String numtext){
       String str = numtext.trim().replaceAll(",", "");
       double num = Double.valueOf(str);
       return num;
}


//上传文件
public void upfile(WebElement element,String filePath) throws InterruptedException, AWTException {
    // 指定图片的路径，这里我放桌面上
    StringSelection sel = new StringSelection(filePath);

    // 把图片文件路径复制到剪贴板
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
    logger.info("将文件【" + filePath + "】上传到【" + element + "】");

    // 点击照相机这个按钮
    element.click();
    // 新建一个Robot类的对象
    Robot robot = new Robot();
    Thread.sleep(1000);


    // 按下 CTRL+V
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);

    // 释放回车
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);

}


    /**
     * 退出登录
     */
public void  LoginOut(){

    WebElement settings = driver.findElement(By.xpath("//*[@id=\"system_user_info_head_img\"]"));
    //WebElement settings = driver.findElement(By.xpath(locator));
    Actions action = new Actions(driver);
    action.moveToElement(settings).perform();


    driver.findElement(By.linkText("退出登录")).click();
}

}


