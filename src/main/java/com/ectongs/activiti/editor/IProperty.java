package com.ectongs.activiti.editor;

/**
 * @author wei_jc
 * @since 1.0.0
 * 2018/3/6.
 */
public interface IProperty {
    String TYPE_TEXT = "Text";
    String TYPE_MULTIPLECOMPLEX = "multiplecomplex";
    String TYPE_STRING = "String";
    String TYPE_BOOLEAN = "Boolean";
    String TYPE_KISBPM_MULTIINSTANCE = "kisbpm-multiinstance";
    String TYPE_COMPLEX = "Complex";

    String getId();

    /**
     * 默认是字符串String
     * @return
     */
    String getType();

    String getTitle();

    String getDescription();

    String getValue();

    /**
     *
     * @return false 则在属性面板不显示 true则会显示出来
     */
    boolean isPopular();

    String getRefToView();
}
