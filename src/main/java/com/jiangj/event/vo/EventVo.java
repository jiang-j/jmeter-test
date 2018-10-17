package com.jiangj.event.vo;

import java.io.Serializable;

/**
 * @author Jiang Jian
 * @since 2018/9/19
 */
public class EventVo implements Serializable {

    private String eventNo;

    /**
     * 订阅事件类型
     */
    private String eventType;

    /**
     * 接入方批次号
     */
    private String batchNo;

    /**
     * 事件来源服务id
     */
    private String serviceId;

    /**
     * 事件数据
     */
    private EventContent content;

    /**
     * 业务id
     */
    private String businessNo;

    public EventVo() {
        super();
    }

    public EventVo(String eventNo, String eventType, String batchNo, String serviceId, EventContent content, String businessNo) {
        this.eventNo = eventNo;
        this.eventType = eventType;
        this.batchNo = batchNo;
        this.serviceId = serviceId;
        this.content = content;
        this.businessNo = businessNo;
    }


    public String getEventNo() {
        return eventNo;
    }

    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public EventContent getContent() {
        return content;
    }

    public void setContent(EventContent content) {
        this.content = content;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    @Override
    public String toString() {
        return "EventVo{" +
                "eventNo='" + eventNo + '\'' +
                ", eventType='" + eventType + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", content=" + content.toString() +
                ", businessNo='" + businessNo + '\'' +
                '}';
    }
}
