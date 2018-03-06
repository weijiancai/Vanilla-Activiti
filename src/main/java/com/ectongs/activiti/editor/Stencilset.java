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
    }

    public void addProperty(String name, IProperty property) {
        JSONObject object = new JSONObject();
        object.put("name", name);
        JSONArray array = new JSONArray();
        array.add(JSONObject.toJSON(property));
        object.put("properties", array);
        propertyPackages.add(object);
    }
}
