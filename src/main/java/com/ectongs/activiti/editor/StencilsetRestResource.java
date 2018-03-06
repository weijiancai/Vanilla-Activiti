package com.ectongs.activiti.editor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ectongs.activiti.editor.model.Stencil;
import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class StencilsetRestResource {
    @RequestMapping(value="/editor/stencilset", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getStencilset() {
        /*InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        try {
            return IOUtils.toString(stencilsetStream, "utf-8");
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }*/
        Stencilset set = Stencilset.getInstance();
        // 读取自定义配置
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset_ect.json");
        try {
            String ect =  IOUtils.toString(stencilsetStream, "utf-8");
            JSONObject jsonObject = JSON.parseObject(ect);
            JSONArray array = jsonObject.getJSONArray("stencils");
            for(int i =0; i < array.size(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Stencil stencil = new Stencil(obj.getString("extend"), obj.getString("id"), obj.getString("title"), obj.getString("description"), "");
                JSONArray groups = obj.getJSONArray("groups");
                stencil.addGroups(groups.toJavaList(String.class));
                JSONArray propertyPackages = obj.getJSONArray("propertyPackages");
                if (propertyPackages != null) {
                    stencil.addPropertyPackages(propertyPackages.toJavaList(String.class));
                }
                JSONArray hiddenPropertyPackages = obj.getJSONArray("hiddenPropertyPackages");
                if (hiddenPropertyPackages != null) {
                    stencil.addHiddenPropertyPackages(hiddenPropertyPackages.toJavaList(String.class));
                }
                JSONArray roles = obj.getJSONArray("roles");
                if (roles != null) {
                    stencil.addRoles(roles.toJavaList(String.class));
                }
                set.addStencil(stencil);
            }

            // 属性
            array = jsonObject.getJSONArray("propertyPackages");
            set.getPropertyPackages().addAll(array);
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }

        set.addStencil(new Stencil(IStencil.NODE_USERTASK, "bmsp", "部门审批", "由部门审批请假申请", "请假"));
        return set.toJsonString();
    }
}
