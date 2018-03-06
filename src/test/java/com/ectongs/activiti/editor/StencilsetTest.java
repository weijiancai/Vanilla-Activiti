package com.ectongs.activiti.editor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ectongs.activiti.editor.model.Stencil;
import org.junit.Test;

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
    }

    @Test
    public void addStencil() {
        Stencil bmsp = new Stencil(IStencil.NODE_USERTASK, "bmsp", "部门审批", "由部门审批请假申请", "请假");
        Stencilset.getInstance().addStencil(bmsp);
        System.out.println(Stencilset.getInstance().toJsonString());
    }
}