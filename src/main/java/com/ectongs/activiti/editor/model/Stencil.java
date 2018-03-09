package com.ectongs.activiti.editor.model;

import com.alibaba.fastjson.JSONObject;
import com.ectongs.activiti.editor.IStencil;
import com.ectongs.activiti.editor.Stencilset;

import java.util.*;

/**
 * 流程节点
 *
 * @author wei_jc
 * @since 1.0.0
 * 2018/3/6.
 */
public class Stencil implements IStencil {
    private String id;
    private String type;
    private String title;
    private String description;
    private String view;
    private String icon;
    private boolean isMayBeRoot;
    private boolean isHide;
    private Set<String> groups = new HashSet<>();
    private Set<String> propertyPackages = new HashSet<>();
    private Set<String> hiddenPropertyPackages = new HashSet<>();
    private Set<String> roles = new HashSet<>();

    private String ectid;

    public Stencil(String extend, String id, String title, String description, String group) {
        JSONObject jsonObject = Stencilset.getInstance().getOriginalStencil(extend);
        this.id = jsonObject.getString("id") + "_" + id;
        type = jsonObject.getString("type");
        view = jsonObject.getString("view");
        icon = jsonObject.getString("icon");
        this.ectid = id;
        this.title = title;
        this.description = description;
        addGroup(group);
    }

    public void setEctid(String ectid) {
        this.ectid = ectid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMayBeRoot(boolean mayBeRoot) {
        isMayBeRoot = mayBeRoot;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public void addGroup(String group) {
        if (group != null && group.length() > 0) {
            String[] strs = group.split(",");
            addGroups(Arrays.asList(strs));
        }
    }

    public void addGroups(List<String> list) {
        this.groups.addAll(list);
    }
    public void addPropertyPackages(List<String> list) {
        this.propertyPackages.addAll(list);
    }
    public void addHiddenPropertyPackages(List<String> list) {
        this.hiddenPropertyPackages.addAll(list);
    }
    public void addRoles(List<String> list) {
        this.roles.addAll(list);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getView() {
        return view;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public Set<String> getGroups() {
        return groups;
    }

    @Override
    public boolean isMayBeRoot() {
        return isMayBeRoot;
    }

    @Override
    public boolean isHide() {
        return isHide;
    }

    @Override
    public Set<String> getPropertyPackages() {
        return propertyPackages;
    }

    @Override
    public Set<String> getHiddenPropertyPackages() {
        return hiddenPropertyPackages;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public String getEctid() {
        return ectid;
    }


}
