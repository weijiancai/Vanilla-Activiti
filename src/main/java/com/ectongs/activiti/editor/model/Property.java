package com.ectongs.activiti.editor.model;

import com.ectongs.activiti.editor.IProperty;

/**
 * 流程编辑器节点属性
 *
 * @author wei_jc
 * @since 1.0.0
 * 2018/3/6.
 */
public class Property implements IProperty {
    private String id;
    private String type = "String";
    private String title;
    private String description;
    private String value;
    private boolean isPopular = true;
    private String refToView;

    public Property(String id, String title) {
        this(id, title, "String");
    }

    public Property(String id, String title, String type) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = title;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean isPopular() {
        return isPopular;
    }

    public void setPopular(boolean popular) {
        isPopular = popular;
    }

    @Override
    public String getRefToView() {
        return refToView;
    }

    public void setRefToView(String refToView) {
        this.refToView = refToView;
    }
}
