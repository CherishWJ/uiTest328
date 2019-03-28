package com.ui.util;

import com.ui.pojo.Page;
import com.ui.pojo.UIElement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.Document;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//解析ui库，提供页面元素信息
public class UILibraryUtil {
    //准备一个List对象保存所有的Page对象

    public static List<Page> pageList = new ArrayList<Page>();
    //通过静态代码块提前准备所需数据，因为一个类里面的代码最先执行，
    static {
        parse();
    }



    /**
     * 从pageList取出满足条件的的页面元素返回
     * @param pageKeyword
     * @param elementKeyword
     * @return
     */
    public static UIElement getUIElement(String pageKeyword,String elementKeyword){
        for(Page page:pageList){
            if(pageKeyword.equals(page.getKeyWord())) {
                List<UIElement> elements = page.getElements();
                for (UIElement uiElement : elements) {
                    if (elementKeyword.equals(uiElement.getKeyword())) {
                        return uiElement;

                    }
                }
            }
        }
        return null;
    }
    private static void parse() {
        //ui库的文件路径
        String filePath="src/main/resources/UILibrary.xml";
        //创建解析器
        SAXReader reader = new SAXReader();

        try {
            //获取文档对象
           Document document =  reader.read(new File(filePath));
           Element root = document.getRootElement();
           //获取所有根元素下的所有Page元素
           List<Element> pList = root.elements("Page");
           //循环处理每个page元素
            //通过根节点，遍历子节点
           for(Element page:pList){
               //获取Page下的关键字信息
              String keyword =  page.attributeValue("keyword");
              //获取当前Page下的所有"UIElement
              List<Element> uiElements = page.elements("UIElement");
              List<UIElement> elements = new ArrayList<UIElement>();
              for(Element element:uiElements) {
                  String elementKeyword = element.attributeValue("keyword");
                  String elementBy=element.attributeValue("by");
                  String elementValue=element.attributeValue("value");

                  UIElement uiElement = new UIElement();
                  uiElement.setKeyword(elementKeyword);
                  uiElement.setBy(elementBy);
                  uiElement.setValue(elementValue);
                  elements.add(uiElement);

              }

              Page pg = new Page();
              pg.setKeyWord(keyword);
              pg.setElements(elements);
              //将解析出来的Page对象保存到PageList集合
              pageList.add(pg);


           }

        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }


}
