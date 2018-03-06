package com.ectongs.activiti.editor;

/**
 * @author wei_jc
 * @since 1.0.0
 * 2018/3/6.
 */
public interface IProperty {
    String getId();

    String getType();

    String getTitle();

    String getDescription();

    String getValue();

    String getIcon();

    boolean isPopular();
}
