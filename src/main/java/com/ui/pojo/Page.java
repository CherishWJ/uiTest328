package com.ui.pojo;

import java.util.List;

/**
 * 封装页面对象
 * 属性： keyWord   页面关键字
 *       elements  页面对象集合List
 */
public class Page {

    private String keyWord;//关键字
    private List<UIElement> elements;   //list集合page下的所有页面元素对象

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<UIElement> getElements() {

        return elements;
    }

    public void setElements(List<UIElement> elements) {
        this.elements = elements;
    }
}
