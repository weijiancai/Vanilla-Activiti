# Vanilla-Activiti
Vanilla-Activiti

## 默认账号
|账号	|密码	|角色|  
kermit	kermit	admin  
gonzo	gonzo	manager  
fozzie	fozzie	user  

# 流程编辑器
## 流程节点
* role ActivitiesMorph 流程节点change element type
## 自定义流程节点
* 定义id, name, title, description
* 节点属性应该有些固定值。例如“经理审批”接口，节点文本是固定的“经理审批”
* 点击流程节点时，节点属性的固定值应该是设置好的。（在界面上可以不显示固定值的属性。）
* 流条件表达式的设置，定做一个表达式编辑窗口，读取前置流程节点所有属性，供表达式使用。
* 双击节点可以编辑节点文本，对于自定义节点，节点文本应该是固定的，不可编辑
* 增加以下节点属性：
  * 流程ID
  * 流程任务项ID
  * 任务项 datalist
  * 需要审批 T/F
  * 审批ID
  * 需要用料 T/F
  * 需要费用 T/F
  * 工时
  * 组合权限 组件
  * 备注 text
### 问题
* 如果使用自定义id，不是activiti内置的，比如jlsp，在部署流程时会报错NPT
	at org.activiti.editor.language.json.converter.BpmnJsonConverter.processJsonElements(BpmnJsonConverter.java:616)
  如果使用内置的节点id，比如UserTask，那么前台流程编辑器是以id作为唯一key的，如果都是UserTask，那么后面的会覆盖掉前面的。
  解决办法，
  1. 节点增加ectid自定义属性，获取流程编辑器节点配置json时，id值附加上ectid，保存模型时，id值去掉附加的ectid，优点是，不改动activiti已有代码
  2. 改动activiti后台代码，增加自定义节点id的支持
  
  
TODO
2018-03-09 
9:00 - 10:00 益华编印项目能成功运行（创建不存在的表、列等信息） 完成
10:00 - 11:00 将工作流模型代码，移到正式项目pub下面，能成功运行
11:00 - 13:00 