package com.mul.view.label.bean;

/**
 * Created by Admin on 2017/10/25.
 */

public class MulLabelBean {
    private String id; // 标签id
    private String tagContent; // 标签内容（有可能带省略号）
    private String tagAllContent; // 全部内容
    private boolean isSelect; // 是否选中
    private int textColor;
    private int textSize;
    private int textDrawable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagContent() {
        return tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextDrawable() {
        return textDrawable;
    }

    public void setTextDrawable(int textDrawable) {
        this.textDrawable = textDrawable;
    }

    public String getTagAllContent() {
        return tagAllContent;
    }

    public void setTagAllContent(String tagAllContent) {
        this.tagAllContent = tagAllContent;
    }
}
