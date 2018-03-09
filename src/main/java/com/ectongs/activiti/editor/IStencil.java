package com.ectongs.activiti.editor;

import java.util.Set;

/**
 *
 * @author wei_jc
 * @since 1.0.0
 * 2018/3/6.
 */
public interface IStencil {
    /** BPMN-Diagram: A BPMN 2.0 diagram. */
    String NODE_BPMNDIAGRAM = "BPMNDiagram";
    /** 开始事件: A start event without a specific trigger */
    String NODE_STARTNONEEVENT = "StartNoneEvent";
    /** 定时开始事件: 有定时任务触发器的开始事件 */
    String NODE_STARTTIMEREVENT = "StartTimerEvent";
    /** 信号开始事件: 有信号触发器的开始事件 */
    String NODE_STARTSIGNALEVENT = "StartSignalEvent";
    /** 消息开始事件: 有消息触发器的开始事件 */
    String NODE_STARTMESSAGEEVENT = "StartMessageEvent";
    /** 错误开始事件: 捕获抛出BMP错误的开始事件 */
    String NODE_STARTERROREVENT = "StartErrorEvent";
    /** 用户任务: 由特定用户完成的任务 */
    String NODE_USERTASK = "UserTask";
    /** 服务任务: 由服务逻辑自动完成的任务 */
    String NODE_SERVICETASK = "ServiceTask";
    /** 脚本任务: 由脚本逻辑自动完成的任务 */
    String NODE_SCRIPTTASK = "ScriptTask";
    /** 业务规则任务: 由规则逻辑自动完成的任务 */
    String NODE_BUSINESSRULE = "BusinessRule";
    /** 接收任务: 等待接收信号的任务 */
    String NODE_RECEIVETASK = "ReceiveTask";
    /** 人工任务: 无需逻辑自动完成的任务 */
    String NODE_MANUALTASK = "ManualTask";
    /** 邮件任务: 发送邮件通知的任务 */
    String NODE_MAILTASK = "MailTask";
    /** 骆驼任务: An task that sends a message to Camel */
    String NODE_CAMELTASK = "CamelTask";
    /** Mule task: An task that sends a message to Mule */
    String NODE_MULETASK = "MuleTask";
    /** 发送任务: An task that sends a message */
    String NODE_SENDTASK = "SendTask";
    /** 子流程: 子流程范围 */
    String NODE_SUBPROCESS = "SubProcess";
    /** 事件子流程: 事件子流程范围 */
    String NODE_EVENTSUBPROCESS = "EventSubProcess";
    /** 调用活动: A call activity */
    String NODE_CALLACTIVITY = "CallActivity";
    /** 互斥网关: 一个选择的网关 */
    String NODE_EXCLUSIVEGATEWAY = "ExclusiveGateway";
    /** 并行网关: 并行处理的网关 */
    String NODE_PARALLELGATEWAY = "ParallelGateway";
    /** 包容性网关: An inclusive gateway */
    String NODE_INCLUSIVEGATEWAY = "InclusiveGateway";
    /** 事件网关: An event gateway */
    String NODE_EVENTGATEWAY = "EventGateway";
    /** 边界错误事件: A boundary event that catches a BPMN error */
    String NODE_BOUNDARYERROREVENT = "BoundaryErrorEvent";
    /** 边界定时事件: A boundary event with a timer trigger */
    String NODE_BOUNDARYTIMEREVENT = "BoundaryTimerEvent";
    /** 边界信号事件: A boundary event with a signal trigger */
    String NODE_BOUNDARYSIGNALEVENT = "BoundarySignalEvent";
    /** 边界消息事件: A boundary event with a message trigger */
    String NODE_BOUNDARYMESSAGEEVENT = "BoundaryMessageEvent";
    /** 边界取消事件: A boundary cancel event */
    String NODE_BOUNDARYCANCELEVENT = "BoundaryCancelEvent";
    /** Boundary compensation event: 边界补偿事件 */
    String NODE_BOUNDARYCOMPENSATIONEVENT = "BoundaryCompensationEvent";
    /** 中间定时器捕获事件: An intermediate catching event with a timer trigger */
    String NODE_CATCHTIMEREVENT = "CatchTimerEvent";
    /** 中间信号捕捉事件: An intermediate catching event with a signal trigger */
    String NODE_CATCHSIGNALEVENT = "CatchSignalEvent";
    /** 中间消息捕捉事件: An intermediate catching event with a message trigger */
    String NODE_CATCHMESSAGEEVENT = "CatchMessageEvent";
    /** 中间无抛出事件: An intermediate event without a specific trigger */
    String NODE_THROWNONEEVENT = "ThrowNoneEvent";
    /** 中间信号抛出事件: An intermediate event with a signal trigger */
    String NODE_THROWSIGNALEVENT = "ThrowSignalEvent";
    /** 结束事件: An end event without a specific trigger */
    String NODE_ENDNONEEVENT = "EndNoneEvent";
    /** 结束错误事件: An end event that throws an error event */
    String NODE_ENDERROREVENT = "EndErrorEvent";
    /** 结束取消事件: A cancel end event */
    String NODE_ENDCANCELEVENT = "EndCancelEvent";
    /** 结束终止事件: A terminate end event */
    String NODE_ENDTERMINATEEVENT = "EndTerminateEvent";
    /** 池: A pool to stucture the process definition */
    String NODE_POOL = "Pool";
    /** 道: A lane to stucture the process definition */
    String NODE_LANE = "Lane";
    /** Sequence flow: Sequence flow defines the execution order of activities. */
    String EDGE_SEQUENCEFLOW = "SequenceFlow";
    /** Message flow: Message flow to connect elements in different pools. */
    String EDGE_MESSAGEFLOW = "MessageFlow";
    /** Association: Associates a text annotation with an element. */
    String EDGE_ASSOCIATION = "Association";
    /** DataAssociation: Associates a data element with an activity. */
    String EDGE_DATAASSOCIATION = "DataAssociation";
    /** 文本注释: Annotates elements with description text. */
    String NODE_TEXTANNOTATION = "TextAnnotation";
    /** Data store: Reference to a data store. */
    String NODE_DATASTORE = "DataStore";

    String getId();

    String getType();

    String getTitle();

    String getDescription();

    String getView();

    String getIcon();

    Set<String> getGroups();

    boolean isMayBeRoot();

    boolean isHide();

    Set<String> getPropertyPackages();

    Set<String> getHiddenPropertyPackages();

    Set<String> getRoles();

    String getEctid();
}
