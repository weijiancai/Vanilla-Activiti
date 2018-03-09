package com.ectongs.activiti.editor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ectongs.activiti.editor.model.Stencil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author wei_jc
 * @since 1.0.0
 * 2018/3/6.
 */
public class StencilsetTest {
    @Test
    public void getOriginalJson() throws Exception {
        JSONObject jsonObject = Stencilset.getInstance().getOriginalJson();
        System.out.println(jsonObject.toJSONString());
        // stencils
        JSONArray stencils = jsonObject.getJSONArray("stencils");
        for(int i =0; i < stencils.size(); i++) {
            JSONObject stencil = stencils.getJSONObject(i);
            String id = stencil.getString("id");
            String type = stencil.getString("type");
            String title = stencil.getString("title");
            String description = stencil.getString("description");
            String str = String.format("/** %s: %s */\nString %s_%s = \"%s\";", title, description, type.toUpperCase(), id.toUpperCase(), id);
            System.out.println(str);
        }

        // 属性
        JSONArray propertyPackages = jsonObject.getJSONArray("propertyPackages");
        Set<String> propertyNames = new HashSet<>();
        Set<String> propertyTypes = new HashSet<>();
        for(int i = 0; i < propertyPackages.size(); i++) {
            JSONObject propertyPackage = propertyPackages.getJSONObject(i);
            JSONArray properties = propertyPackage.getJSONArray("properties");
            if (properties.size() > 1) {
                System.out.println(String.format("属性包%s有%d属性定义", propertyPackage.getString("name"), properties.size()));
            }
            for(int j = 0; j < properties.size(); j++) {
                JSONObject obj = properties.getJSONObject(j);
                propertyTypes.add(obj.getString("type"));
                propertyNames.addAll(obj.keySet());
            }
        }
        System.out.println("==============================================");
        System.out.println("property types: ");
        System.out.println(propertyTypes);
        for (String type : propertyTypes) {
            System.out.println(String.format("String TYPE_%s = \"%s\";", type.replace("-", "_").toUpperCase(), type));
        }
        System.out.println("property names: ");
        System.out.println(propertyNames);
    }

    @Test
    public void addStencil() {
        Stencil bmsp = new Stencil(IStencil.NODE_USERTASK, "bmsp", "部门审批", "由部门审批请假申请", "请假");
        Stencilset.getInstance().addStencil(bmsp);
        System.out.println(Stencilset.getInstance().toJsonString());
    }
}