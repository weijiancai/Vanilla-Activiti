package com.ectongs.activiti.editor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ectongs.activiti.editor.model.Stencil;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wei_jc
 * @since 1.0.0
 * 2018/3/6.
 */
public class Stencilset {
    private static Stencilset ourInstance = new Stencilset();
    private JSONObject originalJson;
    private JSONObject ultimateJson = new JSONObject();
    private JSONArray stencils;
    private JSONArray propertyPackages;
    private Map<String, JSONArray> properties = new HashMap<>();
    private Map<String, IStencil> iStencilMap = new HashMap<>();

    public static Stencilset getInstance() {
        return ourInstance;
    }

    private Stencilset() {
        try {
            InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
            String jsonString = IOUtils.toString(stencilsetStream, "utf-8");
            originalJson = JSON.parseObject(jsonString);
            stencils = originalJson.getJSONArray("stencils");
            propertyPackages = originalJson.getJSONArray("propertyPackages");

            ultimateJson.put("title", "流程编辑器");
            ultimateJson.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            ultimateJson.put("description", "BPMN流程编辑器");
            ultimateJson.put("rules", originalJson.getJSONObject("rules"));
            ultimateJson.put("stencils", stencils);
            for(int i = 0; i < propertyPackages.size(); i++) {

            }
            ultimateJson.put("propertyPackages", propertyPackages);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getOriginalJson() {
        return originalJson;
    }

    public JSONArray getPropertyPackages() {
        return propertyPackages;
    }

    public JSONObject getOriginalStencil(String id) {
        JSONArray array = originalJson.getJSONArray("stencils");
        for(int i = 0;  i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("id").equals(id)) {
                return obj;
            }
        }

        throw new RuntimeException(String.format("stencil %s不存在！", id));
    }

    public JSONObject getStencil(String id) {
        JSONArray array = ultimateJson.getJSONArray("stencils");
        for(int i = 0;  i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("id").equals(id)) {
                return obj;
            }
        }

        return null;
    }
    public IStencil getIStencil(String id) {
        return iStencilMap.get(id);
    }

    public JSONObject getPropertyPackage(String name) {
        for(int i = 0;  i < propertyPackages.size(); i++) {
            JSONObject obj = propertyPackages.getJSONObject(i);
            if (obj.getString("name").equals(name)) {
                return obj;
            }
        }

        return null;
    }

    public JSONObject getProperty(String packageName, String id) {
        JSONObject object = getPropertyPackage(packageName);
        if (object == null) {
            return null;
        }
        JSONArray array = object.getJSONArray("properties");
        for(int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("id").equals(id)) {
                return obj;
            }
        }
        return null;
    }

    public String toJsonString() {
        return ultimateJson.toJSONString();
    }

    public void addStencil(IStencil stencil) {
        JSONObject jsonObject = getStencil(stencil.getId());
        if (jsonObject != null) {
            stencils.remove(jsonObject);
        }
        Object obj = JSONObject.toJSON(stencil);
        stencils.add(obj);
        iStencilMap.put(stencil.getId(), stencil);
    }

    public void addProperty(String name, IProperty property) {
        JSONObject propPackage = getPropertyPackage(name);
        JSONArray array;
        // 已存在的删掉
        if (propPackage != null) {
            JSONObject prop = getProperty(name, property.getId());
            array = propPackage.getJSONArray("properties");
            if (prop != null) {
                array.remove(prop);
            }
        } else {
            propPackage = new JSONObject();
            propPackage.put("name", name);
            array = new JSONArray();
            propPackage.put("properties", array);
            propertyPackages.add(propPackage);
        }
        // 新增
        array.add(JSONObject.toJSON(property));
    }
}
